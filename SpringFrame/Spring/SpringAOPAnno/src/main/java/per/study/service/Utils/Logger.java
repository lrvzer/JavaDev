package per.study.service.Utils;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/* 用于记录日志的工具类，它里面提高了公共的代码 */
@Component("logger")
@Aspect
public class Logger
{

    @Pointcut("execution(* *..*.*(..))")
    private void pt1() {}

    /* 前置通知 */
//    @Before("pt1()")
    public void beforePrintLog() {
        System.out.println("前置通知");
    }

    /* 后置通知 */
//    @AfterReturning("pt1()")
    public void afterReturningPrintLog() {
        System.out.println("后置通知");
    }

    /* 异常通知 */
//    @AfterThrowing("pt1()")
    public void afterThrowingPrintLog() {
        System.out.println("异常通知");
    }

    /* 最终通知 */
//    @After("pt1()")
    public void afterPrintLog() {
        System.out.println("最终通知");
    }

    /* 环绕通知 */
    @Around("pt1()")
    public Object aroundPrintLog(ProceedingJoinPoint pjp) {
        Object ret = null;
        try {
            Object[] args = pjp.getArgs();
            System.out.println("前置通知");
            ret = pjp.proceed(args); // 明确调用业务层方法（切入点方法）
            System.out.println("后置通知");
            return ret;
        } catch (Throwable e) {
            System.out.println("异常通知");
            throw new RuntimeException(e);
        } finally {
            System.out.println("最终通知");
        }

    }

}
