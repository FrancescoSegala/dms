package it.eng.snam.summer.dmsmisuraservice.model.create;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import static it.eng.snam.summer.dmsmisuraservice.util.Utility.SUBFOLDER_REGEX;

import io.swagger.annotations.ApiModelProperty;

public class SubfolderCreate {


    //info necessarie ricevute da clienet per creare una nuova  subfolder
    @ApiModelProperty("The id of the new subfolder (must match the pattern "+ SUBFOLDER_REGEX + " )")
    @NotBlank
    @Pattern(regexp = SUBFOLDER_REGEX)
    public String subfolder_id;

    @NotBlank
    @ApiModelProperty("the description for this subfolder")
    public String description;


    @ApiModelProperty("the source system for this subfolder")
    public String source;


    @ApiModelProperty("the status of the subfolder")
    public String status;


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
