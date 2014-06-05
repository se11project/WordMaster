import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.TextField;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;


public class Frame4 {
	
	static JFrame frame;
	
	static JButton g1;
	static JButton g2;
	static JButton g3;
	static JButton b3;
	static JButton b4;
	static JRadioButton j1, j2, j3;
	static TextField text1, text2;
	static JPanel p4 ;//显示三种图表
	
	static String s1;//词库
	static String s2;//选择的数量
	static String s3;
	static String s4;
	static String s5;

	public Frame4() {
		frame = new JFrame("统计信息");
		frame.setSize(500, 500);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setLayout(new BorderLayout());

		JPanel p1 = new JPanel();
		
		JPanel p2 = new JPanel();
		
		
		JPanel p3 = new JPanel();
		
		p4=new JPanel();
		
		p4.setLayout(new GridLayout(5, 2));
		Label l1=new Label("词库名");
		Label l2=new Label("选择数量");
		Label l3=new Label("正确数量");
		Label l4=new Label("错误数量");
		Label l5=new Label("正确率");
		s1=Project.type;
		s2=Frame2.n;
		s3="";
		s4="";
		s5="";
		Label x1=new Label(s1);
		Label x2=new Label(s2);
		Label x3=new Label(s3);
		Label x4=new Label(s4);
		Label x5=new Label(s5);
		
		p4.add(l1);
		p4.add(x1);
		p4.add(l2);
		p4.add(x2);
		p4.add(l3);
		p4.add(x3);
		p4.add(l4);
		p4.add(x4);
		p4.add(l5);
		p4.add(x5);
		
		
		p1.setLayout(new GridLayout(1, 2));
		p1.add(p3);
		p1.add(p4);
		
		p3.setLayout(new GridLayout(3, 1));
		g1=new JButton("表格");
		g2=new JButton("饼图");
		g3=new JButton("柱状图");
		
		g1.addActionListener(new ActionListener());
		g2.addActionListener(new ActionListener());
		g3.addActionListener(new ActionListener());
		
		p3.add(g1);
		p3.add(g2);
		p3.add(g3);
		
		showg1();
		
		b3 = new JButton("返回主界面");
		b4 = new JButton("退出");

		b3.addActionListener(new ActionListener());
		b4.addActionListener(new ActionListener());
		p2.add(b3);
		p2.add(b4);
		

		frame.add(p1, BorderLayout.CENTER);
		frame.add(p2, BorderLayout.SOUTH);
		frame.validate();
	}
	
	
	static void showg1(){
		p4.removeAll();
		
		p4.setLayout(new GridLayout(5, 2));
		Label l1=new Label("词库名");
		Label l2=new Label("选择数量");
		Label l3=new Label("正确数量");
		Label l4=new Label("错误数量");
		Label l5=new Label("正确率");
		s1=Project.type;
		s2=Frame2.n;
		s3="";
		s4="";
		s5="";
		Label x1=new Label(s1);
		Label x2=new Label(s2);
		Label x3=new Label(s3);
		Label x4=new Label(s4);
		Label x5=new Label(s5);
		
		p4.add(l1);
		p4.add(x1);
		p4.add(l2);
		p4.add(x2);
		p4.add(l3);
		p4.add(x3);
		p4.add(l4);
		p4.add(x4);
		p4.add(l5);
		p4.add(x5);
		System.out.println("1");
		frame.validate();
	}
	
}
