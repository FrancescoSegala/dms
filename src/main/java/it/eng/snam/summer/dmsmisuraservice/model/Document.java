package it.eng.snam.summer.dmsmisuraservice.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import it.eng.snam.summer.dmsmisuraservice.util.Entity;

public class Document {

    public String id;
    public String created_at;
    public String created_by;
    public String link;
    public String folder;
    public String subfolder;
    public String updated_by;
    public String updated_at;
    public String status;
    public String notes;
    public String published_at;
    public String name;
    public List<Entity> info = new ArrayList<>();

    public Document withId(String id) {
        this.id = id;
        return this;
    }

    public Document withCreatedAt(String created_at) {
        this.created_at = created_at;
        return this;
    }

    public Document withCreatedBy(String created_by) {
        this.created_by = created_by;
        return this;
    }

    public Document withLink(String link) {
        this.link = link;
        return this;
    }

    public Document withFolder(String folder) {
        this.folder = folder;
        return this;
    }

    public Document withSubfolder(String subfolder) {
        this.subfolder = subfolder;
        return this;
    }

    public Document withUpdatedBy(String updated_by) {
        this.updated_by = updated_by;
        return this;
    }

    public Document withUpdatedAt(String updated_at) {
        this.updated_at = updated_at;
        return this;
    }

    public Document withStatus(String status) {
        this.status = status;
        return this;
    }

    public Document withNotes(String notes) {
        this.notes = notes;
        return this;
    }

    public Document withPublishedAt(String published_at) {
        this.published_at = published_at;
        return this;
    }

    public Document withName(String name) {
        this.name = name;
        return this;
    }

    public Document withInfo( Entity info) {
        this.info.add(info) ;
        return this;
    }

    public Document withInfo( Map<String, Object> info) {

        this.info.add(Entity.build(info)) ;
        return this;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getCreated_by() {
        return created_by;
    }

    public void setCreated_by(String created_by) {
        this.created_by = created_by;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
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

    public String getUpdated_by() {
        return updated_by;
    }

    public void setUpdated_by(String updated_by) {
        this.updated_by = updated_by;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
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

    public String getPublished_at() {
        return published_at;
    }

    public void setPublished_at(String published_at) {
        this.published_at = published_at;
    }

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




}
