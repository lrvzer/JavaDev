package per.study.proxy;

import java.lang.reflect.Proxy;

public class Client {

    public static void main(String[] args) {
        Producer producer = new Producer();

        IProducer proxyInstance = (IProducer) Proxy.newProxyInstance(producer.getClass().getClassLoader(), producer.getClass().getInterfaces(), (proxy, method, args1) -> {
            Object ret = null;
            float money = (float) args1[0];
            if ("saleProduct".equals(method.getName())) {
                ret = method.invoke(producer, money * 0.8F);
            }
            return ret;
        });
        proxyInstance.saleProduct(1000F);
    }

}
