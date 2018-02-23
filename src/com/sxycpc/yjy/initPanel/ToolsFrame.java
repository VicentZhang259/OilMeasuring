/**
 * copy right 2018 zhangpengfei
 */
package com.sxycpc.yjy.initPanel;

import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JRootPane;
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
	
	private final int DEFAULT_WIDTH = 1000;
	private final int DEFAULT_HIGH = 800;
	
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
		this.setIconImage(Toolkit.getDefaultToolkit().createImage("log.jpg"));
		this.getRootPane().setWindowDecorationStyle(JRootPane.NONE); 
		add(tabbedPane,"Center");
		this.addWindowListener(new WindowAdapter(){
			@Override
			 public void windowClosing(WindowEvent e)
	            {
	                // 释放资源，退出程序
	                  System.exit(0);
	                   dispose();
	                setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	            }
		});
		
	}
	
	
	
	public static void setDialog(String message) {
		initFrame.setIconImage(Toolkit.getDefaultToolkit().createImage("log.jpg"));
		initFrame.setUndecorated(true);

        //采用指定的窗口装饰风格

		initFrame.getRootPane().setWindowDecorationStyle(JRootPane.NONE); 
		JOptionPane.showMessageDialog(initFrame, message);
		
	}
	
	/*ToolsFrame.addWindowListener(new WindowAdapter()
    {
            public void WindowClosing(WindowEvent e)
            {
                // 释放资源，退出程序
                  System.exit(0);
                   dispose();
                //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            }

    }); */
}
