package it.eng.snam.summer.dmsmisuraservice.model;

import java.util.List;
public class Subfolder   {


    public String id;
    public String description;
    public List<Info> attributes ;
    public String folder;

    public Subfolder(String id, String description, String folder, List<Info> attributes ) {
        this.id = id ;
        this.description = description;
        this.folder = folder ;
        this.attributes = attributes;
    }

}
