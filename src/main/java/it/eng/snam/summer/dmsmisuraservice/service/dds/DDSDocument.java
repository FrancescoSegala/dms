package it.eng.snam.summer.dmsmisuraservice.service.dds;

import static it.eng.snam.summer.dmsmisuraservice.util.Utility.listOf;
import static it.eng.snam.summer.dmsmisuraservice.util.EntityMapper.*;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import it.eng.snam.summer.dmsmisuraservice.model.Info;
import it.eng.snam.summer.dmsmisuraservice.model.create.DocumentCreate;
import it.eng.snam.summer.dmsmisuraservice.model.search.DocumentSearch;
import it.eng.snam.summer.dmsmisuraservice.model.search.IdSearch;
import it.eng.snam.summer.dmsmisuraservice.model.search.Pagination;
import it.eng.snam.summer.dmsmisuraservice.model.update.DocumentUpdate;
import it.eng.snam.summer.dmsmisuraservice.service.summer.SummerRemi;
import it.eng.snam.summer.dmsmisuraservice.util.Entity;
import it.eng.snam.summer.dmsmisuraservice.util.MultipartInputStreamFileResource;
import it.eng.snam.summer.dmsmisuraservice.util.SnamSQLClient;

@Component
public class DDSDocument extends DDSEntity {

    @Autowired
    NamedParameterJdbcOperations template;

    @Autowired
    SummerRemi summerRemi;

    @Autowired
    DDSSubfolder subfolderService;

    public List<Entity> list(DocumentSearch params) {
        Long limit = params.getLimit();
        params.setLimit(10000L);

        //@formatter:off
        List<Entity> docs = new SnamSQLClient(template)
        .withTable("v_documenti")
        .withParams(params)
        .list();
        //@formatter:on
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
        //@formatter:off
        Entity doc =  new SnamSQLClient(template)
        .withTable("v_documenti")
        .withParams(new IdSearch(document_id))
        .get();
        //@formatter:on
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

    public Entity post(DocumentCreate params, MultipartFile file) {
        validatePath(params.getFolder(), params.getSubfolder());
        // validate remi
        Info remi = params.getInfo().stream().filter(e -> e.containsKey("remi")).findFirst().orElse(null);
        if (remi != null) {
            summerRemi.get(remi.getAsString("remi"));
        }

        System.out.println("REMI");
        System.out.println(remi);

        System.out.println("params");
        System.out.println(params);

        System.out.println("params.getInfo()");
        System.out.println(params.getInfo());

        Entity ddsDoc = toDDSpayload(params, this.os);
        String sseMessage = postDocumentToDDS(ddsDoc, file);

        boolean successClause = sseMessage.contains("event: message") && sseMessage.contains("data: DMSMIS_");

        if (!successClause) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, sseMessage);
        }
        int rows = new SnamSQLClient(template).withTable("documenti").insert(toSQLpayload(params, ddsDoc.id()));
        if (rows <= 0) {
            this.handleSQLfail(ddsDoc.id(), "Insert document failed");
        }
        return get(ddsDoc.id());
    }

    public Entity put(String document_id, DocumentUpdate params) {
        // TODO validate document update DTO infos other than remi ?
        Info remi = params.getInfo().stream().filter(e -> e.containsKey("remi")).findFirst().orElse(null);
        if (remi != null) {
            summerRemi.get(remi.getAsString("remi"));
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
        //@formatter:off
            int rows = new SnamSQLClient(template)
                .withTable("documenti")
                .update(Entity.build("c_remi_ass", remi.get("remi")), document_id);
        //@formatter:on
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

    public void getContent(String document_id) {
        throw new ResponseStatusException(HttpStatus.NOT_IMPLEMENTED);
    }

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
        return "/link/" + dds.getAsString("_id");
    }

    private void validatePath(String folder, String subfolder) {
        try {
            subfolderService.get(folder, subfolder);
        } catch (ResponseStatusException e) {
            System.out.println(e.getMessage());
            throw new ResponseStatusException(e.getStatus(), e.getMessage());
        }
    }

    private void handleSQLfail(String document_id, String message) {
        delete(document_id);
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, message);
    }

    private String postDocumentToDDS(Entity ddsDoc, MultipartFile file) {
        String sseMessage = "Document creation failed";
        ResponseEntity<byte[]> res = null;
        try {
            res = rest.createDocument().withParam("document", ddsDoc)
                    .withParam("file",
                            new MultipartInputStreamFileResource(file.getInputStream(), file.getOriginalFilename()))
                    .postMultipartForm();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (res == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, sseMessage);
        InputStream is = new ByteArrayInputStream(res.getBody());
        sseMessage = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8)).lines()
                .collect(Collectors.joining("\n"));
        return sseMessage;
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
