package it.eng.snam.summer.dmsmisuraservice.model.update;

import java.util.ArrayList;
import java.util.List;
import static it.eng.snam.summer.dmsmisuraservice.util.Utility.DOCUMENT_REGEX;

import javax.validation.constraints.Pattern;

import io.swagger.annotations.ApiModelProperty;
import it.eng.snam.summer.dmsmisuraservice.model.Info;

public class DocumentUpdate {


    @ApiModelProperty("document name")
    @Pattern(regexp = DOCUMENT_REGEX)
    public String name;

    @ApiModelProperty("List of infos about the document")
    public List<Info> info = new ArrayList<>();

    @ApiModelProperty("status of the document")
    @Pattern(regexp = "(active|inactive)")
    public String status;

    @ApiModelProperty("title of the document")
    public String title;



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
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }






}
