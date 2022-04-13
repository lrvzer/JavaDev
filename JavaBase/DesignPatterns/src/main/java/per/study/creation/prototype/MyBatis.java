package per.study.creation.prototype;

import java.util.HashMap;
import java.util.Map;

public class MyBatis
{

    Map<String, User> userCache = new HashMap<>();

    public User getUser(String username) {
        User user;
        if (!userCache.containsKey(username)) {
            // 查询数据
            user = getUserFromDB(username);
            try {
                userCache.put(username, (User) user.clone());
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
        }
        else {
            // 从缓冲中直接拿，脏缓存问题
            // 原型已经拿到，但是不能直接给
            user = userCache.get(username);
            try {
                user = (User) user.clone();
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
        }
        return user;
    }

    private User getUserFromDB(String username) {
        System.out.println("从数据库查到：" + username);
        User user = new User();
        System.out.println("User对象被创建");
        user.setUsername(username);
        user.setAge(18);
        return user;
    }
}
