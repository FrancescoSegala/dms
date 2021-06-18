package it.eng.snam.summer.dmsmisuraservice.service;

import java.util.List;
import static it.eng.snam.summer.dmsmisuraservice.util.Utility.listOf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import it.eng.snam.summer.dmsmisuraservice.util.Entity;
import it.eng.snam.summer.dmsmisuraservice.util.SnamRestClient;

@Component
public class DDSFolder {

    @Autowired
    UrlProvider url;

    public List<Entity> list() {
        //@formatter:off
        return new SnamRestClient( url.getFolderBySQL() )
            .withParam("OS", "DMSMIS")
            .withParam("select", listOf("*"))
            .withParam("where", "")
            .postForList();
            //@formatter:on
    }

    public Entity get(String id) {
        try {
            //@formatter:off
            return new SnamRestClient( url.getFolderBySQL() )
            .withParam("OS", "DMSMIS")
            .withParam("select", listOf("*"))
            .withParam("where", "id = '" + id + "'")
            .postForList()
            .get(0);
            //@formatter:on
        } catch (IndexOutOfBoundsException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Folder " + id + " does not exists");
        }

    }

}
