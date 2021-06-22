package it.eng.snam.summer.dmsmisuraservice.service;

import static it.eng.snam.summer.dmsmisuraservice.util.Utility.listOf;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import it.eng.snam.summer.dmsmisuraservice.model.create.DocumentCreate;
import it.eng.snam.summer.dmsmisuraservice.model.update.DocumentUpdate;
import it.eng.snam.summer.dmsmisuraservice.util.Entity;

@Component
public class DDSDocument {


    @Autowired
    DDSRestProvider rest;

    @Value("${external.dds.OS}")
    private String os;

    public List<Entity> list() {
        //@formatter:off
        return rest.getDocumentBySQL()
            .withParam("OS", this.os)
            .withParam("select", listOf("*"))
            .withParam("where", "")
            .postForList();
            //@formatter:on
    }


    public Entity get(String document_id ){
        //@formatter:off
        return rest.getDocumentBySQL()
            .withParam("OS", this.os)
            .withParam("select", listOf("*"))
            .withParam("where", "id = '"+ document_id + "'")
            .post();
            //@formatter:on
    }

    public Entity post(DocumentCreate params){
        //TODO implement
        return new Entity();
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
