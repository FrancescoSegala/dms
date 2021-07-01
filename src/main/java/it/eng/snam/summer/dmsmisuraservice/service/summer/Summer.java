package it.eng.snam.summer.dmsmisuraservice.service.summer;

import it.eng.snam.summer.dmsmisuraservice.model.Remi;
import it.eng.snam.summer.dmsmisuraservice.util.Entity;

public interface Summer {

    public Remi get(String id );

    public Entity getProfile(String user_id);
}
