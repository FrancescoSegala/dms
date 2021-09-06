package it.eng.snam.summer.dmsmisuraservice.util.fake;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import static it.eng.snam.summer.dmsmisuraservice.util.Utility.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import it.eng.snam.summer.dmsmisuraservice.model.create.DocumentCreate;
import it.eng.snam.summer.dmsmisuraservice.model.search.DocumentSearch;
import it.eng.snam.summer.dmsmisuraservice.service.summer.SummerSqlProvider;
import it.eng.snam.summer.dmsmisuraservice.util.Entity;

public class FakeSummerSqlProvider implements SummerSqlProvider {


    private static List<Entity> v_docments = new ArrayList<>();

    static {
        int num = 30 + new Random().nextInt(170);
        for (int i = 0 ; i < num ; i++ ){
            v_docments.add(randomDoc());
        }
    }


    @Override
    public Long getDocumentCount(String folder_id, String subfolder_id) {
        return (long) v_docments.size();
    }

    @Override
    public Map<String, Long> getDocumentCount(String folder_id) {
        Map<String , Long > res = new HashMap<>();
        for (int i = 0 ; i < v_docments.size() ; i++){
            Entity e = v_docments.get(i);
            if ( res.containsKey(e.getAsString("folder")) ){
                res.put(e.getAsString("folder"), res.get(e.getAsString("folder")) +1 );
            }
            else {
                res.put(e.getAsString("folder"), 1L);
            }

        }
        return res;
    }

    @Override
    public List<Entity> getDocuments(DocumentSearch params) {
        return v_docments.subList(  params.getOffset().intValue(), params.getOffset().intValue() + params.getLimit().intValue()  )  ;
    }

    @Override
    public Entity getDocument(String document_id) {
        return v_docments
            .stream()
            .filter(e -> document_id.equals( e.id() ) )
            .findAny()
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "entity "+ document_id + " not found"));
    }

     public int insertDocument(DocumentCreate params, String id) {
        v_docments.add(randomDoc(id));
        return 1 ;
    }

    @Override
    public int updateDocument(String document_id, String remi) {
        Entity d = getDocument(document_id);
        int index = v_docments.indexOf(d);
        v_docments.set(index, d.with("remi", remi));
        return 1;
    }


    private static Entity randomDoc(String document_id ){
        return randomDoc().with("id", document_id);
    }

    private static Entity randomDoc(){
        List<Entity> f = new FakeDataLoader().folders();
        List<Entity> s = new FakeDataLoader().subfolders();
        return new Entity()
            .with("id", "DMSMIS_"+UUID.randomUUID().toString())
            .with("data_documento", Instant.now().toString() )
            .with("folder", new FakeDataLoader().folders().get( new Random().nextInt(f.size())) )
            .with("subfolder", new FakeDataLoader().subfolders().get( new Random().nextInt(s.size()) ) )
            .with("remi", "34241401" )
            .with("linea", "['linea']" )
            .with("codice_centro", "codice_centro" )
            .with("codice_distretto", "codice_distretto" )
            .with("codice_area_tecnica", "codice_area_tecnica" )
            .with("codice_polo", "codice_polo" )
            .with("ragione_sociale", "ragione_sociale" )
            .with("ubicazione", "ubicazione" )
            .with("provincia", "provincia" )
            .with("comune", "comune" )
            .with("regione", "regione" )
            .with("codice_aop", "codice_aop" )
            .with("codice_ateco", "codice_ateco" )
            .with("data_entrata_esercizio", "data_entrata_esercizio" )
            .with("remi_terzo", "remi_terzo" )
            .with("reti_trasporto", "reti_trasporto" );
    }

    @Override
    public List<String> getDocumentiByRemi(List<String> listaRemi) {

        return listOf( randomDoc().toString(),randomDoc().toString() );
    }


	@Override
	public String getTDocByProfilo(String profilo) {
		return "COMM,APRI";
	}

    @Override
    public void insertDocument(String path, String id, String remi, String linea) {
        //Nop
    }

    @Override
    public List<Entity> getDocuments(List<String> ids) {
        // TODO Auto-generated method stub
        return null;
    }



}
