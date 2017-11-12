package com.pc.selfTest;


import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Storage
{


    private ConcurrentLinkedQueue<Integer> linkedQueue = new ConcurrentLinkedQueue<>();

    // ��
    private final Lock lock = new ReentrantLock();


    // �ֿ�յ���������
    private final Condition empty = lock.newCondition();

    // ����num����Ʒ
    public void produce(int num)
    {
        // �����
        lock.lock();

        // ����������������£�����num����Ʒ
        for (int i = 1; i <= num; ++i)
        {
           linkedQueue.offer(i);
        }

        System.out.println("[������Ʒ] " + "���� " + num + " \t" + LocalDateTime.now().toString());

        // �������������߳�
        empty.signalAll();

        // �ͷ���
        lock.unlock();
    }

    // ����num����Ʒ
    public void consume(int num)
    {
        // �����
        lock.lock();
        // ����ֿ�洢������
        while (linkedQueue.isEmpty())
        {
            System.out.println("[�ֿ�����] ��0  �ȴ�..." + " \t" + LocalDateTime.now().toString());
            try
            {
                // �������������㣬��������
                empty.await();
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }

        int poll = linkedQueue.poll();
        System.out.println("[����һ��] ֵΪ�� " +  poll +  "\t" + LocalDateTime.now().toString());

        // �������������߳�
        empty.signalAll();

        // �ͷ���
        lock.unlock();
    }




}
