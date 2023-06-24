package per.study.api.utils;

import org.junit.Test;

import java.sql.SQLException;

public class PSCURDPart extends BaseDao {

    @Test
    public void testInsert() throws ClassNotFoundException, SQLException {
        String sql = "insert into t_user(login_name, nick_name, passwd) values (?,?,?)";
        int rows = executeUpdate(sql, "lisi2", "李四2", "123456");
        if (rows > 0) {
            System.out.println("插入成功");
        } else {
            System.out.println("插入失败");
        }
    }

    @Test
    public void testUpdate() throws ClassNotFoundException, SQLException {
        String sql = "update t_user set passwd= ? where id = ?";
        int rows = executeUpdate(sql, "123qwe123", 2);
        if (rows > 0) {
            System.out.println("更新成功");
        } else {
            System.out.println("更新失败");
        }
    }

    @Test
    public void testDelete() throws ClassNotFoundException, SQLException {
        String sql = "delete from t_user where id = ?";
        int rows = executeUpdate(sql, 2);
        if (rows > 0) {
            System.out.println("删除成功");
        } else {
            System.out.println("删除失败");
        }
    }
}
