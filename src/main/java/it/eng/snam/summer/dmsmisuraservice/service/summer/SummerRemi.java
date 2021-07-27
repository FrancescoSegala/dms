package it.eng.snam.summer.dmsmisuraservice.service.summer;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import it.eng.snam.summer.dmsmisuraservice.util.Entity;

@Component
public class SummerRemi extends SummerEntity {

    public Entity get(String id) {
        Entity r ;
        try {
            r = this.rest.getRemiById(id).get();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "remi " + id + " not found");
        }
        return r;
    }

}
