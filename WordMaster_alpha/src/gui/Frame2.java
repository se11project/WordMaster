package gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.TextField;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.MouseListener;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import model.RemberProcess;

import controller.*;

public class Frame2 extends JApplet {

	/**
*
*/
	private static final long serialVersionUID = 3778943415667325983L;
	public static JFrame frame;
	public static JButton b1;

	private RemberProcess rp;
	public static JButton b3;
	public static JButton b4;
	public static JRadioButton j1;
	public static JRadioButton j2;
	public static JRadioButton j3;
	public static JTextField text1;
	public static JTextField text2;
	public static String n;// ����ĵ�������
	String s1 = "";
	String s2 = "";// Ĭ�ϵ�����ʲô
	String s3 = "";// ���ʿ��һ��������ʲô
	public static DefaultComboBoxModel model;
	public static JComboBox cmb;
	static JTextField editor = null;

	public static int startNumber;
	public static int manuNumber;

	public Frame2(Controller controller, JComboBox dbtype) {
		frame = new JFrame("������");
		frame.setSize(500, 500);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setLayout(new BorderLayout());

		JPanel p1 = new JPanel();
		JPanel p2 = new JPanel();
		JPanel p3 = new JPanel();

		frame.add(p1, BorderLayout.NORTH);
		frame.add(p2, BorderLayout.CENTER);
		frame.add(p3, BorderLayout.SOUTH);

		p1.setLayout(new GridLayout(2, 2));
		p2.setLayout(new GridLayout(2, 3));
		p3.setLayout(new GridLayout(1, 3));

		Label l1 = new Label("���ʿ��һ������");
		Label l2 = new Label("�ϴν�������");
		Label l3 = new Label("���뿪ʼ����");
		Label l4 = new Label("���뵥������");
		text1 = new JTextField();
		text2 = new JTextField("1");// ���뿪ʼ����

		j1 = new JRadioButton(s1);
		j2 = new JRadioButton(s2);
		j3 = new JRadioButton(s3);
		ButtonGroup bg = new ButtonGroup();
		bg.add(j1);
		bg.add(j2);
		bg.add(j3);
		j1.setSelected(true);

		p1.add(l1);
		p1.add(j1);
		p1.add(l2);
		p1.add(j2);

		p2.add(l3);
		p2.add(text1);
		model = new DefaultComboBoxModel();
		cmb = new JComboBox(model);
//		editor = (JTextField) cmb.getEditor().getEditorComponent();
		text1.addMouseListener(new MouseClickListener(controller, dbtype));
		text1.addKeyListener(new keyListener(controller, dbtype));
		text2.addMouseListener(new MouseClickListener(controller, dbtype));
		text2.addKeyListener(new keyListener(controller, dbtype));
		cmb.addItemListener(new ItemListener());
//		editor.addMouseListener(new MouseClickListener(controller, dbtype));
//		editor.addKeyListener(new keyListener(controller, dbtype));

//		cmb.setEditable(true);
		p2.add(cmb);
		p2.add(j3);

		p2.add(l4);
		p2.add(text2);

		b1 = new JButton("ȷ��");
		b3 = new JButton("����������");
		b4 = new JButton("�˳�");
		b1.addActionListener(new ActionListener(controller, dbtype, startNumber, Integer.parseInt(text2.getText())));

		b3.addActionListener(new ActionListener());
		b4.addActionListener(new ActionListener());
		p3.add(b1);
		p3.add(b3);
		p3.add(b4);
		String x = "";// ��ʾ��Ϣ
		Label l5 = new Label(x);
		p2.add(l5);

		frame.validate();
	}
}