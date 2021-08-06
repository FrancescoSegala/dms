package it.eng.snam.summer.dmsmisuraservice.service.summer;
import static it.eng.snam.summer.dmsmisuraservice.util.EntityMapper.toRemi;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import it.eng.snam.summer.dmsmisuraservice.model.Remi;
import it.eng.snam.summer.dmsmisuraservice.model.create.DocumentCreate;
import it.eng.snam.summer.dmsmisuraservice.model.search.DocumentSearch;
import it.eng.snam.summer.dmsmisuraservice.util.Entity;

public class SummerImpl implements Summer  {

    @Autowired
    SummerRemi remi;

    @Autowired
    SummerUser users;

    @Autowired
    SummerSqlProvider sql ;


    @Override
    public Remi get(String id) {
        return toRemi(remi.get(id));
    }

    @Override
    public Entity getProfile(String user_id){
        return users.getUserProfile(user_id);
    }

    @Override
    public Long getDocumentCount(String folder_id, String subfolder_id) {
        return sql.getDocumentCount(folder_id, subfolder_id);
    }

    @Override
    public Map<String, Long> getDocumentCount(String folder_id) {
         return sql.getDocumentCount(folder_id);
    }

    @Override
    public List<Entity> getDocuments(DocumentSearch params) {
        return sql.getDocuments(params);
    }

    @Override
    public Entity getDocument(String document_id) {
        return sql.getDocument(document_id);
    }

    @Override
    public int insertDocument(DocumentCreate params, String id) {
        return sql.insertDocument(params, id);
    }

    @Override
    public int updateDocument(String document_id, String remi) {
        return sql.updateDocument(document_id, remi);
    }

}
