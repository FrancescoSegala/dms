package it.eng.snam.summer.dmsmisuraservice.util;

import static it.eng.snam.summer.dmsmisuraservice.util.Utility.listOf;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.security.cert.X509Certificate;
import java.util.List;
import java.util.stream.Collectors;

import javax.net.ssl.SSLContext;

import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.TrustStrategy;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import it.eng.snam.summer.dmsmisuraservice.model.DDSDoc;

public class SnamRestClientImpl implements SnamRestClient {


    private Entity params = new Entity();
    private MediaType contentType;
    private HttpHeaders headers = new HttpHeaders();
    private String url = "";



    public SnamRestClient withContentType(MediaType ct) {
        contentType = ct;
        headers.setContentType(ct);
        return this;
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

        Entity aux = template()
                .exchange(url, HttpMethod.GET, new HttpEntity<>(params.toMultiValueMap(), headers), Entity.class)
                .getBody();

        return aux;
    }


    public List<Entity> getForList(){
       String aux = template()
                .exchange(url, HttpMethod.GET, new HttpEntity<>(params.toMultiValueMap(), headers), String.class)
                .getBody();

        return Entity.parseJsonAsList( aux) ;
    }

    public String getString() {

        String aux = template().getForObject(url, String.class, new HttpEntity<>(params.toMultiValueMap(), headers));

        return aux;
    }

    public String postMultipart() {
        Object body = MediaType.MULTIPART_FORM_DATA.equals(this.contentType) ? params.toMultiValueMap()
                : params.toString();
        template().setMessageConverters(listOf(new ByteArrayHttpMessageConverter()));
        ResponseEntity<byte[]> aux = template().postForEntity(url, new HttpEntity<>(body, headers), byte[].class);
        InputStream is = new ByteArrayInputStream(aux.getBody());
        return new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8)).lines()
                .collect(Collectors.joining("\n"));

    }

    public Entity post() {
        Object body = MediaType.APPLICATION_JSON.equals(this.contentType) ? params.toString()
                : params.toMultiValueMap();

        Entity aux = template().postForObject(url, new HttpEntity<>(body, headers), Entity.class);

        return aux;
    }

    public byte[] postForContent() {
        Object body = MediaType.APPLICATION_JSON.equals(this.contentType) ? params.toString()
                : params.toMultiValueMap();
        ResponseEntity<byte[]> aux = template().postForEntity(url, new HttpEntity<>(body, headers), byte[].class);
        return aux.getBody();
    }

    public String postForString() {
        Object body = MediaType.APPLICATION_JSON.equals(this.contentType) ? params.toString()
                : params.toMultiValueMap();

        String aux = template().postForObject(url, new HttpEntity<>(body, headers), String.class);

        return aux;
    }

    public List<DDSDoc> postForDocs() {
        Object body = MediaType.APPLICATION_JSON.equals(this.contentType) ? params.toString()
                : params.toMultiValueMap();

        String aux = template().postForObject(url, new HttpEntity<>(body, headers), String.class);

        return Entity.parseJsonAsListOfDDSDocs(aux);
    }

    public List<Entity> postForList() {
        Object body = MediaType.APPLICATION_JSON.equals(this.contentType) ? params.toString()
                : params.toMultiValueMap();

        String aux = template().postForObject(url, new HttpEntity<>(body, headers), String.class);

        return Entity.parseJsonAsList(aux);
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
