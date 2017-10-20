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

    // 设置中文主题样式 解决乱码
    public static void setFreeChartTheme()
    {
        Font FONT = new Font("宋体", Font.PLAIN, 12);
        StandardChartTheme chartTheme = new StandardChartTheme("CN");
        // 设置标题字体
        chartTheme.setExtraLargeFont(FONT);
        // 设置图例的字体
        chartTheme.setRegularFont(FONT);
        // 设置轴向的字体
        chartTheme.setLargeFont(FONT);
        chartTheme.setSmallFont(FONT);
        chartTheme.setTitlePaint(new Color(51, 51, 51));//Paint 可以理解为绘制颜色；标题字体颜色
        chartTheme.setSubtitlePaint(new Color(85, 85, 85));//副标题字体颜色

        chartTheme.setLegendBackgroundPaint(Color.WHITE);// 设置标注背景色
        chartTheme.setLegendItemPaint(Color.BLACK);//设置字体颜色
        chartTheme.setChartBackgroundPaint(Color.WHITE);//图表背景色
        // 绘制颜色绘制颜色.轮廓供应商
        // paintSequence,outlinePaintSequence,strokeSequence,outlineStrokeSequence,shapeSequence

        Paint[] OUTLINE_PAINT_SEQUENCE = new Paint[]{Color.WHITE};//外边框线条颜色
        //绘制器颜色源-所有线条 饼图 默认颜色都是来自DefaultDrawingSupplier
        DefaultDrawingSupplier drawingSupplier = new DefaultDrawingSupplier(DEFAULT_PAINT_SEQUENCE,
                                                                            DEFAULT_FILL_PAINT_SEQUENCE,
                                                                            OUTLINE_PAINT_SEQUENCE,
                                                                            DefaultDrawingSupplier
                                                                                    .DEFAULT_STROKE_SEQUENCE,
                                                                            DefaultDrawingSupplier
                                                                                    .DEFAULT_OUTLINE_STROKE_SEQUENCE,
                                                                            DefaultDrawingSupplier
                                                                                    .DEFAULT_SHAPE_SEQUENCE);
        chartTheme.setDrawingSupplier(drawingSupplier);//设置绘制颜色、线条、外边框供应商

        chartTheme.setPlotBackgroundPaint(Color.WHITE);// 绘制区域背景色
        chartTheme.setPlotOutlinePaint(Color.WHITE);// 绘制区域外边框
        chartTheme.setLabelLinkPaint(new Color(8, 55, 114));// 链接标签颜色
        chartTheme.setLabelLinkStyle(PieLabelLinkStyle.CUBIC_CURVE);

        chartTheme.setAxisOffset(new RectangleInsets(5, 12, 5, 12));//设置X-Y坐标轴偏移量
        chartTheme.setDomainGridlinePaint(new Color(192, 208, 224));// X坐标轴垂直网格颜色
        chartTheme.setRangeGridlinePaint(new Color(192, 192, 192));// Y坐标轴水平网格颜色

        chartTheme.setBaselinePaint(Color.WHITE);
        chartTheme.setCrosshairPaint(Color.BLUE);// 不确定含义
        chartTheme.setAxisLabelPaint(new Color(51, 51, 51));// 坐标轴标题文字颜色
        chartTheme.setTickLabelPaint(new Color(67, 67, 72));// 刻度数字颜色
        chartTheme.setBarPainter(new StandardBarPainter());// 设置柱状图渲染问哦基本渲染，不采用渐变
        chartTheme.setXYBarPainter(new StandardXYBarPainter());// XYBar 渲染，值使用颜色渲染，不采用渐变

        chartTheme.setItemLabelPaint(Color.black);
        chartTheme.setThermometerPaint(Color.white);// 温度计

        ChartFactory.setChartTheme(chartTheme);//设置主题样式
    }

}
