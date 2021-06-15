package it.eng.snam.summer.dmsmisuraservice.model.search;

import io.swagger.annotations.ApiModelProperty;

public class Pagination {



    @ApiModelProperty("sort field")
    private String sort;

    @ApiModelProperty("sort direction, must be 'asc' or 'desc' (default = 'asc')")
    private String direction = "asc";

    @ApiModelProperty("limit the number of records given as result, must be an integer greather than 0 ")
    private Long limit = 10L ;

    @ApiModelProperty("the offset from where start giving the result, must be an integer greather tyhan 0")
    private Long offset = 0L;

    public String getSort() {
        return sort;
    }
    public void setSort(String sort) {
        this.sort = sort;
    }
    public String getDirection() {
        return direction;
    }
    public void setDirection(String direction) {
        this.direction = direction;
    }
    public Long getLimit() {
        return limit;
    }
    public void setLimit(Long limit) {
        this.limit = limit;
    }
    public Long getOffset() {
        return offset;
    }
    public void setOffset(Long offset) {
        this.offset = offset;
    }




}
