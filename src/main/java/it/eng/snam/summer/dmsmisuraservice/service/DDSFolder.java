package it.eng.snam.summer.dmsmisuraservice.service;

import java.util.List;
import static it.eng.snam.summer.dmsmisuraservice.util.Utility.listOf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import it.eng.snam.summer.dmsmisuraservice.model.search.FolderSearch;
import it.eng.snam.summer.dmsmisuraservice.util.Entity;

@Component
public class DDSFolder {

    @Autowired
    DDSRestProvider rest;

    @Value("${external.dds.OS}")
    private String os;

    public List<Entity> list(FolderSearch params ) {
        //@formatter:off
        return rest.getFolderBySQL()
            .withParam("OS", this.os )
            .withParam("select", listOf("*"))
            .withParam("where", "limit = " + params.getLimit() )
            .postForList();
            //@formatter:on
    }

    public Entity get(String id) {
        try {
            //@formatter:off
            return rest.getFolderBySQL()
            .withParam("OS", this.os )
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
