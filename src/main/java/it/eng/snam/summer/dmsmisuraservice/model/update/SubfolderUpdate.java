package it.eng.snam.summer.dmsmisuraservice.model.update;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;

public class SubfolderUpdate {


    @ApiModelProperty("The id of the subfolder to update" )
    @NotBlank
    public String subfolder_id;

    @ApiModelProperty("the new description for this subfolder")
    @NotBlank
    public String description;



    public String getSubfolder_id() {
        return subfolder_id;
    }

    public void setSubfolder_id(String subfolder_id) {
        this.subfolder_id = subfolder_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }



}
