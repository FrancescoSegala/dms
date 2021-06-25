package it.eng.snam.summer.dmsmisuraservice.model.search;

import javax.validation.constraints.Pattern;

import io.swagger.annotations.ApiModelProperty;

public class SubfolderSearch extends Pagination {


    @ApiModelProperty("the id of the 1st level folder")
    private String folder ;

    @ApiModelProperty("the id of the 2nd level folder")
    private String id ;

    @ApiModelProperty("the status of the subfolder, should be either 'active' or 'inactive'")
    @Pattern(regexp = "(active|inactive)")
    private String status ;

    public String getFolder() {
        return folder;
    }

    public void setFolder(String folder) {
        this.folder = folder;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }





}
