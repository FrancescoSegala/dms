package it.eng.snam.summer.dmsmisuraservice.model;

public class SubfolderPermission {
    public Boolean search = true ;
    public Boolean update = true ;
    public Boolean upload = true ;



    public SubfolderPermission(Boolean search, Boolean update, Boolean upload) {
        this.search = search;
        this.update = update;
        this.upload = upload;
    }
    public Boolean getSearch() {
        return search;
    }
    public void setSearch(Boolean search) {
        this.search = search;
    }
    public Boolean getUpdate() {
        return update;
    }
    public void setUpdate(Boolean update) {
        this.update = update;
    }
    public Boolean getUpload() {
        return upload;
    }
    public void setUpload(Boolean upload) {
        this.upload = upload;
    }




}



