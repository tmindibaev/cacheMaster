package cache;

import java.io.Serializable;
import java.util.concurrent.ConcurrentHashMap;

public class cacheMaster<K extends Serializable, V extends Serializable> implements Cache<K, V>
{
    private final inMemoryCache<K, V> memoryCache;
    private final inStorageCache<K, V> storageCache;

    public cacheMaster(int memorySize,
                       int storageSize,
                       String pathToFile)
    {
        this.memoryCache = new inMemoryCache<K, V>(memorySize);
        this.storageCache = new inStorageCache<K, V>(storageSize,
                pathToFile);
    }

    public V get(K key)
    {

    }

    public int put(K key, V value)
    {

    }

    public boolean contains(K key)
    {

    }

    public int remove(K key)
    {

    }

    public int size()
    {

    }

    public int clear()
    {

    }
}
