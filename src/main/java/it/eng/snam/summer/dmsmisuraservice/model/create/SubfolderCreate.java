package it.eng.snam.summer.dmsmisuraservice.model.create;

import static it.eng.snam.summer.dmsmisuraservice.util.Utility.FOLDER_REGEX;
import static it.eng.snam.summer.dmsmisuraservice.util.Utility.SUBFOLDER_REGEX;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import io.swagger.annotations.ApiModelProperty;
import it.eng.snam.summer.dmsmisuraservice.model.Info;

public class SubfolderCreate {


    //info necessarie ricevute da clienet per creare una nuova  subfolder
    @ApiModelProperty("The id of the new subfolder (must match the pattern "+ SUBFOLDER_REGEX + " )")
    @NotBlank
    @Pattern(regexp = SUBFOLDER_REGEX)
    private String subfolder_id;

    @NotBlank
    @ApiModelProperty("the description for this subfolder")
    private String description;


    @ApiModelProperty("the source system for this subfolder")
    private String source;


    @ApiModelProperty("the status of the subfolder")
    private String status;

    @ApiModelProperty("the list of addresses to send ...") //TODO cosa rappresenta?
    private String[] send_to;

    @ApiModelProperty("the 1st level folder id which this subfolder belong")
    @NotBlank
    @Pattern(regexp = FOLDER_REGEX)
    private String folder;

    @ApiModelProperty("the list of attibutes of the subfolder ")
    private List<Info> attributes ;



    public List<Info> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<Info> attributes) {
        this.attributes = attributes;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String[] getSend_to() {
        return send_to;
    }

    public void setSend_to(String[] send_to) {
        this.send_to = send_to;
    }

    public String getFolder() {
        return folder;
    }

    public void setFolder(String folder) {
        this.folder = folder;
    }

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
