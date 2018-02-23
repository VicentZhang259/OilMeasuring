/**
 * 
 */
package com.sxycpc.yjy.initPanel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import com.sxycpc.yjy.service.InitProcess;
import com.sxycpc.yjy.serviceImpl.ProcessImpl;

/**
 * @author andymacbook
 *
 */
public class InitShellPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static InitShellPanel initPanel = new InitShellPanel();
	
	private JButton fileOpenButton ;
	
	private JButton submitButton;
	
	private JButton cancelButton;
	
	private JTextField filePathText;
	
	private JFileChooser fileChooser = null;
	
	JPanel panelParent =null;
	JPanel panelChar = null;
	JFreeChart charLine =null;
	 XYSeriesCollection dataSet =null;
	 List<Double> kroList = null;
     List<Double> krwList = null;
     List<Double> swList = null;
	
	//ProcessImpl process = new ProcessImpl();
	ProcessImpl process = new ProcessImpl();
	
	
	/**
	 * 单例
	 * @return
	 */
	public static InitShellPanel getInstance() {
		initPanel.setVisible(true);
		return initPanel;
	}
	
	private InitShellPanel() {
		initPanel();
		initListener();
	}
	/**
	 * initial Panel
	 */
	void initPanel(){
		setLayout(new GridLayout(3,2));
		panelParent = new JPanel(new GridLayout(4,2));
		add(panelParent);
		JPanel panel1 = new JPanel();
		JPanel panel2 = new JPanel();
		JPanel panelResult = new JPanel();
		panelParent.add(panel1);
		panelParent.add(panel2);
		JLabel filePathLabel = new JLabel("测算数据的文件路径");
		panel1.add(filePathLabel);
		filePathText = new JTextField("请选择测算数据文件",30);
		panel1.add(filePathText);
		
		fileOpenButton = new JButton("打开文件目录");
		panel1.add(fileOpenButton);
		submitButton = new JButton("开始智能测算");
		cancelButton = new JButton("取消");
		panel2.add(submitButton);
		panel2.add(cancelButton);
		
		JLabel resultLabel = new JLabel("计算结果");
		JTextField resultText = new JTextField("未开始计算",40);
		panelResult.add(resultLabel);
		panelResult.add(resultText);
		
		
		
		panelParent.add(panelResult);

		//panelParent.setPreferredSize(new Dimension(800, 800));
		
		panelChar = new JPanel(new GridLayout(1,1));
		//panelChar.setPreferredSize(new Dimension(500, 800));
		
		
		add(panelChar);
		
		
	}
	
	/**
	 * initialize Listener
	 */
	void initListener(){
		/**
		 * open file button listener
		 */
		fileOpenButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int returnVal = getFile("xls");
				if(returnVal == 0) {
					filePathText.setText(fileChooser.getSelectedFile().getPath());
				}
				
			}
			
		});
		
		submitButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//start to process
				process.calculate();
				kroList =process.getKroList();
				krwList = process.getKrwList();
				swList = process.getSwList();
								
				//dataSet = CharLine.getInstance().getUserDataSet();
				  XYSeries xyseriesKro = new XYSeries("kro");
				  XYSeries xyseriesKrw = new XYSeries("krw");
				for(int i = 0;i<kroList.size();i++) {
					
					xyseriesKro.add(kroList.get(i).intValue(), swList.get(i).intValue()); 
				}
				for(int i = 0;i<krwList.size();i++) {
					xyseriesKrw.add(krwList.get(i).doubleValue(), swList.get(i).doubleValue()); 
				}
				dataSet = new XYSeriesCollection();
				dataSet.addSeries(xyseriesKro);
				dataSet.addSeries(xyseriesKrw);
				//CharLine.getInstance().setUserDataSet(dataSet);
				charLine = CharLine.getInstance().createChart(dataSet);
				ChartPanel cp = new ChartPanel(charLine);
				panelChar.add(cp,BorderLayout.CENTER);
				panelChar.validate();
				//cp.validate();
				//panelParent.validate();
				
			}
			
		});
		
		cancelButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
				
			}
			
		});
	}
	
	
	private int getFile(String fileType){
		int renturnValue = -1;
		FileNameExtensionFilter filter = null;
		fileChooser = new JFileChooser();
		fileChooser.setAcceptAllFileFilterUsed(false);
		if(fileType.equals("XLS")) {
			filter = new FileNameExtensionFilter(".xls",".xlsx");
		}
		fileChooser.addChoosableFileFilter(filter);
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		renturnValue = fileChooser.showOpenDialog(initPanel);
		return renturnValue;
	}
	
	
}
