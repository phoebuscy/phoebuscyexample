package com.pc;

/**
 * ��������Producer�̳��߳���Thread
 * <p>
 * Email:530025983@qq.com
 *
 * @author MONKEY.D.MENG 2011-03-15
 */
public class Producer extends Thread
{
    // ÿ�������Ĳ�Ʒ����
    private int num;

    // ���ڷ��õĲֿ�
    private Storage_withLock storage;

    // ���캯�������òֿ�
  //  public Producer(Storage storage)
    public Producer(Storage_withLock storage)
    {
        this.storage = storage;
    }

    // �߳�run����
    public void run()
    {
        produce(num);
    }

    // ���òֿ�Storage����������
    public void produce(int num)
    {
        storage.produce(num);
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