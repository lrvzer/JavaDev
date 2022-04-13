package per.study.jdbc.template;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class JdbcTemplateDemo1 {

    public static void main(String[] args) {

        // 配置数据源
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://106.55.32.253:3306/test?useSSL=false");
        dataSource.setUsername("root");
        dataSource.setPassword("qwe@123");

        JdbcTemplate jt = new JdbcTemplate();
        jt.setDataSource(dataSource);
        jt.execute("insert into account(name, money) values ('fff', 1000)");
    }

}
