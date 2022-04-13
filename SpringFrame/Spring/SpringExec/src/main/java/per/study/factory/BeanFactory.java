package per.study.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import per.study.service.IAccountService;
import per.study.utils.TransactionManager;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

@Component
public class BeanFactory {

    @Autowired
    private IAccountService accountService;

    @Autowired
    private TransactionManager txManager;

    @Bean("proxyAccountService")
    public IAccountService getAccountService() {
        return (IAccountService) Proxy.newProxyInstance(accountService.getClass().getClassLoader(), accountService.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                Object obj = null;
                try {
                    // 1.开启事务
                    txManager.begin();

                    // 2.执行操作
                    obj = method.invoke(accountService, args);

                    // 3.提交事务
                    txManager.commit();

                    // 4.返回结果
                    return obj;
                } catch (Exception e) {
                    // 5.回滚事务
                    txManager.rollback();
                    throw new RuntimeException(e);
                } finally {
                    // 6.释放连接
                    txManager.release();
                }
            }
        });
    }


}
