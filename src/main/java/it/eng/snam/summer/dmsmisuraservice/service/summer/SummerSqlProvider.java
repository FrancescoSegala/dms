package it.eng.snam.summer.dmsmisuraservice.service.summer;

import java.util.List;
import java.util.Map;

import it.eng.snam.summer.dmsmisuraservice.model.DocumentSQL;
import it.eng.snam.summer.dmsmisuraservice.model.search.DocumentSearch;
import it.eng.snam.summer.dmsmisuraservice.util.Entity;

public interface SummerSqlProvider {

    public Long getDocumentCount(String folder_id, String subfolder_id);

    public Map<String, Long> getDocumentCount(String folder_id);

    public List<Entity> getDocuments(DocumentSearch params);

    public List<Entity> getDocuments(List<String> ids );

    public Entity getDocument(String document_id);

    public void insertDocument(String path, String id, String remi , String linea);

    public void insertDocuments( List<DocumentSQL> list);

    public int updateDocument(String document_id, String remi);

    public List<String> getDocumentiByRemi(List<String> listaRemi);

    public String getTDocByProfilo(String profilo);
}
