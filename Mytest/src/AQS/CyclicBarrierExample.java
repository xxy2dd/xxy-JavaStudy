package AQS;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author xxy
 * @date 2019/7/19
 * @description
 * CyclicBarrier 用来控制多个线程互相等待，只有当多个线程都到达时，这些线程才会继续执行。
 * 执行await()方法之后计数器减1，并进行等待，直至计数器为0，所有调用await()方法而在等待的线程才能继续执行
 * 可通过reset()循环使用
 */
public class CyclicBarrierExample {
    private static CyclicBarrier barrier = new CyclicBarrier(6,()->{
        System.out.println("所有运动员入场，裁判员一声令下！");
    });
    public static void main(String[] args){
        System.out.println("运动员准备进场，全场欢呼...");

        ExecutorService service = Executors.newFixedThreadPool(6);

        for(int i=0;i<6;i++){
            service.execute(()->{
                try{
                    System.out.println(Thread.currentThread().getName()+"运动员，进场");
                    barrier.await();
                    System.out.println(Thread.currentThread().getName()+"运动员，出发");
                }catch (InterruptedException e){
                    e.printStackTrace();
                }catch (BrokenBarrierException e){
                    e.printStackTrace();
                }
            });
        }
        service.shutdown();
    }
}
