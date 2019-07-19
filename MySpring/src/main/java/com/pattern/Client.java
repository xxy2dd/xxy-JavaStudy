package com.pattern;

import com.pattern.gun.Rifile;

/**
 * @author xxy
 * @date 2019/7/11
 * @description
 */
public class Client {
    public static void main(String[] args){
        Soldier sanmao = new Soldier();
        sanmao.setGun(new Rifile());
        sanmao.killEnemy();
    }
}
