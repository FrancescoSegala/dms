package it.eng.snam.summer.dmsmisuraservice.model;

public class DocumentSQL {


    public String id ;
    public String data ;
    public String c_remi_ass ;
    public String status ;
    public String folder ;
    public String subfolder ;
    public String linea ;


    public DocumentSQL withId( String id ) {
        this.id = id ;
         return this;
    }

    public DocumentSQL withData( String  data ) {
        this.data = data ;
         return this;
    }


    public DocumentSQL withRemi( String  c_remi_ass ) {
        this.c_remi_ass = c_remi_ass ;
         return this;
    }


    public DocumentSQL withStatus(   String status ) {
        this.status = status ;
         return this;
    }


    public DocumentSQL withFolder( String folder ) {
        this.folder = folder ;
         return this;
    }

    public DocumentSQL withSubfolder( String subfolder ) {
        this.subfolder = subfolder ;
         return this;
    }

    public DocumentSQL withLinea( String linea ) {
        this.linea = linea ;
         return this;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getData() {
        return data;
    }
    public void setData(String data) {
        this.data = data;
    }
    public String getC_remi_ass() {
        return c_remi_ass;
    }
    public void setC_remi_ass(String c_remi_ass) {
        this.c_remi_ass = c_remi_ass;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getFolder() {
        return folder;
    }
    public void setFolder(String folder) {
        this.folder = folder;
    }
    public String getSubfolder() {
        return subfolder;
    }
    public void setSubfolder(String subfolder) {
        this.subfolder = subfolder;
    }
    public String getLinea() {
        return linea;
    }
    public void setLinea(String linea) {
        this.linea = linea;
    }



}
