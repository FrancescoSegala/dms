package it.eng.snam.summer.dmsmisuraservice.model.search;

import io.swagger.annotations.ApiModelProperty;

public class FolderSearch extends Pagination {


    @ApiModelProperty("the id of the 1st level folder to search")
    private  String id;


    private String id_like ;

    public FolderSearch withId( String id ) {
        this.id = id ;
         return this;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getId_like() {
        return id_like;
    }


    public void setId_like(String id_like) {
        this.id_like = id_like;
    }



}
