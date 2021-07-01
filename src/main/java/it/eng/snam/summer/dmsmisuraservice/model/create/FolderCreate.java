package it.eng.snam.summer.dmsmisuraservice.model.create;

import io.swagger.annotations.ApiModelProperty;

public class FolderCreate {


    @ApiModelProperty("folder name")
    private String name ;

    @ApiModelProperty("folder class")
    private String class_name  ;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClass_name() {
        return class_name;
    }

    public void setClass_name(String class_name) {
        this.class_name = class_name;
    }





}
