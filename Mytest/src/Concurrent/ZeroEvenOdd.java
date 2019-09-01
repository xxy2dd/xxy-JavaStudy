package Concurrent;

/**
 * @author xxy
 * @date 2019/8/30
 * @description
 */
public class ZeroEvenOdd {
    private int start = 0;
    private boolean flagZero = true;

    public static void main(String[] args) {
        ZeroEvenOdd threeThread = new ZeroEvenOdd();
        Thread t0 = new Thread(new Zero(threeThread));
        Thread t1 = new Thread(new Odd(threeThread));
        Thread t2 = new Thread(new Even(threeThread));
        t0.start();
        t1.start();
        t2.start();
    }

    public static class Zero implements Runnable {
        private ZeroEvenOdd number;

        public Zero(ZeroEvenOdd number) {
            this.number = number;
        }

        @Override
        public void run() {
            while (number.start < 5) {
                synchronized (ZeroEvenOdd.class) {
                    if (!number.flagZero) {
                        try {
                            ZeroEvenOdd.class.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.print("0");
                    if(number.start==0){
                        number.start++;
                    }
                    number.flagZero = false;
                    ZeroEvenOdd.class.notifyAll();
                }
            }
        }
    }

    public static class Odd implements Runnable {
        private ZeroEvenOdd number;

        public Odd(ZeroEvenOdd number) {
            this.number = number;
        }

        @Override
        public void run() {
            while (number.start <= 5) {
                synchronized (ZeroEvenOdd.class) {
                    if (!number.flagZero && number.start % 2 == 0) {
                        System.out.print(number.start);
                        number.start++;
                        number.flagZero = true;
                        ZeroEvenOdd.class.notify();
                    } else {
                        try {
                            ZeroEvenOdd.class.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    public static class Even implements Runnable {
        private ZeroEvenOdd number;

        public Even(ZeroEvenOdd number) {
            this.number = number;
        }

        @Override
        public void run() {
            while (number.start <= 5) {
                synchronized (ZeroEvenOdd.class) {
                    if (!number.flagZero && number.start % 2 != 0) {
                        System.out.print(number.start);
                        number.start++;
                        number.flagZero = true;
                        ZeroEvenOdd.class.notify();
                    } else {
                        try {
                            ZeroEvenOdd.class.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }
}
