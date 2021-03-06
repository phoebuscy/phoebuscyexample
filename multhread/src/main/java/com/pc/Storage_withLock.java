package com.pc;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 二、await() / signal()方法
 * 在JDK5.0之后，Java提供了更加健壮的线程处理机制，包括同步、锁定、线程池等，它们可以实现更细粒度的线程控制。await()和signal()就是其中用来做同步的两种方法，它们的功能基本上和wait() / nofity
 * ()相同，完全可以取代它们，但是它们和新引入的锁定机制Lock直接挂钩，具有更大的灵活性。通过在Lock对象上调用newCondition()方法，将条件变量和一个锁对象进行绑定，进而控制并发程序访问竞争资源的安全。下面来看代码：
 * <p>
 * 只需要更新仓库类Storage的代码即可，生产者Producer、消费者Consumer、测试类Test的代码均不需要进行任何更改。这样我们就知道为神马我要在Storage类中定义public void produce(int
 * num);和public void consume(int num);
 * 方法，并在生产者类Producer和消费者类Consumer中调用Storage
 * 类中的实现了吧。将可能发生的变化集中到一个类中，不影响原有的构架设计，同时无需修改其他业务层代码。无意之中，我们好像使用了某种设计模式，具体是啥我忘记了，啊哈哈，等我想起来再告诉大家~
 */


public class Storage_withLock
{

    // 仓库最大存储量
    private final int MAX_SIZE = 100;

    // 仓库存储的载体
    private LinkedList<Object> list = new LinkedList<Object>();

    // 锁
    private final Lock lock = new ReentrantLock();

    // 仓库满的条件变量
    private final Condition full = lock.newCondition();

    // 仓库空的条件变量
    private final Condition empty = lock.newCondition();

    // 生产num个产品
    public void produce(int num)
    {
        // 获得锁
        lock.lock();

        // 如果仓库剩余容量不足
        while (list.size() + num > MAX_SIZE)
        {
            System.out.println("【要生产的产品数量】:" + num + "/t【库存量】:" + list.size() + "/t暂时不能执行生产任务!");
            try
            {
                // 由于条件不满足，生产阻塞
                full.await();
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }

        // 生产条件满足情况下，生产num个产品
        for (int i = 1; i <= num; ++i)
        {
            list.add(new Object());
        }

        System.out.println("【已经生产产品数】:" + num + "/t【现仓储量为】:" + list.size());

        // 唤醒其他所有线程
        full.signalAll();
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
        while (list.size() < num)
        {
            System.out.println("【要消费的产品数量】:" + num + "/t【库存量】:" + list.size() + "/t暂时不能执行生产任务!");
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

        // 消费条件满足情况下，消费num个产品
        for (int i = 1; i <= num; ++i)
        {
            list.remove();
        }

        System.out.println("【已经消费产品数】:" + num + "/t【现仓储量为】:" + list.size());

        // 唤醒其他所有线程
        full.signalAll();
        empty.signalAll();

        // 释放锁
        lock.unlock();
    }

    // set/get方法
    public int getMAX_SIZE()
    {
        return MAX_SIZE;
    }

    public LinkedList<Object> getList()
    {
        return list;
    }

    public void setList(LinkedList<Object> list)
    {
        this.list = list;
    }

}
