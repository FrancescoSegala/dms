package it.eng.snam.summer.dmsmisuraservice.util;

import java.security.cert.X509Certificate;
import java.util.List;

import javax.net.ssl.SSLContext;

import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.TrustStrategy;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

public class SnamRestClient {

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

    public Entity post() {
        Object body = MediaType.APPLICATION_JSON.equals(this.contentType) ? params.toString()
                : params.toMultiValueMap();
        printRequest();
        Entity aux = template().postForObject(url, new HttpEntity<>(body, headers), Entity.class);
        printResponse(aux);
        return aux;
    }

    public String postForString() {
        Object body = MediaType.APPLICATION_JSON.equals(this.contentType) ? params.toString()
                : params.toMultiValueMap();
        printRequest();
        String aux = template().postForObject(url, new HttpEntity<>(body, headers), String.class);
        printResponse(aux);
        return aux;
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
         //System.out.printf("[SNAM-REST-CLIENT][%s] - url %s \n", method, this.url);
        //  System.out.printf("[SNAM-REST-CLIENT][%s] - headers %s \n", method,
        //  this.headers);
         //System.out.printf("[SNAM-REST-CLIENT][%s] - params %s \n", method,this.params);
    }

    private void printResponse(Object response) {
        String method = "";
        try {
            throw new RuntimeException();
        } catch (RuntimeException e) {
            method = e.getStackTrace()[1].getMethodName();
        }
         //System.out.printf("[SNAM-REST-CLIENT][%s] - response %s \n", method,response);
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
