package it.eng.snam.summer.dmsmisuraservice.util.validation;

import java.util.List;

import it.eng.snam.summer.dmsmisuraservice.util.Entity;

public class InfoValidators {

    private static String ISO8601 = "(?:[1-9]\\d{3}-(?:(?:0[1-9]|1[0-2])-(?:0[1-9]|1\\d|2[0-8])|(?:0[13-9]|1[0-2])-(?:29|30)|(?:0[13578]|1[02])-31)|(?:[1-9]\\d(?:0[48]|[2468][048]|[13579][26])|(?:[2468][048]|[13579][26])00)-02-29)T(?:[01]\\d|2[0-3]):[0-5]\\d:[0-5]\\d(?:Z|[+-][01]\\d:[0-5]\\d)";

    public static InfoValidator required( String field  ){
        return ( context ) -> value(context, field ) != null ? null : "Field " + field + " required";
    }

    public static InfoValidator trueOrFalse( String field ){
        return ( context ) -> {
            Object v = value(context, field);
            return ( v !=null && !(v instanceof Boolean) ) ? "Field "+ field + " must be true or false": null;
        };
    }

    public static InfoValidator number( String field ){
        return ( context ) -> {
            Object v = value(context, field);
            return ( v !=null && !(v instanceof Number) ) ? "Field "+ field + " must be a number": null;
        };
    }

    public static InfoValidator stringOf(String field , int len ){
        return ( context ) -> {
            String msg = string(field).apply(context);
            if(msg!= null){
                return msg;
            }
            return value(context, field).toString().length() <= len ? null : "Field "+ field + " must be long at most "+ len + " characters";
        };
    }

    public static InfoValidator string( String field){
        return ( context ) -> {
            Object v = value(context, field);
            return ( v !=null && !(v instanceof String) ) ? "Field "+ field + " must be a string": null;
        };
    }

    private static Object value(List<Entity> context , String field){
        return context.stream()
            .filter(e -> e.containsKey(field))
            .findFirst()
            .map(e -> e.get(field))
            .orElse(null)
            ;
    }

    public static InfoValidator date( String field ){
        return ( context ) -> {
            return regex(field, ISO8601).apply(context) == null ? null : "Field "+ field + " is not a date";
        };
    }

    public static InfoValidator regex(String field , String regex){
        return ( context ) -> {
            String msg = string(field).apply(context);
            if(msg!= null){
                return msg;
            }
            String v = value(context, field).toString();
            return v.matches(regex) ? null : "Field " + field + " does not match " + regex;
        };
    }

}
