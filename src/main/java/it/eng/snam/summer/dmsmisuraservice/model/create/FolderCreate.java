package it.eng.snam.summer.dmsmisuraservice.model.create;

import io.swagger.annotations.ApiModelProperty;

public class FolderCreate {


    @ApiModelProperty("folder id")
    private String id ;

    // @ApiModelProperty("folder class")
    // private String class_name  = "dms_DMSFolder";


    @ApiModelProperty("folder description")
    private String description = ""  ;


    // public String getClass_name() {
    //     return class_name;
    // }

    // public void setClass_name(String class_name) {
    //     this.class_name = class_name;
    // }



    public String getDescription() {
        return description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }





}
