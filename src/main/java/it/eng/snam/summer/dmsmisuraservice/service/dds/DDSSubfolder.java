package it.eng.snam.summer.dmsmisuraservice.service.dds;

import static it.eng.snam.summer.dmsmisuraservice.util.Utility.listOf;

import java.util.List;

import org.springframework.stereotype.Component;

import it.eng.snam.summer.dmsmisuraservice.model.create.SubfolderCreate;
import it.eng.snam.summer.dmsmisuraservice.model.search.Pagination;
import it.eng.snam.summer.dmsmisuraservice.model.search.SubfolderSearch;
import it.eng.snam.summer.dmsmisuraservice.model.update.SubfolderUpdate;
import it.eng.snam.summer.dmsmisuraservice.util.Entity;

@Component
public class DDSSubfolder extends DDSEntity {


    public List<Entity> list(String folder_id , SubfolderSearch params ){
        return rest.getFolderBySQL()
            .withParam("OS", this.os )
            .withParam("select", listOf("*"))
            .withParam("where", where(params) ) //TODO è corretto ? non serve il folder id?
            .postForList();
    }


    public Entity get(String folder_id , String subfolder_id ){
        return rest.getFolderBySQL()
                .withParam("OS", this.os )
                .withParam("select", listOf("*"))
                .withParam("where", clause("subfolder_id", subfolder_id, "=")) //TODO sbagliato risponde 422
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


    @Override
    protected List<String> clauses(Pagination p) {
        SubfolderSearch params = (SubfolderSearch) p;
        return listOf(
            clause("id", params.getId(), "="),
            clause("folder", params.getFolder(), "="),
            clause("status", params.getStatus(), "=")
        );
    }


}
