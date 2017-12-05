package com.pc.selfTest;

import java.time.LocalDateTime;

public class Prd extends Thread
{
    // ÿ�������Ĳ�Ʒ����
    private int num;

    // ���ڷ��õĲֿ�
    private Storage storageQue;

    // ���캯�������òֿ�
    //  public Producer(Storage storage)
    public Prd(Storage storage)
    {
        this.storageQue = storage;
    }

    // �߳�run����
    public void run()
    {
        while (true)
        {
            int random = (int) (Math.random() * 20);
            produce(random);
            System.out.println(" ������Ʒ��������" + random + " \t" + LocalDateTime.now().toString());
            int sleepS = 1;
            if (random > 3 && random <= 6)
            {
                sleepS = 2;
            } else
            {
                sleepS = 3;
            }

            try
            {
                Thread.sleep(1000 * sleepS);
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }

    // ���òֿ�Storage����������
    public void produce(int num)
    {
        storageQue.produce(num);
    }

    public void setNum(int num)
    {
        this.num = num;
    }
}