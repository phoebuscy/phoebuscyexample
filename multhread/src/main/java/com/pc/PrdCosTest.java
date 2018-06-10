package com.pc;


/**
 * 生产者消费者实现 ，网址 ： http://blog.csdn.net/monkey_d_meng/article/details/6251879
 * <p>
 * <p>
 * 生产者/消费者问题的多种Java实现方式
 * <p>
 * 实质上，很多后台服务程序并发控制的基本原理都可以归纳为生产者/消费者模式，而这是恰恰是在本科操作系统课堂上老师反复讲解，而我们却视而不见不以为然的。在博文《一种面向作业流(工作流)
 * 的轻量级可复用的异步流水开发框架的设计与实现》中将介绍一种生产者/消费者模式的具体应用。
 * 生产者消费者问题是研究多线程程序时绕不开的经典问题之一，它描述是有一块缓冲区作为仓库，生产者可以将产品放入仓库，消费者则可以从仓库中取走产品。解决生产者/消费者问题的方法可分为两类：（1
 * ）采用某种机制保护生产者和消费者之间的同步；（2
 * ）在生产者和消费者之间建立一个管道。第一种方式有较高的效率，并且易于实现，代码的可控制性较好，属于常用的模式。第二种管道缓冲区不易控制，被传输数据对象不易于封装等，实用性不强。因此本文只介绍同步机制实现的生产者/消费者问题。
 * 同步问题核心在于：如何保证同一资源被多个线程并发访问时的完整性。常用的同步方法是采用信号或加锁机制，保证资源在任意时刻至多被一个线程访问。Java语言在多线程编程上实现了完全对象化，提供了对同步机制的良好支持。在Java
 * 中一共有四种方法支持同步，其中前三个是同步方法，一个是管道方法。
 * （1）wait() / notify()方法
 * （2）await() / signal()方法
 * （3）BlockingQueue阻塞队列方法
 * （4）PipedInputStream / PipedOutputStream
 * 本文只介绍最常用的前三种，第四种暂不做讨论，有兴趣的读者可以自己去网上找答案。
 * <p>
 * 一、wait() / notify()方法
 * wait() / nofity()方法是基类Object的两个方法，也就意味着所有Java类都会拥有这两个方法，这样，我们就可以为任何对象实现同步机制。
 * wait()方法：当缓冲区已满/空时，生产者/消费者线程停止自己的执行，放弃锁，使自己处于等等状态，让其他线程执行。
 * notify()方法：当生产者/消费者向缓冲区放入/取出一个产品时，向其他等待的线程发出可执行的通知，同时放弃锁，使自己处于等待状态。
 * 光看文字可能不太好理解，咱来段代码就明白了：
 * <p>
 * * 二、await() / signal()方法
 * 在JDK5.0之后，Java提供了更加健壮的线程处理机制，包括同步、锁定、线程池等，它们可以实现更细粒度的线程控制。await()和signal()就是其中用来做同步的两种方法，它们的功能基本上和wait() / nofity
 * ()相同，完全可以取代它们，但是它们和新引入的锁定机制Lock直接挂钩，具有更大的灵活性。通过在Lock对象上调用newCondition()方法，将条件变量和一个锁对象进行绑定，进而控制并发程序访问竞争资源的安全。下面来看代码：
 *
 */

public class PrdCosTest
{
    public static void main(String[] args)
    {
        // 仓库对象
        // 一、wait() / notify()方法
       // Storage storage = new Storage();

        //二、await() / signal()方法
        Storage_withLock storage = new Storage_withLock();

        // 生产者对象
        Producer p1 = new Producer(storage);
        Producer p2 = new Producer(storage);
        Producer p3 = new Producer(storage);
        Producer p4 = new Producer(storage);
        Producer p5 = new Producer(storage);
        Producer p6 = new Producer(storage);
        Producer p7 = new Producer(storage);

        // 消费者对象
        Consumer c1 = new Consumer(storage);
        Consumer c2 = new Consumer(storage);
        Consumer c3 = new Consumer(storage);

        // 设置生产者产品生产数量
        p1.setNum(10);
        p2.setNum(10);
        p3.setNum(10);
        p4.setNum(10);
        p5.setNum(10);
        p6.setNum(10);
        p7.setNum(80);

        // 设置消费者产品消费数量
        c1.setNum(50);
        c2.setNum(20);
        c3.setNum(30);

        // 线程开始执行
        c1.start();
        c2.start();
        c3.start();
        p1.start();
        p2.start();
        p3.start();
        p4.start();
        p5.start();
        p6.start();
        p7.start();
    }
}
