package per.study.api.utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * 实现：
 * 属性 连接池对象 [只实例化一次]
 *      单例模式
 *      static {
 *          // 全局调用一次
 *      }
 * 方法
 *      对外提供连接的方法
 *      回收外部传入连接方式
 **/
public class JdbcUtils {
    private static DataSource dataSource = null; // 连接池对象
    private static ThreadLocal<Connection> tl = new ThreadLocal<>();

    static {
        Properties properties = new Properties();
        InputStream ips = JdbcUtils.class.getClassLoader().getResourceAsStream("druid.properties");
        try {
            properties.load(ips);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            dataSource = DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static Connection getConnection() throws SQLException {
        Connection connection = tl.get();
        if (connection == null) {
            connection = dataSource.getConnection();
            tl.set(connection);
        }
        return connection;
    }

    public static void freeConnection( ) throws SQLException {
        Connection connection = tl.get();
        if (connection != null) {
            tl.remove();
            connection.setAutoCommit(true);
            connection.close();
        }
    }

}
