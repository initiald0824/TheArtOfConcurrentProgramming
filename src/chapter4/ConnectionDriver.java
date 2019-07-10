package chapter4;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.util.concurrent.TimeUnit;

/**
 * @author initiald0824
 * @date 2019/7/9 21:16
 */
public class ConnectionDriver {

    private Object target;

    public ConnectionDriver(Object target) {
        this.target = target;
    }

    /**
     * 创建一个Connection的代理，在commit时休眠100毫秒
     * @return
     */
    public static final Connection createConnection() {
        return (Connection) Proxy.newProxyInstance(ConnectionDriver.class.getClassLoader(),
                new Class<?>[] {Connection.class}, new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        if (method.getName().equals("commit")) {
                            TimeUnit.MILLISECONDS.sleep(100);
                        }
                        return null;
                    }
                });
    }
}
