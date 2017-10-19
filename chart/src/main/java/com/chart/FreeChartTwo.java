package com.chart;

import java.awt.Color;
import javax.swing.JFrame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.time.Month;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.ui.RectangleInsets;

public class FreeChartTwo extends JFrame
{
    private JFreeChart chart;

    public FreeChartTwo(String title, JFreeChart chart)
    {
        super(title);
        this.chart = chart;
    }


    public static void main(String[] args)
    {
        //时间序列图
        TimeSeries timeseries = new TimeSeries("L&G European Index Trust", Month.class);
        timeseries.add(new Month(2, 2001), 181.8D);//这里用的是Month.class，同样还有Day.class Year.class 等等
        timeseries.add(new Month(3, 2001), 167.3D);
        timeseries.add(new Month(4, 2001), 153.8D);
        timeseries.add(new Month(5, 2001), 167.6D);
        timeseries.add(new Month(6, 2001), 158.8D);
        timeseries.add(new Month(7, 2001), 148.3D);
        timeseries.add(new Month(8, 2001), 153.9D);
        timeseries.add(new Month(9, 2001), 142.7D);
        timeseries.add(new Month(10, 2001), 123.2D);
        timeseries.add(new Month(11, 2001), 131.8D);
        timeseries.add(new Month(12, 2001), 139.6D);
        timeseries.add(new Month(1, 2002), 142.9D);
        timeseries.add(new Month(2, 2002), 138.7D);
        timeseries.add(new Month(3, 2002), 137.3D);
        timeseries.add(new Month(4, 2002), 143.9D);
        timeseries.add(new Month(5, 2002), 139.8D);
        timeseries.add(new Month(6, 2002), 137D);
        timeseries.add(new Month(7, 2002), 132.8D);

        TimeSeries timeseries1 = new TimeSeries("L&G UK Index Trust",Month.class);
        timeseries1.add(new Month(2, 2001), 129.6D);
        timeseries1.add(new Month(3, 2001), 123.2D);
        timeseries1.add(new Month(4, 2001), 117.2D);
        timeseries1.add(new Month(5, 2001), 124.1D);
        timeseries1.add(new Month(6, 2001), 122.6D);
        timeseries1.add(new Month(7, 2001), 119.2D);
        timeseries1.add(new Month(8, 2001), 116.5D);
        timeseries1.add(new Month(9, 2001), 112.7D);
        timeseries1.add(new Month(10, 2001), 101.5D);
        timeseries1.add(new Month(11, 2001), 106.1D);
        timeseries1.add(new Month(12, 2001), 110.3D);
        timeseries1.add(new Month(1, 2002), 111.7D);
        timeseries1.add(new Month(2, 2002), 111D);
        timeseries1.add(new Month(3, 2002), 109.6D);
        timeseries1.add(new Month(4, 2002), 113.2D);
        timeseries1.add(new Month(5, 2002), 111.6D);
        timeseries1.add(new Month(6, 2002), 108.8D);
        timeseries1.add(new Month(7, 2002), 101.6D);
        TimeSeriesCollection timeseriescollection = new TimeSeriesCollection();
        timeseriescollection.addSeries(timeseries);
        timeseriescollection.addSeries(timeseries1);
        timeseriescollection.setDomainIsPointsInTime(true); //domain轴上的刻度点代表的是时间点而不是时间段
        JFreeChart jfreechart = ChartFactory.createTimeSeriesChart("Legal & General Unit Trust Prices",
                                                                   "Date",
                                                                   "Price Per Unit",
                                                                   timeseriescollection,
                                                                   true,
                                                                   true,
                                                                   false);
        jfreechart.setBackgroundPaint(Color.white);
        XYPlot xyplot = (XYPlot)jfreechart.getPlot(); //获得 plot : XYPlot!!
        xyplot.setBackgroundPaint(Color.lightGray);
        xyplot.setDomainGridlinePaint(Color.white);
        xyplot.setRangeGridlinePaint(Color.white);
        xyplot.setAxisOffset(new RectangleInsets(5D, 5D, 5D, 5D));
        xyplot.setDomainCrosshairVisible(true);
        xyplot.setRangeCrosshairVisible(true);
        ChartFrame frame=new ChartFrame ("折线图 ", jfreechart, true);
        frame.pack();
        frame.setVisible(true);
    }

}
