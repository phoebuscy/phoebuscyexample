package com.utils;


import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.imageio.ImageIO;

public class CaptureScreen {

    public static void captureScreen(String fileName, String folder) throws Exception {

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Rectangle screenRectangle = new Rectangle(screenSize);
        Robot robot = new Robot();
        BufferedImage image = robot.createScreenCapture(screenRectangle);
        // ��ͼ�����·��
        File screenFile = new File(fileName);
        // ���·��������,�򴴽�
        if (!screenFile.getParentFile().exists()) {
            screenFile.getParentFile().mkdirs();
        }
        //�ж��ļ��Ƿ���ڣ������ھʹ����ļ�
        if(!screenFile.exists()&& !screenFile .isDirectory()) {
            screenFile.mkdir();
        }

        File f = new File(screenFile, folder);
        ImageIO.write(image, "png", f);
        //�Զ���
        /*if (Desktop.isDesktopSupported()
                 && Desktop.getDesktop().isSupported(Desktop.Action.OPEN))
                    Desktop.getDesktop().open(f);*/
    }

    public static void main(String[] args) {



        ScheduledExecutorService timer = Executors.newSingleThreadScheduledExecutor();

        TimerTask timerTask = new TimerTask(2000); // ������Ҫ 2000 ms ����ִ�����

        System.out.printf("��ʼʱ�䣺%s\n\n", new SimpleDateFormat("HH:mm:ss").format(new Date()));

        // ��ʱ 1 ��󣬰� 5 �������ִ������
        timer.scheduleAtFixedRate(timerTask, 0, 20, TimeUnit.MINUTES);

    }


    private static class TimerTask implements Runnable
    {

        private final int sleepTime;
        private final SimpleDateFormat dateFormat;

        public TimerTask(int sleepTime)
        {
            this.sleepTime = sleepTime;
            dateFormat = new SimpleDateFormat("HH:mm:ss");
        }

        @Override
        public void run()
        {
            Date dt=new Date();
            SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
            SimpleDateFormat sdf1=new SimpleDateFormat("yyyyMMddHHmmss");
            String data=sdf.format(dt);
            String rd=sdf1.format(dt);
            try {
                captureScreen("c:\\CaptureScreen\\"+data,rd+".png");
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    }

}