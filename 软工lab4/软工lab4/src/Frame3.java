import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.TextField;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class Frame3 {
	static JFrame frame;
	static JFrame frame2;// 已背完
	static JButton b1;
	static JButton b2;
	static JButton b3;
	static JButton b4;
	static JRadioButton j1, j2, j3;
	static TextField text1, text2;

	static String s1 = "0", s2 = "0";// 已背/需要背的数量
	static String s3 = "chinese";// 显示的中文
	static String s4 = "提示信息";
	static TextField test = new TextField("请输入英文");

	static JButton f2b1;
	static JButton f2b3;
	static JButton f2b4;
	
	public Frame3() {
		frame = new JFrame("背单词");
		frame.setSize(500, 500);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setLayout(new BorderLayout());

		JPanel p1 = new JPanel();
		JPanel p21 = new JPanel();
		JPanel p22 = new JPanel();
		JPanel p3 = new JPanel();

		frame.add(p1, BorderLayout.NORTH);
		
		frame.add(p3, BorderLayout.SOUTH);

		p1.setLayout(new GridLayout(2, 1));
		p1.add(p21);
		p1.add(p22);

		p21.setLayout(new GridLayout(1, 2));
		Label l1 = new Label("开始背单词");
		Label l2 = new Label(s1 + "/" + s2);
		p21.add(l1);
		p21.add(l2);

		p22.setLayout(new GridLayout(3, 2));
		Label l3 = new Label(s3);
		Label l4 = new Label(s4);
		b1 = new JButton("确认");
		b1.addActionListener(new ActionListener());
		b2 = new JButton("下一个");
		b2.addActionListener(new ActionListener());

		p22.add(l3);
		p22.add(test);
		p22.add(b1);
		p22.add(b2);
		p22.add(l4);
		p3.setLayout(new GridLayout(1, 2));

		b3 = new JButton("返回主界面");
		b4 = new JButton("退出");

		b3.addActionListener(new ActionListener());
		b4.addActionListener(new ActionListener());
		p3.add(b3);
		p3.add(b4);

		frame.validate();
	}

	public static void haveFinished() {
		frame2 = new JFrame("背单词");
		frame2.setSize(500, 500);
		frame2.setLocationRelativeTo(null);
		frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame2.setVisible(true);
		frame2.setLayout(new BorderLayout());

		JPanel p1 = new JPanel();
		JPanel p2 = new JPanel();
		JPanel p3 = new JPanel();
		p1.setLayout(new GridLayout(1, 1));
		p2.setLayout(new GridLayout(1, 1));
		p3.setLayout(new GridLayout(1, 2));
		frame2.add(p1, BorderLayout.NORTH);
		frame2.add(p2, BorderLayout.CENTER);
		frame2.add(p3, BorderLayout.SOUTH);
		p1.add(new Label("已背完"));
		
		
		f2b1=new JButton("查看统计信息");
		f2b1.addActionListener(new ActionListener());
		
		p2.add(f2b1);
		
		f2b3 = new JButton("返回主界面");
		f2b4 = new JButton("退出");

		f2b3.addActionListener(new ActionListener());
		f2b4.addActionListener(new ActionListener());
		
		p3.add(f2b3);
		p3.add(f2b4);
		
		frame2.validate();
	}
}
