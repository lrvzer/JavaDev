package per.study.api.preparedstatement;

import java.sql.*;

/**
 * 使用预编译statement完成用户登陆
 * TODO 防止注入攻击 ｜ 演示PreparedStatement使用流程
 **/
public class PsUserLoginPart {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql:///atguigu", "root", "Init@mysql57");
        String sql = "select * from t_user where login_name = ? and passwd = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setObject(1, "zhangsan");
        preparedStatement.setObject(2, "123456");

        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String loginName = resultSet.getString("login_name");
            String nickName = resultSet.getString("nick_name");
            String passwd = resultSet.getString("passwd");
            System.out.println(id + "----" + loginName + "----" + nickName + "----" + passwd);
        }
        resultSet.close();
        preparedStatement.close();
        connection.close();
    }
}
