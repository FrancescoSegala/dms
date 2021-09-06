package it.eng.snam.summer.dmsmisuraservice.model.search;

import java.util.List;

public class IdSearch extends Pagination {


    public String id ;

    public List<String> id_in ;

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

    public List<String> getIds() {
        return id_in;
    }

    public void setIds(List<String> ids) {
        this.id_in = ids;
    }


    public IdSearch withIds( List<String> ids ) {
        this.id_in = ids ;
         return this;
    }



}
