package it.eng.snam.summer.dmsmisuraservice.model.create;

import java.util.List;
import static it.eng.snam.summer.dmsmisuraservice.util.Utility.DOCUMENT_REGEX;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;


import io.swagger.annotations.ApiModelProperty;
import it.eng.snam.summer.dmsmisuraservice.model.Info;

public class DocumentCreate {

    @ApiModelProperty("The name of the new document")
    @NotBlank
    @Pattern(regexp = DOCUMENT_REGEX)
    public String name;

    @ApiModelProperty("list of infos about the document ")
    public List<Info> info;

    @ApiModelProperty("status of the document")
    public String status = "active";

    @ApiModelProperty("notes for the document")
    public String notes;

    @ApiModelProperty("the id of the 1st level folder")
    @NotBlank
    public String folder;

    @ApiModelProperty("the id of the 2nd level folder")
    @NotBlank
    public String subfolder;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public List<Info> getInfo() {
        return info;
    }
    public void setInfo(List<Info> info) {
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
