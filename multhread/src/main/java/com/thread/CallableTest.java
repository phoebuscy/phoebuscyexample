package com.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CallableTest
{

    public static void main(String[] args) throws Exception
    {
        System.out.println("ʹ���̳߳����� Callable ����");

        ExecutorService threadPool = Executors.newFixedThreadPool(5); // ������С�̶�Ϊ 5 ���̳߳�

        List<Future<Integer>> futures = new ArrayList<>(10);

        for (int i = 0; i < 10; i++)
        {
            AccumCallable task = new AccumCallable(i * 10 + 1, (i + 1) * 10);
            Future<Integer> future = threadPool.submit(task); // �ύ����
            futures.add(future);
        }
        threadPool.shutdown(); // ���̳߳ط��͹رյ�ָ��ȵ��Ѿ��ύ������ִ�����֮���̳߳ػ�ر�

        int total = 0;
        for (Future<Integer> future : futures)
        {
            total += future.get(); // ������ֱ�������������������Ľ��
        }

        System.out.println("Total: " + total);
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