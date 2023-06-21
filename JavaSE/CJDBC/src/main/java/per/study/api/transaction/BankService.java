package per.study.api.transaction;

import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * @Description 银行卡业务方法
 * @Author: Lrwei
 * @Date: 2023/6/21
 **/
public class BankService {

    @Test
    public void start() throws Exception {
        transfer("lvdandan", "ergouzi", 500);
    }

    public void transfer(String addAccount, String subAccount, int money) throws Exception {
        BankDao bankDao = new BankDao();
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql:///atguigu?rewriteBatchedStatements=true", "root", "Init@mysql57");
        try {
            // 开启事务
            connection.setAutoCommit(false); // 关闭事务提交

            bankDao.add(addAccount, money);
            System.out.println("--------");
            bankDao.sub(subAccount, money);

            connection.commit(); // 事务提交
        } catch (Exception e) {
            // 事务回滚
            connection.rollback();
            throw e;
        } finally {
            connection.close();
        }

    }

}
