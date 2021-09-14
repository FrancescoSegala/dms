package it.eng.snam.summer.dmsmisuraservice.util.fake;

import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import it.eng.snam.summer.dmsmisuraservice.model.DDSDoc;
import it.eng.snam.summer.dmsmisuraservice.util.Entity;
import it.eng.snam.summer.dmsmisuraservice.util.SnamRestClient;

public class FakeSnamRestClient implements SnamRestClient {


    private Entity params = new Entity();
    private MediaType contentType;
    private HttpHeaders headers = new HttpHeaders();
    private String url = "";


    @Override
    public SnamRestClient withHeader(String k, String v) {
        this.headers.add(k, v);
        return this;
    }

    @Override
    public SnamRestClient withParam(String k, Object v) {

        return null;
    }

    @Override
    public SnamRestClient withURL(String url) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public SnamRestClient withContentType(MediaType ct) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Entity get() {
        //TODO vai nel fake datasource e fai la get con i parametri
        Entity e = new Entity().with("id","1");
        return e;
    }

    @Override
    public String getString() {
        //TODO vai nel fake datasource e fai la get con i parametri
        return null;
    }

    @Override
    public String postMultipart() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Entity post() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public byte[] postForContent() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String postForString() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<DDSDoc> postForDocs() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Entity> postForList() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Entity> getForList() {
        // TODO Auto-generated method stub
        return null;
    }







}
