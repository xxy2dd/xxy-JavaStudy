package com.config.aop.impl;

import com.config.aop.Advice;
import com.config.aop.MethodInvocation;

import java.lang.reflect.Method;

/**
 * @author xxy
 * @date 2019/6/28
 * @description
 */
public class BeforeAdvice implements Advice{
    private Object bean;
    private MethodInvocation methodInvocation;

    public BeforeAdvice(Object bean,MethodInvocation methodInvocation){
        this.bean = bean;
        this.methodInvocation = methodInvocation;
    }

    /**
     * 重写 InvocationHandler 接口中的invoke 方法
     * @param proxy 被代理的类的实例
     * @param method 调用配置过被代理的类的方法
     * @param args 该方法需要的参数
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable{
        // 在目标方法前调用通知
        methodInvocation.invoke();
        // 真正调用方法
        return method.invoke(bean,args);
    }
}
