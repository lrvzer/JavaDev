package per.study.utils;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.SQLException;

/**
 * 事务管理相关类
 *  开启事务
 *  提交事务
 *  回滚事务
 *  释放连接
 */
@Component
public class TransactionManager {

    private ConnectionUtils connectionUtils;

    @Autowired
    public void setConnectionUtils(ConnectionUtils connectionUtils) {
        this.connectionUtils = connectionUtils;
    }

    @Pointcut("execution( * per.study.service.impl.*.*(..))")
    public void txAdvice() {}

    /**
     * 开启事务
     */
//    @Before("txAdvice()")
    public void begin() {
        try {
            connectionUtils.getThreadConnection().setAutoCommit(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 提交事务
     */
//    @AfterReturning("txAdvice()")
    public void commit() {
        try {
            connectionUtils.getThreadConnection().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 回滚事务
     */
//    @AfterThrowing("txAdvice()")
    public void rollback() {
        try {
            connectionUtils.getThreadConnection().rollback();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 释放连接
     */
//    @After("txAdvice()")
    public void release() {
        try {
            connectionUtils.getThreadConnection().close();
            connectionUtils.removeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Around("txAdvice()")
    public Object aroundAdvice(ProceedingJoinPoint pjp) {
        Object ret = null;
        try {
            Object[] args = pjp.getArgs();
            this.begin();
            ret = pjp.proceed(args);
            this.commit();
            return ret;
        } catch (Throwable e) {
            this.rollback();
            throw new RuntimeException(e);
        } finally {
            this.release();
        }
    }

}
