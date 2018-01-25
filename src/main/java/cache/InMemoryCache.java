package cache;

import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class inMemoryCache<K extends Serializable,
        V extends Serializable>
        implements Cache<K, V> {

    private static final Logger logger =
            LoggerFactory.getLogger(inStorageCache.class);

    private final Map<K, V> map;
    private final int cacheMaxSize;

    public inMemoryCache(int cacheMaxSize) {
        this.cacheMaxSize = cacheMaxSize;
        this.map = new ConcurrentHashMap();
    }

    public V get(K key) {
        return map.get(key);
    }

    public void put(K key, V value) {
        map.put(key, value);
    }

    public boolean contains(K key) {
        return map.containsKey(key);
    }

    public void remove(K key) {
        map.remove(key);
    }

    public int size() {
        return map.size();
    }

    public void clear() {
        map.clear();
    }

    public boolean isFull() {
        return size() == cacheMaxSize;
    }
}