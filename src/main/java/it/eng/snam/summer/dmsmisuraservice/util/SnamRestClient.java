package it.eng.snam.summer.dmsmisuraservice.util;

import java.io.ByteArrayInputStream;
import java.security.cert.X509Certificate;
import java.util.List;

import javax.annotation.Resource;
import javax.net.ssl.SSLContext;
import static it.eng.snam.summer.dmsmisuraservice.util.Utility.*;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.TrustStrategy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

public class SnamRestClient {

    @Value("${external.audit.rest_client_headers}")
    private boolean headers_debug = true;

    @Value("${external.audit.rest_client_response}")
    private boolean response_debug = true;

    @Value("${external.summer.rest_client_request}")
    private boolean request_debug = true;

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


    public ResponseEntity<byte[]>  postMultipart(){
        Object body = MediaType.MULTIPART_FORM_DATA.equals(this.contentType) ?  params.toMultiValueMap() : params.toString();
        System.out.println("body");
        System.out.println(body);
        template().setMessageConverters(listOf(new ByteArrayHttpMessageConverter()));
        ResponseEntity<byte[]>  aux = template().postForEntity(url, new HttpEntity<>(body, headers), byte[].class) ;
        System.out.println("chiamata finita "+ aux.getStatusCode());
        if(aux.getStatusCode().equals(HttpStatus.OK))
            return aux ;
        return null ;
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
        if (request_debug)
            System.out.printf("[SNAM-REST-CLIENT][%s] - url %s \n", method, this.url);
        if (headers_debug)
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
        //TODO perche non funziona ?
        // System.out.println("debugRequest");
        // System.out.println(request_debug);
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
