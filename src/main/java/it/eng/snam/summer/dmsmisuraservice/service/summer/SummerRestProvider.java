package it.eng.snam.summer.dmsmisuraservice.service.summer;

import it.eng.snam.summer.dmsmisuraservice.util.Precall;
import it.eng.snam.summer.dmsmisuraservice.util.SnamRestClient;

public interface SummerRestProvider {





    public Precall getPrecall( String url , String id);


    public SnamRestClient getRemiById(String remi_id );

    public SnamRestClient getUserProfile(String user_id);

    public SnamRestClient getAreaTecnicaByUserId(String user_id) ;

    public SnamRestClient getRemiByAreaTecnica(String areaTecnica) ;
}
