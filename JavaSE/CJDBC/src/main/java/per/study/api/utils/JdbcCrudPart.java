package per.study.api.utils;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @Description
 * @Author: Lrwei
 * @Date: 2023/6/24
 **/
public class JdbcCrudPart {
    public void testInsert() throws SQLException {
        Connection connection = JdbcUtils.getConnection();

        JdbcUtils.freeConnection();
    }
}
