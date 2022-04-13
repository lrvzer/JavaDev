package per.study.creation.prototype;

public class MainTest {
    public static void main(String[] args) {
        MyBatis myBatis = new MyBatis();
        User user1 = myBatis.getUser("zhangsan");
        System.out.println(user1);

        User user2 = myBatis.getUser("zhangsan");
        user2.setUsername("lisi");
        System.out.println(user2);

        User user3 = myBatis.getUser("zhangsan");
        System.out.println(user3);

        System.out.println(user1 == user2);
        System.out.println(user1 == user3);
        System.out.println(user2 == user3);
    }
}
