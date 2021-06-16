package it.eng.snam.summer.dmsmisuraservice.model.update;

import java.util.List;
import java.util.Map;
import static it.eng.snam.summer.dmsmisuraservice.util.Utility.DOCUMENT_REGEX;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import io.swagger.annotations.ApiModelProperty;

public class DocumentUpdate {

    //informazioni necessarie da client per fare update di un documento

    @ApiModelProperty("id of the document to update")
    @NotBlank
    public String id;

    @ApiModelProperty("document name")
    //^[A-Za-z0-9]+[-_A-Za-z0-9]*$
    @Pattern(regexp = DOCUMENT_REGEX)
    @NotBlank
    public String name;

    @ApiModelProperty("List of infos about the document")
    public List<Map<String, String>> info;

    @ApiModelProperty("status of the document")
    public String status;

    @ApiModelProperty("notes for the document")
    public String notes;

    @ApiModelProperty("the id of the 1st level folder")
    public String folder;

    @ApiModelProperty("the id of the 2nd level folder")
    public String subfolder;


    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public List<Map<String, String>> getInfo() {
        return info;
    }
    public void setInfo(List<Map<String, String>> info) {
        this.info = info;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getNotes() {
        return notes;
    }
    public void setNotes(String notes) {
        this.notes = notes;
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


}
