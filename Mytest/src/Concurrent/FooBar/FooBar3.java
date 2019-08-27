package Concurrent.FooBar;

/**
 * @author xxy
 * @date 2019/8/27
 * @description
 */
public class FooBar3 {
    private int n;
    private Object lock = new Object();
    private boolean flag = false;
    public FooBar3(int n){
        this.n = n;
    }
    public void foo(Runnable printFoo) throws InterruptedException{
        for(int i=0;i<n;i++){
            synchronized (lock){
                while(flag){
                    lock.wait();
                }
                printFoo.run();
                flag = true;
                lock.notifyAll();
            }
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {
        for(int i=0;i<n;i++){
            synchronized (lock){
                while(!flag){
                    lock.wait();
                }
                printBar.run();
                flag = false;
                lock.notifyAll();
            }
        }
    }
}
