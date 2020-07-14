package com.itheima.util;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * - @Aspect，标识当前类是一个切面类
 * 一般我们使用环绕通知，因为单独配置前置通知、后置通知、异常通知、最终通知等。
 * 会有一个调用顺序问题，会让最终通知在后置通知之前调用，而环绕通知，调用顺序是我们自己写的，就不会有问题
 */
@Component
@Aspect
public class Logger {
    /**
     * 切入点表达式
     */
    @Pointcut("execution(* com.itheima.service.impl.*.*(..))")
    private void pt1() {
    }

    /**
     * 前置通知，切入点方法执行前执行
     */
//    @Before("pt1()")
    public void beforePrintLog() {
        System.out.println("前置通知 ==> beforePrintLog()执行...");
    }

    /**
     * 后置通知，切入点方法执行成功并返回时执行
     */
//    @AfterReturning("pt1()")
    public void afterReturningPrintLog() {
        System.out.println("后置通知 ==> afterReturningPrintLog()执行...");
    }

    /**
     * 异常通知，切入点方法发生异常时执行
     */
//    @AfterThrowing("pt1()")
    public void afterThrowingPrintLog() {
        System.out.println("异常通知 ==> afterThrowingPrintLog()执行...");
    }

    /**
     * 最终通知，不管切入点方法执行成功、异常都执行
     */
//    @After("pt1()")
    public void afterPrintLog() {
        System.out.println("最终通知 ==> afterPrintLog()执行...");
    }

    /**
     * 环绕通知
     */
    @Around("pt1()")
    public Object aroundPrintLog(ProceedingJoinPoint joinPoint) {
        Object rtValue = null;
        try {
            //方法执行的参数
            Object[] args = joinPoint.getArgs();
            //执行前置通知增强
            System.out.println("Logger => 环绕通知，前置增强...");
            //执行原本的方法
            rtValue = joinPoint.proceed(args);
            System.out.println("Logger => 环绕通知，后置增强...");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            System.out.println("Logger => 环绕通知，异常增强...");
        } finally {
            System.out.println("Logger => 环绕通知，最终增强...");
        }
        return rtValue;
    }
}