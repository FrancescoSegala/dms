package it.eng.snam.summer.dmsmisuraservice.model.search;


import javax.validation.constraints.Pattern;

import io.swagger.annotations.ApiModelProperty;

public class DocumentSearch extends Pagination {

    @ApiModelProperty("the id of the 1st level folder")
    public String folder;

    @ApiModelProperty("the id of the 2nd level folder")
    public String subfolder;

    @ApiModelProperty("the time of publishing ")
    @Pattern(regexp = "(\\d{4})-(\\d{2})-(\\d{2})T(\\d{2}):(\\d{2})\\:(\\d{2})(.)*[\\d]*Z", message = "date format not valid (should be of type yyyy-mm-ddTHH:MM:ss)")
    public String published_at_ge;

    @ApiModelProperty("the time of publishing ")
    @Pattern(regexp = "(\\d{4})-(\\d{2})-(\\d{2})T(\\d{2}):(\\d{2})\\:(\\d{2})(.)*[\\d]*Z", message = "date format not valid (should be of type yyyy-mm-ddTHH:MM:ss)")
    public String published_at_le;

    @ApiModelProperty("the remi")
    @Pattern(regexp = "^[A-Za-z0-9]+[-_A-Za-z0-9]*$")
    public String remi;


    public String ragione_sociale_like ;

    @ApiModelProperty("the list of 'linea' to include the research")
    // @Pattern(regexp = "^[A-Za-z0-9]+[-_A-Za-z0-9]*$")
    public String[] linea_in;

    @ApiModelProperty("the list of 'remi' to include the research")
    public String[] remi_in;

    @ApiModelProperty("The province of the document")
    public String province ;



    public DocumentSearch withProvince(  String province ) {
        this.province = province ;
         return this;
    }

    public DocumentSearch withFolder(String folder) {
        this.folder = folder;
        return this;
    }

    public DocumentSearch withSubfolder(String subfolder) {
        this.subfolder = subfolder;
        return this;
    }



    public String getRagione_sociale_like() {
        return ragione_sociale_like;
    }

    public void setRagione_sociale_like(String ragione_sociale_like) {
        this.ragione_sociale_like = ragione_sociale_like;
    }

    public DocumentSearch withRemi(String remi) {
        this.remi = remi;
        return this;
    }

    public DocumentSearch withLinea(String[] linea) {
        this.linea_in = linea;
        return this;
    }

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



    public String getPublished_at_ge() {
        return published_at_ge;
    }

    public void setPublished_at_ge(String published_at_ge) {
        this.published_at_ge = published_at_ge;
    }

    public String getPublished_at_le() {
        return published_at_le;
    }

    public void setPublished_at_le(String published_at_le) {
        this.published_at_le = published_at_le;
    }

    public String getRemi() {
        return remi;
    }

    public void setRemi(String remi) {
        this.remi = remi;
    }

    public String[] getRemi_in() {
        return remi_in;
    }

    public void setRemi_in(String[] remi_in) {
        this.remi_in = remi_in;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }



}
