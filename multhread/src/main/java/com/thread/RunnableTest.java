package com.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class RunnableTest
{

    public static void main(String[] args) throws Exception
    {
        System.out.println("ʹ���̳߳����� Runnable ����");

        ExecutorService threadPool = Executors.newFixedThreadPool(5); // ������С�̶�Ϊ 5 ���̳߳�

        List<AccumRunnable> tasks = new ArrayList<>(10);

        for (int i = 0; i < 10; i++)
        {
            AccumRunnable task = new AccumRunnable(i * 10 + 1, (i + 1) * 10);
            tasks.add(task);

            threadPool.execute(task); // ���̳߳�ִ������ task
        }
        threadPool.shutdown(); // ���̳߳ط��͹رյ�ָ��ȵ��Ѿ��ύ������ִ�����֮���̳߳ػ�ر�

        threadPool.awaitTermination(1, TimeUnit.HOURS); // �ȴ��̳߳عرգ��ȴ������ʱ��Ϊ 1 Сʱ

        int total = 0;
        for (AccumRunnable task : tasks)
        {
            total += task.getResult(); // ������ AccumRunnable ����� getResult ������÷��صĽ��
        }

        System.out.println("Total: " + total);
    }

    static final class AccumRunnable implements Runnable
    {

        private final int begin;
        private final int end;

        private int result;

        public AccumRunnable(int begin, int end)
        {
            this.begin = begin;
            this.end = end;
        }

        @Override
        public void run()
        {
            result = 0;
            try
            {
                for (int i = begin; i <= end; i++)
                {
                    result += i;
                    Thread.sleep(100);
                }
            } catch (InterruptedException ex)
            {
                ex.printStackTrace(System.err);
            }
            System.out.printf("(%s) - ���н��������Ϊ %d\n",
                    Thread.currentThread().getName(), result);
        }

        public int getResult()
        {
            return result;
        }
    }
}