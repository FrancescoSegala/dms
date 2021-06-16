package it.eng.snam.summer.dmsmisuraservice.model.search;

import javax.validation.constraints.Pattern;


import io.swagger.annotations.ApiModelProperty;

public class DocumentSearch extends Pagination{


    @ApiModelProperty("the id of the 1st level folder")
    private  String folder;

    @ApiModelProperty("the id of the 2nd level folder")
    private  String subfolder;

    @ApiModelProperty("the time of publishing ")
    @Pattern(regexp = "(\\d{4})-(\\d{2})-(\\d{2})T(\\d{2}):(\\d{2})\\:(\\d{2})(.)*[\\d]*Z",
         message = "date format not valid (shoud be of type yyyy-mm-ddTHH:MM:ss)")
    private  String published_at;

    @ApiModelProperty("the remi")
    @Pattern(regexp = "^[A-Za-z0-9]+[-_A-Za-z0-9]*$")
    private  String remi ;

    @ApiModelProperty("the list of 'linea' to include the research")
    //@Pattern(regexp = "^[A-Za-z0-9]+[-_A-Za-z0-9]*$")
    private  String[] linea_in;



    public String[] getLinea_in() {
        return linea_in;
    }
    public void setLinea_in(String[] linea_in) {
        this.linea_in = linea_in;
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
    public String getPublished_at() {
        return published_at;
    }
    public void setPublished_at(String published_at) {
        this.published_at = published_at;
    }
    public String getRemi() {
        return remi;
    }
    public void setRemi(String remi) {
        this.remi = remi;
    }



}
