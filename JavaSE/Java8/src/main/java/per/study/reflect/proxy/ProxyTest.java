package per.study.reflect.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

interface Human {
    String getBelief();
    void eat(String food);
}

class SuperMan implements Human {
    @Override
    public String getBelief() {
        return "I can fly";
    }

    @Override
    public void eat(String food) {
        System.out.println("eat " + food);
    }
}

class ProxyFactory {

    public static Object getProxyInstance(Object obj) {
        return Proxy.newProxyInstance(obj.getClass().getClassLoader(), obj.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                return method.invoke(obj, args);
            }
        });
    }

}

public class ProxyTest {
    public static void main(String[] args) {
        Human man = new SuperMan();
        Human proxyInstance = (Human)ProxyFactory.getProxyInstance(man);
        proxyInstance.eat("egg");
    }
}
