package it.eng.snam.summer.dmsmisuraservice.service.summer;

import it.eng.snam.summer.dmsmisuraservice.util.SnamRestClient;

public interface SummerRestProvider {

    public SnamRestClient getRemiById(String remi_id );
    public SnamRestClient getUserProfile(String user_id);
}
