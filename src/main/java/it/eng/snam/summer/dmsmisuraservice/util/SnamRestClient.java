package it.eng.snam.summer.dmsmisuraservice.util;

import static it.eng.snam.summer.dmsmisuraservice.util.Utility.listOf;

import java.security.cert.X509Certificate;
import java.util.List;

import javax.net.ssl.SSLContext;

import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.TrustStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import it.eng.snam.summer.dmsmisuraservice.model.DDSDoc;

public class SnamRestClient {

    // @Autowired
    // AuditValues audit ;

    // @Value("${external.dds.OS}")
    public boolean rest_client_headers = false ;

    // @Value("${francesco.rest_client.response}")
    private boolean response_debug = true ;

    // @Value("${francesco.rest_client.request}")
    private boolean request_debug  = true ;

    private Entity params = new Entity();
    private MediaType contentType;
    private HttpHeaders headers = new HttpHeaders();
    private String url = "";

    public static SnamRestClient rest(String url) {
        return new SnamRestClient(url);
    }

    public static SnamRestClient rest(Precall precall) {
        return new SnamRestClient(precall);
    }

    public SnamRestClient withContentType(MediaType ct) {
        contentType = ct;
        headers.setContentType(ct);
        return this;
    }

    private SnamRestClient(String url) {
        this.withURL(url).withContentType(MediaType.APPLICATION_JSON);
    }

    private SnamRestClient(Precall precall) {
        this(precall.url);
        this.headers.set("Authorization", precall.accessToken);
    }

    public SnamRestClient withHeader(String k, String v) {
        headers.set(k, v);
        return this;
    }

    public SnamRestClient withParam(String k, Object v) {
        params.put(k, v);
        return this;
    }

    public SnamRestClient withURL(String url) {
        this.url = url;
        return this;
    }

    public Entity get() {
        printRequest();
        Entity aux = template()
                .exchange(url, HttpMethod.GET, new HttpEntity<>(params.toMultiValueMap(), headers), Entity.class)
                .getBody();
        printResponse(aux);
        return aux;
    }

    public String getString() {
        printRequest();
        String aux = template().getForObject(url, String.class, new HttpEntity<>(params.toMultiValueMap(), headers));
        printResponse(aux);
        return aux;
    }

    // public ResponseEntity<byte[]> postMultipartForm() {
    //     MultiValueMap<String, Object> multipartRequest = new LinkedMultiValueMap<>();
    //     multipartRequest.set("file",params.get("file"));

    //     HttpHeaders requestHeadersJSON = new HttpHeaders();
    //     requestHeadersJSON.setContentType(MediaType.APPLICATION_JSON);
    //     HttpEntity<Entity> requestEntityJSON = new HttpEntity<>(params.getAsEntity("document"), requestHeadersJSON);
    //     multipartRequest.set("document",requestEntityJSON);

    //     HttpEntity<MultiValueMap<String,Object>> requestEntity = new HttpEntity<>(multipartRequest,headers);

    //     ResponseEntity<byte[]> aux = template().postForEntity(url, requestEntity, byte[].class);
    //     if (aux.getStatusCode().equals(HttpStatus.OK)) {
    //         return aux;
    //     }
    //     return null;
    // }

    public ResponseEntity<byte[]> postMultipart() {
        Object body = MediaType.MULTIPART_FORM_DATA.equals(this.contentType) ? params.toMultiValueMap()
                : params.toString();
        template().setMessageConverters(listOf(new ByteArrayHttpMessageConverter()));

        ResponseEntity<byte[]> aux = template().postForEntity(url, new HttpEntity<>(body, headers), byte[].class);
        if (aux.getStatusCode().equals(HttpStatus.OK)) {
            return aux;
        }
        return null;
    }

    public Entity post() {
        Object body = MediaType.APPLICATION_JSON.equals(this.contentType) ? params.toString()
                : params.toMultiValueMap();
        printRequest();
        Entity aux = template().postForObject(url, new HttpEntity<>(body, headers), Entity.class);
        printResponse(aux);
        return aux;
    }


    public byte[] postForContent(){
        Object body = MediaType.APPLICATION_JSON.equals(this.contentType) ? params.toString()
                : params.toMultiValueMap();
        ResponseEntity<byte[]> aux = template().postForEntity(url, new HttpEntity<>(body, headers), byte[].class);
        return aux.getBody();
    }


    public String postForString() {
        Object body = MediaType.APPLICATION_JSON.equals(this.contentType) ? params.toString()
                : params.toMultiValueMap();
        printRequest();
        String aux = template().postForObject(url, new HttpEntity<>(body, headers), String.class);
        printResponse(aux);
        return aux;
    }

    public List<DDSDoc> postForDocs() {
        Object body = MediaType.APPLICATION_JSON.equals(this.contentType) ? params.toString()
                : params.toMultiValueMap();
        printRequest();
        String aux = template().postForObject(url, new HttpEntity<>(body, headers), String.class);
        printResponse(aux);
        return Entity.parseJsonAsListOfDDSDocs(aux);
    }

    public List<Entity> postForList() {
        Object body = MediaType.APPLICATION_JSON.equals(this.contentType) ? params.toString()
                : params.toMultiValueMap();
        printRequest();
        String aux = template().postForObject(url, new HttpEntity<>(body, headers), String.class);
        printResponse(aux);
        return Entity.parseJsonAsList(aux);
    }


    private void printRequest() {
        String method = "";
        try {
            throw new RuntimeException();
        } catch (RuntimeException e) {
            method = e.getStackTrace()[1].getMethodName();
        }
        if (request_debug)
            System.out.printf("[SNAM-REST-CLIENT][%s] - url %s \n", method, this.url);
        if (rest_client_headers )
            System.out.printf("[SNAM-REST-CLIENT][%s] - headers %s \n", method, this.headers);
        if (request_debug)
            System.out.printf("[SNAM-REST-CLIENT][%s] - params %s \n", method, this.params);
    }

    private void printResponse(Object response) {
        String method = "";
        try {
            throw new RuntimeException();
        } catch (RuntimeException e) {
            method = e.getStackTrace()[1].getMethodName();
        }
        if (response_debug)
            System.out.printf("[SNAM-REST-CLIENT][%s] - response %s \n", method, response);
    }

    public RestTemplate SSLtemplate() {
        return new RestTemplate();
    }

    public RestTemplate template() {
        TrustStrategy acceptingTrustStrategy = (X509Certificate[] chain, String authType) -> true;
        try {
            SSLContext sslContext = org.apache.http.ssl.SSLContexts.custom()
                    .loadTrustMaterial(null, acceptingTrustStrategy).build();
            SSLConnectionSocketFactory csf = new SSLConnectionSocketFactory(sslContext);
            CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(csf).build();
            HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
            requestFactory.setHttpClient(httpClient);
            RestTemplate restTemplate = new RestTemplate(requestFactory);
            return restTemplate;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

}
