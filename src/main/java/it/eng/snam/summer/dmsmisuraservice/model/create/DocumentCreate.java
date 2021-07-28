package it.eng.snam.summer.dmsmisuraservice.model.create;

import java.util.ArrayList;
import java.util.List;
import static it.eng.snam.summer.dmsmisuraservice.util.Utility.DOCUMENT_REGEX;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import io.swagger.annotations.ApiModelProperty;
import it.eng.snam.summer.dmsmisuraservice.model.Info;
import it.eng.snam.summer.dmsmisuraservice.util.Entity;


public class DocumentCreate {

    @ApiModelProperty("The name of the new document")
    @NotBlank(message = "name must be not empty")
    @Pattern(regexp = DOCUMENT_REGEX)
    private String name; // solo su dds

    @ApiModelProperty("list of infos about the document ")
    @NotEmpty(message = "info must be not empty")
    private List<Info> info = new ArrayList<>(); // es {"remi" : "codice_remi"}

    @ApiModelProperty("title of the document")
    @NotBlank(message = "title must be not empty")
    @Pattern(regexp = DOCUMENT_REGEX)
    private String title; //solo su dds

    @ApiModelProperty("the id of the 1st level folder")
    @NotBlank(message = "folder must be not empty")
    private String folder;

    @ApiModelProperty("the id of the 2nd level folder")
    @NotBlank(message = "subfolder must be not empty")
    private String subfolder;


    public DocumentCreate fromEntity(Entity e ){
        this.setFolder(e.getAsString("folder"));
        this.setSubfolder(e.getAsString("subfolder"));
        this.setName(e.getAsString("name"));
        this.setTitle(e.getAsString("title"));
        this.info.addAll(e.getAsList("info"));
        return this;
    }


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
    @Override
    public String toString() {
        return "{folder :" + folder + ", info :" + info + ", name :" + name + ", subfolder :" + subfolder
                + ", title :" + title + "}";
    }




}
