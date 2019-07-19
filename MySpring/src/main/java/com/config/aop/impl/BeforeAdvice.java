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

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable{
        // 在目标方法前调用通知
        methodInvocation.invoke();
        return method.invoke(bean,args);
    }
}
