package com.pc.selfTest;

public class MainTest
{


    public static void main(String[] args)
    {

        Storage storage = new Storage();

        // ������
        Cos c1 = new Cos(storage);
        c1.start();

        // �����߶���
        Prd p1 = new Prd(storage);
        p1.start();


    }






}
