package it.eng.snam.summer.dmsmisuraservice.model.search;

import javax.validation.constraints.Pattern;

import io.swagger.annotations.ApiModelProperty;

public class DocumentSearch extends Pagination {

    public String id;
    public String data_documento_gt;
    public String data_documento_lt;
    public String linea;
    public String codice_centro;
    public String codice_distretto;
    public String codice_area_tecnica;
    public String codice_polo;
    public String ragione_sociale;
    public String ragione_sociale_like;
    public String ubicazione;
    public String ubicazione_like;
    public String provincia;
    public String comune;
    public String regione;
    public String codice_aop;
    public String codice_ateco;
    public String data_entrata_esercizio_gt;
    public String data_entrata_esercizio_lt;
    public String folder;
    public String subfolder;
    public String remi_terzo;
    public String reti_trasporto;

    // @Pattern(regexp =
    // "(\\d{4})-(\\d{2})-(\\d{2})T(\\d{2}):(\\d{2})\\:(\\d{2})(.)*[\\d]*Z", message
    // = "date format not valid (should be of type yyyy-mm-ddTHH:MM:ss)")

    @ApiModelProperty("the remi")
    @Pattern(regexp = "^[A-Za-z0-9]+[-_A-Za-z0-9]*$")
    public String remi;

    @ApiModelProperty("the list of 'linea' to include the research")
    // @Pattern(regexp = "^[A-Za-z0-9]+[-_A-Za-z0-9]*$")
    public String[] linea_in;

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

    public String getRemi() {
        return remi;
    }

    public void setRemi(String remi) {
        this.remi = remi;
    }

}
