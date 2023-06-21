package per.study.api.preparedstatement;

import org.junit.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PSCURDPart {

    @Test
    public void testInsert() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql:///atguigu", "root", "Init@mysql57");
        String sql = "insert into t_user(login_name, nick_name, passwd) values (?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setObject(1, "lisi");
        preparedStatement.setObject(2, "李四");
        preparedStatement.setObject(3, "123456");

        int rows = preparedStatement.executeUpdate();
        if (rows > 0) {
            System.out.println("插入成功");
        } else {
            System.out.println("插入失败");
        }
        preparedStatement.close();
        connection.close();
    }

    @Test
    public void testUpdate() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql:///atguigu", "root", "Init@mysql57");
        String sql = "update t_user set passwd= ? where id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setObject(1, "123qwe");
        preparedStatement.setObject(2, 2);
        int rows = preparedStatement.executeUpdate();
        if (rows > 0) {
            System.out.println("更新成功");
        } else {
            System.out.println("更新失败");
        }
        preparedStatement.close();
        connection.close();
    }

    @Test
    public void testDelete() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql:///atguigu", "root", "Init@mysql57");
        String sql = "delete from t_user where id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setObject(1, 2);
        int rows = preparedStatement.executeUpdate();
        if (rows > 0) {
            System.out.println("删除成功");
        } else {
            System.out.println("删除失败");
        }
        preparedStatement.close();
        connection.close();
    }

    @Test
    public void testSelect() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql:///atguigu", "root", "Init@mysql57");
        String sql = "select id, login_name, nick_name, passwd from t_user";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        ResultSet resultSet = preparedStatement.executeQuery();
        List<Map<String, Object>> list = new ArrayList<>();

        // 当前结果集的信息对象（可以获取列的名称，列的数量）
        ResultSetMetaData metaData = resultSet.getMetaData();
        int columnCount = metaData.getColumnCount();
        while (resultSet.next()) {
            Map<String, Object> map = new HashMap<>();
            // 手动取值
//            map.put("id", resultSet.getInt("id"));
//            map.put("login_name", resultSet.getString("login_name"));
//            map.put("nick_name", resultSet.getString("nick_name"));
//            map.put("passwd", resultSet.getString("passwd"));
            for (int i = 1; i <= columnCount; i++) {
                Object object = resultSet.getObject(i);
                // select aaa as xxx
                String columnLabel = metaData.getColumnLabel(i);
                map.put(columnLabel, object);
            }
            //
            list.add(map);
        }
        System.out.println("list=" + list);

        resultSet.close();
        preparedStatement.close();
        connection.close();
    }

}
