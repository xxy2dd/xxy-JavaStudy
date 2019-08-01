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
        // 调用java,lang.reflect 包中的 Proxy 的 newProxyInstance 方法
        // newProxyInstance(ClassLoader loader,Class<?>[] interfaces,InvocationHandler h)
        // loader : 被代理的类的类加载器
        // interfaces : 被代理类的接口数组
        // invocationHandler : 调用处理器类的对象实例
        return Proxy.newProxyInstance(SimpleAOP.class.getClassLoader(),bean.getClass().getInterfaces(), advice);
    }
}
