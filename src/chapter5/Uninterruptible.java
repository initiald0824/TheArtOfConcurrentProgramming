package chapter5;

/**
 * @author initiald0824
 * @date 2019/7/10 16:38
 */
public class Uninterruptible {

    private static final Object o1 = new Object();
    private static final Object o2 = new Object();

    static Thread t1 = new Thread(() -> {
       synchronized (o1) {
           try {
               System.out.println("start t1");
               Thread.sleep(1000);
               synchronized (o2) {
                   Thread.sleep(1000);
                   System.out.println("t1 lock o2");
               }
           } catch (InterruptedException e) {
               System.out.println("t1 interrupted");
               e.printStackTrace();
           }
       }
    });

    static Thread t2 = new Thread(() -> {
       synchronized (o2) {
           try {
               System.out.println("start t2");
               Thread.sleep(1000);
               synchronized (o1) {
                   System.out.println("t2 lock o1");
               }
           } catch (InterruptedException e) {
               System.out.println("t2 interrupted");
               e.printStackTrace();
           }
       }
    });

    public static void main(String[] args) throws Exception {
        t1.start();
        Thread.sleep(1000);
        t1.interrupt();
    }
}
