package it.eng.snam.summer.dmsmisuraservice.util;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class CachedFunction<K,V> implements Function<K,V> {


    private Long expireAfterMillis ;
    private Function<K,V> fn ;

    private Map<K, Long> expirations = new HashMap<>();
    private Map<K,V> map = new HashMap<>();


    public CachedFunction(Long expireAfterMillis , Function<K,V> fn  ) {
        this.expireAfterMillis = expireAfterMillis;
        this.fn = fn ;
    }


    private boolean containsKey(K key) {
        return map.containsKey(key) && expirations.get(key) + this.expireAfterMillis > Instant.now().toEpochMilli()  ;
    }

    public V get(K key) {
        if (containsKey(key) ){
            return map.get(key);
        }
        V value = this.fn.apply(key);
        map.put(key, value);
        expirations.put(key, Instant.now().toEpochMilli() );
        return value ;
    }


    @Override
    public V apply(K t) {
        return get(t);
    }



}
