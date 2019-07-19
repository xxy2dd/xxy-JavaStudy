package AQS;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author xxy
 * @date 2019/7/19
 * @description
 * CountDownLatch 用来控制一个线程等待多个线程
 * 维护一个计数器cnt,每次调用countDown()方法会让cnt-1,
 * 减到0的时候，那些因调用了await()方法而在等待的线程就会被唤醒
 */
public class CountDownLatchExample {
    /**
     * 线程数量
      */
    private static int threadCount = 10;

    public static void main(String[] args){

        ExecutorService executorService = Executors.newCachedThreadPool();

        final CountDownLatch countDownLatch = new CountDownLatch(threadCount);

        for(int i = 1;i<=threadCount;i++){
            final int threadNum = i;
            executorService.execute(()->{
                try{
                    test(threadNum);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }finally{
                    countDownLatch.countDown();
                }
            });
        }
        try {
            // 父任务等待所有子任务都完成的时候， 再继续往下进行。
            countDownLatch.await();
            // 上面线程如果在10 毫秒内未完成，则有可能会执行主线程
//            countDownLatch.await(10, TimeUnit.MILLISECONDS);
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        System.out.println("Finished!");
        executorService.shutdown();
    }

    private static void test(int threadNum) throws InterruptedException{
        Thread.sleep(10);
        System.out.println("run: Thread" + threadNum);
        Thread.sleep(10);
    }
}
