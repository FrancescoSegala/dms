package it.eng.snam.summer.dmsmisuraservice.service;

import static it.eng.snam.summer.dmsmisuraservice.util.Utility.listOf;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import it.eng.snam.summer.dmsmisuraservice.model.create.SubfolderCreate;
import it.eng.snam.summer.dmsmisuraservice.model.search.SubfolderSearch;
import it.eng.snam.summer.dmsmisuraservice.model.update.SubfolderUpdate;
import it.eng.snam.summer.dmsmisuraservice.util.Entity;

@Component
public class DDSSubfolder {


    @Autowired
    DDSRestProvider rest;

    @Value("${external.dds.OS}")
    private String os;


    public List<Entity> list(String folder_id , SubfolderSearch params ){
        return rest.getFolderBySQL()
            .withParam("OS", this.os )
            .withParam("select", listOf("*"))
            .withParam("where", "limit = " + params.getLimit() )
            .postForList();
    }


    public Entity get(String folder_id , String subfolder_id ){
        return rest.getFolderBySQL()
                .withParam("OS", this.os )
                .withParam("select", listOf("*"))
                .withParam("where", "subfolder condition")
                .post();
    }


    public void delete(String folder_id, String subfolder_id){
        //TODO IMPLEMENT
    }


    public Entity post(String folder_id, SubfolderCreate params){
        return new Entity();
        //TODO IMPLEMENT
    }

    public Entity put(String folder_id, SubfolderUpdate params){
        return new Entity();
        //TODO IMPLEMENT
    }


}
