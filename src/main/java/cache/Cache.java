package cache;

public interface Cache<K, V> // key, value
{
    int put(K key, V value);

    boolean contains(K key);

    V get(K key);

    int remove(K key);

    int clear();

    int size();

    //boolean isFull();

    //boolean isEmpty();
}