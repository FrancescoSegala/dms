package it.eng.snam.summer.dmsmisuraservice.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import it.eng.snam.summer.dmsmisuraservice.util.Entity;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DDSDoc {


    public String _id;
    public String schemaId;
    public Entity systemAttributes;
    public Entity versions;
    public List<String> customSecurityTemplateId;
    public List<Entity> contents;
    public List<Entity> customAttributes;
    public List<Entity> customPermission;




}
