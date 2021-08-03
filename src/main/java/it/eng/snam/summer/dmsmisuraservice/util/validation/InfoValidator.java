package it.eng.snam.summer.dmsmisuraservice.util.validation;

import java.util.List;

import it.eng.snam.summer.dmsmisuraservice.model.Info;

@FunctionalInterface
public interface InfoValidator {


    public String apply( List<Info> context );
}
