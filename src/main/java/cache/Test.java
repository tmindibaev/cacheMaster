package cache;

public class Test {
    public static void main(String[] args) {

        Cache<String, String> cache = new CacheBuilder()
                .setMaxInMemorySize(10)
                .setMaxInStorageSize(100)
                .setPathToFile("dir")
                .build();

        cache.put("1", "a");
        cache.put("2", "b");
        cache.put("2", "b");

        cache.get("3");
        cache.get("2");
        cache.get("1");
    }
}
