/**
 * copy right 2018 zhangpengfei
 */
package com.sxycpc.yjy.serviceImpl;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.jfree.chart.ChartFrame;

import com.sxycpc.yjy.initPanel.CharLine;
import com.sxycpc.yjy.linerRegression.MultiLinearRegression;
import com.sxycpc.yjy.service.InitProcess;
import com.sxycpc.yjy.util.DataPoint;
import com.sxycpc.yjy.util.ExcelRead;
import com.sxycpc.yjy.util.RegressionLine;

/**
 * @author andymacbook
 *
 */
public class ProcessImpl implements InitProcess {
	
	final double Swi=0.295;
	final double Sor=0.28;
	final double N = 490;
	final double U0 = 6.3;
	final double P0 = 0.865;
	final double B0 = 1.042;
	final double Bw = 1;
	final double Pw =1;
	final double Uw = 0.34;
	final double Step = 0.1;
	private List<Double> swList  = null;
	private List<Double> kroList =null;
	private List<Double> krwList = null;
	
//	static private ProcessImpl instance = new ProcessImpl();
//	
//	static public ProcessImpl getInstane(){
//		if(null == instance) {
//			instance = new ProcessImpl();
//		}
//		return instance;
//	}
	

	@Override
	public void calculate(String filePath) {
		// TODO Auto-generated method stub
		//从excel中读取数据到容器
		double a1 = 0;
		double b1 = 0;
		File file = new File("data.xls");
		ExcelRead instance = ExcelRead.getInstance();
		List<ArrayList<String>> list =instance.readXls(file);
		//进行一元线性回归计算
		//List rowList = null;
		double oilOneYear = 0;
		double waterOneYear = 0;
		double npOil = 0;
		double x=0;
		double y=0;
		double wor =0;
		double kro_krw = 0;
		List<Double> worList = new ArrayList<Double>();
        RegressionLine line = new RegressionLine(); 
		for(List<String> rowList:list) {
			//rowList = list.iterator().next();
			if("年份".equals(rowList.get(0).toString()) || "".equals(rowList.get(0).toString()) ){
				continue;
			}
			oilOneYear =(Double.parseDouble(rowList.get(2)));
			 //y =Math.log10(Double.parseDouble(rowList.get(3)));
			waterOneYear =(Double.parseDouble(rowList.get(3)));
			npOil = (Double.parseDouble(rowList.get(4)));
			wor =waterOneYear/oilOneYear;
				worList.add(wor);
				System.out.println("wor:"+wor);
				x=npOil;
				y=Math.log10(wor);
			line.addDataPoint(new DataPoint(x, y)); 
			//wor =y/x;			
		}
		
		b1 = line.getA1();
		a1 = line.getA0();		
		//进行1到4步的计算
		double a=0.0;
		double b=b1;
		//Math.l
		//a=a1 +(Math.log10(2.303*b1));
		a=a1;
		double A = 0;
		double B =0;
		double C =0;
		
		A = Math.exp(2.303*(a-(b*N*Swi)/(1-Swi)));
		B =2.303*b*N/(1-Swi);
		C=Pw*U0*B0/(P0*Uw*Bw);
		
		System.out.println("a1"+a1+",b1:"+b1);
		
		System.out.println("a"+a+",A:"+A+",B:"+B+",C:"+C);
		
	    swList = new ArrayList<Double>();
		List<Double> kro_krwList = new ArrayList<Double>();
		//第5步用
		 
      	List<Double> x1List = new ArrayList<Double>();
		List<Double> x2List = new ArrayList<Double>();
		List<Double> yList = new ArrayList<Double>();

		
		double sw = 0;
		double kro_krw_temp=0.0;
		
		for(double worTemp :worList) {
			//worTemp = (Double)worList.listIterator().next();
			sw =Math.log(worTemp/A)/B;
			//第四步求得
			kro_krw_temp = C/A*Math.exp(-B*sw);
			//计算y
			yList.add(Math.log10(kro_krw_temp));
			swList.add(sw);
			//计算x1
			x1List.add(Math.log10((1-Sor-sw)/(1-Swi-Sor)));
			x2List.add(Math.log10((sw-Swi)/(1-Swi-Sor))*(-1));
			kro_krwList.add(kro_krw_temp);
		}
		
		//进行第5步 二元线性回归
		double paraAlpha=0.0;
		double paraM =0.0;
		double paraN =0.0;
		
		for(int i =0;i<swList.size();i++) {

			System.out.println(i+"->swList"+swList.get(i));
		}
		for(int i =0;i<x1List.size();i++) {

			System.out.println(i+"->x1List"+x1List.get(i)+",x2List"+x2List.get(i)+",yList"+yList.get(i));
		}
	  	MultiLinearRegression mlr = new MultiLinearRegression(x1List,x2List,yList,0.001,1000000);  	
	  	mlr.trainTheta();
	  	paraAlpha = mlr.getTheta()[0]*(-1);
	  	paraM = mlr.getTheta()[1];
	  	paraN = mlr.getTheta()[2];
	  	mlr.printTheta();
	  	//
	  	//mlr.printTrainData();
	  	
	  //	mlr.printTheta();
	  	
	  	System.out.println("Alpha:"+paraAlpha+",M:"+paraM +",N:"+paraM);

	  	
	        // 利用步长补充点到边缘
	 		//sw的最小值为swi，最大值为1-sor
	 		double leftTemp = swList.get(0);
	 		double rightTemp = swList.get(swList.size()-1);
	 		//向左
	 		while((leftTemp-Step)>Swi) {
	 			leftTemp= leftTemp-Step;
	 			swList.add(leftTemp);
	 		}
	 		swList.add(Swi);
	  	
	 		//向右
	 		while((leftTemp-Step)>(1-Sor)) {
	 			leftTemp= leftTemp-Step;
	 			swList.add(leftTemp);
	 		}
	 		swList.add(1-Sor);
	 		
	 		//排序
	 		Collections.sort(swList,new Comparator<Double>(){
	          
				@Override
				public int compare(Double o1, Double o2) {
					// TODO Auto-generated method stub
					return o1.compareTo(o2);
				}
	         });

	 		for(int i =0;i<swList.size();i++) {

				System.out.println(i+"->sw2List"+swList.get(i));
			}	
		//第6步 求出kro和krw
	  	
		kroList = new ArrayList<Double>();
	    krwList = new ArrayList<Double>();
	  	double kro_swi=1.0;
	  	double krw_sor =(1/Math.pow(10,paraAlpha ));
	  	System.out.println("krw_sor:"+krw_sor );	 	  	
	  	
	  	for(double swTmp:swList) {
	  	kroList.add( kro_swi*Math.pow((1-Sor-swTmp)/(1-Sor-Swi), paraM));
	  	krwList.add(krw_sor*Math.pow((swTmp-Swi)/(1-Sor-Swi), paraN));
	  	}	
	  	System.out.println("kro:"+ kroList.toString()+"\nKrw"+krwList.toString());
	  		     		
		
	}
	
	
	public List<Double> getSwList() {
		return swList;
	}


	public void setSwList(List<Double> swList) {
		this.swList = swList;
	}


	public List<Double> getKroList() {
		return kroList;
	}


	public void setKroList(List<Double> kroList) {
		this.kroList = kroList;
	}


	public List<Double> getKrwList() {
		return krwList;
	}


	public void setKrwList(List<Double> krwList) {
		this.krwList = krwList;
	}

	// 本地测试  
	  public static void main(String[] args) {  
//		  File file = new File("data.xls");
//			ExcelRead instance = ExcelRead.getInstance();
//			List<ArrayList<String>> list =instance.readXls(file);
//			System.out.println(list.toString());
			ProcessImpl process = new ProcessImpl();
			process.calculate("");

	  }  
}
