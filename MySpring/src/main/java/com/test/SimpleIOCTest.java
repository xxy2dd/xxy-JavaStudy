package com.test;

import com.config.SimpleIOC;
import com.vo.Car;
import com.vo.Wheel;

/**
 * @author xxy
 * @date 2019/6/28
 * @description
 */
public class SimpleIOCTest {
    public static void main(String[] args){
        SimpleIOCTest test = new SimpleIOCTest();
        try{
            test.getBean();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    private void getBean() throws Exception{
        String location = SimpleIOC.class.getClassLoader().getResource("ioc.xml").getFile();
        SimpleIOC bf = new SimpleIOC(location);
        Wheel wheel = (Wheel) bf.getBean("wheel");
        System.out.println(wheel);
        Car car = (Car) bf.getBean("car");
        System.out.println(car);
    }
}
