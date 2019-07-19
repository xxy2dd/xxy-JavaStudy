package Concurrent;

import java.util.concurrent.*;

/**
 * @author xxy
 * @date 2019/7/5
 * @description
 */
public  final class MyThreadPool {

    // 线程池维护的线程最少数量
    private static final int SIZE_CORE_POOL = 5;
    // 线程池维护的线程最大数量
    private static final int SIZE_MAX_POOL = 10;
    // 线程池维护线程所允许的空闲时间
    private static final int TIME_KEEP_ALIVE = 5000;
    // 线程池所使用的缓冲队列大小
    private static final int SIZE_WORK_QUEUE = 500;
    // 用来储存等待执行任务的队列
    private static final BlockingQueue<Runnable> BLOCKING_QUEUE = new ArrayBlockingQueue<Runnable>(10);

    public static class MyTask implements Runnable{
        @Override
        public void run(){
            System.out.println(Thread.currentThread().getName()+"正在运行！");
            try{
                Thread.sleep(100);
            }catch (InterruptedException e){
                System.out.println("中断异常");
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"运行结束！");
        }
    }
    public static class MyThreadFactory implements ThreadFactory{
        @Override
        public Thread newThread(Runnable runnable){
            Thread t = new Thread(runnable);
            System.out.println("生成线程"+t);
            return t;
        }

    }
    public static void main(String[] args){
        MyThreadFactory factory = new MyThreadFactory();
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(SIZE_CORE_POOL,SIZE_MAX_POOL,TIME_KEEP_ALIVE, TimeUnit.MILLISECONDS, BLOCKING_QUEUE,factory);

        for(int i = 0;i<5;i++){
            MyTask runnable = new MyTask();
            threadPoolExecutor.execute(runnable);
        }
        threadPoolExecutor.shutdown();
    }




}
