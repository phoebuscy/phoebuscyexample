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
                System.out.println("����1ִ��ʱ�䣺" + sdf.format(new Date()));
                throw new RuntimeException();  // �����׳��쳣���õ�һ���߳���������Ӱ��ڶ����߳�
            }
        };
        TimerTask timerTask2 = new TimerTask()
        {
            @Override
            public void run()
            {

                System.out.println("����2ִ��ʱ�䣺" + sdf.format(new Date()));
            }
        };

        System.out.println("��ǰʱ�䣺" + sdf.format(new Date()));

        ScheduledThreadPoolExecutor poolExecutor = new ScheduledThreadPoolExecutor(2);
        poolExecutor.scheduleAtFixedRate(timerTask1, 0, 1000, TimeUnit.MILLISECONDS);
        poolExecutor.scheduleAtFixedRate(timerTask2, 0, 2000, TimeUnit.MILLISECONDS);
    }
}
