package it.eng.snam.summer.dmsmisuraservice.service.dds;

import static it.eng.snam.summer.dmsmisuraservice.util.EntityMapper.toDDSpayload;
import static it.eng.snam.summer.dmsmisuraservice.util.EntityMapper.toUpdateDocPayload;
import static it.eng.snam.summer.dmsmisuraservice.util.Utility.concat;
import static it.eng.snam.summer.dmsmisuraservice.util.Utility.listOf;
import static it.eng.snam.summer.dmsmisuraservice.util.validation.InfoValidators.stringOf;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import it.eng.snam.summer.dmsmisuraservice.model.DDSDoc;
import it.eng.snam.summer.dmsmisuraservice.model.create.DocumentCreate;
import it.eng.snam.summer.dmsmisuraservice.model.search.DocumentSearch;
import it.eng.snam.summer.dmsmisuraservice.model.search.Pagination;
import it.eng.snam.summer.dmsmisuraservice.model.update.DocumentUpdate;
import it.eng.snam.summer.dmsmisuraservice.service.summer.Summer;
import it.eng.snam.summer.dmsmisuraservice.util.Entity;
import it.eng.snam.summer.dmsmisuraservice.util.MultipartInputStreamFileResource;
import it.eng.snam.summer.dmsmisuraservice.util.validation.InfoValidator;

@Component
public class DDSDocument extends DDSEntity {



    @Autowired
    DDSSubfolder subfolderService;

    @Autowired
    Summer summer;

