package chapter2;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author initiald0824
 * @date 2019/7/8 16:03
 */
public class Counter {
    private int i = 0;
    private AtomicInteger atomicInteger = new AtomicInteger(0);

    public static void main(String[] args) {
        final Counter cas = new Counter();
        List<Thread> ts = new ArrayList<>(600);
        long start = System.currentTimeMillis();
        for (int j = 0; j < 100; j++) {
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i< 10000; i++) {
                        cas.count();
                        cas.safeCount();
                    }
                }
            });
            ts.add(t);
        }
        for (Thread t: ts) {
            t.start();
        }

        for (Thread t: ts) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(cas.i);
        System.out.println(cas.atomicInteger.get());
        System.out.println(System.currentTimeMillis() - start);
    }

    private void safeCount () {
        for (; ;) {
            int i = atomicInteger.get();
            boolean success = atomicInteger.compareAndSet(i, i+1);
            if (success) {
                break;
            }
        }
    }

    private void count () {
        i++;
    }
}
