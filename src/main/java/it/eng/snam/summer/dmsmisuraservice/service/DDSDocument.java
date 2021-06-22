package it.eng.snam.summer.dmsmisuraservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import static it.eng.snam.summer.dmsmisuraservice.util.Utility.listOf;
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




}
