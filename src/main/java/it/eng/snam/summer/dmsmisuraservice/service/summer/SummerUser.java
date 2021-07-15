package it.eng.snam.summer.dmsmisuraservice.service.summer;

import org.springframework.stereotype.Component;

import it.eng.snam.summer.dmsmisuraservice.util.Entity;

@Component
public class SummerUser extends SummerEntity {


    public Entity getUserProfile(String user_id){
        return rest.getUserProfile(user_id).get();
    }

}
