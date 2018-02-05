/**
 * copy right 2018 zhangpengfei
 */
package com.sxycpc.yjy.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author andymacbook
 *
 */
public class FileUtils {
	
	
	private  FileUtils() {
		
	}

	public static FileUtils getInstances() {
		return new FileUtils();
	}
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		File file = new File("data.xls");
		ExcelRead instance = ExcelRead.getInstance();
		List<ArrayList<String>> list =instance.readXls(file);
		System.out.println(list.toString());

	}

}
