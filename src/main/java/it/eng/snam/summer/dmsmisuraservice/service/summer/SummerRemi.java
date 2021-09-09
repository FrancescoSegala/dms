package it.eng.snam.summer.dmsmisuraservice.service.summer;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import it.eng.snam.summer.dmsmisuraservice.model.search.RemiSearch;
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

    public Entity getAreaTecnicaByUserId(String userId) {
        Entity r ;
        try {
            r = this.rest.getAreaTecnicaByUserId(userId).get();
        } catch (Exception e) {
        	e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "area tecnica per userId " + userId + " not found");
        }
        return r;
    }

    public List<Entity> list(RemiSearch params){
        return null ;
    }


    public Entity[] getRemiByAreaTecnica(String areaTecnica) {
        Entity[] r ;
        try {
            r =  this.rest.getRemiByAreaTecnica(areaTecnica).getForList().toArray(new Entity[0]);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "lista remi per areaTecnica " + areaTecnica + " not found");
        }
        return r;
    }
}
