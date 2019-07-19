package com.pattern;

import com.pattern.gun.AbstarctGun;

/**
 * @author xxy
 * @date 2019/7/11
 * @description
 */
public class Soldier {
    private AbstarctGun gun;
    public void setGun(AbstarctGun _gun){
        this.gun = _gun;
    }
    public void killEnemy(){
        System.out.println("士兵开始杀敌人...");
        gun.shoot();
    }
}
