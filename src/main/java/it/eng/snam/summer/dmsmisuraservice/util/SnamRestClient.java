package it.eng.snam.summer.dmsmisuraservice.util;

import java.util.List;

import org.springframework.http.MediaType;

import it.eng.snam.summer.dmsmisuraservice.model.DDSDoc;

public interface SnamRestClient {

    public SnamRestClient withHeader(String k, String v);

    public SnamRestClient withParam(String k, Object v);

    public SnamRestClient withURL(String url);

    public SnamRestClient withContentType(MediaType ct);

    public Entity get();

    public List<Entity> getForList();

    public String getString();

    public String postMultipart();

    public Entity post();

    public byte[] postForContent();

    public String postForString();

    public List<DDSDoc> postForDocs();

    public List<Entity> postForList();
}
