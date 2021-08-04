package it.eng.snam.summer.dmsmisuraservice.controller;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import it.eng.snam.summer.dmsmisuraservice.util.Entity;

public class ParsingResponseTest {


    @Test
    public void parse() throws IOException, URISyntaxException, ScriptException, NoSuchMethodException{
        byte[] sse = Files.readAllBytes(Paths.get(this.getClass().getResource("/SSEresponse_OK.txt").toURI())) ;
        String response = new String(sse , Charset.forName("UTF-8"));
        System.out.println(response);
        // String parser = Files.readAllLines(Paths.get(this.getClass().getResource("/SSEparser.js").toURI())).stream().collect(Collectors.joining("\n")) ;
        // ScriptEngine engine = new ScriptEngineManager().getEngineByName("graal.js");
        // String res = (String) engine.eval(parser + "JSON.stringify( parse.parse(`"+response+"`) )");
        // List<Entity> list =   new ObjectMapper().readValue(res, new TypeReference<List<Entity>>(){});
        List<Entity> list = parseResponse(response);
        System.out.println(list);
        System.out.println(((Entity)list.get(list.size()-1)).getAsString("value"));
    }


    private List<Entity> parseResponse(String response ){
        String parser;
        try {
            parser = Files.readAllLines(Paths.get(this.getClass().getResource("/SSEparser.js").toURI())).stream().collect(Collectors.joining("\n"));
        } catch (IOException | URISyntaxException e1) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e1.getMessage());
        }
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("graal.js");
        String res;
        try {
            res = (String) engine.eval(parser + "JSON.stringify( parse.parse(`"+response+"`) )");
        } catch (ScriptException e1) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e1.getMessage());
        }
        try {
            return  new ObjectMapper().readValue(res, new TypeReference<List<Entity>>(){});
        } catch (JsonProcessingException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }


}
