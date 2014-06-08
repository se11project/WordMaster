package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import controller.Controller;

import model.BigLibrary;
import model.WordLibrary;

public class SelectStartView {

	public JFrame frame;
	private JButton b1;

	private JButton b3;
	private JButton b4;
	private JRadioButton j1;
	private JRadioButton j2;
	private JRadioButton j3;
	private JTextField text1;
	private JTextField text2;
	private String n;// ����ĵ�������
	private String s1 = "";
	private String s2 = "";// Ĭ�ϵ�����ʲô
	private String s3 = "";// ���ʿ��һ��������ʲô
	private DefaultComboBoxModel model;
	private JComboBox cmb;
	static JTextField editor = null;

	public static int startNumber;
	private int manuNumber;
	private BigLibrary rp;
	private Controller controller;

	public SelectStartView(Controller controller) {
		this.controller = controller;
		this.rp = controller.getProcess();

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

		j1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				WordLibrary libraryList = rp.getSelectedLibrary();
				String selectedWord = libraryList.getWordlist().get(0)
						.getContent();
				SelectStartView.this.controller.startNumber(selectedWord);
			}

		});

		j2.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				SelectStartView.this.controller.getProcess()
						.setCurrentWordNumber(
								(rp.getCurrentWordNumber() + 1)
										% rp.getSelectedLibrary()
												.getLibraryLength());
			}
		});

		j3.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				WordLibrary libraryList = rp.getSelectedLibrary();
				String selectedWord = libraryList.getWordlist().get(0)
						.getContent();
				SelectStartView.this.controller.startNumber(selectedWord);
			}

		});

		j1.setSelected(true);

		p1.add(l1);
		p1.add(j1);
		p1.add(l2);
		p1.add(j2);

		p2.add(l3);
		p2.add(text1);
		model = new DefaultComboBoxModel();
		cmb = new JComboBox(model);

		text1.addMouseListener(new MouseListener() {

			public void mouseReleased(MouseEvent e) {
				j3.setSelected(true);
				String str = text1.getText();
				System.out.println(str);// �������ĸ����̨����
				cmb.removeAllItems();

				String[] matchedWords = SelectStartView.this.controller
						.matchInputString(str);
				for (int i = 0; i < matchedWords.length; i++) {
					model.addElement(matchedWords[i]);
					cmb.addItem(matchedWords[i]);
				}

			}

			@Override
			public void mouseClicked(MouseEvent e) {

			}

			@Override
			public void mousePressed(MouseEvent e) {

			}

			@Override
			public void mouseEntered(MouseEvent e) {

			}

			@Override
			public void mouseExited(MouseEvent e) {

			}

		});

		text1.addKeyListener(new KeyListener() {

			@Override
			public void keyReleased(KeyEvent e) {

				j3.setSelected(true);
				String str = text1.getText();
				System.out.println(str);// �������ĸ����̨����
				model.removeAllElements();
				String[] matchedWords = SelectStartView.this.controller
						.matchInputString(str);
				for (int i = 0; i < matchedWords.length; i++)
					model.addElement(matchedWords[i]);

			}

			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyPressed(KeyEvent e) {
			}

		});

		text2.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {

				String selectedWord = "unselected";
				try {
					selectedWord = cmb.getSelectedItem().toString();
				} catch (NullPointerException ex) {
					WordLibrary libraryList = rp.getSelectedLibrary();
					selectedWord = libraryList.getWordlist().get(0)
							.getContent();
				}
				System.out.println(selectedWord + " 345");

				String str = text1.getText();
				System.out.println(str);
				cmb.removeAllItems();

				String[] matchedWords = SelectStartView.this.controller
						.matchInputString(str);
				for (int i = 0; i < matchedWords.length; i++) {
					cmb.addItem(matchedWords[i]);
				}
				cmb.setSelectedItem(selectedWord);
			}

			@Override
			public void mouseClicked(MouseEvent e) {

			}

			@Override
			public void mousePressed(MouseEvent e) {

			}

			@Override
			public void mouseEntered(MouseEvent e) {

			}

			@Override
			public void mouseExited(MouseEvent e) {

			}

		});

		text2.addKeyListener(new KeyListener() {

			public void keyReleased(KeyEvent e) {

				int keyChar = e.getKeyChar();
				if (keyChar >= KeyEvent.VK_0 && keyChar <= KeyEvent.VK_9
						|| e.getKeyChar() == KeyEvent.VK_BACK_SPACE
						|| e.getKeyChar() == KeyEvent.VK_DELETE
						|| e.getKeyChar() == KeyEvent.VK_LEFT
						|| e.getKeyChar() == KeyEvent.VK_RIGHT) {
					if (text2.getText().equals(""))
						text2.setText("1");
					int input = Integer.parseInt(text2.getText());
					int max = SelectStartView.this.controller
							.checkInputNumber(input);
					text2.setText(String.valueOf(max));
				} else {
					e.consume();
				}
			}

			@Override
			public void keyTyped(KeyEvent e) {

			}

			@Override
			public void keyPressed(KeyEvent e) {

			}

		});

		cmb.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				String s = e.getItem().toString();
				SelectStartView.this.controller.startNumber(s);
			}

		});

		p2.add(cmb);
		p2.add(j3);

		p2.add(l4);
		p2.add(text2);

		b1 = new JButton("ȷ��");
		b3 = new JButton("����������");
		b4 = new JButton("�˳�");

		b1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				SelectStartView.this.controller.changeView(3);
				frame.dispose();
			}

		});

		b3.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				SelectStartView.this.controller.changeView(0);
				frame.dispose();
			}
		});

		b4.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				System.exit(0);
			}
		});

		p3.add(b1);
		p3.add(b3);
		p3.add(b4);
		String x = "";
		Label l5 = new Label(x);
		p2.add(l5);

		frame.validate();
	}
}
