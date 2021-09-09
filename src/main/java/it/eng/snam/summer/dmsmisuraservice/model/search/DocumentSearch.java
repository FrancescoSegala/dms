package it.eng.snam.summer.dmsmisuraservice.model.search;

import java.util.List;

import javax.validation.constraints.Pattern;

import io.swagger.annotations.ApiModelProperty;

public class DocumentSearch extends Pagination {


    public String id ;

    public List<String> id_in ;

    @ApiModelProperty("name of the document")
    public String name;

    @ApiModelProperty("document status, can be one of 'active', 'inactive'")
    @Pattern(regexp = "(active|inactive)")
    public String status;

    @ApiModelProperty("start of date range search")
    public String data_documento_gt;

    @ApiModelProperty("end of date range search")
    public String data_documento_lt;

    @ApiModelProperty("linea ")
    public String linea;

    @ApiModelProperty("codice centro ")
    public String codice_centro;

    @ApiModelProperty("codice distretto")
    public String codice_distretto;

    @ApiModelProperty("codice area tecnica")
    public String codice_area_tecnica;

    @ApiModelProperty("codice polo")
    public String codice_polo;

    @ApiModelProperty("rargione sociale")
    public String ragione_sociale;

    @ApiModelProperty("substring of rargione sociale")
    public String ragione_sociale_like;

    @ApiModelProperty("ubicazione")
    public String ubicazione;

    @ApiModelProperty("substring of ubicazione")
    public String ubicazione_like;

    @ApiModelProperty("provincia")
    public String provincia;

    @ApiModelProperty("comune")
    public String comune;

    @ApiModelProperty("regione")
    public String regione;

    @ApiModelProperty("codice aop")
    public String codice_aop;

    @ApiModelProperty("codice ateco")
    public String codice_ateco;

    @ApiModelProperty("start of data entrata esercizio")
    public String data_entrata_esercizio_gt;

    @ApiModelProperty("end of data entrata esercizio")
    public String data_entrata_esercizio_lt;

    @ApiModelProperty("folder")
    public String folder;

    @ApiModelProperty("subfolder")
    public String subfolder;

    @ApiModelProperty("remi terzo")
    public String remi_terzo;

    @ApiModelProperty("reti trasporto")
    public String reti_trasporto;

    @ApiModelProperty("the remi")
    @Pattern(regexp = "^[A-Za-z0-9]+[-_A-Za-z0-9]*$")
    public String remi;

    @ApiModelProperty("the list of 'linea' to include the research")
    public String[] linea_in;


    public DocumentSearch withId( String id ) {
        this.id = id ;
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


    public List<String> getIds() {
        return id_in;
    }

    public void setIds(List<String> ids) {
        this.id_in = ids;
    }


    public DocumentSearch withIds( List<String> ids ) {
        this.id_in = ids ;
         return this;
    }


    public String getId() {
        return id;
    }


    public void setId(String id) {
        this.id = id;
    }

    public List<String> getId_in() {
        return id_in;
    }


    public void setId_in(List<String> id_in) {
        this.id_in = id_in;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getData_documento_gt() {
        return data_documento_gt;
    }

    public void setData_documento_gt(String data_documento_gt) {
        this.data_documento_gt = data_documento_gt;
    }

    public String getData_documento_lt() {
        return data_documento_lt;
    }

    public void setData_documento_lt(String data_documento_lt) {
        this.data_documento_lt = data_documento_lt;
    }

    public String getLinea() {
        return linea;
    }

    public void setLinea(String linea) {
        this.linea = linea;
    }

    public String getCodice_centro() {
        return codice_centro;
    }

    public void setCodice_centro(String codice_centro) {
        this.codice_centro = codice_centro;
    }

    public String getCodice_distretto() {
        return codice_distretto;
    }

    public void setCodice_distretto(String codice_distretto) {
        this.codice_distretto = codice_distretto;
    }

    public String getCodice_area_tecnica() {
        return codice_area_tecnica;
    }

    public void setCodice_area_tecnica(String codice_area_tecnica) {
        this.codice_area_tecnica = codice_area_tecnica;
    }

    public String getCodice_polo() {
        return codice_polo;
    }

    public void setCodice_polo(String codice_polo) {
        this.codice_polo = codice_polo;
    }

    public String getRagione_sociale() {
        return ragione_sociale;
    }

    public void setRagione_sociale(String ragione_sociale) {
        this.ragione_sociale = ragione_sociale;
    }

    public String getUbicazione() {
        return ubicazione;
    }

    public void setUbicazione(String ubicazione) {
        this.ubicazione = ubicazione;
    }

    public String getUbicazione_like() {
        return ubicazione_like;
    }

    public void setUbicazione_like(String ubicazione_like) {
        this.ubicazione_like = ubicazione_like;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getComune() {
        return comune;
    }

    public void setComune(String comune) {
        this.comune = comune;
    }

    public String getRegione() {
        return regione;
    }

    public void setRegione(String regione) {
        this.regione = regione;
    }

    public String getCodice_aop() {
        return codice_aop;
    }

    public void setCodice_aop(String codice_aop) {
        this.codice_aop = codice_aop;
    }

    public String getCodice_ateco() {
        return codice_ateco;
    }

    public void setCodice_ateco(String codice_ateco) {
        this.codice_ateco = codice_ateco;
    }

    public String getData_entrata_esercizio_gt() {
        return data_entrata_esercizio_gt;
    }

    public void setData_entrata_esercizio_gt(String data_entrata_esercizio_gt) {
        this.data_entrata_esercizio_gt = data_entrata_esercizio_gt;
    }

    public String getData_entrata_esercizio_lt() {
        return data_entrata_esercizio_lt;
    }

    public void setData_entrata_esercizio_lt(String data_entrata_esercizio_lt) {
        this.data_entrata_esercizio_lt = data_entrata_esercizio_lt;
    }

    public String getRemi_terzo() {
        return remi_terzo;
    }

    public void setRemi_terzo(String remi_terzo) {
        this.remi_terzo = remi_terzo;
    }

    public String getReti_trasporto() {
        return reti_trasporto;
    }

    public void setReti_trasporto(String reti_trasporto) {
        this.reti_trasporto = reti_trasporto;
    }



}
