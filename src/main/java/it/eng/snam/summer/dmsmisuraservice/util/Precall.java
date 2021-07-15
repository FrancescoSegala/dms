package it.eng.snam.summer.dmsmisuraservice.util;

public class Precall {

    public String accessToken ;
    public String url ;


    public Precall withUrl(String url){
        this.url = url ;
        return this;
    }

    public Precall withAccessToken(String accessToken){
        this.accessToken = accessToken ;
        return this;
    }

    private Precall(){
    }

    public static Precall precall(){
        return new Precall();
    }

}
