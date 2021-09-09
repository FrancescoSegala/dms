package it.eng.snam.summer.dmsmisuraservice.util.validation;

import java.io.IOException;
import java.util.Collection;
import java.util.regex.Pattern;

import org.apache.commons.io.IOUtils;

import it.eng.snam.summer.dmsmisuraservice.util.Entity;

public class InfoValidators {

    private static String ISO8601 = ""; // "^([\\+-]?\\d{4}(?!\\d{2}\\b))((-?)((0[1-9]|1[0-2])(\\3([12]\\d|0[1-9]|3[01]))?|W([0-4]\\d|5[0-2])(-?[1-7])?|(00[1-9]|0[1-9]\\d|[12]\\d{2}|3([0-5]\\d|6[1-6])))([T\\s]((([01]\\d|2[0-3])((:?)[0-5]\\d)?|24\\:?00)([\.,]\\d+(?!:))?)?(\\17[0-5]\\d([\\.,]\\d+)?)?([zZ]|([\\+-])([01]\\d|2[0-3]):?([0-5]\\d)?)?)?)?$";
    static {
        try {
            ISO8601 = IOUtils.toString(InfoValidators.class.getResourceAsStream("/ISO8601.regex"), "UTF-8" ).trim() ;
            System.out.println(ISO8601);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static InfoValidator required( String field  ){
        return ( context ) -> value(context, field ) != null ? null : "Field '" + field + "' is required";
    }

    public static InfoValidator trueOrFalse( String field ){
        return ( context ) -> {
            Object v = value(context, field);
            return ( v !=null && !(v instanceof Boolean) ) ? "Field '"+ field + "' must be true or false (value: ["+ v + "])" : null;
        };
    }

    public static InfoValidator number( String field ){
        return ( context ) -> {
            Object v = value(context, field);
            return ( v !=null && !(v instanceof Number) ) ? "Field '"+ field + "' must be a number (value: ["+ v + "])" : null;
        };
    }

    public static InfoValidator stringOf(String field , Number len ){
        return ( context ) -> {
            Object v = value(context, field);
            if (v == null ) return null ;
            String msg = string(field).apply(context);
            if(msg!= null){
                return msg;
            }
            return v.toString().length() <= len.intValue() ? null : "Field '"+ field + "' must be long at most "+ len + " characters (value: ["+ v + "])" ;
        };
    }

    public static InfoValidator string( String field){
        return ( context ) -> {
            Object v = value(context, field);
            return ( v !=null && !(v instanceof String) ) ? "Field '"+ field + "' must be a string (value: ["+ v + "])" : null;
        };
    }

    private static Object value( Entity info , String field){
        return info.get(field);
    }

    public static InfoValidator date( String field ){
        return ( context ) -> {
            Object v = value(context, field);
            if (v == null ) return null ;
            return regex(field, ISO8601).apply(context) == null ? null : "Field '"+ field + "' is not ISO8601 date (value: ["+ v + "])";
        };
    }

    public static InfoValidator regex(String field , String regex){
        return ( context ) -> {
            Object v = value(context, field);
            if ( v == null ) return null ;
            String msg = string(field).apply(context);
            if(msg!= null){
                return msg;
            }
            System.out.println("regex "+ regex  );
            System.out.println( " v" + v + " match " + v.toString().matches(regex) );
            Pattern p = Pattern.compile(regex);
            return p.matcher(v.toString()).matches() ? null : "Field '" + field + "' does not match (value: ["+ v + "])" + regex;
        };
    }


    public static InfoValidator singleOf(String field,  InfoValidator type  ){
        return (context) -> {
            Object v = value(context, field);
            if ( v == null ) return null ;
            if ( Collection.class.isAssignableFrom(v.getClass()) ) return "Field '" + field + "' must have only 1 element (value: ["+ v + "])";
            return  type.apply(context) ;
        };
    }

    public static InfoValidator listOf(String field, InfoValidator type ){
        return (context) -> {
            Object v = value(context, field);
            if ( v == null ) return null ;
            if ( ! Collection.class.isAssignableFrom(v.getClass()) ) return "Field '" +  field + "' must be a list (value: ["+ v + "])" ;
             return ((Collection<Object>) v)
                .stream()
                .map(e -> type.apply( Entity.build(field ,e )))
                .filter(e -> e!=null )
                .findFirst()
                .orElse(null);
        };
    }




}
