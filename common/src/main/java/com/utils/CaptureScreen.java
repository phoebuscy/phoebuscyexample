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
        // 截图保存的路径
        File screenFile = new File(fileName);
        // 如果路径不存在,则创建
        if (!screenFile.getParentFile().exists()) {
            screenFile.getParentFile().mkdirs();
        }
        //判断文件是否存在，不存在就创建文件
        if(!screenFile.exists()&& !screenFile .isDirectory()) {
            screenFile.mkdir();
        }

        File f = new File(screenFile, folder);
        ImageIO.write(image, "png", f);
        //自动打开
        /*if (Desktop.isDesktopSupported()
                 && Desktop.getDesktop().isSupported(Desktop.Action.OPEN))
                    Desktop.getDesktop().open(f);*/
    }

    public static void main(String[] args) {



        ScheduledExecutorService timer = Executors.newSingleThreadScheduledExecutor();

        TimerTask timerTask = new TimerTask(2000); // 任务需要 2000 ms 才能执行完毕

        System.out.printf("起始时间：%s\n\n", new SimpleDateFormat("HH:mm:ss").format(new Date()));

        // 延时 1 秒后，按 5 秒的周期执行任务
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