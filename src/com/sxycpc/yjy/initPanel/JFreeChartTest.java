/**
 * 
 */
package com.sxycpc.yjy.initPanel;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.chart.title.LegendTitle;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.TextAnchor;

/**
 * @author zhangpengfei2
 *
 */
public class JFreeChartTest {

	static JFreeChartTest charInstance;

	XYDataset userDataSet;

	public static JFreeChartTest getInstance() {
		if (charInstance == null) {
			charInstance = new JFreeChartTest();
		}
		return charInstance;
	}

	/**
	 * 创建数据集合
	 * 
	 * @return dataSet
	 */
	public static DefaultCategoryDataset createDataSet() {
		// 实例化DefaultCategoryDataset对象
		DefaultCategoryDataset dataSet = new DefaultCategoryDataset();
		// 向数据集合中添加数据

		dataSet.addValue(50, "Kro", "0.2");
		dataSet.addValue(100, "Kro", "0.4");
		dataSet.addValue(900, "Kro", "0.6");
		dataSet.addValue(20, "Krw", "0.2");
		dataSet.addValue(500, "Krw", "0.4");
		dataSet.addValue(800, "Krw", "0.6");
		return dataSet;
	}

	public JFreeChart drawLineChart2D(DefaultCategoryDataset dataset, String title, String scope) {
		JFreeChart chart = ChartFactory.createLineChart(title, null, "数量", dataset, PlotOrientation.VERTICAL, true,
				true, true);
		// 图表方向垂直
		chart.addSubtitle(new TextTitle(scope));
		LegendTitle legend = chart.getLegend(); // 设置图例的字体
		legend.setItemFont(new Font("宋体", Font.BOLD, 16));
		// 获取折线图plot对象
		CategoryPlot plot = (CategoryPlot) chart.getPlot();
		// 设置背景颜色
		plot.setBackgroundPaint(Color.WHITE);
		// 设置网格竖线颜色
		plot.setDomainGridlinePaint(Color.pink);
		// 设置网格横线颜色
		plot.setRangeGridlinePaint(Color.pink);
		plot.setNoDataMessage("暂无数据显示！");// 没有数据显示的时候显示这个提示
		// 取得横轴
		CategoryAxis categoryAxis = plot.getDomainAxis();
		// 设置横轴的字体
		categoryAxis.setLabelFont(new Font("宋体", Font.BOLD, 16));
		// 设置分类标签以45度倾斜
		categoryAxis.setCategoryLabelPositions(CategoryLabelPositions.STANDARD);//
		// 设置分类标签字体
		categoryAxis.setTickLabelFont(new Font("宋体", Font.BOLD, 18));
		// 取得纵轴
		NumberAxis numberAxis = (NumberAxis) plot.getRangeAxis();
		numberAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
		// 设置纵轴的字体
		numberAxis.setLabelFont(new Font("黑体", Font.PLAIN, 18));
		numberAxis.setUpperMargin(0.15);// 设置最高数据显示与顶端的距离
		numberAxis.setLowerMargin(2);// 设置最低的一个值与图片底端的距离
		// 设置背景透明度（0~1）
		plot.setBackgroundAlpha(1f);
		// 设置前景色透明度（0~1）
		plot.setForegroundAlpha(1f);
		// 获取显示线条的对象
		LineAndShapeRenderer lasp = (LineAndShapeRenderer) plot.getRenderer();
		// 设置拐点是否可见/是否显示拐点
		lasp.setBaseShapesVisible(true);
		// 设置拐点不同用不同的形状
		lasp.setDrawOutlines(true);
		// 设置线条是否被显示填充颜色
		lasp.setUseFillPaint(true);
		// 设置拐点颜色
		lasp.setBaseFillPaint(Color.blue);// 蓝色
		// 设置折线加粗
		// lasp.setSeriesStroke(0, new BasicStroke(3F));
		lasp.setSeriesOutlineStroke(0, new BasicStroke(4.5F));// 设置折点的大小
		lasp.setSeriesOutlineStroke(1, new BasicStroke(4.5F));
		lasp.setSeriesOutlineStroke(2, new BasicStroke(4.5F));
		lasp.setSeriesOutlineStroke(3, new BasicStroke(4.5F));
		lasp.setSeriesOutlineStroke(4, new BasicStroke(4.5F));
		lasp.setSeriesOutlineStroke(5, new BasicStroke(4.5F));
		lasp.setSeriesOutlineStroke(6, new BasicStroke(4.5F));
		lasp.setSeriesOutlineStroke(7, new BasicStroke(4.5F));
		lasp.setSeriesOutlineStroke(8, new BasicStroke(4.5F));
		lasp.setSeriesOutlineStroke(9, new BasicStroke(4.5F));
		lasp.setSeriesOutlineStroke(10, new BasicStroke(4.5F));
		// 设置折线拐点

		// lasp.setSeriesShape(0, new java.awt.geom.Ellipse2D.Double(-5D, -5D,
		// 10D, 10D));
		// lasp.setSeriesShape(1, new java.awt.geom.Ellipse2D.Double(-1D, -2D,
		// 10D, 10D));
		// lasp.setSeriesShape(2, new java.awt.geom.Ellipse2D.Double(-5D, -5D,
		// 10D, 10D));
		// lasp.setSeriesShape(3, new java.awt.geom.Ellipse2D.Double(-5D, -5D,
		// 10D, 10D));
		// lasp.setSeriesShape(4, new java.awt.geom.Ellipse2D.Double(-5D, -5D,
		// 10D, 10D));
		// lasp.setSeriesShape(5, new java.awt.geom.Ellipse2D.Double(-5D, -5D,
		// 10D, 10D));
		// lasp.setSeriesShape(6, new java.awt.geom.Ellipse2D.Double(-5D, -5D,
		// 10D, 10D));
		// lasp.setSeriesShape(7, new java.awt.geom.Ellipse2D.Double(-5D, -5D,
		// 10D, 10D));
		// lasp.setSeriesShape(8, new java.awt.geom.Ellipse2D.Double(-5D, -5D,
		// 10D, 10D));
		// lasp.setSeriesShape(9, new java.awt.geom.Ellipse2D.Double(-5D, -5D,
		// 10D, 10D));
		// lasp.setSeriesShape(10, new java.awt.geom.Ellipse2D.Double(-5D, -5D,
		// 10D, 10D));
		plot.setNoDataMessage("没有相关统计数据");
		LineAndShapeRenderer renderer = (LineAndShapeRenderer) plot.getRenderer();// 设置样式

		// 设置每个地区所包含的平行柱的之间距离
		renderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
		renderer.setItemLabelsVisible(true);// 显示每个点上的数据值的提示工具,数据标签是否可见
		renderer.setBasePositiveItemLabelPosition(
				new ItemLabelPosition(ItemLabelAnchor.OUTSIDE12, TextAnchor.BASELINE_LEFT));
		renderer.setItemLabelFont(new Font("黑体", Font.PLAIN, 14));// 设置数字的字体大小
		renderer.setItemLabelAnchorOffset(2D);
		// // 设置颜色-----------------------------------------------------------
		renderer.setSeriesStroke(0, new BasicStroke(4.0F));// 设置折线大小
		// renderer.setSeriesPaint(0, Color.RED);//红色
		renderer.setSeriesStroke(1, new BasicStroke(4.0F));
		// renderer.setSeriesPaint(1, Color.GREEN);//绿色
		renderer.setSeriesStroke(2, new BasicStroke(4.0F));
		// renderer.setSeriesPaint(2, Color.ORANGE);//c橙色
		renderer.setSeriesStroke(3, new BasicStroke(4.0F));
		// renderer.setSeriesPaint(3, Color.BLUE);//蓝色
		renderer.setSeriesStroke(4, new BasicStroke(4.0F));
		// renderer.setSeriesPaint(4, Color.BLACK);//黑色
		renderer.setSeriesStroke(5, new BasicStroke(4.0F));
		// renderer.setSeriesPaint(5, Color.CYAN);
		renderer.setSeriesStroke(6, new BasicStroke(4.0F));
		renderer.setSeriesStroke(7, new BasicStroke(4.0F));
		renderer.setSeriesStroke(8, new BasicStroke(4.0F));
		renderer.setSeriesStroke(9, new BasicStroke(4.0F));
		renderer.setSeriesStroke(10, new BasicStroke(4.0F));
		plot.setRenderer(renderer);// 使用我们设计的效果F
		CategoryPlot xyplot = chart.getCategoryPlot();
		xyplot.setBackgroundPaint(new Color(255, 253, 246));
		xyplot.setOutlineStroke(new BasicStroke(1.5f)); // 边框粗细

		ValueAxis vaxis = xyplot.getRangeAxisForDataset(0);
		vaxis.setAxisLineStroke(new BasicStroke(1.5f)); // 坐标轴粗细
		vaxis.setAxisLinePaint(new Color(215, 215, 215)); // 坐标轴颜色
		vaxis.setLabelPaint(new Color(10, 10, 10)); // 坐标轴标题颜色
		vaxis.setTickLabelPaint(new Color(102, 102, 102)); // 坐标轴标尺值颜色
		vaxis.setLowerMargin(0.06d);// 分类轴下（左）边距
		vaxis.setUpperMargin(2d);// 分类轴下（右）边距,防止最后边的一个数据靠近了坐标

		return chart;
	}

	public static void main(String[] args) {
		JFreeChartTest instance = getInstance();
		DefaultCategoryDataset dataSet = instance.createDataSet();
		ChartFrame cf = new ChartFrame("Test", instance.drawLineChart2D(dataSet, "Test", "See"));
		cf.pack();
		cf.setVisible(true);
	}

}
