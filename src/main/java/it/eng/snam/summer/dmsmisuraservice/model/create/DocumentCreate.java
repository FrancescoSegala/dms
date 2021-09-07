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
    private List<Entity> info = new ArrayList<>(); // es {"remi" : {"id": "", "linea": "linea"} } //TODO tieni traccia

    @ApiModelProperty("title of the document")
    @NotBlank(message = "title must be not empty")
    @Pattern(regexp = DOCUMENT_REGEX)
    private String title; // solo su dds

    @ApiModelProperty("the id of the 2nd level folder")
    @NotEmpty(message = "the list of subfolders must not be empty")
    private List<String> subfolders;

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

    public List<String> getSubfolders() {
        return subfolders;
    }

    public void setSubfolders(List<String> subfolders) {
        this.subfolders = subfolders;
    }

    @Override
    public String toString() {
        return "info :" + info + ", name :" + name + ", subfolders :" + subfolders + ", title :" + title + "}";
    }

    public static DocumentCreate parseJson(String document) {
        DocumentCreate doc = null;
        try {
            doc = new ObjectMapper().readValue(document, DocumentCreate.class);
        } catch (JsonMappingException e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid JSON for DocumentCreate");
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid JSON for DocumentCreate");
        }
        return doc;
    }

}
