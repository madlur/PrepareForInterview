package lesson_03;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Counter {

    private int counter = 0;
    private final Lock lock = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {

        Counter counter = new Counter();

        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(() -> {
                for (int j = 0; j < 100; j++) {
                    counter.increaseCounter();
                }
            });
            thread.start();
        }
        Thread.sleep(1000);
        System.out.println(counter.getCounter());
    }

    public void increaseCounter() {

        try {
            lock.lock();
            counter++;
            System.out.printf("counter = %d %s%n", counter, Thread.currentThread().getName());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public int getCounter() {
        return counter;
    }

}
