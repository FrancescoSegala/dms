package it.eng.snam.summer.dmsmisuraservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import static it.eng.snam.summer.dmsmisuraservice.util.Utility.listOf;

import it.eng.snam.summer.dmsmisuraservice.model.search.SubfolderSearch;
import it.eng.snam.summer.dmsmisuraservice.util.Entity;

public class DDSSubfolder {


    @Autowired
    DDSRestProvider rest;

    @Value("${external.dds.OS}")
    private String os;


    List<Entity> list(String folder_id , SubfolderSearch params ){
        return rest.getFolderBySQL()
            .withParam("OS", this.os )
            .withParam("select", listOf("*"))
            .withParam("where", "limit = " + params.getLimit() )
            .postForList();
    }

}
