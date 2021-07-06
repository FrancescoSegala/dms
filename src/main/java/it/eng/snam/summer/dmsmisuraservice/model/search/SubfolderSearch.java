package it.eng.snam.summer.dmsmisuraservice.model.search;

import javax.validation.constraints.Pattern;

import io.swagger.annotations.ApiModelProperty;

public class SubfolderSearch extends Pagination {


    @ApiModelProperty("the status of the subfolder, should be either 'active' or 'inactive'")
    @Pattern(regexp = "(active|inactive)")
    private String status ;

    public SubfolderSearch withStatus( String status ) {
        this.status = status ;
         return this;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


}