    static ScriptEngine engine = null;
    static String parser = null;
    static {
        try {
            parser = Files.readAllLines(Paths.get(DDSDocument.class.getResource("/SSEparser.js").toURI())).stream()
                    .collect(Collectors.joining("\n"));
        } catch (  Exception e1) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e1.getMessage());
        }
        engine = new ScriptEngineManager().getEngineByName("graal.js");
    }

    public List<Entity> list(DocumentSearch params) {
        Long limit = params.getLimit();
        List<Entity> docs = summer.getDocuments(params);
        //@formatter:off
        List<Entity> ddsDocs =  rest.getDocumentBySQL()
                                    .withParam("OS", this.os )
                                    .withParam("select", listOf("*"))
                                    .withParam("where", where(params) )
                                    .postForList();
        //@formatter:on
        //@formatter:off
        return docs.stream()
            .map(e -> merge(e, pickDDSDocumentById(ddsDocs, e.getAsString("id") ) ) )
            .filter(e -> e != null )
            .limit(limit)
            .collect(Collectors.toList());
        //@formatter:on
    }

    public Entity get(String document_id) {
        return this.get(document_id, clause("_id", document_id, "="));
    }

    public Entity get(String document_id, String folder, String subfolder) {
        DocumentSearch params = new DocumentSearch().withFolder(folder).withSubfolder(subfolder);
        return this.get(document_id, clause("_id", document_id, "=") + " and " + where(params));
    }

    private Entity get(String document_id, String clause) {
        Entity doc = summer.getDocument(document_id);
        //@formatter:off
        List<Entity> ddsList =  rest.getDocumentBySQL()
            .withParam("OS", this.os )
            .withParam("select", listOf("*"))
            .withParam("where", clause )
            .postForList();
        //@formatter:on
        if (ddsList.size() <= 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Document " + document_id + "not found in path");
        }
        return merge(doc, ddsList.get(0));
    }

    private static List<InfoValidator> base = listOf(stringOf("StatoDocumento", 64), stringOf("Note", 1024),
            stringOf("Tag", 64), stringOf("CreatoDa", 64));
    private static Entity validators = new Entity().with("INTE", concat(base, listOf(stringOf("CodiceRemi", 32))));

    public Entity post(DocumentCreate params, MultipartFile file) {
        validatePath(params);
        List<InfoValidator> v = validators.getAsList(params.getSubfolder());
        v.stream().map(e -> e.apply(params.getInfo())).filter(e -> e != null).findAny().ifPresent(e -> {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e);
        });

        Entity remi = params.getInfo().stream().filter(e -> e.containsKey("remi")).findFirst().orElse(null);
        if (remi != null) {
            summer.get(remi.getAsString("remi"));
        }
        Entity ddsDoc = toDDSpayload(params, this.os);
        List<Entity> sseStream = postDocumentToDDS(ddsDoc, file);

        if (!"message".equals(sseStream.get(sseStream.size() - 1).getAsString("event"))) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    sseStream.get(sseStream.size() - 1).getAsEntity("data").getAsString("reason"));
        }
        int rows = summer.insertDocument(params, ddsDoc.id() );
        if (rows <= 0) {
            this.handleSQLfail(ddsDoc.id(), "Insert document failed");
        }
        return get(ddsDoc.id());
    }

    public Entity put(String document_id, DocumentUpdate params) {
        // TODO validate document update DTO infos other than remi ?
        Entity remi = params.getInfo().stream().filter(e -> e.containsKey("remi")).findFirst().orElse(null);
        if (remi != null) {
            summer.get(remi.getAsString("remi"));
        }
        //@formatter:off
        List<Entity> ddsList = rest.getDocumentBySQL()
                .withParam("OS", this.os)
                .withParam("select", listOf("*"))
                .withParam("where", clause("_id", document_id, "="))
                .postForList();
        //@formatter:on
        if (ddsList.size() <= 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Document " + document_id + "not found");
        }
        Entity ddsPreSysAttr = ddsList.get(0).getAsEntity("systemAttributes");
        Entity systemAttributes = toUpdateDocPayload(params, this.os, ddsPreSysAttr);
        //@formatter:off
        String res = rest.updateDocument()
            .withParam("id", document_id)
            .withParam("OS", this.os)
            .withParam("systemAttributes", systemAttributes)
            .postForString();
        //@formatter:on
        if (res != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, res);
        }
        if (remi != null) {
             int rows = summer.updateDocument(document_id, remi.getAsString("remi"));
             if (rows <= 0)
                handleSQLfail(document_id, "Update document failed");
        }
        return get(document_id);
    }

    public void delete(String document_id) {
        //@formatter:off
        rest.logicalDeleteDocument()
            .withParam("id", document_id)
            .withParam("OS", this.os)
            .withParam("deleteAllVersion", true)
            .postForString();
        //@formatter:on
    }

    public byte[] getContent(String document_id) {
        System.out.println("getContentName(document_id)");
        System.out.println(getContentName(document_id));
        return rest.getDocumentContent().withParam("id", document_id).withParam("OS", this.os)
                .withParam("contentName", getContentName(document_id)).postForContent();
    }

    private String getContentName(String document_id) {
        DDSDoc dds = rest.getDocumentBySQL().withParam("OS", this.os).withParam("select", listOf("*"))
                .withParam("where", "_id = '" + document_id + "'").postForDocs().get(0);
        List<Entity> contents = dds.contents;
        System.out.println("contents");
        System.out.println(contents.toString());
        if (contents.size() <= 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "This document hash no content");
        }
        Entity c = contents.get(0);
        return c.getAsString("contentsName");
    }

    // private List<String> getContentNames(String document_id){
    // Entity dds = rest.getDocumentBySQL()
    // .withParam("OS", this.os )
    // .withParam("select", listOf("*"))
    // .withParam("where", "_id = '" + document_id + "'")
    // .postForList().get(0);

    // return dds.getAsListEntity("contents").stream()
    // .map(e -> e.getAsString("ContentsName") )
    // .collect(Collectors.toList());
    // }

    private Entity pickDDSDocumentById(List<Entity> list, String id) {
        return list.stream().filter(e -> id.equals(e.getAsString("_id"))).findFirst().orElse(null);
    }

    private Entity merge(Entity doc, Entity dds) {
        // merge delle info da doc e da dds, priorità a db sql
        if (dds == null)
            return null;
        Entity res = Entity.build(doc);
        //@formatter:off
        return res
            .with("name", dds.getAsEntity("systemAttributes").getAsString("name"))
            .with("created_at", dds.getAsEntity("systemAttributes").getAsEntity("dateCreated").getAsString("$date"))
            .with("updated_at",
                    dds.getAsEntity("systemAttributes").getAsEntity("dateLastModified").getAsString("$date"))
            .with("created_by", dds.getAsEntity("systemAttributes").getAsString("creator"))
            .with("updated_by", dds.getAsEntity("systemAttributes").getAsString("lastModifier"))
            .with("notes", dds.getAsEntity("systemAttributes").getAsString("documentTitle"))
            .with("status",
                    dds.getAsEntity("systemAttributes").getAsBoolean("isLogicalDeleted") ? "inactive" : "active")
            .with("link", link(dds));
        //@formatter:on
    }

    private String link(Entity dds) {
        return "/documents/" + dds.getAsString("_id") + "/content";
    }

    private void validatePath(DocumentCreate params) {
        try {
            subfolderService.get(params.getFolder(), params.getSubfolder());
        } catch (ResponseStatusException e) {
            System.out.println(e.getMessage());
            throw new ResponseStatusException(e.getStatus(), e.getMessage());
        }
    }

    private void handleSQLfail(String document_id, String message) {
        delete(document_id);
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, message);
    }

    private List<Entity> postDocumentToDDS(Entity ddsDoc, MultipartFile file) {
        String sseMessage = "Document creation failed";

        try {
            sseMessage = rest.createDocument().withParam("document", ddsDoc)
                    .withParam("file",
                            new MultipartInputStreamFileResource(file.getInputStream(), file.getOriginalFilename()))
                    .postMultipart();
            System.out.println("sseMessage");
            System.out.println(sseMessage);
            String res = (String) engine.eval(parser + "JSON.stringify( parse.parse(`" + sseMessage + "`) )");
            return new ObjectMapper().readValue(res, new TypeReference<List<Entity>>() {
            });
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @Override
    protected String sortField(String field) {
        return "_id";
    }

    @Override
    protected List<String> clauses(Pagination p) {
        DocumentSearch params = (DocumentSearch) p;
        String notDeleted = clause("isLogicalDeleted", "inactive".equalsIgnoreCase(params.getStatus()));
        String inSubfolder = params.getFolder() != null && params.getSubfolder() != null
                ? clause("foldersParents", "/" + params.getFolder() + "/" + params.getSubfolder(), "=")
                : "";
        return listOf(notDeleted, inSubfolder);
    }

}
