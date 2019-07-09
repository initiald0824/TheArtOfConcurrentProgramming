package chapter3;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author initiald0824
 * @date 2019/7/8 19:47
 */
public class ReentrantLockExample {
    int a = 0;
    ReentrantLock lock = new ReentrantLock();

    public void writer () {
        lock.lock();
        try {
            a++;
        } finally {
            lock.unlock();
        }
    }

    public void reader () {
        lock.lock();
        try {
            int i = a;
        } finally {
            lock.unlock();
        }
    }
}
