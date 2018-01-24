package cache;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class cacheMaster<K extends Serializable,
        V extends Serializable>
        implements Cache<K, V> {
    private static final Logger logger =
            LoggerFactory.getLogger(inStorageCache.class);

    private final inMemoryCache<K, V> memoryCache;
    private final inStorageCache<K, V> storageCache;

    public cacheMaster(int memorySize,
                       int storageSize) {
        this.memoryCache = new inMemoryCache<K, V>(memorySize);
        this.storageCache = new inStorageCache<K, V>(storageSize);
    }


    public V get(K key) {
        V value = null;
        if (memoryCache.contains(key))
            value = memoryCache.get(key);
        else if (storageCache.contains(key))
            value = storageCache.get(key);
        return value;
    }

    public void put(K key, V value) {
        if (!memoryCache.isFull() ||
                memoryCache.contains(key)) {
            logger.debug(String.format(
                    "Put key %s to memory", key));
            memoryCache.put(key, value);

            if (storageCache.contains(key))
                storageCache.remove(key);
        } else if (!storageCache.isFull() ||
                storageCache.contains(key)) {
            logger.debug(String.format("Put key %s to storage", key));
            storageCache.put(key, value);
        }
    }

    public boolean contains(K key) {
        return memoryCache.contains(key) || storageCache.contains(key);
    }

    public void remove(K key) {
        if (memoryCache.contains(key))
            memoryCache.remove(key);

        if (storageCache.contains(key))
            storageCache.remove(key);
    }

    public int size() {
        return memoryCache.size() +
                storageCache.size();
    }

    public void clear() {
        memoryCache.clear();
        storageCache.clear();
    }

    public boolean isFull() {
        return false;
    }
}
