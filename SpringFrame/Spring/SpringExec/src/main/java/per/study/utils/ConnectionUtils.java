package per.study.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Component
public class ConnectionUtils
{
    private ThreadLocal<Connection> tl = new ThreadLocal<>();

    private DataSource dataSource;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Connection getThreadConnection() {
        Connection conn = tl.get();
        try {
            if (conn == null) {
                conn = dataSource.getConnection();
                tl.set(conn);
            }
            return conn;
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * 把连接与线程解绑
     */
    public void removeConnection() {
        tl.remove();
    }

}
