package it.eng.snam.summer.dmsmisuraservice.model.search;

import io.swagger.annotations.ApiModelProperty;

public class FolderSearch extends Pagination {


    @ApiModelProperty("the id of the 1st level folder to search")
    private  String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


}
