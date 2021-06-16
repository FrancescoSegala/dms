package it.eng.snam.summer.dmsmisuraservice.model.search;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

import io.swagger.annotations.ApiModelProperty;

public class Pagination {



    @ApiModelProperty("sort field, it indicates the id to perform the sorting")
    @Pattern(regexp = "^[a-zA-Z]+[_1-9]*[a-zA-Z-0-9]*$")
    private String sort;


    @ApiModelProperty("sort direction, must be 'asc' or 'desc' (default = 'asc')")
    @Pattern(regexp = "(asc|desc)")
    private String direction = "asc";

    @ApiModelProperty("limit the number of records given as result, must be an integer greater than 0 and less than 100")
    @Min(0L)
    @Max(100L)
    private Long limit = 10L ;


    @Min(0L)
    @ApiModelProperty("the offset from where start giving the result, must be an integer greater than 0")
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
