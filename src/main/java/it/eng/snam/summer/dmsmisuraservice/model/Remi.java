package it.eng.snam.summer.dmsmisuraservice.model;

public class Remi {


    private String id ;
    private String description ;
    private String linea ;

    public Remi withId(String id ){
        this.id = id ;
        return this;
    }

    public Remi withDescription( String description ) {
        this.description = description ;
         return this;
    }


    public Remi withLinea( String linea ) {
        this.linea = linea ;
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

    public String getLinea() {
        return linea;
    }

    public void setLinea(String linea) {
        this.linea = linea;
    }





}
