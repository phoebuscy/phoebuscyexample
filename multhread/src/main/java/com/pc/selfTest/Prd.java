package com.pc.selfTest;

import java.time.LocalDateTime;

public class Prd extends Thread
{
    // 每次生产的产品数量
    private int num;

    // 所在放置的仓库
    private Storage storageQue;

    // 构造函数，设置仓库
    //  public Producer(Storage storage)
    public Prd(Storage storage)
    {
        this.storageQue = storage;
    }

    // 线程run函数
    public void run()
    {
        while (true)
        {
            int random = (int) (Math.random() * 20);
            produce(random);
            System.out.println(" 生产产品，数量：" + random + " \t" + LocalDateTime.now().toString());
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

    // 调用仓库Storage的生产函数
    public void produce(int num)
    {
        storageQue.produce(num);
    }

    public void setNum(int num)
    {
        this.num = num;
    }
}