package it.eng.snam.summer.dmsmisuraservice.model;

import java.util.ArrayList;
import java.util.List;

public class Subfolder {

    public String id;
    public String status;
    public String description;
    public String source;
    public List<String> send_to = new ArrayList<>();
    public String folder;
    public Long document_count = 0L;

    public Subfolder(String id, String status, String description, String source, List<String> send_to, String folder) {
        this.id = id;
        this.status = status;
        this.description = description;
        this.source = source;
        this.send_to = send_to;
        this.folder = folder;
    }

    public Subfolder() {
    }

    public Subfolder withId(String id) {
        this.id = id;
        return this;
    }

    public Subfolder withStatus(String status) {
        this.status = status;
        return this;
    }

    public Subfolder withDescription(String description) {
        this.description = description;
        return this;
    }

    public Subfolder withSource(String source) {
        this.source = source;
        return this;
    }

    public Subfolder withFolder(String folder) {
        this.folder = folder;
        return this;
    }

    public Subfolder withDest(String dest) {
        this.send_to.add(dest);
        return this;
    }

    public Subfolder withDocumentCount(  Long count ) {
        this.document_count = count ;
         return this;
    }

}
