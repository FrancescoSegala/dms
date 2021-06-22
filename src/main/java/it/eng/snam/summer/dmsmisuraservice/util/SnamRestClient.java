package it.eng.snam.summer.dmsmisuraservice.util;

import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

import it.eng.snam.summer.dmsmisuraservice.service.DDSPrecall;


public class SnamRestClient {

    private RestOperations template = new RestTemplate();

    private Entity params = new Entity();
    private HttpHeaders headers = new HttpHeaders();
    private String url = "";


    public static SnamRestClient rest(String url){
        return new SnamRestClient(url);
    }

    public static SnamRestClient rest( DDSPrecall precall ){
        return new SnamRestClient(precall);
    }

    public SnamRestClient withContentType(MediaType ct) {
        headers.setContentType(ct);
        return this;
    }



    private SnamRestClient(String url) {
        this.withURL(url)
        .withContentType(MediaType.APPLICATION_JSON);
    }


    private SnamRestClient( DDSPrecall precall ) {
        this(precall.url);
        this.headers.set("Authorization", "Bearer "+ precall.accessToken );
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


    public Entity get(){
        System.out.println("[SNAM-REST-CLIENT][GET] - url:" + url);
        System.out.println("[SNAM-REST-CLIENT][GET] - params:" + params);
        Entity aux = template.getForObject(url, Entity.class);
        System.out.println("[SNAM-REST-CLIENT][GET] - response:"+aux);
        return aux;
    }

    public Entity post() {
        System.out.println("[SNAM-REST-CLIENT][POST] - url:" + url);
        System.out.println("[SNAM-REST-CLIENT][POST] - params:" + params);
        Entity aux =  template.postForObject(url, new HttpEntity<>(params.toMultiValueMap(), headers), Entity.class);
        System.out.println("[SNAM-REST-CLIENT][POST] - response:" + aux);
        return aux;
    }

    public List<Entity> postForList(){
        System.out.println("[SNAM-REST-CLIENT][POSTFORLIST] - url:" + url);
        System.out.println("[SNAM-REST-CLIENT][POSTFORLIST] - params:" + params);
        List<Entity> aux = (List<Entity>) template.postForObject(url, new HttpEntity<>(params.toString(), headers), List.class);
        System.out.println("[SNAM-REST-CLIENT][POSTFORLIST] - response:"+ aux);
        return aux;
    }

}
