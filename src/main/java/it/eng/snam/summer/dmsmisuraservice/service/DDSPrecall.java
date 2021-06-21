package it.eng.snam.summer.dmsmisuraservice.service;

public class DDSPrecall {

    public String accessToken ;
    public String url ;


    public DDSPrecall withUrl(String url){
        this.url = url ;
        return this;
    }

    public DDSPrecall withAccessToken(String accessToken){
        this.accessToken = accessToken ;
        return this;
    }

    private DDSPrecall(){
    }

    public static DDSPrecall precall(){
        return new DDSPrecall();
    }

}
