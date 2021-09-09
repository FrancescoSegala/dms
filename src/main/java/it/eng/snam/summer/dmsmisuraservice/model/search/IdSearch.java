package it.eng.snam.summer.dmsmisuraservice.model.search;


public class IdSearch extends Pagination {


    public String id ;

    public IdSearch(String id) {
        this.id = id;
    }

    public IdSearch(){}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }





}
