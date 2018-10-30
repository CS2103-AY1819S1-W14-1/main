package seedu.clinicio.model.analytics.data;

/**
 *
 * @param <K>
 * @param <V>
 */
public class Tuple<K, V> {
    private K key;
    private V value;

    public Tuple(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }

    public void setKey(K newKey) {
        key = newKey;
    }

    public void setValue(V newValue) {
        value = newValue;
    }

    public void setTuple(Tuple tuple) {

    }
}
