package com.pc.selfTest;


import java.time.LocalDateTime;

public class Cos extends Thread
{
    // ÿ�����ѵĲ�Ʒ����
    private int num;

    // ���ڷ��õĲֿ�
    private Storage storageQue;

    // ���캯�������òֿ�
    public Cos(Storage storage)
    {
        this.storageQue = storage;
    }

    // �߳�run����
    public void run()
    {
        int count = 0;
        while (true)
        {
            count++;
            if(count >= 5)
            {
                count = 0;
                System.out.println(" begin sleep: " + LocalDateTime.now().toString());
                try
                {
                    Thread.sleep(2000);
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
            consume(num);
        }
    }

    // ���òֿ�Storage����������
    public void consume(int num)
    {
        storageQue.consume(num);
    }


    public void setNum(int num)
    {
        this.num = num;
    }
}