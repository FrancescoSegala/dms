package it.eng.snam.summer.dmsmisuraservice.service.summer;

import java.util.List;
import java.util.Map;

import static it.eng.snam.summer.dmsmisuraservice.util.EntityMapper.toSQLpayload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Component;

import it.eng.snam.summer.dmsmisuraservice.model.create.DocumentCreate;
import it.eng.snam.summer.dmsmisuraservice.model.search.DocumentCount;
import it.eng.snam.summer.dmsmisuraservice.model.search.DocumentSearch;
import it.eng.snam.summer.dmsmisuraservice.model.search.IdSearch;
import it.eng.snam.summer.dmsmisuraservice.util.Entity;
import it.eng.snam.summer.dmsmisuraservice.util.SnamSQLClient;

@Component
public class SummerSqlProvider {

    @Autowired(required = false )
    private NamedParameterJdbcOperations template;

    public Long getDocumentCount(String folder_id, String subfolder_id) {
        return new SnamSQLClient(template).withTable("documenti").withParams(new DocumentCount(folder_id, subfolder_id))
                .count();
    }

    public Map<String, Long> getDocumentCount(String folder_id) {
        return new SnamSQLClient(template).withTable("documenti").withParams(new DocumentCount(folder_id, null))
                .countByField("subfolder");
    }

    public List<Entity> getDocuments(DocumentSearch params) {
        //@formatter:off
        return new SnamSQLClient(template)
        .withTable("v_documenti")
        .withParams(params)
        .list();
        //@formatter:on
    }

    public Entity getDocument(String document_id ){
        return new SnamSQLClient(template)
        .withTable("v_documenti")
        .withParams(new IdSearch(document_id))
        .get();
    }


    public int insertDocument(DocumentCreate params , String id  ){
        return new SnamSQLClient(template).withTable("documenti").insert(toSQLpayload(params, id ));
    }


    public int updateDocument( String document_id , String remi ){
        return new SnamSQLClient(template)
        .withTable("documenti")
        .update(Entity.build("c_remi_ass", remi ), document_id);
    }

}
