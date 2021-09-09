package it.eng.snam.summer.dmsmisuraservice.model.search;

import java.util.List;

public class RemiSearch extends Pagination {

    public String c_remi_ass;

    public List<String> c_remi_ass_in ;


    public RemiSearch(List<String> remiList) {
        this.c_remi_ass_in = remiList;
        this.setSort("c_remi_ass");
    }

    public RemiSearch(String remi){
        this.c_remi_ass = remi ;
        this.setSort("c_remi_ass");
    }

    public RemiSearch withRemi( String remi ) {
        this.c_remi_ass = remi ;
         return this;
    }

    public RemiSearch withRemiIn( List<String> remis ) {
        this.c_remi_ass_in = remis ;
         return this;
    }

    public String getC_remi_ass() {
        return c_remi_ass;
    }


    public void setC_remi_ass(String c_remi_ass) {
        this.c_remi_ass = c_remi_ass;
    }



}
