package per.study.jdbc.template;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import per.study.domain.Account;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class JdbcTemplateDemo3
{

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");
        JdbcTemplate jdbcTemplate = (JdbcTemplate) context.getBean("jdbcTemplate");

        // 保存
        // jdbcTemplate.update("insert into account(name, money) values (?, ?)", "hhh",
        // 1000F);

        // update
        // jdbcTemplate.update("update account set name = ? where id = ?", "test", 7);

        // 删除
        // jdbcTemplate.update("delete from account where id = ?", 8);

        // 查询所有
        // List<Account> accounts = jdbcTemplate.query("select * from account where
        // money > ?", new AccountRowMapper(),10F);
        // List<Account> accounts = jdbcTemplate.query("select * from account where
        // money > ?", new BeanPropertyRowMapper<>(Account.class),
        // 10F);
        // System.out.println(accounts);

        // 查询一个
        List<Account> accounts = jdbcTemplate.query("select * from account where id = ?",
                new BeanPropertyRowMapper<>(Account.class), 7);

        System.out.println(accounts.isEmpty() ? "null" : accounts.get(0));

        // 查询返回一行一列
        Long count = jdbcTemplate.queryForObject("select count(*) from account where money > ?", Long.class, 100f);
        System.out.println(count);
    }

}

class AccountRowMapper implements RowMapper<Account>
{

    @Override
    public Account mapRow(ResultSet rs, int rowNum) throws SQLException {
        Account account = new Account();
        account.setId(rs.getInt("id"));
        account.setName(rs.getString("name"));
        account.setMoney(rs.getFloat("money"));
        return account;
    }
}
