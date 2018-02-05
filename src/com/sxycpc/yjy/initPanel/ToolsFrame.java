/**
 * copy right 2018 zhangpengfei
 */
package com.sxycpc.yjy.initPanel;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;

/**
 * @author andymacbook
 *
 */
public class ToolsFrame extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private final int DEFAULT_WIDTH = 700;
	private final int DEFAULT_HIGH = 450;
	
	private static ToolsFrame  initFrame = new ToolsFrame();
	
	public static ToolsFrame getInstance() {
		return initFrame;
	}
	private ToolsFrame() {
		setTitle("延长石油油井智能测算平台");
		this.setVisible(true);
		setSize(DEFAULT_WIDTH,DEFAULT_HIGH);
		JTabbedPane tabbedPane = new JTabbedPane();
		tabbedPane.addTab("根据测算数据源文件测算测量结果", InitShellPanel.getInstance());
		add(tabbedPane,"Center");
	}
	
	public static void setDialog(String message) {
		JOptionPane.showMessageDialog(initFrame, message);
		
	}

}
