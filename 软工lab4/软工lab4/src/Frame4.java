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
	static JPanel p4 ;//��ʾ����ͼ��
	
	static String s1;//�ʿ�
	static String s2;//ѡ�������
	static String s3;
	static String s4;
	static String s5;

	public Frame4() {
		frame = new JFrame("ͳ����Ϣ");
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
		Label l1=new Label("�ʿ���");
		Label l2=new Label("ѡ������");
		Label l3=new Label("��ȷ����");
		Label l4=new Label("��������");
		Label l5=new Label("��ȷ��");
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
		g1=new JButton("���");
		g2=new JButton("��ͼ");
		g3=new JButton("��״ͼ");
		
		g1.addActionListener(new ActionListener());
		g2.addActionListener(new ActionListener());
		g3.addActionListener(new ActionListener());
		
		p3.add(g1);
		p3.add(g2);
		p3.add(g3);
		
		showg1();
		
		b3 = new JButton("����������");
		b4 = new JButton("�˳�");

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
		Label l1=new Label("�ʿ���");
		Label l2=new Label("ѡ������");
		Label l3=new Label("��ȷ����");
		Label l4=new Label("��������");
		Label l5=new Label("��ȷ��");
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
