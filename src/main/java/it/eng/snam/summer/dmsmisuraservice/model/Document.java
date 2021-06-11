package it.eng.snam.summer.dmsmisuraservice.model;

import java.util.List;
import java.util.Map;

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
    public List<Map<String, String>> info;

public Document withId( String id ) {
    this.id = id ;
     return this;
}

public Document withCreatedAt( String created_at ) {
    this.created_at = created_at ;
     return this;
}
public Document withCreatedBy( String created_by ) {
    this.created_by = created_by ;
     return this;
}
public Document withLink( String link ) {
    this.link = link ;
     return this;
}
public Document withFolder( String folder ) {
    this.folder = folder ;
     return this;
}
public Document withSubfolder( String subfolder ) {
    this.subfolder = subfolder ;
     return this;
}
public Document withUpdatedBy( String updated_by ) {
    this.updated_by = updated_by ;
     return this;
}
public Document withUpdatedAt( String updated_at ) {
    this.updated_at = updated_at ;
     return this;
}
public Document withStatus( String status ) {
    this.status = status ;
     return this;
}
public Document withNotes( String notes ) {
    this.notes = notes ;
     return this;
}
public Document withPublishedAt( String published_at ) {
    this.published_at = published_at ;
     return this;
}
public Document withName( String name ) {
    this.name = name ;
     return this;
}
public Document withInfo(List<Map<String, String>> info){
    this.info = info ;
    return this;
}



}
