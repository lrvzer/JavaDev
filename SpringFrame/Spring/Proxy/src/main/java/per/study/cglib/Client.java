package per.study.cglib;

import net.sf.cglib.proxy.Callback;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class Client {

    public static void main(String[] args) {
        Producer producer = new Producer();
        Producer cglibProduce = (Producer) Enhancer.create(producer.getClass(), (MethodInterceptor) (o, method, objects, methodProxy) -> {
            Object ret = null;
            float money = (float) objects[0];
            if ("saleProduct".equals(method.getName())) {
                ret = method.invoke(producer, money * 0.8F);
            }
            return ret;
        });
        cglibProduce.saleProduct(800);
    }

}
