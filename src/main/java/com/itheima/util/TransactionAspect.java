package com.itheima.util;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 事务AOP切面类
 */
@Component
@Aspect
public class TransactionAspect {
    @Autowired
    private TransactionManager txManager;

    /**
     * 切入点表达式
     */
    @Pointcut("execution(* com.itheima.service.impl.*.*(..))")
    private void pointcut() {
    }

    /**
     * 环绕通知
     */
    @Around("pointcut()")
    public Object aroundTransaction(ProceedingJoinPoint joinPoint) {
        Object rtValue = null;
        //方法执行的参数
        Object[] args = joinPoint.getArgs();
        String currentMethodName = joinPoint.getSignature().getName();
        try {
            //执行前置通知增强
            txManager.beginTransaction();
            System.out.println("前置增强：事务AOP，开启事务...");
            //执行原本的方法
            rtValue = joinPoint.proceed(args);
            System.out.println("------------ 业务方法执行，" + currentMethodName + "() ------------");
            txManager.commit();
            System.out.println("后置增强：事务AOP，提交事务...");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            txManager.rollback();
            System.out.println("异常增强：事务AOP，回滚事务...");
        } finally {
            txManager.release();
            System.out.println("最终增强：事务AOP，回收连接...");
        }
        return rtValue;
    }
}