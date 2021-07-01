package it.eng.snam.summer.dmsmisuraservice.model.create;

import java.util.List;
import static it.eng.snam.summer.dmsmisuraservice.util.Utility.DOCUMENT_REGEX;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;


import io.swagger.annotations.ApiModelProperty;
import it.eng.snam.summer.dmsmisuraservice.model.Info;

public class DocumentCreate {

    @ApiModelProperty("The name of the new document")
    @NotBlank(message = "name must be not empty")
    @Pattern(regexp = DOCUMENT_REGEX)
    private String name;

    @ApiModelProperty("list of infos about the document ")
    private List<Info> info;

    @ApiModelProperty("status of the document")
    private String status = "active";

    @ApiModelProperty("notes for the document")
    private String notes;

    @ApiModelProperty("title of the document")
    private String title;

    @ApiModelProperty("the id of the 1st level folder")
    @NotBlank(message = "folder must be not empty")
    private String folder;

    @ApiModelProperty("the id of the 2nd level folder")
    @NotBlank(message = "subfolder must be not empty")
    private String subfolder;

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


    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
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
