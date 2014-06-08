package gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Label;
import java.io.IOException;

import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.ActionListener;
import controller.Controller;
import controller.ItemListener;

public class Project extends JApplet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static JFrame frame;
	public static JButton exitb;
    public static JButton confirm;
    static JPanel panel;
    public static String type = "";
    public Controller controller;
    
    public Project(Controller controller)
    {
    	
    	this.controller = controller;
    	
    	frame = new JFrame("背单词");
		frame.setSize(500, 500);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

		panel = new JPanel();
		frame.setLayout(new BorderLayout());
		frame.add(panel, BorderLayout.CENTER);
		
		JLabel text=new JLabel("选择词库");
		panel.add(text);
		
		JComboBox<String> dbtype = new JComboBox<String>();
		dbtype.addItem("A");
		dbtype.addItem("B");
		dbtype.addItem("C");
		dbtype.addItem("D");
		dbtype.addItem("E");
		dbtype.addItem("F");
		dbtype.addItem("G");
		dbtype.addItem("H");
		dbtype.addItem("I");
		dbtype.addItem("J");
		dbtype.addItem("K");
		dbtype.addItem("L");
		dbtype.addItem("M");
		dbtype.addItem("N");
		dbtype.addItem("O");
		dbtype.addItem("P");
		dbtype.addItem("Q");
		dbtype.addItem("R");
		dbtype.addItem("S");
		dbtype.addItem("T");
		dbtype.addItem("U");
		dbtype.addItem("V");
		dbtype.addItem("W");
		dbtype.addItem("X");
		dbtype.addItem("Y");
		dbtype.addItem("Z");
		dbtype.setSelectedItem("A");
		dbtype.addItemListener(new ItemListener());
		panel.add(dbtype);
		
		confirm=new JButton("确认");
		
		confirm.addActionListener(new ActionListener(controller, dbtype));
		panel.add(confirm);
		
		exitb = new JButton("退出");
		exitb.addActionListener(new ActionListener());
		panel.add(exitb);
		panel.validate();
		frame.validate();
		frame.repaint();
    }
    
}