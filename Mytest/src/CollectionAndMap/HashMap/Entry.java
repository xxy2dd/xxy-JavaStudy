package CollectionAndMap.HashMap;

/**
 * @author xxy
 * @date 2019/7/27
 * @description
 */
public class Entry<K,V> implements MyMap.Entry<K,V> {
    private K key;
    private V value;
    private Entry<K,V> next;

    public Entry(){

    }
    public Entry(K key,V value,Entry<K,V> next){
        this.key = key;
        this.value = value;
        this.next = next;
    }
    @Override
    public K getKey(){
        return key;
    }
    @Override
    public V getValue(){
        return value;
    }
}
