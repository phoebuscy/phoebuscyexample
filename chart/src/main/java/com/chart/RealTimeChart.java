package com.chart;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardXYItemLabelGenerator;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.data.time.Millisecond;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.ui.TextAnchor;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Date;

public class RealTimeChart extends ChartPanel implements Runnable
{

    private static TimeSeries timeSeries;
    private static Date begDate = new Date(2017, 10,20);
    private static Date endDate = new Date(2017,10,21);
    private static long cruTime = begDate.getTime();



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

        // X�����趨
        ValueAxis xAxis = xyplot.getDomainAxis();
        // �Զ��������������ݷ�Χ
         xAxis.setAutoRange(false);
        // ������̶����ݷ�Χ 300s
       // valueaxis.setFixedAutoRange(300000D);
        xAxis.setRange(begDate.getTime(), endDate.getTime());

        // ����Y�᷶Χ
        ValueAxis yAxis = xyplot.getRangeAxis();
        yAxis.setRange(255.50,257.55);
        // rangeAxis.setAutoRange(true);
        xyplot.getRangeAxis().setUpperMargin(1.1);// ���ö���Y��������,��ֹ�����޷���ʾ
        xyplot.getRangeAxis().setLowerMargin(1.1);// ���õײ�Y��������

        /*
        //����������ʾ�����ݵ��ֵ
        XYItemRenderer xyitem = xyplot.getRenderer();
        xyitem.setBaseItemLabelsVisible(true);
        xyitem.setBasePositiveItemLabelPosition(new ItemLabelPosition(ItemLabelAnchor.OUTSIDE12, TextAnchor.BASELINE_LEFT));
        //���������Ƕ���������ͼ���ݱ�ʾ�Ĺؼ�����
        xyitem.setBaseItemLabelGenerator(new StandardXYItemLabelGenerator());
        xyitem.setBaseItemLabelFont(new Font("Dialog", 1, 14));
        xyplot.setRenderer(xyitem);
        */


        return jfreechart;
    }

    private Double randomNum()
    {
        Double val = Math.random()  + 256.05;
        System.out.println(val);
        return val;
    }
    @Override
    public void run()
    {
        while (true)
        {
            try
            {
                //timeSeries.add(new Millisecond(), randomNum());
                timeSeries.add(new Millisecond(new Date(cruTime+= 800000)), randomNum());
                Thread.sleep(300);
            }
            catch (InterruptedException e)
            {
            }
        }
    }

}