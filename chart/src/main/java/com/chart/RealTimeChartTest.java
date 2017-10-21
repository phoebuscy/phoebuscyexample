package com.chart;

import com.utils.GBC;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import static com.chart.pub.setFreeChartTheme;

public class RealTimeChartTest extends ChartPanel
{

    private static XYSeries xySeries;

    private static int xTicket = 1;

    public static void main(String[] args)
    {
        JFrame frame = new JFrame("实时数据");
        RealTimeChartTest rtcp = new RealTimeChartTest("随机数据", "标题", "yaxisName");
        frame.getContentPane().add(crtContentPnl(rtcp), new BorderLayout().CENTER);
        frame.pack();
        frame.setVisible(true);
        frame.addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent windowevent)
            {
                System.exit(0);
            }
        });
    }

    public static JPanel crtContentPnl(RealTimeChartTest rtcp)
    {
        setFreeChartTheme();
        JPanel contentPnl = new JPanel();
        contentPnl.setLayout(new GridBagLayout());
        contentPnl.add(rtcp,new GBC(0, 0));
        JButton testBtn = new JButton("Test");
        testBtn.addActionListener(new AbstractAction()
        {
            public void actionPerformed(ActionEvent e)
            {
                onBtnPut();
            }
        });
        contentPnl.add(testBtn,new GBC(0,1));
        return contentPnl;
    }

    private static void onBtnPut()
    {
        for(int i = 0; i< 100; i+=5)
        {
            Double val = randomNum();

            xySeries.add(i, val);
            System.out.println(val);
        }

    }

    private static Double randomNum()
    {
        Double val = Math.random()  + 150.5;
        System.out.println(val);
        return val;
    }


    public RealTimeChartTest(String chartContent, String title, String yaxisName)
    {
        super(createChart(chartContent, title, yaxisName));
    }

    private static JFreeChart createChart(String chartContent, String title, String yaxisName)
    {

        // 创建时序图对象
        xySeries = new XYSeries(0, false);
        XYSeriesCollection xySeriesCollection = new XYSeriesCollection(xySeries);
        /*
        JFreeChart jfreechart = ChartFactory.createTimeSeriesChart(title,
                                                                   "time(second)",
                                                                   yaxisName,
                                                                   xySeriesCollection,
                                                                   true,
                                                                   true,
                                                                   false);
                                                                   */

        JFreeChart jfreechart = ChartFactory.createXYLineChart("title","xAxis", "yAxis", xySeriesCollection);


        XYPlot xyplot = jfreechart.getXYPlot();

        // X轴设定
        ValueAxis valueaxis = xyplot.getDomainAxis();
        // 自动设置数据轴数据范围
        valueaxis.setAutoRange(false);

        // 数据轴固定数据范围 30s
      //  valueaxis.setFixedAutoRange(300D);

        ValueAxis rangeAxis = xyplot.getRangeAxis();
        rangeAxis.setRange(150.0,152.0);

        NumberAxis domainAxis = (NumberAxis)xyplot.getDomainAxis();//x轴设置
        domainAxis.setRange(1.0,200.0);
        domainAxis.setTickUnit(new NumberTickUnit(20)); //每100个刻度显示一个刻度值

        // valueaxis.setRange(0.0D,200D);
        return jfreechart;
    }



}