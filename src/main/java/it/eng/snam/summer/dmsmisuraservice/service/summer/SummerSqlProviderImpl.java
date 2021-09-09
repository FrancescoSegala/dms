package it.eng.snam.summer.dmsmisuraservice.service.summer;

import static it.eng.snam.summer.dmsmisuraservice.util.EntityMapper.toSQLpayload;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;

import it.eng.snam.summer.dmsmisuraservice.model.DocumentSQL;
import it.eng.snam.summer.dmsmisuraservice.model.search.DocumentCount;
import it.eng.snam.summer.dmsmisuraservice.model.search.DocumentSearch;
import it.eng.snam.summer.dmsmisuraservice.model.search.IdSearch;
import it.eng.snam.summer.dmsmisuraservice.model.search.RemiSearch;
import it.eng.snam.summer.dmsmisuraservice.util.Entity;
import it.eng.snam.summer.dmsmisuraservice.util.SnamSQLClient;


public class SummerSqlProviderImpl implements SummerSqlProvider {

    @Autowired
    private NamedParameterJdbcOperations template;


    public Long getDocumentCount(String folder_id, String subfolder_id) {
        return new SnamSQLClient(template).withTable("documenti").withParams(new DocumentCount(folder_id, subfolder_id))
                .count();
    }

    public Map<String, Long> getDocumentCount(String folder_id) {
        return new SnamSQLClient(template).withTable("documenti").withParams(new DocumentCount(folder_id, null))
                .countByField("subfolder");
    }

    public Long countDocuments(DocumentSearch params ){
        //@formatter:off
        return new SnamSQLClient(template)
        .withTable("v_documenti")
        .withParams(params)
        .count();
        //@formatter:on
    }

    public List<Entity> listDocuments(DocumentSearch params) {
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


    public void insertDocument(String path, String id, String remi , String linea ){
         insertDocument(id, path.split("/")[1] , path.split("/")[2], remi , linea  ) ;
    }

    private int insertDocument(String id , String folder_id , String subfolder_id,String remi ,  String linea){
        return new SnamSQLClient(template).withTable("documenti")
        .insert(
            toSQLpayload(id, folder_id, subfolder_id, remi, linea )
            );
    }


    @Transactional //TODO va bene qui?
    @Override
    public void insertDocuments( List<DocumentSQL> list){
        new SnamSQLClient(template)
            .withTable("documenti")
            .insert( list.stream().map(e -> toSQLpayload(e)).collect(Collectors.toList() )  )
        ;
    }


    public int updateDocument( String document_id , String remi ){
        return new SnamSQLClient(template)
        .withTable("documenti")
        .update(Entity.build("c_remi_ass", remi ), document_id);
    }


    public List<String> getDocumentiByRemi(List<String> listaRemi) {
		Map<String,Object> remiParameters = Collections.singletonMap("remis", listaRemi);

        List<Map<String, Object>> idDocs = template.queryForList( "SELECT d.id FROM documenti d "
		        + "WHERE c_remi_ass in (:remis)", remiParameters);
		List<String> listDocuments=new ArrayList<String>();
		for (Map<String, Object> mapId : idDocs) {
			String idDoc = (String) mapId.get("id");
			listDocuments.add(idDoc);
		}
		return listDocuments;
    }

	@Override
	public String getTDocByProfilo(String profilo) {
		Map<String,String> params = Collections.singletonMap("profiloParam", profilo);

		List<Map<String, Object>> result = template.queryForList("SELECT t.tdoc_primo_livello from profilo_tdoc t WHERE profilo=:profiloParam", params);

		return (String) result.get(0).get("tdoc_primo_livello");
	}

    @Override
    public Entity getRemi(String remi) {
        return new SnamSQLClient(template)
        .withTable("anag_remi")
        .withParams(new RemiSearch(remi))
        .get();
    }

    @Override
    public List<Entity> listRemi(RemiSearch params) {
        return new SnamSQLClient(template)
        .withTable("anag_remi")
        .withParams(params)
        .list();
    }

    @Override
    public List<Entity> listValidations(String subfolder) {
        return new SnamSQLClient(template)
            .withTable("cartella_campi")
            .withParams(new IdSearch(subfolder).withLimit(1000L)  )
            .list();
    }
}
