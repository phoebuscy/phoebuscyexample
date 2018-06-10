package com.chart;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class RealTimeChartChange extends ChartPanel implements Runnable
{
    // private static TimeSeries timeSeries;
    private static XYSeries timeSeries;
    private long value = 0;
    private long xValue = 0;

    public RealTimeChartChange(String chartContent, String title, String yaxisName)
    {
        super(createChart(chartContent, title, yaxisName));
    }

    private static JFreeChart createChart(String chartContent, String title, String yaxisName)
    {
        //创建时序图对象
        // timeSeries = new TimeSeries(chartContent);
        timeSeries = new XYSeries(chartContent);

        // TimeSeriesCollection timeseriescollection = new TimeSeriesCollection(timeSeries);
        XYSeriesCollection timeseriescollection = new XYSeriesCollection(timeSeries);

        //  JFreeChart jfreechart = ChartFactory.createTimeSeriesChart(title, "时间(秒)", yaxisName, timeseriescollection, true,
        //     true,false);
        JFreeChart jfreechart = ChartFactory.createXYLineChart("title", "xAxis", "yAxis", null);
        XYPlot xyplot = jfreechart.getXYPlot();
        //纵坐标设定
        ValueAxis valueaxis = xyplot.getDomainAxis();
        //自动设置数据轴数据范围
        valueaxis.setAutoRange(true);
        //数据轴固定数据范围 30s
        valueaxis.setFixedAutoRange(30000D);

        valueaxis = xyplot.getRangeAxis();
        //valueaxis.setRange(0.0D,200D);

        return jfreechart;
    }

    public void run()
    {
        while (true)
        {
            try
            {
                // timeSeries.add(new Millisecond(), randomNum());
                timeSeries.add(xValue++, randomNum());
                Thread.sleep(300);
            } catch (InterruptedException e)
            {
            }
        }
    }

    private long randomNum()
    {
        System.out.println((Math.random() * 20 + 80));
        return (long) (Math.random() * 20 + 80);
    }


    public static void main(String[] args)
    {
        JFrame frame = new JFrame("Test Chart");
        RealTimeChartChange rtcp = new RealTimeChartChange("Random Data", "随机数", "数值");
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
}