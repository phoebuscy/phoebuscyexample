package com.chart;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

public class TestJFreeChart extends JFrame
{
    public TestJFreeChart(String title, JFreeChart chart, boolean scrollPane)
    {
        super(title);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        ChartPanel chartPanel = new ChartPanel(chart);
        if (scrollPane)
        {
            setContentPane(new JScrollPane(chartPanel));
        }
        else
        {
            this.setContentPane(chartPanel);
        }
    }

    public static void main(String[] args)
    {
        JFreeChart freeChart = getFreeChart();
        TestJFreeChart testJFreeChart = new TestJFreeChart("", freeChart,false);
        testJFreeChart.pack();
        testJFreeChart.setVisible(true);

    }



    public static JFreeChart getFreeChart()
    {
        StandardChartTheme mChartTheme = new StandardChartTheme("CN");
        mChartTheme.setLargeFont(new Font("����", Font.BOLD, 20));
        mChartTheme.setExtraLargeFont(new Font("����", Font.PLAIN, 15));
        mChartTheme.setRegularFont(new Font("����", Font.PLAIN, 15));
        ChartFactory.setChartTheme(mChartTheme);
        CategoryDataset mDataset = GetDataset();

        JFreeChart mChart = ChartFactory.createLineChart("����ͼ",//ͼ����
                                                         "���",//������
                                                         "����",//������
                                                         mDataset,//���ݼ�
                                                         PlotOrientation.VERTICAL, true, // ��ʾͼ��
                                                         true, // ���ñ�׼������
                                                         false);// �Ƿ����ɳ�����

        CategoryPlot mPlot = (CategoryPlot) mChart.getPlot();
        mPlot.setBackgroundPaint(Color.LIGHT_GRAY);
        mPlot.setRangeGridlinePaint(Color.BLUE);//�����ײ�������
        mPlot.setOutlinePaint(Color.RED);//�߽���
        return mChart;
    }

    public  static CategoryDataset GetDataset()
    {
        DefaultCategoryDataset mDataset = new DefaultCategoryDataset();

        /*
        mDataset.addValue(1, "First", "2013");
        mDataset.addValue(3, "First", "2014");
        mDataset.addValue(2, "First", "2015");
        mDataset.addValue(6, "First", "2016");
        mDataset.addValue(5, "First", "2017");
        mDataset.addValue(12, "First", "2018");

        mDataset.addValue(14, "Second", "2013");
        mDataset.addValue(13, "Second", "2014");
        mDataset.addValue(12, "Second", "2015");
        mDataset.addValue(9, "Second", "2016");
        mDataset.addValue(5, "Second", "2017");
        mDataset.addValue(7, "Second", "2018");
        */

        return mDataset;
    }


}
