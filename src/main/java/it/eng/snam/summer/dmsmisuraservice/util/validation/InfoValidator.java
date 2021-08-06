package it.eng.snam.summer.dmsmisuraservice.util.validation;

import java.util.List;

import it.eng.snam.summer.dmsmisuraservice.util.Entity;

@FunctionalInterface
public interface InfoValidator {


    public String apply( List<Entity> context );
}
