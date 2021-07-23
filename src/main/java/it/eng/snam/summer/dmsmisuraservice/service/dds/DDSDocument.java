package it.eng.snam.summer.dmsmisuraservice.service.dds;

import static it.eng.snam.summer.dmsmisuraservice.util.Utility.listOf;
import static it.eng.snam.summer.dmsmisuraservice.util.Validators.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

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
        try {
            //valudazione folder e subfolder con get
            subfolderService.get(params.getFolder(),params.getSubfolder());
        } catch (ResponseStatusException e) {
            System.out.println(e.getMessage());
            throw new ResponseStatusException(e.getStatus(), e.getMessage());
        }
        System.out.println("params");
        System.out.println(params);

        System.out.println("file");
        System.out.println(file.getOriginalFilename());


        //TODO post su dds
        byte[] newFile;
        try {
        	newFile = file.getBytes();
        	ResponseEntity<byte[]> res = rest.createDocument()
        			.withParam("document", params)
        			.withParam("file", newFile)
        			.postMultipart();


        	System.out.println(res.getStatusCode() );
        } catch (IOException e) {
        	e.printStackTrace();
        }
        //TODO se risultato è ok continua
        //TODO se ok scrivi su db

        // TODO prima scrivi file (attuale file pdf) su dds e poi su db
        return null;
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
