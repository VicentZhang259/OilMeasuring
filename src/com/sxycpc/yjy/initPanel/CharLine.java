/**
 * copy right 2018 zhangpengfei
 */
package com.sxycpc.yjy.initPanel;


/**
 * @author andymacbook
 *
 */
import java.awt.Color;
import java.awt.Font;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeriesCollection;

import com.sxycpc.yjy.service.InitProcess;

public class CharLine {
	
	static CharLine charInstance;
	
	XYDataset userDataSet ;

	
  public static CharLine getInstance() {
	  if(charInstance == null) {
		  charInstance  = new CharLine();
	  }
	  return charInstance;
  }
  
  

  /** 
   * 创建数据集合 
   * @return dataSet 
   */  
  public static XYDataset createDataSet() {  
      // 实例化DefaultCategoryDataset对象  
	  XYSeriesCollection dataSet = new XYSeriesCollection();  
      // 向数据集合中添加数据  
      
//      dataSet.addValue(50, "Kro",0.2);  
//      dataSet.addValue(100, "Kro", "0.4");  
//      dataSet.addValue(900, "Kro", "0.6");  
//      dataSet.addValue(20, "Krw", "0.2");  
//      dataSet.addValue(500, "Krw", "0.4");  
//      dataSet.addValue(800, "Krw", "0.6");  
      return dataSet;  
  }  
  /** 
   * 创建JFreeChart对象 
   * @return chart 
   */  
  public JFreeChart createChart(XYSeriesCollection dataSet) {  
      StandardChartTheme standardChartTheme = new StandardChartTheme("CN"); //创建主题样式  
      standardChartTheme.setExtraLargeFont(new Font("隶书", Font.BOLD, 20)); //设置标题字体  
      standardChartTheme.setRegularFont(new Font("宋体", Font.PLAIN, 15)); //设置图例的字体  
      standardChartTheme.setLargeFont(new Font("宋体", Font.PLAIN, 15)); //设置轴向的字体  
      ChartFactory.setChartTheme(standardChartTheme);//设置主题样式  
      // 通过ChartFactory创建JFreeChart  
      JFreeChart chart = ChartFactory.createXYLineChart(  
              "渗透率统计", //图表标题  
              "SW", //横轴标题  
              "渗透率（%）",//纵轴标题  
              //createDataSet(),//数据集合  
              dataSet,
              PlotOrientation.VERTICAL, //图表方向  
              true,//是否显示图例标识  
              false,//是否显示tooltips  
              false);//是否支持超链接  
      return chart;  
  }  
  
  
  public XYDataset getUserDataSet() {
	return userDataSet;
}



public void setUserDataSet(XYDataset userDataSet) {
	this.userDataSet = userDataSet;
}



// 本地测试  
  public static void main(String[] args) {  
	  CharLine instance = getInstance();
      //ChartFrame cf = new ChartFrame("Test", instance.createChart());  
      //cf.pack();  
     // cf.setVisible(true);  
  }  
} 

