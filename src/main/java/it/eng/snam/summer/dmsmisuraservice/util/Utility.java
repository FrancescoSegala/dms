package it.eng.snam.summer.dmsmisuraservice.util;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;

public class Utility {


    public static final String DOCUMENT_REGEX = "^[a-zA-Z]+[a-zA-Z0-9_\\-]*$";


    public static boolean isEmpty(String string) {
        return (string == null || string.trim().isEmpty());
    }

    public static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor) {
        Map<Object, Boolean> map = new ConcurrentHashMap<>();
        return t -> map.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }

    public static Map<String, String> mapOf(String k , String v ){
        HashMap<String, String> map = new HashMap<>();
        map.put(k, v);
        return map;
    }
}
