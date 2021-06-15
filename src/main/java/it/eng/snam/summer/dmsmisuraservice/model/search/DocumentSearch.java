package it.eng.snam.summer.dmsmisuraservice.model.search;

import io.swagger.annotations.ApiModelProperty;

public class DocumentSearch extends Pagination{


    @ApiModelProperty("the id of the 1st level folder")
    private  String folder;

    @ApiModelProperty("the id of the 2nd level folder")
    private  String subfolder;

    @ApiModelProperty("the time of publishing ")
    private  String published_at;

    @ApiModelProperty("the remi")
    private  String remi ;

    @ApiModelProperty("the list of 'linea' to include the research")
    private  String linea_in;



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
    public String getLinea_in() {
        return linea_in;
    }
    public void setLinea_in(String linea_in) {
        this.linea_in = linea_in;
    }


}
