package it.eng.snam.summer.dmsmisuraservice.model.search;

public class DocumentCount extends Pagination {


    public String folder ;
    public String subfolder;

    public DocumentCount(String folder, String subfolder) {
        this.folder = folder;
        this.subfolder = subfolder;
    }




}
