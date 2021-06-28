package it.eng.snam.summer.dmsmisuraservice.service.dds;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import static it.eng.snam.summer.dmsmisuraservice.util.SnamRestClient.rest;
import static it.eng.snam.summer.dmsmisuraservice.util.Precall.precall;

import it.eng.snam.summer.dmsmisuraservice.util.Precall;
import it.eng.snam.summer.dmsmisuraservice.util.Entity;
import it.eng.snam.summer.dmsmisuraservice.util.SnamRestClient;

@Component
public class DDSRestProvider {


    @Value("${external.dds.folder_precall_url}")
    private String folder_precall_url ;
    @Value("${external.dds.document_read_precall_url}")
    private String document_read_precall_url ;
    @Value("${external.dds.document_write_precall_url}")
    private String document_write_precall_url ;
    @Value("${external.dds.client_id}")
    private String client_id ;
    @Value("${external.dds.client_secret}")
    private String client_secret ;

    private static Map<String, Entity> SSO_EXPIRATION = new HashMap<>() ;

    private Precall getPrecall(String precallUrl , String fn , String method ){
        Entity precall = rest(precallUrl).get();
        return precall()
                .withUrl(precall.getAsString(fn) + "/"+ method)
                .withAccessToken(accessToken(precall.getAsString("SSOUrl")));
    }


    private boolean isAccessTokenExpired(String ssoUrl){
        return (! SSO_EXPIRATION.containsKey(ssoUrl)) || (Instant.now().isAfter((Instant) SSO_EXPIRATION.get(ssoUrl).get("expiration")));
    }


    private String accessToken(String ssoUrl ){
        if ( ! isAccessTokenExpired(ssoUrl) ){
            return SSO_EXPIRATION.get(ssoUrl).getAsString("access_token");
        }
        Entity ssoResult = rest(ssoUrl)
            .withContentType(MediaType.APPLICATION_FORM_URLENCODED)
            .withParam("grant_type", "client_credentials")
            .withParam("client_id", client_id)
            .withParam("client_secret", client_secret)
            .post();
        Entity refreshResponse = rest(ssoUrl)
            .withContentType(MediaType.APPLICATION_FORM_URLENCODED)
            .withParam("grant_type", "refresh_token")
            .withParam("client_id", client_id)
            .withParam("client_secret", client_secret)
            .withParam("refresh_token", ssoResult.getAsString("refresh_token"))
            .post();

        Entity aux = Entity.build("access_token", refreshResponse.getAsString("access_token"))
                        .with("expiration", Instant.now().plusSeconds(   ((Integer) refreshResponse.get("expires_in")).longValue()  ) );
        SSO_EXPIRATION.put(ssoUrl, aux );
        return "Bearer "+ refreshResponse.getAsString("access_token");
    }


    public SnamRestClient getFolderBySQL(){
        return rest( getPrecall(folder_precall_url, "getfolder", "getFolderBySQL"));
    }

    public SnamRestClient getDocumentBySQL(){
        return rest(getPrecall(document_read_precall_url, "getdoc", "getDocumentBySQL"));
    }

    public SnamRestClient createDocumentBySQL(){
        return rest(getPrecall(document_read_precall_url, "upsertdoc", "createDocument"));
    }



}
