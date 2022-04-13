package per.study.config;

import java.beans.PropertyVetoException;

import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import per.study.utils.ConnectionUtils;

@Configuration
public class JDBCConfig
{

    @Value("${jdbc.driver}")
    private String driver;

    @Value("${jdbc.url}")
    private String url;

    @Value("${jdbc.username}")
    private String username;

    @Value("${jdbc.password}")
    private String password;

//    @Bean("queryRunner")
//    @Scope("prototype")
//    public QueryRunner getQueryRunner(@Qualifier("dataSource") DataSource dataSource) {
//        return new QueryRunner(dataSource);
//    }

    @Bean("queryRunner")
    @Scope("prototype")
    public QueryRunner getQueryRunner() {
        return new QueryRunner();
    }

    @Bean("dataSource")
    public DataSource getDataSource() {
        try {
            ComboPooledDataSource ds = new ComboPooledDataSource();
            ds.setDriverClass(driver);
            ds.setJdbcUrl(url);
            ds.setUser(username);
            ds.setPassword(password);
            return ds;
        }
        catch (PropertyVetoException e) {
            throw new RuntimeException(e);
        }
    }

    @Bean("dataSource1")
    public DataSource getDataSource1() {
        try {
            ComboPooledDataSource ds = new ComboPooledDataSource();
            ds.setDriverClass("com.mysql.jdbc.Driver");
            ds.setJdbcUrl("jdbc:mysql://106.55.32.253:3306/test?useSSL=false");
            ds.setUser("root");
            ds.setPassword("qwe@123");
            return ds;
        }
        catch (PropertyVetoException e) {
            throw new RuntimeException(e);
        }
    }

}
