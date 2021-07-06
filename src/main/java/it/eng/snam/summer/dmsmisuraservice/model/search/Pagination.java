package it.eng.snam.summer.dmsmisuraservice.model.search;

import java.lang.reflect.Field;
import java.util.stream.Stream;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import io.swagger.annotations.ApiModelProperty;

public class Pagination {

    @ApiModelProperty("sort field, it indicates the id to perform the sorting")
    @Pattern(regexp = "^[a-zA-Z]+[_1-9]*[a-zA-Z-0-9]*$")
    private String sort;

    @ApiModelProperty("sort direction, must be 'asc' or 'desc' (default = 'asc')")
    @Pattern(regexp = "(asc|desc)")
    private String direction = "asc";

    @ApiModelProperty("limit the number of records given as result, must be an integer greater than 0 and less than 100")
    @Min(1L)
    @Max(100L)
    private Long limit = 10L;

    @Min(0L)
    @ApiModelProperty("the offset from where start giving the result, must be an integer greater than 0")
    private Long offset = 0L;

    public Pagination() {
    }

    public Pagination(@Pattern(regexp = "^[a-zA-Z]+[_1-9]*[a-zA-Z-0-9]*$") String sort,
            @Pattern(regexp = "(asc|desc)") String direction, @Min(1) @Max(100) Long limit, @Min(0) Long offset) {
        this.sort = sort;
        this.direction = direction;
        this.limit = limit;
        this.offset = offset;
    }

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

    public String stringfy() {
        ObjectMapper om = new ObjectMapper();
        try {
            return om.writeValueAsString(this);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String toString() {
        return this.stringfy();
    }


    public Stream<Field> notNulls(){
        return Stream.of(  this.getClass().getDeclaredFields() )
            .filter(f -> {
                try {
                    return f.get(this) != null;
                } catch (Exception e ) {
                    throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
                }
            }  );
    }


}
