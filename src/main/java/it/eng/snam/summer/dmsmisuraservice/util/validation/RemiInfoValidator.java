package it.eng.snam.summer.dmsmisuraservice.util.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.eng.snam.summer.dmsmisuraservice.service.summer.SummerSqlProvider;
import it.eng.snam.summer.dmsmisuraservice.util.Entity;

@Component
public class RemiInfoValidator implements InfoValidator {


    @Autowired
    SummerSqlProvider summerSql ;

    String field = "";

    public RemiInfoValidator withField( String field ) {
        this.field = field ;
         return this;
    }

    @Override
    public String apply(Entity info) {
        try {
            summerSql.getRemi(info.getAsString(field));
        } catch (Exception e) {
            return "remi '"+ info.getAsString(field) + "' not found";
        }
        return null ;

    }

}
