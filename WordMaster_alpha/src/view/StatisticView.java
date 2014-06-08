package view;

import gui.BarCharts;
import gui.Frame2;
import gui.Frame3;
import gui.PieCharts;
import gui.PieCharts2;
import gui.Project;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import org.jfree.ui.RefineryUtilities;

import controller.ActionListener;
import controller.Controller;

import model.RemberProcess;

public class StatisticView {

	public JFrame frame;

	private JButton g1;
	private JButton g2;
	private JButton g3;
	private JButton b3;
	private JButton b4;
	private JRadioButton j1, j2, j3;
	private TextField text1, text2;
	private JPanel p4;// ��ʾ����ͼ��

	private String s1;// �ʿ�
	private String s2;// ѡ�������
	private String s3;
	private String s4;
	private String s5;

	private RemberProcess rp;
	private Controller controller;
	
	public StatisticView(Controller controller, int s)
	{
		this.controller = controller;
		this.rp = controller.getProcess();
		
		frame = new JFrame("ͳ����Ϣ");
		frame.setSize(500, 500);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setLayout(new BorderLayout());

		JPanel p1 = new JPanel();

		JPanel p2 = new JPanel();

		JPanel p3 = new JPanel();

		p4 = new JPanel();

		p4.setLayout(new GridLayout(5, 2));
		Label l1 = new Label("�ʿ���");
		Label l2 = new Label("ѡ������");
		Label l3 = new Label("��ȷ����");
		Label l4 = new Label("��������");
		Label l5 = new Label("��ȷ��");
		s1 = ""+rp.getSelectedLibrary().getLibraryName();
		s2 = ""+rp.getRememberWordNumber();
		s3 = ""+rp.getCurrentCorrectNumber();
		s4 = ""+rp.getCurrentWrongNumber();
		s5 = ""+((double)rp.getCurrentCorrectNumber()/(rp.getCurrentCorrectNumber()+rp.getCurrentWrongNumber()));
		Label x1 = new Label(s1);
		Label x2 = new Label(s2);
		Label x3 = new Label(s3);
		Label x4 = new Label(s4);
		Label x5 = new Label(s5);

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

		if (s == 1)
		{
			showg1();
		}
		
		p1.setLayout(new GridLayout(1, 2));
		p1.add(p3);
		p1.add(p4);

		p3.setLayout(new GridLayout(3, 1));
		g1 = new JButton("���");
		g2 = new JButton("��ͼ");
		g3 = new JButton("��״ͼ");

		g1.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				
				showg1();
			}

		});
		
		g2.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent e) {
				
				double[] x = new double[2];
				//
				x[0] = rp.calcCorrectRate(1);
				x[1] = 1 - x[0];
				PieCharts fjc = new PieCharts("���ʿ���ȷ�������", x);

				fjc.pack();
				RefineryUtilities.centerFrameOnScreen(fjc);
				fjc.setVisible(true);

				//
				x[0] = (rp.calcCorrectWordNumber(1) + rp.calcWrongWordNumber(1)) / (double)rp.getSelectedLibrary().getLibraryLength();
				x[1] = 1 - x[0];
				PieCharts2 fjc2 = new PieCharts2("���ʿ�ʿ��ѱ�/��������", x); 
				
				fjc2.pack();
				RefineryUtilities.centerFrameOnScreen(fjc2);
				fjc2.setVisible(true);
				
				x[0] = rp.calcCorrectWordNumber(0) + rp.calcWrongWordNumber(0);
				x[0] = x[0] / 7989;
				x[1] = 1 - x[0];
				PieCharts2 fjc3 = new PieCharts2("ȫ���ʿ��ѱ�/��������", x);

				fjc3.pack();
				RefineryUtilities.centerFrameOnScreen(fjc3);
				fjc3.setVisible(true);
			}
			
		});
		
		g3.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				
				double[][] x = new double[3][27];
				for (int j = 0; j < 26; j++) {
					x[0][j] = rp.getLibraryList()[j].getLibraryLength();
					x[1][j] = rp.getLibraryList()[j].calcCorrectWordNumber() + rp.getLibraryList()[j].calcWrongWordNumber();
					x[2][j] = rp.getLibraryList()[j].calcCorrectWordNumber();
				}
				BarCharts fjc = new BarCharts("��״ͼ", x);
				fjc.pack();
				RefineryUtilities.centerFrameOnScreen(fjc);
				fjc.setVisible(true);
			}
			
		});

		p3.add(g1);
		p3.add(g2);
		p3.add(g3);

		b3 = new JButton("����������");
		b4 = new JButton("�˳�");

		b3.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent e){
				
				StatisticView.this.controller.changeView(0);
			}
		});
		
		b4.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent e){
				
				System.exit(0);
			}
		});
		
		p2.add(b3);
		p2.add(b4);

		frame.add(p1, BorderLayout.CENTER);
		frame.add(p2, BorderLayout.SOUTH);
		frame.validate();
	}
	
	public void showg1() {
		p4.removeAll();

		p4.setLayout(new GridLayout(5, 2));
		Label l1 = new Label("�ʿ���");
		Label l2 = new Label("�ʿ�����");
		Label l3 = new Label("��ȷ����");
		Label l4 = new Label("��������");
		Label l5 = new Label("��ȷ��");
		s1 = rp.getSelectedLibrary().getLibraryName();
		s2 = ""+rp.getSelectedLibrary().getLibraryLength();
		s3 = ""+rp.getSelectedLibrary().calcCorrectWordNumber();
		s4 = ""+rp.getSelectedLibrary().calcWrongWordNumber();
		s5 = ""+rp.getSelectedLibrary().calcCorrectRate();
		Label x1 = new Label(s1);
		Label x2 = new Label(s2);
		Label x3 = new Label(s3);
		Label x4 = new Label(s4);
		Label x5 = new Label(s5);

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
		
		frame.validate();
	}
}
