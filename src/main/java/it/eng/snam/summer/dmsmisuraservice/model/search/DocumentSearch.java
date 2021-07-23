package it.eng.snam.summer.dmsmisuraservice.model.search;

import javax.validation.constraints.Pattern;

import io.swagger.annotations.ApiModelProperty;

public class DocumentSearch extends Pagination {

    @ApiModelProperty("folder description")
    public String name;
    @Pattern(regexp = "(active|inactive)")
    public String status;
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


    @ApiModelProperty("the remi")
    @Pattern(regexp = "^[A-Za-z0-9]+[-_A-Za-z0-9]*$")
    public String remi;

    @ApiModelProperty("the list of 'linea' to include the research")
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
