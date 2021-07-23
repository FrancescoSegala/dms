package it.eng.snam.summer.dmsmisuraservice.util;

import org.springframework.web.server.ResponseStatusException;

public interface Validator {

	ResponseStatusException validate(Entity input);

}
