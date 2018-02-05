/**
 * copy right 2018 zhangpengfei
 */
package com.sxycpc.yjy.util;

/**
 * @author andymacbook
 *
 */
public class LinearRegression {
	private static final int MAX_POINTS = 10;  
	  
    private double E;  
  
    /** 
     * Main program. 
     *  
     * @param args 
     *            the array of runtime arguments 
     */  
    /*				
				
				
年份	序号	累积产油（Np）	累积产油（Wp）	
1991	1	521.5	1181.3	
1992	2	534.5	1452.9	
1993	3	544.7	1688.4	
1994	4	552.7	1908.4	
1995	5	558.3	2111.6	
1996	6	562.4	2301.9	
1997	7	567.8	2565.7	
1998	8	567.8	2565.7	
1999	9	569.7	2654	
     * 
     */
    public static void main(String args[]) {  
        RegressionLine line = new RegressionLine(); 
        
        
        double x1 = 521.5;
        double y1 = Math.log10(1181.3);	
        double x2 = 	534.5;
        double y2 = Math.log10(1452.9);	
        double x3 = 	544.7;
        double y3 = Math.log10(1688.4);	
        double x4 = 552.7;
        double y4 = Math.log10(1908.4);	
        double x5 = 558.3;
        double y5 = Math.log10(2111.6);	
        double x6 = 562.4;
        double y6 = Math.log10(2301.9);	
        double x7 = 567.8;
        double y7 = Math.log10(2565.7);	
        double x8 = 567.8;
        double y8 = Math.log10(2565.7);	
        double x9 = 569.7;
        double y9 = Math.log10(2654);
        line.addDataPoint(new DataPoint(x1, y1));  
        line.addDataPoint(new DataPoint(x2, y2));  
        line.addDataPoint(new DataPoint(x3, y3));  
        line.addDataPoint(new DataPoint(x4, y4));  
        line.addDataPoint(new DataPoint(x5, y5));  
        line.addDataPoint(new DataPoint(x6, y6));  
        line.addDataPoint(new DataPoint(x7, y7));  
        line.addDataPoint(new DataPoint(x8, y8));  
        line.addDataPoint(new DataPoint(x9, y9));  
  
//        line.addDataPoint(new DataPoint(1, 136));  
//        line.addDataPoint(new DataPoint(2, 143));  
//        line.addDataPoint(new DataPoint(3, 132));  
//        line.addDataPoint(new DataPoint(4, 142));  
//        line.addDataPoint(new DataPoint(5, 147));  
  
        printSums(line);  
        printLine(line);  
    }  
  
    /** 
     * Print the computed sums. 
     *  
     * @param line 
     *            the regression line 
     */  
    private static void printSums(RegressionLine line) {  
        System.out.println("\n数据点个数 n = " + line.getDataPointCount());  
        System.out.println("\nSum x  = " + line.getSumX());  
        System.out.println("Sum y  = " + line.getSumY());  
        System.out.println("Sum xx = " + line.getSumXX());  
        System.out.println("Sum xy = " + line.getSumXY());  
        System.out.println("Sum yy = " + line.getSumYY());  
  
    }  
  
    /** 
     * Print the regression line function. 
     *  
     * @param line 
     *            the regression line 
     */  
    private static void printLine(RegressionLine line) {  
        System.out.println("\n回归线公式:  y = " + line.getA1() + "x + "  
                + line.getA0());  
        System.out.println("误差：     R^2 = " + line.getR());  
    }  
      
    //y = 2.1x + 133.7   2.1 * 6 + 133.7 = 12.6 + 133.7 = 146.3  
    //y = 2.1x + 133.7   2.1 * 7 + 133.7 = 14.7 + 133.7 = 148.4  
  
}
