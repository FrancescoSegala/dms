package it.eng.snam.summer.dmsmisuraservice.model;

import it.eng.snam.summer.dmsmisuraservice.util.Entity;

public class Document {

    public String id;
    public String link;
    public String folder;
    public String subfolder;
    public String published_at;
    public String name;
    public Entity info = new Entity() ;

    public Document withId(String id) {
        this.id = id;
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

    public Document withPublishedAt(String published_at) {
        this.published_at = published_at;
        return this;
    }

    public Document withName(String name) {
        this.name = name;
        return this;
    }

    public Document withInfo( Entity info) {
        this.info.putAll(info) ;
        return this;
    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public Entity getInfo() {
        return info;
    }

    public void setInfo(Entity info) {
        this.info = info;
    }




}
