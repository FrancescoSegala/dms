package it.eng.snam.summer.dmsmisuraservice.model.create;

import java.util.List;
import java.util.Map;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import io.swagger.annotations.ApiModelProperty;

public class DocumentCreate {




    //info necessarie ricevute da clienet per creare un nuovo  documento
    @ApiModelProperty("The name of the new document")
    //^[A-Za-z0-9]+[-_A-Za-z0-9]*$
    @NotBlank
    @Pattern(regexp = "^[a-zA-Z]+[_1-9]*[a-zA-Z-0-9]*$")
    public String name;

    @ApiModelProperty("list of infos about the document ")
    public List<Map<String, String>> info;

    @ApiModelProperty("status of the document")
    public String status;

    @ApiModelProperty("notes for the document")
    public String notes;

    @NotBlank
    @ApiModelProperty("the id of the 1st level folder")
    public String folder;

    @NotBlank
    @ApiModelProperty("the id of the 2nd level folder")
    public String subfolder;

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
