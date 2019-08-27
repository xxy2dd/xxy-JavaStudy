package Concurrent.FooBar;

import java.util.concurrent.Semaphore;

/**
 * @author xxy
 * @date 2019/8/27
 * @description
 */
public class FooBar2 {
    private int n;
    private Semaphore semaphore;
    private Semaphore semaphore1;
    public FooBar2(int n){
        this.n = n;
    }
    public void foo(Runnable printFoo) throws InterruptedException{
        for(int i=0;i<n;i++){
            semaphore.acquire();
            printFoo.run();
            semaphore1.release();
        }
    }
    public void bar(Runnable printBar) throws InterruptedException{
        for(int i=0;i<n;i++){
            semaphore1.acquire();
            printBar.run();
            semaphore.release();
        }
    }
}

