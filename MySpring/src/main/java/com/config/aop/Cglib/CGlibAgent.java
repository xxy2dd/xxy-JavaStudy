package com.config.aop.Cglib;

import com.config.aop.MethodInvocation;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author xxy
 * @date 2019/8/1
 * @description
 */
public class CGlibAgent implements MethodInterceptor {
    private Object proxy;
    public Object getInstance(Object proxy){
        this.proxy = proxy;
        Enhancer enhancer = new Enhancer();
        // 创建父类
        enhancer.setSuperclass(this.proxy.getClass());
        // 回调方法
        enhancer.setCallback(this);
        return enhancer.create();

    }

    /**
     * 回调方法
     * @param obj
     * @param method
     * @param objects
     * @param methodProxy
     * @return
     * @throws Throwable
     */
    @Override
    public Object intercept(Object obj, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
       System.out.println("before invoking");
       // 真正调用
       Object ret = methodProxy.invokeSuper(obj,objects);
       System.out.println("after invoking");
       return ret;
    }
}
