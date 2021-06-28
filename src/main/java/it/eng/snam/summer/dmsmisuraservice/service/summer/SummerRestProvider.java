package it.eng.snam.summer.dmsmisuraservice.service.summer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import static it.eng.snam.summer.dmsmisuraservice.util.Precall.precall;
import static it.eng.snam.summer.dmsmisuraservice.util.SnamRestClient.*;
 import it.eng.snam.summer.dmsmisuraservice.util.Precall;
import it.eng.snam.summer.dmsmisuraservice.util.SnamRestClient;

@Component
public class SummerRestProvider {


    @Value("${external.summer.jwt_url}")
    private String jwt_url ;

    @Value("${external.summer.remi_url}")
    private String anagrafica_remi_url  ;


    private Precall getPrecall(String remi_id){
        String authorization = rest(jwt_url).getString();
        return precall()
            .withUrl(anagrafica_remi_url + "/" + remi_id )
            .withAccessToken(authorization);
    }


    public SnamRestClient getRemiById(String id ){
        return rest(getPrecall(id));
    }

}
