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

    private Entity entity = new Entity();
    private HttpHeaders headers = new HttpHeaders();
    private String url = "";

    public SnamRestClient withContentType(MediaType ct) {
        headers.setContentType(ct);
        return this;
    }


    public SnamRestClient(String url) {
        this.withURL(url)
        .withContentType(MediaType.APPLICATION_JSON);
    }


    public SnamRestClient( DDSPrecall precall ) {
        this(precall.url);
        this.headers.set("Authorization", "Bearer "+ precall.accessToken );
    }

    public SnamRestClient withHeader(String k, String v) {
        headers.set(k, v);
        return this;
    }

    public SnamRestClient withParam(String k, Object v) {
        entity.put(k, v);
        return this;
    }

    public SnamRestClient withURL(String url) {
        this.url = url;
        return this;
    }


    public Entity get(){
        Entity aux = template.getForObject(url, Entity.class);
        System.out.println(aux);
        return aux;
    }

    public Entity post() {
        System.out.println("Entity "+ entity);
        Entity aux =  template.postForObject(url, new HttpEntity<>(entity.toMultiValueMap(), headers), Entity.class);
        System.out.println(aux);
        return aux;
    }

    public List<Entity> postForList(){
        List<Entity> aux = (List<Entity>) template.postForObject(url, new HttpEntity<>(entity.toString(), headers), List.class);
        System.out.println(aux);
        return aux;
    }

}
