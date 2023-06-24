package per.study.api.transactionnew;

import per.study.api.utils.JdbcUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

/**
 * @Description
 * @Author: Lrwei
 * @Date: 2023/6/21
 **/
public class BankDao {
    /**
     * 加钱操作
     *
     * @param account
     * @param money
     */
    public void add(String account, int money) throws Exception {
        Connection connection = JdbcUtils.getConnection();

        String sql = "update t_bank set money = money + ? where account = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

        preparedStatement.setObject(1, money);
        preparedStatement.setObject(2, account);
        preparedStatement.executeUpdate();

        preparedStatement.close();
        connection.close();
        System.out.println("存钱成功");
    }

    /**
     * 减钱操作
     *
     * @param account
     * @param money
     */
    public void sub(String account, int money) throws Exception {
        Connection connection = JdbcUtils.getConnection();
        String sql = "update t_bank set money = money - ? where account = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

        preparedStatement.setObject(1, money);
        preparedStatement.setObject(2, account);
        preparedStatement.executeUpdate();

        preparedStatement.close();
        connection.close();
        System.out.println("取钱成功");
    }
}
