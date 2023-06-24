package per.study.api.druid;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @Description Druid连接池使用类
 * @Author: Lrwei
 * @Date: 2023/6/24
 **/
public class DruidUserPart {
    /**
     * 直接使用代码设置连接池连接参数方式
     * 1.创建一个druid连接池对象
     * 2.设置连接池参数 [必须｜非必须]
     * 3.获取连接 [通用方法，所有连接池]
     * 4.回收连接 [通用方法，左右连接池都一样]
     */
    public void testHard() throws SQLException {
        DruidDataSource dataSource = new DruidDataSource();
        // 设置参数
        // 必须 连接数据库驱动类的全限定符 [注册启动] url user passwd
        dataSource.setUrl("jdbc:mysql://localhost:3306/atguigu");
        dataSource.setUsername("root");
        dataSource.setPassword("Init@mysql57");
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");

        // 非必须 初始化连接数量 最大的连接数量
        dataSource.setInitialSize(5);
        dataSource.setMaxActive(10);

        // 获取连接
        Connection connection = dataSource.getConnection();

        // 数据库CRUD

        // 回收连接
        connection.close();
    }

    /**
     * 通过读取外部配置文件的方法吗，实例化druid连接池对象
     */
    public void testSoft() throws Exception {
        // 1.读取外部配置文件Properties
        Properties properties = new Properties();
        InputStream ips = this.getClass().getClassLoader().getResourceAsStream("druid.properties");
        properties.load(ips);
        // 使用连接池的工具类的工程模式，创建连接池
        DataSource dataSource = DruidDataSourceFactory.createDataSource(properties);
        Connection connection = dataSource.getConnection();
        // TODO CRUD
        connection.close();
    }
}
