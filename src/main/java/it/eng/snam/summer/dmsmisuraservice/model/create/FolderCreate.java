package it.eng.snam.summer.dmsmisuraservice.model.create;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;

public class FolderCreate {

    @NotBlank
    @ApiModelProperty("folder id")
    private String id;

    @ApiModelProperty("folder description")
    private String description = "";

    public String getDescription() {
        return description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
