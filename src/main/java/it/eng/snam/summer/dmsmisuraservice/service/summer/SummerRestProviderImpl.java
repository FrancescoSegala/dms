package it.eng.snam.summer.dmsmisuraservice.service.summer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import static it.eng.snam.summer.dmsmisuraservice.util.Precall.precall;
import it.eng.snam.summer.dmsmisuraservice.util.Precall;
import it.eng.snam.summer.dmsmisuraservice.util.RestClientBuilder;
import it.eng.snam.summer.dmsmisuraservice.util.SnamRestClient;

@Component
public class SummerRestProviderImpl implements SummerRestProvider{


    @Value("${external.summer.jwt_url}")
    private String jwt_url ;

    @Value("${external.summer.remi_url}")
    private String anagrafica_remi_url  ;

    @Value("${external.summer.user_url}")
    private String user_url  ;


    private Precall getPrecall( String url , String id){
        String authorization = new RestClientBuilder().build(jwt_url).getString();
        return precall()
            .withUrl(url + "/" + id )
            .withAccessToken(authorization);
    }


    public SnamRestClient getRemiById(String remi_id ){
        return new RestClientBuilder().build(getPrecall(anagrafica_remi_url, remi_id));
    }


    public SnamRestClient getUserProfile(String user_id){
        return new RestClientBuilder().build(getPrecall( user_url ,user_id));
    }


}
