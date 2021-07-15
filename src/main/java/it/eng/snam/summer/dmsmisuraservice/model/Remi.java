package it.eng.snam.summer.dmsmisuraservice.model;

public class Remi {


    private String id ;
    private String description ;

    public Remi withId(String id ){
        this.id = id ;
        return this;
    }

    public Remi withDescription( String description ) {
        this.description = description ;
         return this;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }




}
