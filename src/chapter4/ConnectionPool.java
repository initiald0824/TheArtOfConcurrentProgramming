package chapter4;

import java.sql.Connection;
import java.util.LinkedList;

/**
 * @author initiald0824
 * @date 2019/7/9 21:10
 */
public class ConnectionPool {
    private LinkedList<Connection> pool = new LinkedList<>();

    public ConnectionPool(int initialSize) {
        if (initialSize > 0) {
            for (int i = 0; i < initialSize ;i ++) {
                pool.addLast(ConnectionDriver.createConnection());
            }
        }
    }
}
