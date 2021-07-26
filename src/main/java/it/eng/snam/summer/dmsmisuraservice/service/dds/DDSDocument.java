package it.eng.snam.summer.dmsmisuraservice.service.dds;

import static it.eng.snam.summer.dmsmisuraservice.util.Utility.listOf;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.List;
import java.util.UUID;
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
import it.eng.snam.summer.dmsmisuraservice.util.Entity;
import it.eng.snam.summer.dmsmisuraservice.util.SnamSQLClient;

@Component
public class DDSDocument extends DDSEntity {

    @Autowired
    NamedParameterJdbcOperations template;

    @Autowired
    DDSSubfolder subfolderService;

    public List<Entity> list(DocumentSearch params) {
        if (params.getLimit() <= 10)
            params.setLimit(100000L);
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
            .collect(Collectors.toList());
        //@formatter:on
    }

    public Entity get(String document_id) {
        //@formatter:off
        Entity doc =  new SnamSQLClient(template)
        .withTable("v_documenti")
        .withParams(new IdSearch(document_id))
        .get();
        //@formatter:on
        //@formatter:off
        Entity dds =  rest.getDocumentBySQL()
        .withParam("OS", this.os )
        .withParam("select", listOf("*"))
        .withParam("where", clause("_id", document_id, "=") )
        .postForList().get(0);
        //@formatter:on
        return merge(doc, dds);
    }

    public Entity post(DocumentCreate params, MultipartFile file) {
        // TODO validazione info ?
        validatePath(params.getFolder(), params.getSubfolder());

        Entity ddsDoc = toDDSpayload(params);
        String sseMessage = this.postToDDS( ddsDoc, file);

        System.out.println("SSEMESSAGE");
        System.out.println(sseMessage);

        if (!sseMessage.contains("\"status\" : 200")) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, sseMessage);
        }
        int rows = new SnamSQLClient(template).withTable("documenti").insert(toSQLpayload(params, ddsDoc.id()));
        if (rows <= 0) {
            this.handleSQLfail(ddsDoc.id());
        }
        return get(ddsDoc.id());
    }

    public Entity put(DocumentUpdate params) {
        // TODO implement
        return new Entity();
    }

    public void delete(String document_id) {
        // TODO IMPLEMENT
    }

    public void getContent(String document_id) {
        throw new ResponseStatusException(HttpStatus.NOT_IMPLEMENTED);
    }

    private Entity pickDDSDocumentById(List<Entity> list, String id) {
        return list.stream().filter(e -> id.equals(e.getAsString("_id"))).findFirst().orElse(null);
    }

    private Entity toSQLpayload(DocumentCreate params, String id) {
        return Entity.build("id", id).with("data", Instant.now().toString())
                .with("c_remi_ass",
                        params.getInfo().stream().filter(e -> e.containsKey("remi")).findFirst()
                                .orElse((Info) Info.build().with("remi", null)).getAsString("remi"))
                .with("folder", params.getFolder()).with("subfolder", params.getSubfolder());
    }

    private Entity toDDSpayload(DocumentCreate doc) {
        String id = "DMSMIS_" + UUID.randomUUID().toString();
        return Entity.build("OS", this.os).with("documentalClass", "ALTRO").with("id", id).with("name", doc.getName())
                .with("documentTitle", doc.getTitle()).with("customAttributes", listOf())
                .with("customPermissions", listOf())
                .with("folders", listOf("/" + doc.getFolder() + "/" + doc.getSubfolder()))
                .with("folderClass", "dms_DMSFolder").with("createFolderIfNotExist", false);
    }

    private Entity merge(Entity doc, Entity dds) {
        // merge delle info da doc e da dds, priorità a db sql
        if (dds == null)
            return null;
        Entity res = Entity.build(doc);
        return res.with("name", dds.getAsEntity("systemAttributes").getAsString("name"))
                .with("created_at", dds.getAsEntity("systemAttributes").getAsEntity("dateCreated").getAsString("$date"))
                .with("updated_at",
                        dds.getAsEntity("systemAttributes").getAsEntity("dateLastModified").getAsString("$date"))
                .with("created_by", dds.getAsEntity("systemAttributes").getAsString("creator"))
                .with("updated_by", dds.getAsEntity("systemAttributes").getAsString("lastModifier"))
                .with("notes", dds.getAsEntity("systemAttributes").getAsString("documentTitle"))
                .with("status",
                        dds.getAsEntity("systemAttributes").getAsBoolean("isLogicalDeleted") ? "inactive" : "active")
                .with("link", link(dds));
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

    private void handleSQLfail(String document_id) {
        delete(document_id);
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Insert document failed");
    }

    private String postToDDS(Entity ddsDoc ,  MultipartFile file ){
        String sseMessage = "Document creation failed";
        ResponseEntity<byte[]> res = null;
        try {
            byte[] fileStream = file.getBytes();
            res = rest.createDocument()
                .withParam("document", ddsDoc)
                .withParam("file", fileStream)
                .postMultipart();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (res == null) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, sseMessage);

        InputStream is = new ByteArrayInputStream(res.getBody());
        sseMessage = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))
                .lines()
                .collect(Collectors.joining("\n"));
        return sseMessage ;
    }

    @Override
    protected String sortField(String field) {
        return "_id";
    }

    @Override
    protected List<String> clauses(Pagination p) {
        DocumentSearch params = (DocumentSearch) p;
        return listOf(clause("isLogicalDeleted", "inactive".equalsIgnoreCase(params.getStatus())));
    }

}
