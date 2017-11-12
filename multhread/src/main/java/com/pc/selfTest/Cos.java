package com.pc.selfTest;


import java.time.LocalDateTime;

public class Cos extends Thread
{
    // 每次消费的产品数量
    private int num;

    // 所在放置的仓库
    private Storage storageQue;

    // 构造函数，设置仓库
    public Cos(Storage storage)
    {
        this.storageQue = storage;
    }

    // 线程run函数
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

    // 调用仓库Storage的生产函数
    public void consume(int num)
    {
        storageQue.consume(num);
    }


    public void setNum(int num)
    {
        this.num = num;
    }
}