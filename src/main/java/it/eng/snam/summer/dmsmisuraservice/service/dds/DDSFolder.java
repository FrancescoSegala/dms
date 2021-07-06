package it.eng.snam.summer.dmsmisuraservice.service.dds;

import java.util.List;
import static it.eng.snam.summer.dmsmisuraservice.util.Utility.*;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import it.eng.snam.summer.dmsmisuraservice.model.create.FolderCreate;
import it.eng.snam.summer.dmsmisuraservice.model.search.FolderSearch;
import it.eng.snam.summer.dmsmisuraservice.model.search.Pagination;
import it.eng.snam.summer.dmsmisuraservice.util.Entity;
import it.eng.snam.summer.dmsmisuraservice.util.Utility;

@Component
public class DDSFolder extends DDSEntity {

    public List<Entity> list(FolderSearch params) {
        //@formatter:off
        return rest.getFolderBySQL()
            .withParam("OS", this.os )
            .withParam("select", listOf("*"))
            .withParam("where", where(params))
            .postForList();
            //@formatter:on
    }

    private Entity update(FolderCreate params) {
        Entity folder = get(params.getId());

        Entity systemAttributes = folder.getAsEntity("systemAttributes")
        .with("annotations", params.getDescription());
        //@formatter:off
        rest.updateFolder()
            .withParam("OS", this.os)
            .withParam("id", "/" + params.getId())
            .withParam("systemAttributes", systemAttributes)
            .post();
        //@formatter:on
        return get(params.getId());
    }

    public Entity get(String id) {
        try {
            //@formatter:off
            return rest.getFolderBySQL()
            .withParam("OS", this.os )
            .withParam("select", listOf("*"))
            .withParam("where", clause("name", id, "=", "/", ""))
            .postForList()
            .get(0);
            //@formatter:on
        } catch (IndexOutOfBoundsException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Folder " + id + " does not exists");
        }
    }

    public Entity post(FolderCreate params) {
        //@formatter:off
        rest.createFolder()
                .withParam("OS", this.os)
                .withParam("folderName", params.getId())
                .withParam("folderClass", "dms_DMSFolder")
                .postForString();
        //@formatter:on
        return update(params);
    }


    @Override
    protected List<String> clauses(Pagination p) {
        FolderSearch params = (FolderSearch) p;
        return Utility.listOf(
            clause("foldersParents", "/", "=")
        );
    }

    @Override
    protected String sortField(String field) {
        return "name";
    }

}
