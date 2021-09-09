package it.eng.snam.summer.dmsmisuraservice.util.validation;


import it.eng.snam.summer.dmsmisuraservice.util.Entity;

@FunctionalInterface
public interface InfoValidator {


    public String apply( Entity info );
}
