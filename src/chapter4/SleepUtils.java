package chapter4;

import java.util.concurrent.TimeUnit;

/**
 * @author initiald0824
 * @date 2019/7/9 8:41
 */
public class SleepUtils {
    public static final void second (long seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
