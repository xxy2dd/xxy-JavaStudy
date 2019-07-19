package JUC;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

/**
 * @author xxy
 * @date 2019/7/19
 * @description
 * 主要用于并行计算
 * 和MapReduce原理类似，都是把大的计算任务拆分成多个小人物并行计算
 *
 */
public class ForkJoinExample extends RecursiveTask<Integer> {
    private final int threshold = 5;
    private int first;
    private int last;

    public ForkJoinExample(int first,int last){
        this.first = first;
        this.last = last;
    }
    @Override
    protected Integer compute(){
        int result = 0;
        if(last-first<=threshold){
            // 任务足够小则直接计算
            for(int i=first;i<=last;i++){
                result +=i;
            }
        }else{
            // 拆分成小任务
            int middle=first+(last-first)/2;
            ForkJoinExample leftTask = new ForkJoinExample(first,middle);
            ForkJoinExample rightTask = new ForkJoinExample(middle,last);
            leftTask.fork();
            rightTask.fork();
            result = leftTask.join()+rightTask.join();
        }
        return result;
    }

    public static void main(String[] args) throws ExecutionException,InterruptedException{
        ForkJoinExample example = new ForkJoinExample(1,10000);
        // ForkJoinPool线程池启动，特殊的线程池，线程数量取决于CPU核数
        // 实现工作窃取算法提高CPU利用率
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        Future future = forkJoinPool.submit(example);
        System.out.println(future.get());
    }
}
