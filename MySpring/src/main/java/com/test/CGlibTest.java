package com.test;

import com.config.aop.Cglib.Apple;
import com.config.aop.Cglib.CGlibAgent;

/**
 * @author xxy
 * @date 2019/8/1
 * @description
 */
public class CGlibTest {
    public static void main(String[] args){
        CGlibAgent cGlibAgent = new CGlibAgent();
        Apple apple = (Apple) cGlibAgent.getInstance(new Apple());
        apple.show();
    }
}
