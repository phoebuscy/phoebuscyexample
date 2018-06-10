package com.thread;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimerTask;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ScheduledThreadPoolExecutorTest
{

    final static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static void main(String[] args)
    {
        TimerTask timerTask1 = new TimerTask()
        {

            @Override
            public void run()
            {
                System.out.println("任务1执行时间：" + sdf.format(new Date()));
                throw new RuntimeException();  // 故意抛出异常，让第一个线程死掉，不影响第二个线程
            }
        };
        TimerTask timerTask2 = new TimerTask()
        {
            @Override
            public void run()
            {

                System.out.println("任务2执行时间：" + sdf.format(new Date()));
            }
        };

        System.out.println("当前时间：" + sdf.format(new Date()));

        ScheduledThreadPoolExecutor poolExecutor = new ScheduledThreadPoolExecutor(2);
        poolExecutor.scheduleAtFixedRate(timerTask1, 0, 1000, TimeUnit.MILLISECONDS);
        poolExecutor.scheduleAtFixedRate(timerTask2, 0, 2000, TimeUnit.MILLISECONDS);
    }
}
