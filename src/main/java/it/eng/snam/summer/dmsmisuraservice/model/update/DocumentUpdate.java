package it.eng.snam.summer.dmsmisuraservice.model.update;

import java.util.List;
import static it.eng.snam.summer.dmsmisuraservice.util.Utility.DOCUMENT_REGEX;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import io.swagger.annotations.ApiModelProperty;
import it.eng.snam.summer.dmsmisuraservice.model.Info;

public class DocumentUpdate {


    @ApiModelProperty("document name")
    //^[A-Za-z0-9]+[-_A-Za-z0-9]*$
    @Pattern(regexp = DOCUMENT_REGEX)
    @NotBlank
    public String name;

    @ApiModelProperty("List of infos about the document")
    public List<Info> info;

    @ApiModelProperty("status of the document")
    public String status;

    @ApiModelProperty("notes for the document")
    public String notes;



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



}
