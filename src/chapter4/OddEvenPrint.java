package chapter4;

/**
 * @author initiald0824
 * @date 2019/7/9 19:00
 * @desc 两个线程分别打印奇数，偶数，让他们同时运行，连续打印
 */
public class OddEvenPrint {
    private int count = 0;

    public void increase () {
        count++;
    }

    public static void main(String[] args) {
        OddEvenPrint oddEvenPrint = new OddEvenPrint();
        new Thread(() -> {
            synchronized (oddEvenPrint) {
                while (oddEvenPrint.count <= 100) {
                    if (oddEvenPrint.count % 2 == 1) {
                        System.out.println("odd Thread: " + oddEvenPrint.count);
                        oddEvenPrint.increase();
                        oddEvenPrint.notify();
                    } else {
                        try {
                            oddEvenPrint.wait();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }).start();
        new Thread(() -> {
            synchronized (oddEvenPrint) {
                while (oddEvenPrint.count <= 100) {
                    if (oddEvenPrint.count % 2 == 0) {
                        System.out.println("even Thread: " + oddEvenPrint.count);
                        oddEvenPrint.increase();
                        oddEvenPrint.notify();
                    } else {
                        try {
                            oddEvenPrint.wait();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }).start();
    }

}
