package cache;

import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class inMemoryCache<K extends Serializable, V extends Serializable> implements Cache<K, V>
{
    private final Map<K, V> map;
    private final int cacheMaxSize;

    public inMemoryCache(int cacheMaxSize)
    {
        this.cacheMaxSize = cacheMaxSize;
        this.map = new ConcurrentHashMap();
    }

    public V get(K key)
    {
        return map.get(key);
    }

    public int put(K key, V value)
    {
        map.put(key, value);
        return 0;
    }

    public boolean contains(K key)
    {
        return map.containsKey(key);
    }

    public int remove(K key)
    {
        map.remove(key);
        return 0;
    }

    public int size()
    {
        return map.size();
    }

    public int clear()
    {
        map.clear();
        return 0;
    }

    public boolean isEmpty()
    {
        map.isEmpty();
        return false;
    }

    public boolean isFull()
    {
        return size() == cacheMaxSize;
    }
}