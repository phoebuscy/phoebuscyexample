package com.chart;

import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.time.Millisecond;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;

public class RealTimeChart extends ChartPanel implements Runnable
{

    private static TimeSeries timeSeries;


    public static void main(String[] args)
    {
        JFrame frame = new JFrame("Test Chart");
        RealTimeChart rtcp = new RealTimeChart("Random Data", "title", "yaxisName");
        frame.getContentPane().add(rtcp, new BorderLayout().CENTER);
        frame.pack();
        frame.setVisible(true);
        (new Thread(rtcp)).start();
        frame.addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent windowevent)
            {
                System.exit(0);
            }
        });
    }


    public RealTimeChart(String chartContent, String title, String yaxisName)
    {
        super(createChart(chartContent, title, yaxisName));
    }

    private static JFreeChart createChart(String chartContent, String title, String yaxisName)
    {
        // TODO Auto-generated method stub
        // ����ʱ��ͼ����
        timeSeries = new TimeSeries(chartContent);
        TimeSeriesCollection timeseriescollection = new TimeSeriesCollection(timeSeries);
        JFreeChart jfreechart = ChartFactory.createTimeSeriesChart(title,
                                                                   "time(second)",
                                                                   yaxisName,
                                                                   timeseriescollection,
                                                                   true,
                                                                   true,
                                                                   false);
        XYPlot xyplot = jfreechart.getXYPlot();

        // �������趨
        ValueAxis valueaxis = xyplot.getDomainAxis();
        // �Զ��������������ݷ�Χ
         valueaxis.setAutoRange(true);
        // ������̶����ݷ�Χ 30s
        valueaxis.setFixedAutoRange(30000D);

        valueaxis = xyplot.getRangeAxis();

        // valueaxis.setRange(0.0D,200D);
        return jfreechart;
    }

    private long randomNum()
    {
        System.out.println((Math.random() * 20 + 80));
        return (long) (Math.random() * 20 + 80);
    }
    @Override
    public void run()
    {
        while (true)
        {
            try
            {
                timeSeries.add(new Millisecond(), randomNum());
                Thread.sleep(300);
            }
            catch (InterruptedException e)
            {
            }
        }
    }

}