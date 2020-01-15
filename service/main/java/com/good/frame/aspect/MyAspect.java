package com.good.frame.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;

//@Aspect
//@Component/*这个注解也是要的，默认不会注入切面类*/
public class MyAspect {
    /**
     * 定义切点
     * "*Controller"：表示以Controller结尾的类
     * "add*"：表示以add开头的方法
     * 符合以上条件的方法，将成为一个切点
     */
    @Pointcut("execution(* com.good.frame.controller.*Controller.add*(..))")
    public void myPointcut() {
    }

    /**
     * 前置通知
     */
    @Before("myPointcut()")
    public void before(JoinPoint joinPoint) {
        //参数集合
        Object[] args = joinPoint.getArgs();
        Signature signature = joinPoint.getSignature();
        //方法名称
        signature.getDeclaringTypeName();
        System.out.println("前置通知");
    }

    /**
     * 后置通知
     */
    @After("myPointcut()")
    public void after(JoinPoint joinPoint) {
        System.out.println("后置通知");
    }

    /**
     * 环绕通知
     */
    @Around(value = "myPointcut()")
    public Object around(ProceedingJoinPoint joinPoint) {
        System.out.println("环绕通知开始");
        Object[] args = joinPoint.getArgs();
        //修改参数值
        if (args[0] instanceof String) {
            args[0] = "river66";
        }
        if (args[1] instanceof String) {
            args[1] = "66";
        }
        try {
            Object returnValue = joinPoint.proceed(args);
            //使用returnValue，做一些判断和处理
            System.out.println("环绕通知结束");
            return "环绕通知返回的值";
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return null;
        }
    }

    /**
     * 获取返回值
     */
    @AfterReturning(returning = "returnObject", value = "myPointcut()")
    public void getReturnValue(Object returnObject) {
        System.out.println((String) returnObject);
    }

    /**
     * 定义需要捕获异常的切点
     */
    @Pointcut("execution(* com.good.frame.controller.*Service.add*(..))")
    public void exceptionPointcut() {
    }

    /**
     * 捕获异常
     */
    @AfterThrowing(throwing = "e", pointcut = "exceptionPointcut()")
    public void catchException(Exception e) {
        System.out.println(e.getMessage());
    }

}
