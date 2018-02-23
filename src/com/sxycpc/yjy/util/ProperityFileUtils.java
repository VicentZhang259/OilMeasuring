/**
 * 
 */
package com.sxycpc.yjy.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author zhangpengfei2
 *
 */
public class ProperityFileUtils {
	
	/** 
	   * 加载属性文件 
	11.     * @param filePath 文件路径 
	12.     * @return 
	13.     */  
	private static Properties properties;  
	    public static Properties loadProps(){  
	    	  //String filePath = "\\project.properties";
	    	//String filePath = "E:\\Work\\Project\\Oil\\OilMeasuring\\bin\\project.properties";	    	
	    	properties = new Properties();  
	    	  try {
	    		       //File file = new File (filePath);
	    	          InputStream in = ProperityFileUtils.class.getClassLoader().getResourceAsStream("project.properties");  
	    	          //InputStream in =new BufferedInputStream(new FileInputStream(file));      
	    	          properties.load(in);    
	    	   } catch (Exception e) {  
	    	        e.printStackTrace();  
	    	  }  
	    	   return properties;  

	   }  
	     
	   /** 
	    * 读取配置文件 
	     * @param props 配置文件 
	     * @param key  
	    * @return 
    */  
	   public static String getString(Properties properties,String key){  
	        return properties.getProperty(key);  
	   }  


	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Properties p = loadProps();
		System.out.println(getString(p,"application.hello"));
	}

}
