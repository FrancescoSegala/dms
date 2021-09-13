package it.eng.snam.summer.dmsmisuraservice.service.dds;

import static it.eng.snam.summer.dmsmisuraservice.util.EntityMapper.toDDSPayload;
import static it.eng.snam.summer.dmsmisuraservice.util.EntityMapper.toUpdateDocPayload;
import static it.eng.snam.summer.dmsmisuraservice.util.Utility.listOf;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
import it.eng.snam.summer.dmsmisuraservice.model.DocumentSQL;
import it.eng.snam.summer.dmsmisuraservice.model.create.DocumentCreate;
import it.eng.snam.summer.dmsmisuraservice.model.search.DocumentSearch;
import it.eng.snam.summer.dmsmisuraservice.model.search.Pagination;
import it.eng.snam.summer.dmsmisuraservice.model.search.RemiSearch;
import it.eng.snam.summer.dmsmisuraservice.model.update.DocumentUpdate;
import it.eng.snam.summer.dmsmisuraservice.service.summer.SummerSqlProvider;
import it.eng.snam.summer.dmsmisuraservice.util.Entity;
import it.eng.snam.summer.dmsmisuraservice.util.MultipartInputStreamFileResource;

@Component
public class DDSDocument extends DDSEntity {

    @Autowired
    DDSSubfolder subfolderService;

    @Autowired
    SummerSqlProvider summerSql;

    @Autowired
    DDSFolder folderService;

