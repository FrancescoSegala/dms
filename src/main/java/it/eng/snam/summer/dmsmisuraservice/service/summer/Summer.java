package it.eng.snam.summer.dmsmisuraservice.service.summer;

import java.util.List;
import java.util.Map;

import it.eng.snam.summer.dmsmisuraservice.model.Document;
import it.eng.snam.summer.dmsmisuraservice.model.DocumentSQL;
import it.eng.snam.summer.dmsmisuraservice.model.Permission;
import it.eng.snam.summer.dmsmisuraservice.model.Remi;
import it.eng.snam.summer.dmsmisuraservice.model.search.DocumentSearch;
import it.eng.snam.summer.dmsmisuraservice.util.Entity;

public interface Summer {

    public Remi get(String id);

    public Entity getProfile(String user_id);

    public Long getDocumentCount(String folder_id, String subfolder_id);

    public Map<String, Long> getDocumentCount(String folder_id);

    public List<Document> listDocuments(DocumentSearch params);

    public Document getDocument(String document_id);

    public void insertDocument( String path, String id, String remi , String linea ) ;

    public void insertDocuments( List<DocumentSQL> list);

    public int updateDocument(String document_id, String remi);

    public Entity getAreaTecnicaByUserId(String userId);

    public Entity[] getRemiByAreaTecnica(String areaTecnica);

    public Long countDocuments(DocumentSearch params);

    //ritorna la tabellina sul db della validazione , con quello si puo costruire la lista dei validatori
    public List<Entity> validation( String subfolder );

    public List<Entity> getPermissionBySubfolder(String subfolder_id);

}
