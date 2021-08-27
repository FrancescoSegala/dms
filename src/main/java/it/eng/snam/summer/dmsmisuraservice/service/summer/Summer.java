package it.eng.snam.summer.dmsmisuraservice.service.summer;

import java.util.Map;

import it.eng.snam.summer.dmsmisuraservice.model.Remi;
import it.eng.snam.summer.dmsmisuraservice.util.Entity;

public interface Summer {

    public Remi get(String id );

    public Entity getProfile(String user_id);
    
    public Entity getAreaTecnicaByUserId(String userId);
    
    public Entity[] getRemiByAreaTecnica(String areaTecnica);

	Long getDocumentCount(String folder_id, String subfolder_id);

	Map<String, Long> getDocumentCount(String folder_id);
}
