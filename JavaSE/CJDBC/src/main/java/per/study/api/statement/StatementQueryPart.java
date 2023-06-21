package per.study.api.statement;

import com.mysql.cj.jdbc.Driver;

import java.sql.*;

public class StatementQueryPart {
    public static void main(String[] args) throws SQLException {
        // 1.注册驱动
        /**
         * 注册驱动
         * 依赖：驱动版本8+ com.mysql.cj.jdbc.Driver
         *      驱动版本5+ com.mysql.jdbc.Driver
         */
        DriverManager.registerDriver(new Driver());
        // 2.获取链接
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/atguigu", "root", "Init@mysql57");
        // 3.创建statement
        Statement statement = conn.createStatement();
        // 4.发送sql语句，并获取返回结果
        String sql = "select * from t_user";
        ResultSet resultSet = statement.executeQuery(sql);
        // 5.结果集解析
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String loginName = resultSet.getString("login_name");
            String nickName = resultSet.getString("nick_name");
            String passwd = resultSet.getString("passwd");
            System.out.println(id + "----" + loginName + "----" + nickName + "----" + passwd);
        }
        // 6.释放资源
        resultSet.close();
        statement.close();
        conn.close();
    }
}
