package com.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class InvokeAllTest
{

    public static void main(String[] args) throws Exception
    {
        System.out.println("ʹ���̳߳� invokeAll ����һ�� Callable ����");

        ExecutorService threadPool = Executors.newFixedThreadPool(5); // ������С�̶�Ϊ 5 ���̳߳�

        List<AccumCallable> tasks = new ArrayList<>(10); // tasks Ϊһ������
        for (int i = 0; i < 10; i++)
        {
            tasks.add(new AccumCallable(i * 10 + 1, (i + 1) * 10));
        }

        List<Future<Integer>> futures = threadPool.invokeAll(tasks); // ������ֱ�����������������

        int total = 0;
        for (Future<Integer> future : futures)
        {
            total += future.get(); // ��������Ľ��
        }

        System.out.println("Total: " + total);

        threadPool.shutdown(); // ���̳߳ط��͹رյ�ָ��
    }


    static final class AccumCallable implements Callable<Integer>
    {

        private final int begin;
        private final int end;

        public AccumCallable(int begin, int end)
        {
            this.begin = begin;
            this.end = end;
        }

        @Override
        public Integer call() throws Exception
        {
            int result = 0;
            for (int i = begin; i <= end; i++)
            {
                result += i;
                Thread.sleep(100);
            }
            System.out.printf("(%s) - ���н��������Ϊ %d\n",
                    Thread.currentThread().getName(), result);

            return result;
        }

    }


}
