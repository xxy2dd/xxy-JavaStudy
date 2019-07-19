package AQS;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @author xxy
 * @date 2019/7/19
 * @description
 * Semaphore 类似于操作系统的信号量，可以控制对互斥资源的访问进程数
 * 维护了当前访问的个数，通过提供同步机制来控制同时访问的个数
 * acquire()访问资源
 * release()释放资源
 */
public class SemaphoreExample {
    // Semaphore 允许的最大并发执行线程数
    private static int clientCount = 3;
    private static int totalRequestCount = 10;

    public static void main(String[] args){
        ExecutorService executorService = Executors.newCachedThreadPool();

        final Semaphore semaphore = new Semaphore(clientCount);

        for(int i=1;i<=totalRequestCount;i++){
            final int threadNum = i;
            executorService.execute(()->{
                try{
                    // 每次获取一个许可，也可设置参数acquire(3)每次获取三个许可，设置参数时相当于单线程
                    semaphore.acquire();
                    test(threadNum);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }finally {
                    semaphore.release();
                }
            });
        }
        executorService.shutdown();
    }
    private static void test(int threadNum) throws InterruptedException{
        System.out.println("run: "+threadNum);
        Thread.sleep(1000);
    }
}
