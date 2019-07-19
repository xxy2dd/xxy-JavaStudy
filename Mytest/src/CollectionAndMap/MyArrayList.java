package CollectionAndMap;

import java.util.Arrays;
import java.util.Collection;

/**
 * @author xxy
 * @date 2019/5/8
 * @description
 */
public class MyArrayList<E>  {
    private static final long serialVersionUID = 8683452581122892189L;
    /**
     * 初始容量为10
     */
    private static final int  DEFAULT_CAPACITY = 10;
    /**
     * 空数组
     */
    private static final Object[] EMPTY_ELEMENTDATA = {};
    /**
     * 共享的空数组实例，作用是对于默认大小的空数组，使其区别于
     * EMPTY_ELEMENTDATA，这样可以知道在添加第一个元素时容量需要增加多少。
     */
    private static final Object[] DEFAULTCAPACITY_EMPTY_ELEMENTDATA = {};
    /**
     * 存储ArrayList元素的数组，ArrayList的容量就是这个缓冲区的容量。
     当elementData == DEFAULTCAPACITY_EMPTY_ELEMENTDATA时，
     第一次添加元素后，扩容至默认容量10。非私有域便于嵌套类的访问。

     另外虽然这里用了transient修饰，但是其实现了readObject和writeObject
     for (int i = 0; i < size; i++)
     s.writeObject(elementData[i]);
     查看源码可知,实现了序列化
     */
    transient Object[] elementData;

    private int size;
    /**
     * 记录List被修改的次数
     */
    protected transient int modCount = 0;

    /**
     * 无参构造函数
     */
    public MyArrayList(){
        this.elementData = DEFAULTCAPACITY_EMPTY_ELEMENTDATA;
    }

    /**
     * 带初始容量的构造函数
     * @param initialCapacity
     */
    public MyArrayList(int initialCapacity){
        if(initialCapacity > 0){
            // 创建容量为传入值大小的数组
            this.elementData = new Object[initialCapacity];
        }else if(initialCapacity == 0){
            // 创建空数组
            this.elementData = EMPTY_ELEMENTDATA;
        }else{
            // 参数合法性判断
            throw new IllegalArgumentException("Illegal Capacity:" + initialCapacity);
        }
    }

    /**
     *  构造一个包含指定集合的元素的列表，按照它们由集合的迭代器返回的顺序。
     * @param c
     */
    public MyArrayList(Collection<? extends E> c){
        // Arrays 的 copyOf 本质上还是用 System.arraycopy 实现的，是JVM 提供的数组拷贝实现
        elementData = c.toArray();
        // 数组长度是否为0 判断 为0则赋值为空数组
        if((size=elementData.length)!=0){
            // 这里有个bug，c.toArray()可能不会返回Object[] ,
            if(elementData.getClass()!=Object[].class){
                elementData = Arrays.copyOf(elementData,size,Object[].class);
            }
        }else{
            this.elementData = EMPTY_ELEMENTDATA;
        }
    }

    /**
     * 将元素追加到List尾部
     * @param e
     * @return
     */
    public boolean add(E e){
        // 判断容量是否足够
        ensureCapacityInternal(size+1);
        elementData[size++] = e;
        return true;

    }
    private void ensureCapacityInternal(int minCapacity){
        if(elementData == DEFAULTCAPACITY_EMPTY_ELEMENTDATA){
            minCapacity = Math.max(DEFAULT_CAPACITY,minCapacity);
        }
        ensureExplicitCapacity(minCapacity);
    }
    private void ensureExplicitCapacity(int minCapacity){
        modCount++;
        if(minCapacity - elementData.length > 0){
            // 扩容
            grow(minCapacity);
        }

    }
    private int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;
    private void grow(int minCapacity){
        // 扩容至原来容量的1.5倍
        int oldCapacity = elementData.length;
        int newCapacity = oldCapacity + (oldCapacity>>1);
        if(newCapacity - minCapacity < 0 ){
            newCapacity = minCapacity;
        }
        if(newCapacity < MAX_ARRAY_SIZE){
            newCapacity = hugeCapacity(minCapacity);
        }
        // 复制原数组信息，底层还是调用的 System.arraycopy 代价很高
        elementData = Arrays.copyOf(elementData,newCapacity);
    }
    private int hugeCapacity(int minCapacity){
        if(minCapacity<0){
            throw new OutOfMemoryError();
        }
        return (minCapacity>MAX_ARRAY_SIZE)?Integer.MAX_VALUE:MAX_ARRAY_SIZE;
    }
    public E remove(int index){
        rangeCheck(index);
        modCount++;

        E oldValue = elementData(index);

        int numMoved = size-index-1;
        if(numMoved>0){
            System.arraycopy(elementData,index+1,elementData,index,numMoved);
        }
        elementData[--size] = null;
        return oldValue;
    }
    private void rangeCheck(int index){
        if(index>=size){
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
        }
    }
    private String outOfBoundsMsg(int index){
        return "Index: " + index +", Size: " +size;
    }
    @SuppressWarnings("unchecked")
    private  E elementData(int index){
        return (E) elementData[index];
    }
}
