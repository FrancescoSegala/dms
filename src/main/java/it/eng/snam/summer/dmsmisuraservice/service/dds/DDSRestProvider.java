package it.eng.snam.summer.dmsmisuraservice.service.dds;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import static it.eng.snam.summer.dmsmisuraservice.util.Precall.precall;

import it.eng.snam.summer.dmsmisuraservice.util.Precall;
import it.eng.snam.summer.dmsmisuraservice.util.RestClientBuilder;
import it.eng.snam.summer.dmsmisuraservice.aspect.ExecutionTime;
import it.eng.snam.summer.dmsmisuraservice.util.CachedFunction;
import it.eng.snam.summer.dmsmisuraservice.util.Entity;
import it.eng.snam.summer.dmsmisuraservice.util.SnamRestClient;

@Component
public class DDSRestProvider {

    @Value("${external.dds.folder_precall_url}")
    private String folder_precall_url;
    @Value("${external.dds.document_read_precall_url}")
    private String document_read_precall_url;
    @Value("${external.dds.document_write_precall_url}")
    private String document_write_precall_url;
    @Value("${external.dds.client_id}")
    private String client_id;
    @Value("${external.dds.client_secret}")
    private String client_secret;
    @Value("${external.dds.cache_expire}")
    private Long cache_expire;

    @Autowired
    RestClientBuilder rest;

    private static Map<String, Entity> SSO_EXPIRATION = new HashMap<>();
    // private static CachedFunction<String, Entity> CACHED_PRECALL = new
    // CachedFunction<>(1000000L,precallUrl -> rest.build(precallUrl).get());

    public Precall getPrecall(String precallUrl, String fn, String method) {

        Long start = Instant.now().toEpochMilli();
        try {
            Entity precall = rest.build(precallUrl).get();
            // Entity precall = CACHED_PRECALL.apply(precallUrl);
            // Entity precall = rest(precallUrl).get();
            return precall().withUrl(precall.getAsString(fn) + "/" + method)
                    .withAccessToken(accessToken(precall.getAsString("SSOUrl")));
        } finally {
            Long end = Instant.now().toEpochMilli();
            System.out.printf("Precall : %s %s %s - execution time %d ms \n ", precallUrl, fn, method, (end - start));
        }

    }

    private boolean isAccessTokenExpired(String ssoUrl) {
        return (!SSO_EXPIRATION.containsKey(ssoUrl))
                || (Instant.now().isAfter((Instant) SSO_EXPIRATION.get(ssoUrl).get("expiration")));
    }

    private String accessToken(String ssoUrl) {
        if (!isAccessTokenExpired(ssoUrl)) {
            return SSO_EXPIRATION.get(ssoUrl).getAsString("access_token");
        }
        Entity ssoResult = rest.build(ssoUrl).withContentType(MediaType.APPLICATION_FORM_URLENCODED)
                .withParam("grant_type", "client_credentials").withParam("client_id", client_id)
                .withParam("client_secret", client_secret).post();
        Entity refreshResponse = rest.build(ssoUrl).withContentType(MediaType.APPLICATION_FORM_URLENCODED)
                .withParam("grant_type", "refresh_token").withParam("client_id", client_id)
                .withParam("client_secret", client_secret)
                .withParam("refresh_token", ssoResult.getAsString("refresh_token")).post();

        Entity aux = Entity.build("access_token", "Bearer " + refreshResponse.getAsString("access_token")).with(
                "expiration", Instant.now().plusSeconds(((Integer) refreshResponse.get("expires_in")).longValue()));
        SSO_EXPIRATION.put(ssoUrl, aux);
        return "Bearer " + refreshResponse.getAsString("access_token");
    }

    @ExecutionTime
    public SnamRestClient getFolderBySQL() {
        return rest.build(getPrecall(folder_precall_url, "getfolder", "getFolderBySQL")).withHeader("OAM_REMOTE_USER",
                "user1");
    }

    @ExecutionTime
    public SnamRestClient createFolder() {
        return rest.build(getPrecall(folder_precall_url, "upsertfolder", "createFolders")).withHeader("OAM_REMOTE_USER",
                "user1");
    }

    @ExecutionTime
    public SnamRestClient updateFolder() {
        return rest.build(getPrecall(folder_precall_url, "upsertfolder", "setFoldersSystem"))
                .withHeader("OAM_REMOTE_USER", "user1");
    }

    @ExecutionTime
    public SnamRestClient deleteFolder() {
        return rest.build(getPrecall(folder_precall_url, "deletefolder", "deleteFolder")).withHeader("OAM_REMOTE_USER",
                "user1");
    }

    @ExecutionTime
    public SnamRestClient getDocumentBySQL() {
        return rest.build(getPrecall(document_read_precall_url, "getdoc", "getDocumentBySQL"))
                .withHeader("OAM_REMOTE_USER", "user1");
    }

    @ExecutionTime
    public SnamRestClient createDocument() {
        return rest.build(getPrecall(document_write_precall_url, "upsertdoc", "streaming/insertDocument"))
                .withHeader("OAM_REMOTE_USER", "user1").withContentType(MediaType.MULTIPART_FORM_DATA);
    }

    @ExecutionTime
    public SnamRestClient updateDocument() {
        return rest.build(getPrecall(document_write_precall_url, "upsertdoc", "setDocumentSystem"))
                .withHeader("OAM_REMOTE_USER", "user1");
    }

    @ExecutionTime
    public SnamRestClient logicalDeleteDocument() {
        return rest.build(getPrecall(document_write_precall_url, "deletedoc", "logicalDeleteDocument"))
                .withHeader("OAM_REMOTE_USER", "user1");
    }


    @ExecutionTime
    public SnamRestClient getDocumentContent() {
        return rest.build(getPrecall(document_read_precall_url, "getdoc", "getContentElements"))
                .withHeader("OAM_REMOTE_USER", "user1");
    }
}
