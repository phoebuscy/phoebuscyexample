package com.chart;

import com.utils.GBC;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.RefineryUtilities;
import static com.chart.pub.setFreeChartTheme;

public class FreeChartTest extends JFrame
{

    public FreeChartTest(String s)
    {
        super(s);
        setFreeChartTheme();
        initUI();
    }

    public void initUI()
    {
        JPanel contentPnl = new JPanel();
        contentPnl.setLayout(new GridBagLayout());
        JPanel linePnl = createDemoLine();
        contentPnl.add(linePnl,new GBC(0,0));
        JButton testBtn = new JButton("Test");
        testBtn.addActionListener(new AbstractAction()
        {
            public void actionPerformed(ActionEvent e)
            {
                onBtnPut();
            }
        });
        contentPnl.add(testBtn,new GBC(0,1));
        setContentPane(contentPnl);
    }

    private void onBtnPut()
    {

    }

    public static void main(String[] args)
    {
        FreeChartTest fjc = new FreeChartTest("����ͼ");
        fjc.pack();
        RefineryUtilities.centerFrameOnScreen(fjc);
        fjc.setVisible(true);
    }

    // ������ʾͼ������
    public  JPanel createDemoLine()
    {
        JFreeChart jfreechart = createChart(createDataset());
        return new ChartPanel(jfreechart);
    }

    // ����ͼ��������JFreeChart
    public JFreeChart createChart(DefaultCategoryDataset linedataset)
    {
        // ����ͼ�����
        JFreeChart chart = ChartFactory.createLineChart("һ������������", //����ͼ����
                                                        "ʱ��", // ����������
                                                        "���۶�(����)", // ����������
                                                        linedataset, // ����
                                                        PlotOrientation.VERTICAL, // ˮƽ��ʾͼ��
                                                        true, // include legend
                                                        true, // tooltips
                                                        false // urls
                                                       );

        CategoryPlot plot = chart.getCategoryPlot();

        plot.setRangeGridlinesVisible(true); //�Ƿ���ʾ������
        plot.setBackgroundAlpha(0.3f); //���ñ���͸����
        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        rangeAxis.setAutoRangeIncludesZero(true);
        rangeAxis.setUpperMargin(0.20);
        rangeAxis.setLabelAngle(Math.PI / 2.0);

        return chart;
    }

    // ��������
    public DefaultCategoryDataset createDataset()
    {
        DefaultCategoryDataset linedataset = new DefaultCategoryDataset();


        // ����������
        String series1 = "����";
        String series2 = "�ʵ�";
        String series3 = "ϴ�»�";
        // ��������(������)
        String type1 = "1��";
        String type2 = "2��";
        String type3 = "3��";
        linedataset.addValue(0.0, series1, type1);
        linedataset.addValue(4.2, series1, type2);
        linedataset.addValue(3.9, series1, type3);
        linedataset.addValue(1.0, series2, type1);
        linedataset.addValue(5.2, series2, type2);
        linedataset.addValue(7.9, series2, type3);
        linedataset.addValue(2.0, series3, type1);
        linedataset.addValue(9.2, series3, type2);
        linedataset.addValue(8.9, series3, type3);

        return linedataset;
    }




}
