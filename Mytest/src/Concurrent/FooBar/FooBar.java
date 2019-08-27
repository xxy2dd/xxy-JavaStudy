package Concurrent.FooBar;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

/**
 * @author xxy
 * @date 2019/8/23
 * @description
 */
public class FooBar {
    private int n;
    private CountDownLatch a;
    private CyclicBarrier barrier;

    public FooBar(int n) {
        this.n = n;
        a = new CountDownLatch(1);
        barrier = new CyclicBarrier(2);
    }

    public static void main(String[] args) {
        FooBar fb = new FooBar(3);
        printFoo foo = new printFoo();
        printBar bar = new printBar();
        Thread t1 = new Thread(){
            @Override
            public void run(){
                try{
                    fb.foo(foo);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
            }
        };
        Thread t2 = new Thread(){
            @Override
            public void run(){
                try{
                    fb.bar(bar);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
            }
        };
        t1.start();
        t2.start();

    }

    public void foo(Runnable printFoo) throws InterruptedException {
        try {
            for (int i = 0; i < n; i++) {
                printFoo.run();
                a.countDown();
                barrier.await();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {
        try {
            for (int i = 0; i < n; i++) {
                a.await();
                printBar.run();
                a = new CountDownLatch(1);
                barrier.await();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class printFoo implements Runnable {
    @Override
    public void run() {
        System.out.print("foo");
    }
}

class printBar implements Runnable {
    @Override
    public void run() {
        System.out.print("bar");
    }
}


