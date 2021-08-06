package it.eng.snam.summer.dmsmisuraservice.model.create;

import static it.eng.snam.summer.dmsmisuraservice.util.Utility.DOCUMENT_REGEX;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import io.swagger.annotations.ApiModelProperty;
import it.eng.snam.summer.dmsmisuraservice.util.Entity;


public class DocumentCreate {

    @ApiModelProperty("The name of the new document")
    @NotBlank(message = "name must be not empty")
    @Pattern(regexp = DOCUMENT_REGEX)
    private String name; // solo su dds

    @ApiModelProperty("list of infos about the document ")
    @NotEmpty(message = "info must be not empty")
    private List<Entity> info = new ArrayList<>(); // es {"remi" : "codice_remi"}

    @ApiModelProperty("title of the document")
    @NotBlank(message = "title must be not empty")
    @Pattern(regexp = DOCUMENT_REGEX)
    private String title; //solo su dds

    @ApiModelProperty("the id of the 1st level folder")
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

    public List<Entity> getInfo() {
        return info;
    }
    public void setInfo(List<Entity> info) {
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


    public static DocumentCreate parseJson(String document ){
        DocumentCreate doc = null ;
        try {
            doc = new ObjectMapper().readValue(document, DocumentCreate.class);
        } catch (JsonMappingException e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid JSON for DocumentCreate");
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid JSON for DocumentCreate");
        }
        return doc ;
    }


}
