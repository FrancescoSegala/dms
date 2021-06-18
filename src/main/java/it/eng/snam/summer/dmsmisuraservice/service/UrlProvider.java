package it.eng.snam.summer.dmsmisuraservice.service;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import it.eng.snam.summer.dmsmisuraservice.util.Entity;
import it.eng.snam.summer.dmsmisuraservice.util.SnamRestClient;

@Component
public class UrlProvider {


    private static String FOLDER_PRECALL_URL = "https://dds-folder-t.snam.it/precall";
    private static String DOCUMENT_GET_PRECALL_URL = "https://dds-doc-read-t.snam.it/precall";
    private static String CLIENT_ID = "1550tdmi";
    private static String CLIENT_SECRET = "4eb4ca2b54bfc5fa7f7c539afe950637";
    private static Map<String, Entity> SSO_EXPIRATION = new HashMap<>() ;

    private DDSPrecall getPrecall(String precallUrl , String fn , String method ){
        Entity precall = new SnamRestClient(precallUrl).get();
        DDSPrecall result = new DDSPrecall();
        result.url = precall.getAsString(fn) + "/"+ method;
        result.accessToken =  accessToken(precall.getAsString("SSOUrl"));
        return result;
    }


    private boolean isAccessTokenExpired(String ssoUrl){
        if (! SSO_EXPIRATION.containsKey(ssoUrl) ){
            return true;
        }
        if( Instant.now().isAfter((Instant) SSO_EXPIRATION.get(ssoUrl).get("expiration")) ){
            return true;
        }
        return false;
    }


    private String accessToken(String ssoUrl ){
        if ( ! isAccessTokenExpired(ssoUrl) ){
            return SSO_EXPIRATION.get(ssoUrl).getAsString("access_token");
        }
        Entity ssoResult = new SnamRestClient(ssoUrl)
            .withContentType(MediaType.APPLICATION_FORM_URLENCODED)
            .withParam("grant_type", "client_credentials")
            .withParam("client_id", CLIENT_ID)
            .withParam("client_secret", CLIENT_SECRET)
            .post();
        Entity refreshResponse = new SnamRestClient(ssoUrl)
            .withContentType(MediaType.APPLICATION_FORM_URLENCODED)
            .withParam("grant_type", "refresh_token")
            .withParam("client_id", CLIENT_ID)
            .withParam("client_secret", CLIENT_SECRET)
            .withParam("refresh_token", ssoResult.getAsString("refresh_token"))
            .post();

        Entity aux = Entity.build("access_token", refreshResponse.getAsString("access_token"))
                        .with("expiration", Instant.now().plusSeconds(   ((Integer) refreshResponse.get("expires_in")).longValue()  ) );
        SSO_EXPIRATION.put(ssoUrl, aux );
        return refreshResponse.getAsString("access_token");
    }

    public DDSPrecall getFolderBySQL(){
        return getPrecall(FOLDER_PRECALL_URL, "getfolder", "getFolderBySQL");
    }


    public DDSPrecall getDocumentBySQL(){
        return getPrecall(DOCUMENT_GET_PRECALL_URL, "getdoc", "getDocumentBySQL");
    }



}
