package it.eng.snam.summer.dmsmisuraservice.util;


import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Utility {

    public static final String DOCUMENT_REGEX = "^[a-zA-Z]+[a-zA-Z0-9_\\-.]*$";
    public static final String SUBFOLDER_REGEX = "^[a-zA-Z]+[a-zA-Z0-9_\\-.]*$";
    public static final String FOLDER_REGEX = "^[a-zA-Z]+[a-zA-Z0-9_\\-.]*$";

    public static boolean isEmpty(String string) {
        return (string == null || string.trim().isEmpty());
    }

    public static <T> boolean isEmpty(T[] values) {
        return values == null || values.length == 0;
    }

    public static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor) {
        Map<Object, Boolean> map = new ConcurrentHashMap<>();
        return t -> map.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }

    public static <T> Map<String, T> mapOf(String k, T v) {
        HashMap<String, T> map = new HashMap<>();
        map.put(k, v);
        return map;
    }

    public static <T> Map<String, T> mapOf( String k1 , T v1 , String k2 , T v2  ){
        Map<String, T> map = mapOf(k1, v1 );
        map.put(k2, v2);
        return map ;
    }

    public static <T> Map<String, T> mapOf( String k1 , T v1 , String k2 , T v2 , String k3 , T v3  ){
        Map<String, T> map = mapOf(k1, v1, k2 , v2  );
        map.put(k3, v3);
        return map ;
    }


    public static <T> Map<String, T> mapOf( String k1 , T v1 , String k2 , T v2 , String k3 , T v3 , String k4 , T v4  ){
        Map<String, T> map = mapOf(k1, v1, k2 , v2, k3 , v3   );
        map.put(k4, v4);
        return map ;
    }

    public static <T> Map<String, T> mapOf( String k1 , T v1 , String k2 , T v2 , String k3 , T v3 , String k4 , T v4 , String k5 , T v5  ){
        Map<String, T> map = mapOf(k1, v1, k2 , v2, k3 , v3, k4 , v4   );
        map.put(k5, v5);
        return map ;
    }

    public static Map<String, Entity> mapOf(String k, Entity v) {
        HashMap<String, Entity> map = new HashMap<>();
        map.put(k, v);
        return map;
    }

    public static <T> Map<String, T> emptyMap() {
        return new HashMap<>();
    }

    public static <T> List<T> listOf(T... e) {
        return Arrays.asList(e);

    }

    public static <T> Set<T> setOf(T... e) {
        return new HashSet<>(Arrays.asList(e));

    }


    public static <T> List<T> concat(List<T> ... l ){
        return Stream.of(l)
            // .flatMap( List::stream )
            .flatMap(e -> e.stream() )
            .collect(Collectors.toList());
    }



}
