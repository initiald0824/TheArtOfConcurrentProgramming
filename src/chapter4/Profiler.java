package chapter4;

import sun.rmi.runtime.Log;

import java.util.concurrent.TimeUnit;

/**
 * @author initiald0824
 * @date 2019/7/9 20:48
 */
public class Profiler {
    private static final ThreadLocal<Long> THREAD_LOCAL = new ThreadLocal<Long>() {
        @Override
        protected Long initialValue() {
            return System.currentTimeMillis();
        }
    };

    public static final void begin () {
        THREAD_LOCAL.set(System.currentTimeMillis());
    }

    public static final long end () {
        return System.currentTimeMillis() - THREAD_LOCAL.get();
    }

    public static void main(String[] args) throws Exception {
        Profiler.begin();
        TimeUnit.SECONDS.sleep(1);
        System.out.println("Cost: " +  Profiler.end() + " mills");
    }
}
