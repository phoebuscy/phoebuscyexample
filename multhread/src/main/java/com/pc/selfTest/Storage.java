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

    // 锁
    private final Lock lock = new ReentrantLock();


    // 仓库空的条件变量
    private final Condition empty = lock.newCondition();

    // 生产num个产品
    public void produce(int num)
    {
        // 获得锁
        lock.lock();

        // 生产条件满足情况下，生产num个产品
        for (int i = 1; i <= num; ++i)
        {
           linkedQueue.offer(i);
        }

        System.out.println("[生产产品] " + "数量 " + num + " \t" + LocalDateTime.now().toString());

        // 唤醒其他所有线程
        empty.signalAll();

        // 释放锁
        lock.unlock();
    }

    // 消费num个产品
    public void consume(int num)
    {
        // 获得锁
        lock.lock();
        // 如果仓库存储量不足
        while (linkedQueue.isEmpty())
        {
            System.out.println("[仓库数量] ：0  等待..." + " \t" + LocalDateTime.now().toString());
            try
            {
                // 由于条件不满足，消费阻塞
                empty.await();
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }

        int poll = linkedQueue.poll();
        System.out.println("[消费一个] 值为： " +  poll +  "\t" + LocalDateTime.now().toString());

        // 唤醒其他所有线程
        empty.signalAll();

        // 释放锁
        lock.unlock();
    }




}
