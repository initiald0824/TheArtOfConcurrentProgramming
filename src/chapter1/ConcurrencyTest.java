package chapter1;

/**
 * @author initiald0824
 * @date 2019/7/8 9:19
 * @note: 感觉都是CPU密集型的操作，不涉及iO，循环次数少时有较多的上下文切换，串行更快，这可以理解。
 * 循环次数变多时，为什么并行计数变快了呢？不明白
 */
public class ConcurrencyTest {
    private static final long count = 100000;

    private static void concurrency() throws InterruptedException {
        long start = System.currentTimeMillis();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                int a = 0;
                for (long i = 0; i < count; i++) {
                    a += 5;
                }
            }
        });
        thread.start();
        int b = 0;
        for (long i = 0; i < count; i++) {
            b--;
        }
        thread.join();
        long time = System.currentTimeMillis() - start;
        System.out.println("concurrency: " + time + "ms, b = " + b);
    }

    private static void serial() {
        long start = System.currentTimeMillis();
        int a = 0;
        for (long i = 0; i < count; i++) {
            a += 5;
        }
        int b = 0;
        for (long i = 0; i < count; i++) {
            b--;
        }
        long time = System.currentTimeMillis() - start;
        System.out.println("serial: " + time + "ms, b = " + b + ", a = " + a);
    }

    public static void main(String[] args) throws InterruptedException {
        concurrency();
        serial();
    }
}
