package com.swingwork;

import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import java.math.BigInteger;
import java.util.concurrent.ExecutionException;

public class SwingWorkTest
{


    public static void main(String[] args)
    {

        SwingUtilities.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                swtest(3);
            }
        });
    }




    public static void swtest(int sec)
    {

        SwingWorker worker = new SwingWorker()
        {
            @Override
            protected Object doInBackground() throws Exception
            {
                int ret = getInt(3);
                return BigInteger.valueOf(ret);
            }


            @Override
            protected void done()
            {
                try
                {
                    Object ret = get();
                    int a = 1;

                } catch (InterruptedException | ExecutionException e)
                {
                    e.printStackTrace();
                }
                super.done();
            }
        };
        worker.run();

    }


    public static int getInt(int in)
    {

        try
        {
            Thread.sleep(in * 1000);
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        return in;
    }


}
