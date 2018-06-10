package com.pc.selfTest;

public class MainTest
{


    public static void main(String[] args)
    {

        Storage storage = new Storage();

        // 消费者
        Cos c1 = new Cos(storage);
        c1.start();

        // 生产者对象
        Prd p1 = new Prd(storage);
        p1.start();


    }






}
