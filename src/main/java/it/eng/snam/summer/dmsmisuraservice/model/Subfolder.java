package it.eng.snam.summer.dmsmisuraservice.model;

import java.util.List;
import java.util.Map;

public class Subfolder   {


    public String id;
    public String description;
    public List<Map <String, String>> attributes ;
    public String folder;

    public Subfolder(String id, String description, String folder, List<Map <String, String>> attributes ) {
        this.id = id ;
        this.description = description;
        this.folder = folder ;
        this.attributes = attributes;
    }

}
