package com.pc;

/**
 * ��������Consumer�̳��߳���Thread
 * <p>
 * Email:530025983@qq.com
 *
 * @author MONKEY.D.MENG 2011-03-15
 */
public class Consumer extends Thread
{
    // ÿ�����ѵĲ�Ʒ����
    private int num;

    // ���ڷ��õĲֿ�
    private Storage_withLock storage;

    // ���캯�������òֿ�
    public Consumer(Storage_withLock storage)
    {
        this.storage = storage;
    }

    // �߳�run����
    public void run()
    {
        consume(num);
    }

    // ���òֿ�Storage����������
    public void consume(int num)
    {
        storage.consume(num);
    }

    // get/set����
    public int getNum()
    {
        return num;
    }

    public void setNum(int num)
    {
        this.num = num;
    }

    public Storage_withLock getStorage()
    {
        return storage;
    }

    public void setStorage(Storage_withLock storage)
    {
        this.storage = storage;
    }
}