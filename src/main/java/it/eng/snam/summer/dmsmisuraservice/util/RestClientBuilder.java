package it.eng.snam.summer.dmsmisuraservice.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import it.eng.snam.summer.dmsmisuraservice.util.fake.FakeSnamRestClient;

@Component
public class RestClientBuilder {

    @Value("${external.dds.fake:false}")
    boolean fake ;

    public boolean isFake(){
        return fake;
    }
    //TODO gestione url fake client

    public SnamRestClient build(String url) {
        return fake ? new FakeSnamRestClient() : new SnamRestClientImpl().withURL(url).withContentType(MediaType.APPLICATION_JSON);
    }

    public SnamRestClient build(Precall precall) {
       return fake ? new FakeSnamRestClient() : new SnamRestClientImpl().withURL(precall.url).withHeader("Authorization", precall.accessToken);
    }

}
