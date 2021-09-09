package it.eng.snam.summer.dmsmisuraservice.service.summer;
import static it.eng.snam.summer.dmsmisuraservice.util.EntityMapper.toRemi;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import it.eng.snam.summer.dmsmisuraservice.model.Document;
import it.eng.snam.summer.dmsmisuraservice.model.DocumentSQL;
import it.eng.snam.summer.dmsmisuraservice.model.Permission;
import it.eng.snam.summer.dmsmisuraservice.model.Remi;
import it.eng.snam.summer.dmsmisuraservice.model.search.DocumentSearch;
import it.eng.snam.summer.dmsmisuraservice.util.Entity;
import it.eng.snam.summer.dmsmisuraservice.util.EntityMapper;

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
    public List<Document> listDocuments(DocumentSearch params) {
        return sql.listDocuments(params)
        .stream()
        .map(EntityMapper::toDocument)
        .collect(Collectors.toList());
    }

    @Override
    public Document getDocument(String document_id) {
        return EntityMapper.toDocument(  sql.getDocument(document_id) ) ;
    }

    @Override
    public void insertDocument(String path , String id,  String remi , String linea ) {
        sql.insertDocument(path, id, remi , linea );
    }

    @Override
    public int updateDocument(String document_id, String remi) {
        return sql.updateDocument(document_id, remi);
    }


	@Override
	public Entity getAreaTecnicaByUserId(String userId) {
		return remi.getAreaTecnicaByUserId(userId);
	}

	@Override
	public Entity[] getRemiByAreaTecnica(String areaTecnica) {
		return remi.getRemiByAreaTecnica(areaTecnica);
	}


    @Override
    public void insertDocuments(List<DocumentSQL> list) {
          sql.insertDocuments(list);
    }

    @Override
    public Long countDocuments(DocumentSearch params) {
        return sql.countDocuments(params);
    }

    @Override
    public List<Entity> validation(String subfolder) {
        return sql.listValidations(subfolder);
    }

    @Override
    public List<Entity> getPermissionBySubfolder(String subfolder_id) {
        return sql.listValidations(subfolder_id);
    }
}
