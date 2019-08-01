package CollectionAndMap.HashMap;

import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.hash;

/**
 * @author xxy
 * @date 2019/6/14
 * @description
 */
public class MyHashMap<K,V> implements MyMap<K,V> {
    // 数组的默认初始化长度为16
    private static final int DEFAULT_INITIAL_CAPACITY = 1<<4;
    // 数组默认阈值比列
    private static final float DEFAULT_LOAD_FACTOR = 0.75f;
    private int defaultInitsize;
    private float defaultLoadFactor;
    /**
     *  map中entry的数量
     */
    private int entryUseSize;
    /**
     * 数组
      */
    private Entry<K,V>[] table = null;
    public class Entry<K,V> implements MyMap.Entry<K,V> {
        private K key;
        private V value;
        private Entry<K, V> next;

        public Entry() {

        }

        public Entry(K key, V value, Entry<K, V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }
    }
    /**
     * 构造函数
     * 门面模式，2个构造函数指向同一个
     */
    public MyHashMap(){
        this(DEFAULT_INITIAL_CAPACITY,DEFAULT_LOAD_FACTOR);
    }
    public MyHashMap(int defaultInitCapacity,float defaultLoadFactor){
        if(defaultInitCapacity<0){
            throw new IllegalArgumentException("Illegal initial capacity: " + defaultInitCapacity);
        }
        if(defaultLoadFactor<=0||Float.isNaN(defaultLoadFactor)){
            throw new IllegalArgumentException("Illegal load factor: " + defaultInitCapacity);
        }
        this.defaultInitsize = defaultInitCapacity;
        this.defaultLoadFactor = defaultLoadFactor;
        table = new Entry[this.defaultInitsize];
    }

    @Override
    public V put(K k,V v){
        V oldValue = null;
        // 是否需要扩容
        // 若需要，扩容至原容量的2倍
        if(entryUseSize>=defaultInitsize*defaultLoadFactor){
            resize(2*defaultInitsize);
        }
        int index = hash(k)&(defaultInitsize-1);
        if(table[index]==null){
            table[index] = new Entry<>(k,v,null);
            ++entryUseSize;
        }else{
            Entry<K,V> entry = table[index];
            Entry<K,V> e = entry;
            while(e!=null){
                if(k==e.getKey()||k.equals(e.getKey())){
                    oldValue = e.value;
                    e.value  = v;
                    return oldValue;
                }
                e = e.next;
            }
            table[index] =new Entry<K,V>(k,v,entry);
            ++entryUseSize;
        }
        return oldValue;
    }
    @Override
    public V get(K k){
        int index = hash(k)&(defaultInitsize-1);
        if(table[index]==null){
            return null;
        }else{
            Entry<K,V> entry = table[index];
            do{
                if(k==entry.getKey()||k.equals(entry.getKey())){
                    return entry.value;
                }
                entry = entry.next;
            }while(entry!=null);
        }

        return null;
    }

    private void resize(int i){
        Entry[] newTable = new Entry[i];
        defaultInitsize = i;
        entryUseSize = 0;
        rehash(newTable);
    }

    private void rehash(Entry[] newTable){
        List<Entry<K,V>> entryList = new ArrayList<Entry<K,V>>();
        for(Entry<K,V> entry:table){
            if(entry!=null){
                do{
                    entryList.add(entry);
                    entry = entry.next;
                }while(entry!=null);
            }
        }
        if(newTable.length>0){
            table = newTable;
        }
        for(Entry<K,V> entry:entryList){
            put(entry.getKey(),entry.getValue());
        }
    }
}
