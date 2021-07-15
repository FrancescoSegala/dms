package it.eng.snam.summer.dmsmisuraservice.model;

public class Folder {


    public String id;
    public String description;


    public Folder(String id, String description) {
        this.id = id;
        this.description = description;
    }

    public Folder withId(String v ){
        this.id = v ;
        return this;
    }

    public Folder withDescription(String desc ){
        this.description = desc;
        return this;
    }

    public Folder() {
    }



}
