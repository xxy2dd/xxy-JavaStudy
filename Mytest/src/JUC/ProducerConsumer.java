package JUC;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @author xxy
 * @date 2019/7/19
 * @description
 * 用BlockingQueue 实现生产者消费者问题
 * FIFO队列：LinkedBlockingQueue  ArrayBlockingQueue
 * 优先级队列：PriorityBlockingQueue
 * 提供了阻塞的take()和put()
 * 如果队列为空 take()阻塞，直到队列中有内容
 * 如果队列为满 put()阻塞，直到队列有空闲位置
 */
public class ProducerConsumer {
    private static BlockingQueue<String> queue = new ArrayBlockingQueue<String>(5);

    private static class Producer extends Thread{
        @Override
        public void run(){
            try{
                queue.put("product");
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            System.out.println("produce..");
        }
    }
    private static class Consumer extends Thread{
        @Override
        public void run(){
            try{
                String product = queue.take();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            System.out.println("consume..");
        }
    }
    public static void main(String[] args){
        for(int i=0;i<2;i++){
            Producer producer = new Producer();
            producer.start();
        }
        for(int i=0;i<5;i++){
            Consumer consumer = new Consumer();
            consumer.start();
        }
        for(int i=0;i<3;i++){
            Producer producer = new Producer();
            producer.start();
        }
    }
}
