package per.study.api.transactionnew;

import org.junit.Test;
import per.study.api.utils.JdbcUtils;

import java.sql.Connection;

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
        Connection connection = JdbcUtils.getConnection();
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
            JdbcUtils.freeConnection();
        }

    }

}
