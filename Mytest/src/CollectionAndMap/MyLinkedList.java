package CollectionAndMap;

import java.util.*;

/**
 * @author xxy
 * @date 2019/7/16
 * @description
 */
public class MyLinkedList<E> {
    private static class Node<E> {
        E item;
        Node<E> next;
        Node<E> prev;

        Node(Node<E> prev, E element, Node<E> next) {
            this.item = element;
            this.prev = prev;
            this.next = next;
        }
    }

    transient int size = 0;
    protected transient int modeCount = 0;
    transient Node<E> first;
    transient Node<E> last;

    public MyLinkedList() {
    }

    private void linkLast(E e) {
        // 记录当前尾节点
        final Node<E> l = last;
        // 新建新节点，pre指向l
        final Node<E> newNode = new Node<>(l, e, null);
        // 尾节点指向newNode
        last = newNode;
        // 判断尾节点是否为空
        if (l == null) {
            first = newNode;
        } else {
            l.next = newNode;
        }
        size++;
        modeCount++;
    }

    private E unlink(Node<E> x) {
        // 假设x!=null
        final E element = x.item;
        final Node<E> next = x.next;
        final Node<E> prev = x.prev;
        // 判断是否为头结点
        if (prev == null) {
            first = next;
        } else {
            prev.next = next;
            x.prev = null;
        }
        // 判断是否是尾节点
        if (next == null) {
            last = prev;
        } else {
            next.prev = prev;
            x.next = null;
        }
        x.item = null;
        size--;
        modeCount++;
        return element;
    }

    private E unlinkFirst(Node<E> f) {
        // 假设 f == first && f != null
        final E element = f.item;
        final Node<E> next = f.next;
        f.item = null;
        // help GC
        f.next = null;
        // 改变first指针指向
        first = next;
        // 判断next是否为null
        if (next == null) {
            last = null;
        } else {
            next.prev = null;
        }
        size--;
        modeCount++;
        return element;
    }

    public int size(){
        return size;
    }

    public boolean add(E e) {
        // 在尾部添加元素
        linkLast(e);
        return true;
    }

    public boolean remove(Object o) {
        // 对删除空元素特殊处理
        if (o == null) {
            for (Node<E> x = first; x != null; x = x.next) {
                if (x.item == null) {
                    unlink(x);
                    return true;
                }
            }
        } else {
            for (Node<E> x = first; x != null; x = x.next) {
                if (o.equals(x.item)) {
                    unlink(x);
                    return true;
                }
            }
        }
        return false;
    }


    /**
     * 获取头结点的值
     */
    public E peek() {
        final Node<E> f = first;
        return (f == null) ? null : f.item;
    }

    /**
     * 获取头节点并删除
     */
    public E poll() {
        final Node<E> f = first;
        return (f == null) ? null : unlinkFirst(f);
    }

    public E getFirst(){
        final Node<E> f = first;
        if(f==null){
            throw new NoSuchElementException();
        }
        return f.item;
    }

    public E element(){
        return getFirst();
    }

    public ListIterator<E> listIterator(int index) {
        return null;
    }
}
