package it.eng.snam.summer.dmsmisuraservice.service.dds;

import static it.eng.snam.summer.dmsmisuraservice.util.Utility.listOf;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import it.eng.snam.summer.dmsmisuraservice.model.create.DocumentCreate;
import it.eng.snam.summer.dmsmisuraservice.model.search.DocumentSearch;
import it.eng.snam.summer.dmsmisuraservice.model.search.IdSearch;
import it.eng.snam.summer.dmsmisuraservice.model.update.DocumentUpdate;
import it.eng.snam.summer.dmsmisuraservice.util.Entity;
import it.eng.snam.summer.dmsmisuraservice.util.SnamSQLClient;

@Component
public class DDSDocument {

    @Autowired
    NamedParameterJdbcOperations template ;

    public List<Entity> list(DocumentSearch params) {
        return new SnamSQLClient(template)
        .withTable("v_documenti")
        .withParams(params)
        .list();

    }


    public Entity get(String document_id ){
        //@formatter:off
        return new SnamSQLClient(template)
        .withTable("v_documenti")
        .withParams(new IdSearch(document_id))
        .get();
        //@formatter:on
    }

    public Entity post(DocumentCreate params){
        // return rest.createDocument()
        //         .withParam("OS", this.os)
        //         .withParam("documentalClass", params.getFolder() )
        //         .withParam("name", params.getName() )
        //         .withParam("documentTitle", params.getTitle() )
        //         .withParam("customAttributes", listOf() )
        //         .withParam("customPermission", listOf() )
        //         .post()
        //         ;
        return null ;
    }


    public Entity put(DocumentUpdate params){
        //TODO implement
        return new Entity();
    }


    public void delete(String document_id){
        //TODO IMPLEMENT
    }

    public void getContent(String document_id){
        throw new ResponseStatusException(HttpStatus.NOT_IMPLEMENTED);
    }

}
