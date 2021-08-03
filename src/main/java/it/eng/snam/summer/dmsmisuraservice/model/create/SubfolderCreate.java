package it.eng.snam.summer.dmsmisuraservice.model.create;


import javax.validation.constraints.Pattern;

import io.swagger.annotations.ApiModelProperty;

public class SubfolderCreate extends FolderCreate {

    @ApiModelProperty("the source system for this subfolder")
    private String source;

    @ApiModelProperty("the list of system addresses to send the subfolder")
    private String[] send_to;

    @ApiModelProperty("the initial status of the subfolder")
    @Pattern(regexp = "(active|inactive)")
    private String status ;

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String[] getSend_to() {
        return send_to;
    }

    public void setSend_to(String[] send_to) {
        this.send_to = send_to;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }



}
