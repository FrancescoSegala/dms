package it.eng.snam.summer.dmsmisuraservice.util;

import java.util.function.Function;
import static it.eng.snam.summer.dmsmisuraservice.util.Utility.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public abstract class Validators {

    public static Validator required(String key) {
        return body -> body.isSet(key) ? null : new ResponseStatusException(HttpStatus.BAD_REQUEST, key + "_required");
    }

    public static Validator oneOf(String key, String... values) {
        return body -> {
            if (!body.containsKey(key))
                return null;
            return setOf
            (values).contains(body.getAsString(key)) ? null
                    : new ResponseStatusException(HttpStatus.BAD_REQUEST, key + " must be one of " + listOf(values));
        };
    }

    public static Validator regex(String key, String regex) {
        return body -> body.id().matches(regex) ? null
                : new ResponseStatusException(HttpStatus.BAD_REQUEST, key + " must match " + regex);
    }

    public static Validator notExists(String key, Function<String, Boolean> fn) {
        return body -> fn.apply(body.getAsString(key))
                ? null : new ResponseStatusException(HttpStatus.CONFLICT, key + " already exists")
                ;
    }

    public static Validator exists(String key, Function<String, Boolean> fn) {
        return body -> !fn.apply(body.getAsString(key))
                ? new ResponseStatusException(HttpStatus.BAD_REQUEST, key + " must exist")
                : null;
    }

    public static void validate(Entity body, Validator... validators) {
    //@formatter:off
    listOf(validators).stream()
    .map(v -> v.validate(body))
    .filter(v -> v != null)
    .findAny()
    .ifPresent(a -> {
        System.out.println(a.getMessage());
        throw a;
    });
    //@formatter:on
    }



}
