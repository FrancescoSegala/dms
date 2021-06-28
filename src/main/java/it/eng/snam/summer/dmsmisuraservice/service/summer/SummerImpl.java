package it.eng.snam.summer.dmsmisuraservice.service.summer;
import static it.eng.snam.summer.dmsmisuraservice.util.EntityMapper.*;
import org.springframework.beans.factory.annotation.Autowired;

import it.eng.snam.summer.dmsmisuraservice.model.Remi;

public class SummerImpl implements Summer  {



    @Autowired
    SummerRemi remi;


    @Override
    public Remi get(String id) {
        return toRemi(remi.get(id));
    }

}
