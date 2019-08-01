package CollectionAndMap.HashMap;

/**
 * @author xxy
 * @date 2019/7/27
 * @description
 */
public interface MyMap<K ,V> {
    public V put(K k,V v);
    public V get(K k);

    interface Entry<K,V>{
        public K getKey();
        public V getValue();
    }
}
