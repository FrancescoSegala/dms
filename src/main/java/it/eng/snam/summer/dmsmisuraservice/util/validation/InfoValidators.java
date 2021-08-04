package it.eng.snam.summer.dmsmisuraservice.util.validation;

import java.util.List;
import it.eng.snam.summer.dmsmisuraservice.model.Info;

public class InfoValidators {


    public static InfoValidator required( String field  ){
        return ( context ) -> value(context, field ) != null ? null : "Field " + field + " required";
    }


    public static InfoValidator isString( String field ){
        return ( context ) ->  value(context, field) instanceof String ? null : "Field "+ field + " must be a string";
    }


    public static InfoValidator isBoolean( String field ){
        return ( context ) ->  value(context, field) instanceof Boolean ? null : "Field "+ field + " must be a boolean";
    }


    public static InfoValidator stringOf(String field , int len ){
        return ( context ) -> {
            Object v = value(context, field);
            if (v == null  ){
                return null ;
            }
            if (! (v instanceof String) ){
                return "Field "+ field + " must be a string";
            }
            return v.toString().length() <= len ? null : "Field "+ field + " must be long at most "+ len + " characters";
        };
    }


    private static Object value(List<Info> context , String field){
        return context.stream()
            .filter(e -> e.containsKey(field))
            .findFirst()
            .map(e -> e.get(field))
            .orElse(null)
            ;
    }

}
