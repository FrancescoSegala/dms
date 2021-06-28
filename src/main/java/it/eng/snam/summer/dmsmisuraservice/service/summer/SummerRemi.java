package it.eng.snam.summer.dmsmisuraservice.service.summer;

import org.springframework.stereotype.Component;

import it.eng.snam.summer.dmsmisuraservice.util.Entity;

@Component
public class SummerRemi extends SummerEntity {

    public Entity get(String id) {
        return this.rest.getRemiById(id).get();
    }

}
