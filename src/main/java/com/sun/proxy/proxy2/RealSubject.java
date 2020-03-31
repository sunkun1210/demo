package com.sun.proxy.proxy2;

/**
 * 实际对象
 */
public class RealSubject implements Subject{
    public void save(){
        System.out.println("insert into ......");
    }

}