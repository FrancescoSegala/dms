package it.eng.snam.summer.dmsmisuraservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static it.eng.snam.summer.dmsmisuraservice.util.Utility.listOf;
import it.eng.snam.summer.dmsmisuraservice.util.Entity;
import it.eng.snam.summer.dmsmisuraservice.util.SnamRestClient;

@Component
public class DDSDocument {


    @Autowired
    UrlProvider url;


    public List<Entity> list() {
        //@formatter:off
        return new SnamRestClient( url.getDocumentBySQL() )
            .withParam("OS", "DMSMIS")
            .withParam("select", listOf("*"))
            .withParam("where", "")
            .postForList();
            //@formatter:on
    }


}
