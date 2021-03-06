package it.eng.snam.summer.dmsmisuraservice.service.summer;

import java.util.List;
import java.util.Map;

import it.eng.snam.summer.dmsmisuraservice.model.Remi;
import it.eng.snam.summer.dmsmisuraservice.model.create.DocumentCreate;
import it.eng.snam.summer.dmsmisuraservice.model.search.DocumentSearch;
import it.eng.snam.summer.dmsmisuraservice.util.Entity;

public interface Summer {

    public Remi get(String id );

    public Entity getProfile(String user_id);

    public Long getDocumentCount(String folder_id, String subfolder_id);

    public Map<String, Long> getDocumentCount(String folder_id);

    public List<Entity> getDocuments(DocumentSearch params);

    public Entity getDocument(String document_id);

    public int insertDocument(DocumentCreate params, String id);

    public int updateDocument(String document_id, String remi);
}
