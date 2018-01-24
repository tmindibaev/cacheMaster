package cache;

public interface Cache<K, V> // key, value
{
    void put(K key, V value);

    boolean contains(K key);

    V get(K key);

    void remove(K key);

    void clear();

    int size();

    boolean isFull();
}