package it.eng.snam.summer.dmsmisuraservice.service.summer;
import static it.eng.snam.summer.dmsmisuraservice.util.EntityMapper.*;
import org.springframework.beans.factory.annotation.Autowired;

import it.eng.snam.summer.dmsmisuraservice.model.Remi;
import it.eng.snam.summer.dmsmisuraservice.util.Entity;

public class SummerImpl implements Summer  {



    @Autowired
    SummerRemi remi;

    @Autowired
    SummerUser users;


    @Override
    public Remi get(String id) {
        return toRemi(remi.get(id));
    }

    @Override
    public Entity getProfile(String user_id){
        return users.getUserProfile(user_id); //TODO make profile model?
    }

}
