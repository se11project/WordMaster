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

public class Frame2 extends JApplet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3778943415667325983L;
	static JFrame frame;
	static JButton b1;

	static JButton b3;
	static JButton b4;
	static JRadioButton j1, j2, j3;
	static TextField text1, text2;
	static String n;// ����ĵ�������
	String s1 = "";
	String s2 = "";// Ĭ�ϵ�����ʲô
	String s3 = "";// ���ʿ��һ��������ʲô
	static DefaultComboBoxModel<String> model;
	static JTextField editor = null;

	public Frame2() {
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
		text1 = new TextField();
		text2 = new TextField();// ���뿪ʼ����

		j1 = new JRadioButton(s1);
		j2 = new JRadioButton(s2);
		j3 = new JRadioButton(s3);
		ButtonGroup bg = new ButtonGroup();
		bg.add(j1);
		bg.add(j2);
		bg.add(j3);

		p1.add(l1);
		p1.add(j1);
		p1.add(l2);
		p1.add(j2);

		p2.add(l3);
		model = new DefaultComboBoxModel<String>();
		JComboBox cmb = new JComboBox(model);
		editor   =   (JTextField)   cmb.getEditor().getEditorComponent(); 
        editor.addKeyListener(new keyListener());
        if(!Project.type.equals("")){
        	model.addElement(Project.type.toLowerCase());
        }
		
		cmb.setEditable(true);
		p2.add(cmb);
		p2.add(j3);

		p2.add(l4);
		p2.add(text2);

		b1 = new JButton("ȷ��");
		b3 = new JButton("����������");
		b4 = new JButton("�˳�");
		b1.addActionListener(new ActionListener());

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
