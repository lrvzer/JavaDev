package per.study.feature.optional;

import java.util.Optional;

import org.junit.Test;

public class OptionalTest {
    /**
     * Optional.of(T t)：创建一个Optional实例，t必须为空
     * Optional.empty()：创建一个空的Optional实例
     * Optional.ofNullable(T value)：t可以为null
     *
     * T orElse(T other)：如果有值，将其返回，否则返回指定的other对象
     */

    @Test
    public void test0() {
        String aim = "";
//        String aDefault = Optional.ofNullable(aim).orElse("default");
//        System.out.println(aDefault);
        if (aim == null || aim.equals("")) {
            aim = "default";
        }
        System.out.println(aim);
    }

    @Test
    public void test1() {
        Girl girl = new Girl();
        Optional.of(girl);
    }

    @Test
    public void test2() {
        Girl girl = null; // optionalGirl = Optional.empty
//        girl = new Girl(); // Optional[Girl{name='null'}]
        Optional<Girl> optionalGirl = Optional.ofNullable(girl);
        Girl girl1 = optionalGirl.orElse(new Girl("default"));
        System.out.println(girl1);
    }

    public String getGirlName(Boy boy) {
        return boy.getGirl().getName();
    }

    public String getGirlName_if(Boy boy) {
        if (boy != null) {
            Girl girl = boy.getGirl();
            if (girl != null) {
                return girl.getName();
            }
        }
        return null;
    }

    public String getGirlName_Optional(Boy boy) {
        Optional<Boy> optionalBoy = Optional.ofNullable(boy);
        Boy ob = optionalBoy.orElse(new Boy(new Girl("迪丽热巴")));
        Optional<Girl> optionalGirl = Optional.ofNullable(ob.getGirl());
        Girl girl = optionalGirl.orElse(new Girl("古力娜扎"));
        return girl.getName();
    }

    @Test
    public void test3() {
        Boy boy = new Boy();
//        String girlName = getGirlName(boy);
//        String girlName = getGirlName_if(boy);
        String girlName = getGirlName_Optional(boy);
        System.out.println(girlName);
    }
}
