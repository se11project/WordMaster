package gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

import controller.ActionListener;
import controller.Controller;

public class Frame1 extends JApplet {
	private static final long serialVersionUID = -546617228147743489L;
	public static JFrame frame;
	public static JButton b1;
	public static JButton b2;
	public static JButton b3;
	public static JButton b4;

	public Frame1(Controller controller, JComboBox dbtype) {
		frame = new JFrame("背单词");
		frame.setSize(500, 500);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setLayout(new BorderLayout());

		JPanel p = new JPanel();
		frame.add(p, BorderLayout.CENTER);
		p.setLayout(new GridLayout(2, 2));

		b1 = new JButton("查看统计信息");
		b2 = new JButton("背单词");
		b3 = new JButton("返回主界面");
		b4 = new JButton("退出");
		b1.addActionListener(new ActionListener(controller, dbtype));
		b2.addActionListener(new ActionListener(controller, dbtype));
		b3.addActionListener(new ActionListener(controller, dbtype));
		b4.addActionListener(new ActionListener(controller, dbtype));
		p.add(b1);
		p.add(b2);
		p.add(b3);
		p.add(b4);
		p.validate();
		frame.validate();
	}
}