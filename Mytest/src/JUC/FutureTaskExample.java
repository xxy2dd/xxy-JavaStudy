package JUC;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author xxy
 * @date 2019/7/19
 * @description
 * FutureTask 继承Runnable和Future接口，所以既可以当做一个任务执行，也可以有返回值
 * 用于异步获取执行结果或取消执行任务的场景，
 * 当一个计算任务需要执行很长时间时，可以用FutureTask来封装这个任务
 */
public class FutureTaskExample {
    public static void main(String[] args) throws ExecutionException{
        FutureTask<Integer> futureTask = new FutureTask<Integer>(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                int result = 0;
                for(int i=0;i<100;i++){
                    Thread.sleep(10);
                    result += i;
                }
                return result;
            }
        });
        Thread computeThread = new Thread(futureTask);
        computeThread.start();
        Thread otherThread = new Thread(()->{
            System.out.println("other task is running...");
            try{
                Thread.sleep(1000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        });
        otherThread.start();
        int res = 0;
        try {
           res =  futureTask.get();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.println(res);
    }
}
