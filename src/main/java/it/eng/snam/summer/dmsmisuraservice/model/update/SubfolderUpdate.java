package it.eng.snam.summer.dmsmisuraservice.model.update;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;

public class SubfolderUpdate  {

    @ApiModelProperty("the new description for this subfolder")
    @NotBlank
    public String description;

    //todo anche send_to e status

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
