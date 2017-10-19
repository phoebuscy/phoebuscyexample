package com.chart;

import java.awt.Color;
import java.awt.Font;
import java.awt.Paint;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.plot.DefaultDrawingSupplier;
import org.jfree.chart.plot.PieLabelLinkStyle;
import org.jfree.chart.renderer.category.StandardBarPainter;
import org.jfree.chart.renderer.xy.StandardXYBarPainter;
import org.jfree.ui.RectangleInsets;
import static org.jfree.chart.plot.DefaultDrawingSupplier.DEFAULT_FILL_PAINT_SEQUENCE;
import static org.jfree.chart.plot.DefaultDrawingSupplier.DEFAULT_PAINT_SEQUENCE;

public class pub
{

    // ��������������ʽ �������
    public static void setFreeChartTheme()
    {
        Font FONT = new Font("����", Font.PLAIN, 12);
        StandardChartTheme chartTheme = new StandardChartTheme("CN");
        // ���ñ�������
        chartTheme.setExtraLargeFont(FONT);
        // ����ͼ��������
        chartTheme.setRegularFont(FONT);
        // �������������
        chartTheme.setLargeFont(FONT);
        chartTheme.setSmallFont(FONT);
        chartTheme.setTitlePaint(new Color(51, 51, 51));//Paint �������Ϊ������ɫ������������ɫ
        chartTheme.setSubtitlePaint(new Color(85, 85, 85));//������������ɫ

        chartTheme.setLegendBackgroundPaint(Color.WHITE);// ���ñ�ע����ɫ
        chartTheme.setLegendItemPaint(Color.BLACK);//����������ɫ
        chartTheme.setChartBackgroundPaint(Color.WHITE);//ͼ����ɫ
        // ������ɫ������ɫ.������Ӧ��
        // paintSequence,outlinePaintSequence,strokeSequence,outlineStrokeSequence,shapeSequence

        Paint[] OUTLINE_PAINT_SEQUENCE = new Paint[]{Color.WHITE};//��߿�������ɫ
        //��������ɫԴ-�������� ��ͼ Ĭ����ɫ��������DefaultDrawingSupplier
        DefaultDrawingSupplier drawingSupplier = new DefaultDrawingSupplier(DEFAULT_PAINT_SEQUENCE,
                                                                            DEFAULT_FILL_PAINT_SEQUENCE,
                                                                            OUTLINE_PAINT_SEQUENCE,
                                                                            DefaultDrawingSupplier
                                                                                    .DEFAULT_STROKE_SEQUENCE,
                                                                            DefaultDrawingSupplier
                                                                                    .DEFAULT_OUTLINE_STROKE_SEQUENCE,
                                                                            DefaultDrawingSupplier
                                                                                    .DEFAULT_SHAPE_SEQUENCE);
        chartTheme.setDrawingSupplier(drawingSupplier);//���û�����ɫ����������߿�Ӧ��

        chartTheme.setPlotBackgroundPaint(Color.WHITE);// �������򱳾�ɫ
        chartTheme.setPlotOutlinePaint(Color.WHITE);// ����������߿�
        chartTheme.setLabelLinkPaint(new Color(8, 55, 114));// ���ӱ�ǩ��ɫ
        chartTheme.setLabelLinkStyle(PieLabelLinkStyle.CUBIC_CURVE);

        chartTheme.setAxisOffset(new RectangleInsets(5, 12, 5, 12));//����X-Y������ƫ����
        chartTheme.setDomainGridlinePaint(new Color(192, 208, 224));// X�����ᴹֱ������ɫ
        chartTheme.setRangeGridlinePaint(new Color(192, 192, 192));// Y������ˮƽ������ɫ

        chartTheme.setBaselinePaint(Color.WHITE);
        chartTheme.setCrosshairPaint(Color.BLUE);// ��ȷ������
        chartTheme.setAxisLabelPaint(new Color(51, 51, 51));// ���������������ɫ
        chartTheme.setTickLabelPaint(new Color(67, 67, 72));// �̶�������ɫ
        chartTheme.setBarPainter(new StandardBarPainter());// ������״ͼ��Ⱦ��Ŷ������Ⱦ�������ý���
        chartTheme.setXYBarPainter(new StandardXYBarPainter());// XYBar ��Ⱦ��ֵʹ����ɫ��Ⱦ�������ý���

        chartTheme.setItemLabelPaint(Color.black);
        chartTheme.setThermometerPaint(Color.white);// �¶ȼ�

        ChartFactory.setChartTheme(chartTheme);//����������ʽ
    }

}
