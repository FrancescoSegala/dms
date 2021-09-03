package it.eng.snam.summer.dmsmisuraservice.service.dds;

import static it.eng.snam.summer.dmsmisuraservice.util.Utility.listOf;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import it.eng.snam.summer.dmsmisuraservice.aspect.ExecutionTime;
import it.eng.snam.summer.dmsmisuraservice.model.create.SubfolderCreate;
import it.eng.snam.summer.dmsmisuraservice.model.search.Pagination;
import it.eng.snam.summer.dmsmisuraservice.model.search.SubfolderSearch;
import it.eng.snam.summer.dmsmisuraservice.model.update.SubfolderUpdate;
import it.eng.snam.summer.dmsmisuraservice.util.Entity;

@Component
public class DDSSubfolder extends DDSEntity {


    @Autowired
    DDSFolder ddsFolder;

    @ExecutionTime
    public List<Entity> list(String folder_id , SubfolderSearch params ){
        return rest.getFolderBySQL()
            .withParam("OS", this.os )
            .withParam("select", listOf("*"))
            .withParam("where",  clause("name", folder_id , "like", "/", "/%") + " and " + where(params)   )
            .postForList();
    }


    @ExecutionTime
    public Entity getFolderBySubfolder(String subfolder_id){
        return rest.getFolderBySQL()
            .withParam("OS", this.os)
            .withParam("select", listOf("*"))
            .withParam("where", "name like '/%/"+ subfolder_id + "' limit 1 offset 0" )
            .postForList().get(0)
            ;
    }

    @ExecutionTime
    public Entity get(String folder_id , String subfolder_id ){
        List<Entity> list = rest.getFolderBySQL()
            .withParam("OS", this.os )
            .withParam("select", listOf("*"))
            .withParam("where", clause("name", folder_id + "/" + subfolder_id, "=", "/", ""))
            .postForList();
        if (list.size() == 0 ){
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Entity "+ folder_id +"/" + subfolder_id + " not found");
        }
        return list.get(0);
    }


    @ExecutionTime
    public void delete(String id ){
        rest.deleteFolder()
            .withParam("OS", this.os)
            .withParam("id", "/" + id )
            .postForString();
    }


    @ExecutionTime
    public Entity post(String folder_id, SubfolderCreate params){
        params.setId( folder_id + "/" + params.getId() );
        return ddsFolder.post(params);
    }

    @ExecutionTime
    public Entity put(String id, SubfolderUpdate params){
        Entity folder = ddsFolder.get(id);
        Entity systemAttributes = folder.getAsEntity("systemAttributes")
        .with("annotations", params.getDescription());
        //@formatter:off
        rest.updateFolder()
            .withParam("OS", this.os)
            .withParam("id", "/" + id)
            .withParam("systemAttributes", systemAttributes)
            .post();
        //@formatter:on
        return ddsFolder.get(id);
    }


    @Override
    protected List<String> clauses(Pagination p) {
        SubfolderSearch params = (SubfolderSearch) p;
        return listOf(
            clause("isLogicalDeleted", "inactive".equalsIgnoreCase(params.getStatus())  )
        );
    }


    @Override
    protected String sortField(String field) {
        return "name";
    }


}
