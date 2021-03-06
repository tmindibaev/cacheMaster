package cache;

public class CacheBuilder {
    private final static int MAX_INMEMORY_SIZE = 100;
    private final static int MAX_INSTORAGE_SIZE = 10000;

    private int maxInMemorySize = MAX_INMEMORY_SIZE;
    private int maxInStorageSize = MAX_INSTORAGE_SIZE;
    private String pathToFile;

    public CacheBuilder setMaxInMemorySize(int maxInMemorySize) {
        this.maxInMemorySize = maxInMemorySize;
        return this;
    }

    public CacheBuilder setMaxInStorageSize(int maxInStorageSize) {
        this.maxInStorageSize = maxInStorageSize;
        return this;

    }

    public CacheBuilder setPathToFile(String pathToFile) {
        this.pathToFile = pathToFile;
        return this;
    }

    public <K, V> Cache<K, V> build() {
        doCheck();
        return new CacheMaster<K, V>(
                new InMemoryCache(maxInMemorySize),
                new InStorageCache(maxInStorageSize, pathToFile)
        );
    }

    private void doCheck() {
        if (maxInMemorySize < 1)
            throw new IllegalArgumentException("maxInStorageSize must greater 0");
        if (maxInStorageSize < 1)
            throw new IllegalArgumentException("maxInMemorySize must greater 0");
        if (pathToFile == null || pathToFile.isEmpty())
            throw new IllegalArgumentException("pathFile is null or empty");
    }
}