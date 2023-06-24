package per.study.api.utils;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 封装两个方法
 * 一个简化非DQL
 * 一个简化DQL
 **/
public abstract class BaseDao {

    /**
     * 封装简化非DQL语句
     *
     * @param sql    带占位符的SQL语句
     * @param params 占位符的值 必须等于SQL语句'?'占位位置
     * @return 执行影响的行数
     */
    public int executeUpdate(String sql, Object... params) throws SQLException {
        Connection connection = JdbcUtils.getConnection();

        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        for (int i = 1; i <= params.length; i++) {
            preparedStatement.setObject(i, params[i - 1]);
        }

        int rows = preparedStatement.executeUpdate();
        preparedStatement.close();

        // 是否回收连接，需要考虑是不是事务
        if (connection.getAutoCommit()) {
            // 没有开始事务，正常回收连接
            JdbcUtils.freeConnection();
        }
        // connection.setAutoCommit(false); // 开启事务，不用管连接即可，业务层处理
        return rows;
    }

    /**
     * DQL语句封装方法 -> 返回值 是什么类型呢？？？
     *
     * @param sql
     * @return <T> 声明一个范型，不确定类型
     * 1.确定范型 User.class -> T = User
     * 2.要使用反射技术属性赋值
     * public <T> List<T> executeQuery(Class<T> clazz, String sql, Object... params);
     */
    public <T> List<T> executeQuery(Class<T> clazz, String sql, Object... params) throws SQLException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        Connection connection = JdbcUtils.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        if (params != null && params.length != 0) {
            for (int i = 1; i <= params.length; i++) {
                preparedStatement.setObject(i, params[i - 1]);
            }
        }

        ResultSet resultSet = preparedStatement.executeQuery();
        List<T> list = new ArrayList<>();

        // 当前结果集的信息对象（可以获取列的名称，列的数量）
        ResultSetMetaData metaData = resultSet.getMetaData();
        int columnCount = metaData.getColumnCount();
        while (resultSet.next()) {
            T t = clazz.newInstance(); // 调用类的无参构造函数实例话化对象
            // 手动取值
            for (int i = 1; i <= columnCount; i++) {
                Object value = resultSet.getObject(i);
                String propertyName = metaData.getColumnLabel(i);
                // 反射给对象的属性值赋值
                Field field = clazz.getDeclaredField(propertyName);
                field.setAccessible(true); // 属性可以设置，打破private的修饰限制
                // set(Object obj, Object value)
                // obj：要赋值的对象，如果属性是静态，可以为null
                // value：具体的属性值
                field.set(t, value);
            }
            list.add(t);
        }

        resultSet.close();
        preparedStatement.close();
        if (connection.getAutoCommit()) {
            JdbcUtils.freeConnection();
        }
        return list;
    }
}
