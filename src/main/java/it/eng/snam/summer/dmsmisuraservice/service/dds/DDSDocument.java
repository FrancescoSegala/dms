package it.eng.snam.summer.dmsmisuraservice.service.dds;

import static it.eng.snam.summer.dmsmisuraservice.util.Utility.listOf;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import it.eng.snam.summer.dmsmisuraservice.model.create.DocumentCreate;
import it.eng.snam.summer.dmsmisuraservice.model.search.DocumentSearch;
import it.eng.snam.summer.dmsmisuraservice.model.search.Pagination;
import it.eng.snam.summer.dmsmisuraservice.model.update.DocumentUpdate;
import it.eng.snam.summer.dmsmisuraservice.util.Entity;

@Component
public class DDSDocument extends DDSEntity{


    public List<Entity> list(DocumentSearch params) {
        //@formatter:off
        return rest.getDocumentBySQL()
            .withParam("OS", this.os)
            .withParam("select", listOf("*"))
            .withParam("where", where(params) )
            .postForList();
            //@formatter:on
    }


    public Entity get(String document_id ){
        //@formatter:off
        return rest.getDocumentBySQL()
            .withParam("OS", this.os)
            .withParam("select", listOf("*"))
            .withParam("where", clause("id",  document_id , "=") )
            .post();
            //@formatter:on
    }

    public Entity post(DocumentCreate params){
        return rest.createDocument()
                .withParam("OS", this.os)
                .withParam("documentalClass", params.getFolder() )
                .withParam("name", params.getName() )
                .withParam("documentTitle", params.getTitle() )
                .withParam("customAttributes", listOf() )
                .withParam("customPermission", listOf() )
                .post()
                ;
    }


    public Entity put(DocumentUpdate params){
        //TODO implement
        return new Entity();
    }


    public void delete(String document_id){
        //TODO IMPLEMENT
    }

    public void getContent(String document_id){
        throw new ResponseStatusException(HttpStatus.NOT_IMPLEMENTED);
    }


    @Override
    protected List<String> clauses(Pagination p) {
        DocumentSearch params = (DocumentSearch) p;
        return listOf(
        clause("folder", params.getFolder(), "="),
        clause("subfolder", params.getSubfolder(), "=") ,
        clause("published_at", params.getPublished_at(), "="),
        clause("remi", params.getRemi(), "="),
        clause("linea", params.getLinea_in(), "in")
        );
    }

}
