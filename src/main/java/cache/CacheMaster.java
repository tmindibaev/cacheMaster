package cache;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class cacheMaster<K extends Serializable,
        V extends Serializable>
        implements Cache<K, V> {
    private static final Logger logger =
            LoggerFactory.getLogger(inStorageCache.class);

    private inMemoryCache<K, V> memoryCache;
    private inStorageCache<K, V> storageCache;

    public cacheMaster() {
        //this.memoryCache = builder.addSizeMemoryCache(100);
        //this.storageCache = builder.addSizeStorageCache(1000);
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

    public static cacheBuilder newBuilder() {
        return new cacheMaster().newBuilder();
    }
}

public class cacheBuilder <K extends Serializable,
        V extends Serializable> {
    public static cacheBuilder instance;

    public static synchronized cacheBuilder getInstance() {
        if (instance == null)
            instance = new cacheBuilder();
        return instance;
    }
    private cacheBuilder(){

    }

    public cacheMaster build() {
        return cacheMaster.this;
    }
    public cacheBuilder addSizeMemoryCache(int memorySize) {
        return cacheMaster.this.inMemoryCache<K, V>(memorySize);
    }

    public inStorageCache addSizeStorageCache(int storageSize) {
        return new inStorageCache<K, V>(storageSize);
    }

    public cacheMaster build() {
        return new cacheMaster(this);
    }*/
}