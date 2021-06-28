package it.eng.snam.summer.dmsmisuraservice.service.dds;

import java.util.List;
import static it.eng.snam.summer.dmsmisuraservice.util.Utility.listOf;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import it.eng.snam.summer.dmsmisuraservice.model.search.FolderSearch;
import it.eng.snam.summer.dmsmisuraservice.model.search.Pagination;
import it.eng.snam.summer.dmsmisuraservice.util.Entity;
import it.eng.snam.summer.dmsmisuraservice.util.Utility;

@Component
public class DDSFolder extends DDSEntity {

    public List<Entity> list(FolderSearch params ) {
        //@formatter:off
        return rest.getFolderBySQL()
            .withParam("OS", this.os )
            .withParam("select", listOf("*"))
            .withParam("where", where(params))
            .postForList();
            //@formatter:on
    }

    public Entity get(String id) {
        try {
            //@formatter:off
            return rest.getFolderBySQL()
            .withParam("OS", this.os )
            .withParam("select", listOf("*"))
            .withParam("where", clause("id", id, "="))
            .postForList()
            .get(0);
            //@formatter:on
        } catch (IndexOutOfBoundsException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Folder " + id + " does not exists");
        }
    }

    @Override
    protected List<String> clauses(Pagination p) {
        FolderSearch params = (FolderSearch) p;
        return Utility.listOf(
            clause("id", params.getId(), "=")
        );
    }

}
