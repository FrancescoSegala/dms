package it.eng.snam.summer.dmsmisuraservice.util;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

public class Entity extends HashMap<String, Object> {

    private static final long serialVersionUID = 1L;

    public String id() {
        return this.getAsString("id");
    }

    public String id(String v) {
        this.put("id", v);
        return id();
    }

    public static Entity build(MultiValueMap<String, Object> p) {
        Entity result = new Entity();
        p.keySet().stream().forEach(k -> result.put(k, p.get(k).size() > 1 ? p.get(k) : p.getFirst(k)));
        return result;
    }

    public static Entity build(String k , Object v){
        return new Entity().with(k, v);
    }

    public static Entity build(Map<String, Object> o) {
        return new Entity(o);
    }

    public Entity(Map<String, Object> o) {
        super(o);
    }

    public Entity() {
        super();
    }


    public void inTo(MultiValueMap<String, Object> dest) {
        dest.setAll(this.entrySet().stream().collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue())));
    }

    public boolean isNull(String k) {
        return this.get(k) == null;
    }

    public boolean isSet(String k) {
        return this.get(k) != null;
    }


    public String getAsString(String k) {
        return (String) this.get(k);
    }


    public <T> List<T> getAsList(String k ){
        if (! this.containsKey(k)){
            return Collections.emptyList();
        }
        return (List<T>) this.get(k);
    }

    public List<String> getAsListString(String k){
        return this.getAsList(k);
    }


    public Entity with(String k, Object v) {
        this.put(k, v);
        this.types.put(k, "varchar");
        return this;
    }

    public Entity with(String k, Object v, String type) {
        return this.with(k, v).withType(k, type);
    }

    public Entity getAsEntity(String k ){
        return new Entity( (Map<String, Object>) this.get(k));
    }


    private Map<String, String> types = new HashMap<>();

    public Map<String, String> types() {
        return this.types;
    }

    public Entity withType(String k, String type) {
        this.types.put(k, type);
        return this;
    }

    public String getType(String k) {
        return types().getOrDefault(k, "varchar");
    }


    public Map<String, String> toMap(){
        return this.keySet()
            .stream()
            .collect( Collectors.toMap(e -> e, e -> this.getAsString(e) ));
    }

    public MultiValueMap<String, Object> toMultiValueMap(){
        MultiValueMap<String, Object> res = new LinkedMultiValueMap<>();
        this.keySet().forEach(
            k -> res.add(k, this.get(k))
        );
        return res ;
    }

    public static Entity parseJson(String input) {
		ObjectMapper om = new ObjectMapper();
		try {
			return om.readValue(input, Entity.class);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}


    public static List<Entity> parseJsonAsList(String input){
        ObjectMapper om = new ObjectMapper();
        try {
            return om.readValue(input , new TypeReference<List<Entity>>(){}) ;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


	public String stringfy() {
		ObjectMapper om = new ObjectMapper();
		try {
			return om.writeValueAsString(this);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}


    @Override
    public String toString() {
        return this.stringfy();
    }

}