    static ScriptEngine engine = null;
    static String parser = null;
    static {
        try {
            InputStream is = DDSDocument.class.getResourceAsStream("/SSEparser.js");
            Stream<String> stream = new BufferedReader(new InputStreamReader(is)).lines();
            parser = stream.collect(Collectors.joining("\n"));
        } catch (Exception e1) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e1.getMessage());
        }
        engine = new ScriptEngineManager().getEngineByName("graal.js");
    }

    // --------------------------------------------------------------------

    public Entity get(String document_id) {
        return this.get(document_id, clause("_id", document_id, "="));
    }

    private Entity get(String document_id, String clause) {
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
        return ddsList.get(0);
    }

    // --------------------------------------------------------------------

    public List<Entity> post(DocumentCreate params, MultipartFile file) {

        // verifica validità delle subfolders
        List<String> paramsPaths = folderService.tree().stream().filter(e -> e.getAsEntity("systemAttributes") != null)
                .map(e -> e.getAsEntity("systemAttributes").getAsString("name"))
                .filter(e -> params.getSubfolders().stream().anyMatch(s -> e.endsWith(s))).collect(Collectors.toList());

        if (paramsPaths.size() != params.getSubfolders().size()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "One or more subfolders are invalid : " + params.getSubfolders());
        }

        List<Entity> inserted = new ArrayList<>();

        // get remis from sql
        List<String> remisFromInfo = params.getInfo().stream().map(e -> e.getAsString("remi"))
                .collect(Collectors.toList());

        if (remisFromInfo.size() > 0) {
            //@formatter:off
            List<Entity> remisFromSQL = summerSql.listRemi(new RemiSearch(remisFromInfo));
            remisFromSQL.stream()
            .forEach(remi ->
                paramsPaths.stream()
                    .forEach(path ->{
                        Entity ddsDoc = insertDocumentDDS(params, path, file).with("remi", remi);
                        inserted.add(ddsDoc);
                        } )
                    );
            //@formatter:on
        } else {
            paramsPaths.stream()
                    .forEach(path -> inserted.add(insertDocumentDDS(params, path, file).with("remi", new Entity())));
        }
        //@formatter:off
        //create sql DTO list
        List<DocumentSQL> docsSQL = inserted.stream()
                .map(e -> new DocumentSQL()
                        .withId(e.id())
                        .withFolder(e.getAsListString("folders").get(0).split("/")[1])
                        .withSubfolder(   e.getAsListString("folders").get(0).split("/")[ e.getAsListString("folders").get(0).split("/").length - 1  ] )
                        .withLinea(e.getAsEntity("remi").getAsString("linea"))
                        .withRemi(e.getAsEntity("remi").getAsString("c_remi_ass"))
                        .withStatus("active"))
                .collect(Collectors.toList());
        //@formatter:on
        // insert into sql db and return
        summerSql.insertDocuments(docsSQL);
        List<String> docIds = inserted.stream().map(e -> e.getAsString("id")).collect(Collectors.toList());
        return summerSql.listDocuments(new DocumentSearch().withIds(docIds));
    }

    private Entity insertDocumentDDS(DocumentCreate params, String path, MultipartFile file) {
        Entity ddsPayload = toDDSPayload(params, this.os, path);
        List<Entity> sseStream = postDocumentToDDS(ddsPayload, file);
        if (!sseSuccess(sseStream)) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, sseFailReason(sseStream));
        }
        return ddsPayload;
    }

    private List<Entity> postDocumentToDDS(Entity ddsPayload, MultipartFile file) {
        try {
            //@formatter:off
            String sseMessage = rest.createDocument()
                .withParam("document", ddsPayload)
                .withParam("file",
                        new MultipartInputStreamFileResource(file.getInputStream(), file.getOriginalFilename()))
                .postMultipart();
            //@formatter:off
            String res = (String) engine.eval(parser + "JSON.stringify( parse.parse(`" + sseMessage + "`) )");
            return new ObjectMapper().readValue(res, new TypeReference<List<Entity>>() {
            });
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }


    private boolean sseSuccess(List<Entity> stream){
        return "message".equals(stream.get(stream.size() - 1).getAsString("event"));
    }

    private String sseFailReason(List<Entity> stream){
        return stream.get(stream.size() - 1).getAsEntity("data").getAsString("reason");
    }

    // private Entity merge(Entity doc, Entity dds) {
    //     // merge delle info da doc e da dds, priorità a db sql
    //     if (dds == null)
    //         return null;
    //     Entity res = Entity.build(doc);
    //     //@formatter:off
    //     return res
    //         .with("name", dds.getAsEntity("systemAttributes").getAsString("name"))
    //        .with("notes", dds.getAsEntity("systemAttributes").getAsString("documentTitle"))
    //         .with("status",
    //                 dds.getAsEntity("systemAttributes").getAsBoolean("isLogicalDeleted") ? "inactive" : "active")
    //         .with("link", link(dds));
    //     //@formatter:on
    // }

    // --------------------------------------------------------------------

    public Entity put(String document_id, DocumentUpdate params) {
        //@formatter:off
        String remi = params.getInfo().stream()
                .filter(e -> e.containsKey("remi"))
                .findFirst()
                .orElse(Entity.build("remi", null)
                )
            .getAsString("remi");
        if (remi != null) {
            summerSql.getRemi(remi);
        }
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
        int rows = summerSql.updateDocument(document_id, remi);
        if (rows <= 0)
            handleSQLfail(document_id, "Update document failed");

        return get(document_id);
    }

    private void handleSQLfail(String document_id, String message) {
        delete(document_id);
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, message);
    }

    // --------------------------------------------------------------------

    public void delete(String document_id) {
        //@formatter:off
        rest.logicalDeleteDocument()
            .withParam("id", document_id)
            .withParam("OS", this.os)
            .withParam("deleteAllVersion", false)
            .postForString();
        //@formatter:on

    }

    // --------------------------------------------------------------------

    public byte[] getContent(String document_id) {
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

    // private List<String> getAllContent(String document_id){
    // Entity dds = rest.getDocumentBySQL()
    // .withParam("OS", this.os )
    // .withParam("select", listOf("*"))
    // .withParam("where", "_id = '" + document_id + "'")
    // .postForList().get(0);

    // return dds.getAsList("contents").stream()
    // .map(e -> e.getAsString("ContentsName") )
    // .collect(Collectors.toList());
    // }

    // private Entity pickDocumentById(List<Entity> list, String id) {
    // return list.stream()
    // .filter(e -> id.equals(e.getAsString("id")))
    // .findFirst()
    // .orElse(null);
    // }

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
