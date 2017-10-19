package com.chart;

import com.utils.GBC;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.Range;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import static com.chart.pub.setFreeChartTheme;

public class RealTimeChartTest extends ChartPanel
{

    private static XYSeries xySeries;

    private static int xTicket = 1;

    public static void main(String[] args)
    {
        JFrame frame = new JFrame("ʵʱ����");
        RealTimeChartTest rtcp = new RealTimeChartTest("�������", "����", "yaxisName");
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
       // xySeries.add(randomNum(), randomNum());
      //  xTicket++;
       // xySeries.add(xTicket,0.2);

        switch (xTicket++)
        {
            case 1:
                xySeries.add(0.1, 0.1);
                break;
            case 2:
                xySeries.add(0.2, 0.1);
                break;
            case 3:
                xySeries.add(0.3, 0.1);
                break;
            case 4:
                xySeries.add(0.4,0.1);

        }


    }

    private static long randomNum()
    {
        System.out.println((Math.random() * 20 + 80));
        return (long) (Math.random() * 20 + 80);
    }


    public RealTimeChartTest(String chartContent, String title, String yaxisName)
    {
        super(createChart(chartContent, title, yaxisName));
    }

    private static JFreeChart createChart(String chartContent, String title, String yaxisName)
    {

        // ����ʱ��ͼ����
        xySeries = new XYSeries(0, false);
        XYSeriesCollection xySeriesCollection = new XYSeriesCollection(xySeries);
        JFreeChart jfreechart = ChartFactory.createTimeSeriesChart(title,
                                                                   "time(second)",
                                                                   yaxisName,
                                                                   xySeriesCollection,
                                                                   true,
                                                                   true,
                                                                   false);

        jfreechart = ChartFactory.createXYLineChart("title","xAxis", "yAxis", xySeriesCollection);


        XYPlot xyplot = jfreechart.getXYPlot();

        // �������趨
        ValueAxis valueaxis = xyplot.getDomainAxis();
        // �Զ��������������ݷ�Χ
        valueaxis.setAutoRange(false);
        // ������̶����ݷ�Χ 30s
      //  valueaxis.setFixedAutoRange(300D);

        ValueAxis rangeAxis = xyplot.getRangeAxis();

        NumberAxis domainAxis = (NumberAxis)xyplot.getDomainAxis();//x������
        domainAxis.setRange(1.0,840.0);
        domainAxis.setTickUnit(new NumberTickUnit(100)); //ÿ100���̶���ʾһ���̶�ֵ

        // valueaxis.setRange(0.0D,200D);
        return jfreechart;
    }



}