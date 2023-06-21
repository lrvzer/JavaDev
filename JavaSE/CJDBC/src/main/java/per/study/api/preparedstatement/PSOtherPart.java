package per.study.api.preparedstatement;

import org.junit.Test;

import java.sql.*;

public class PSOtherPart {

    @Test
    public void returnPrimaryKey() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql:///atguigu", "root", "Init@mysql57");
        String sql = "insert into t_user(login_name, nick_name, passwd) values (?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setObject(1, "lisi");
        preparedStatement.setObject(2, "李四");
        preparedStatement.setObject(3, "123456");

        int rows = preparedStatement.executeUpdate();
        if (rows > 0) {
            System.out.println("插入成功");
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();
            int id = resultSet.getInt(1);
            System.out.println("id=" + id);
        } else {
            System.out.println("插入失败");
        }
        preparedStatement.close();
        connection.close();
    }


    /**
     * jdbc:mysql:///atguigu?rewriteBatchedStatements=true
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    @Test
    public void testBacthInsert() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql:///atguigu?rewriteBatchedStatements=true", "root", "Init@mysql57");
        String sql = "insert into t_user(login_name, nick_name, passwd) values (?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

        for (int i = 0; i < 10000; i++) {
            preparedStatement.setObject(1, "lisi" + i);
            preparedStatement.setObject(2, "李四" + i);
            preparedStatement.setObject(3, "123456" + i);
            preparedStatement.addBatch(); // 不执行，追加到values后面
        }
        preparedStatement.executeBatch(); // 执行批量操作

        preparedStatement.close();
        connection.close();
    }


}
