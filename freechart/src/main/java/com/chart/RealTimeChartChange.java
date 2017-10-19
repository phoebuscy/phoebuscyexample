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
        //����ʱ��ͼ����
        // timeSeries = new TimeSeries(chartContent);
        timeSeries = new XYSeries(chartContent);

        // TimeSeriesCollection timeseriescollection = new TimeSeriesCollection(timeSeries);
        XYSeriesCollection timeseriescollection = new XYSeriesCollection(timeSeries);

        //  JFreeChart jfreechart = ChartFactory.createTimeSeriesChart(title, "ʱ��(��)", yaxisName, timeseriescollection, true,
        //     true,false);
        JFreeChart jfreechart = ChartFactory.createXYLineChart("title", "xAxis", "yAxis", null);
        XYPlot xyplot = jfreechart.getXYPlot();
        //�������趨
        ValueAxis valueaxis = xyplot.getDomainAxis();
        //�Զ��������������ݷ�Χ
        valueaxis.setAutoRange(true);
        //������̶����ݷ�Χ 30s
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
        RealTimeChartChange rtcp = new RealTimeChartChange("Random Data", "�����", "��ֵ");
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