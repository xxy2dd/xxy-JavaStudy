package com.config.aop.Cglib;

/**
 * @author xxy
 * @date 2019/8/1
 * @description
 */
public class Apple implements Fruit {
    @Override
    public void show() {
        System.out.println("Apple's show is invoked");
    }
}
