package Concurrent;

/**
 * @author xxy
 * @date 2019/8/19
 * @description
 */
public class TwoThreadWaitNotify {
    private int start = 1;
    private boolean flag = false;
    public static void main(String[] args){
        TwoThreadWaitNotify twoThread = new TwoThreadWaitNotify();
        Thread t1 = new Thread(new Odd(twoThread));
        t1.setName("A");
        Thread t2 = new Thread(new Even(twoThread));
        t2.setName("B");
        t1.start();
        t2.start();
    }
    public static class Odd implements Runnable{
        private TwoThreadWaitNotify number;
        public Odd(TwoThreadWaitNotify number){
            this.number = number;
        }
        @Override
        public void run(){
           while(number.start<=10){
               synchronized (TwoThreadWaitNotify.class){
                   System.out.println("奇数线程抢到了锁");
                   if(!number.flag){
                       System.out.println(Thread.currentThread().getName());
                       number.start++;
                       number.flag=true;
                       TwoThreadWaitNotify.class.notify();
                   }else{
                       try{
                           TwoThreadWaitNotify.class.wait();
                       }catch (InterruptedException e){
                           e.printStackTrace();
                       }
                   }
               }
           }
        }
    }
    public static class Even implements Runnable{
        private TwoThreadWaitNotify number;
        public Even(TwoThreadWaitNotify number){
            this.number = number;
        }
        @Override
        public void run(){
            while(number.start<=10){
                synchronized (TwoThreadWaitNotify.class){
                    System.out.println("偶数线程抢到锁");
                    if(number.flag){
                        System.out.println(Thread.currentThread().getName());
                        number.start++;
                        number.flag = false;
                        TwoThreadWaitNotify.class.notify();
                    }else{
                        try{
                            TwoThreadWaitNotify.class.wait();
                        }catch (InterruptedException e){
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }
}
