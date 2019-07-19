package com.config;

import com.config.aop.Advice;

import java.lang.reflect.Proxy;

/**
 * @author xxy
 * @date 2019/6/28
 * @description
 */
public class SimpleAOP {
    public static Object getProxy(Object bean,Advice advice){
        return Proxy.newProxyInstance(SimpleAOP.class.getClassLoader(),bean.getClass().getInterfaces(), advice);
    }
}
