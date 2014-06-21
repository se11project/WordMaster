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
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import controller.Controller;

import model.InputOutputXml;
import model.SingleWord;
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
	private String s1 = "";
	private String s2 = "";// 默认单词是什么
	private String s3 = "";// 本词库第一个单词是什么
	private DefaultComboBoxModel model;
	private JComboBox cmb;
	private JLabel jSelectedWord = new JLabel();
	private String cmbSelected = "";
	static JTextField editor = null;

	private Controller controller;

	public SelectStartView(Controller controller) {
		this.controller = controller;

		final int CURRENT_NUMBER = SelectStartView.this.controller.getCurrentWordNumber();

		SelectStartView.this.controller.setCurrentWordNumber(0);// 默认选择第一个单词，起始序号为0

		frame = new JFrame("背单词");
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
		p2.setLayout(new GridLayout(2, 5));
		p3.setLayout(new GridLayout(1, 3));

		Label l1 = new Label("本词库第一个单词");
		Label l2 = new Label("上次结束单词");
		Label l3 = new Label("输入开始单词");
		Label l4 = new Label("输入单词数量");
		text1 = new JTextField();
		text2 = new JTextField("1");// 输入背诵单词数量

		j1 = new JRadioButton(s1);
		j2 = new JRadioButton(s2);
		j3 = new JRadioButton(s3);
		ButtonGroup bg = new ButtonGroup();
		bg.add(j1);
		bg.add(j2);
		bg.add(j3);

		// 从第一个单词开始
		j1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SelectStartView.this.controller.setCurrentWordNumber(0);
			}
		});

		// 从上次结束单词开始
		j2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SelectStartView.this.controller.setCurrentWordNumber(CURRENT_NUMBER);
			}
		});

		// 手动输入起始单词
		j3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					cmbSelected = cmb.getSelectedItem().toString();
				} catch (NullPointerException ex) {
					WordLibrary libraryList = SelectStartView.this.controller.getSelectedLibrary();
					cmbSelected = libraryList.getWordlist().get(0).getEnglish();
				}
				jSelectedWord.setText(cmbSelected);
				int startNumber = SelectStartView.this.controller
						.startNumber(cmbSelected);
				SelectStartView.this.controller.setCurrentWordNumber(startNumber);
				frame.validate();
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

		text1.addKeyListener(new KeyListener() {
			@Override
			public void keyReleased(KeyEvent e) {
				j3.setSelected(true);
				String str = text1.getText();
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
				if (j3.isSelected()) {// 如果手动输入起始单词，更新当前单词序号
					String selectedWord = "";
					try {
						cmbSelected = cmb.getSelectedItem().toString();
					} catch (NullPointerException ex) {
						WordLibrary libraryList = SelectStartView.this.controller.getSelectedLibrary();
						cmbSelected = libraryList.getWordlist().get(0)
								.getEnglish();
					}
					jSelectedWord.setText(cmbSelected);
					int startNumber = SelectStartView.this.controller
							.startNumber(cmbSelected);
					SelectStartView.this.controller.getProcess()
							.setCurrentWordNumber(startNumber);
					frame.validate();

					String str = text1.getText();
					cmb.removeAllItems();

					String[] matchedWords = SelectStartView.this.controller
							.matchInputString(str);
					for (int i = 0; i < matchedWords.length; i++) {
						cmb.addItem(matchedWords[i]);
					}
					cmb.setSelectedItem(selectedWord);
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

		text2.addKeyListener(new KeyListener() {
			String currentText = text2.getText();
			@Override
			public void keyReleased(KeyEvent e) {
				int keyCode = e.getKeyCode();
				if (keyCode >= KeyEvent.VK_0 && keyCode <= KeyEvent.VK_9
						|| keyCode == KeyEvent.VK_BACK_SPACE
						|| keyCode == KeyEvent.VK_DELETE
						|| keyCode == KeyEvent.VK_LEFT
						|| keyCode == KeyEvent.VK_RIGHT) {
					if (text2.getText().equals("")
							|| Integer.parseInt(text2.getText()) == 0)
						text2.setText("1");
					int input = Integer.parseInt(text2.getText());
					int max = SelectStartView.this.controller
							.checkInputNumber(input);
					if (max < input)
						text2.setText(String.valueOf(max));
				} else
					text2.setText(currentText);
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
				cmbSelected = e.getItem().toString();
				SelectStartView.this.controller.startNumber(cmbSelected);
			}

		});

		p2.add(cmb);
		p2.add(jSelectedWord);
		p2.add(j3);

		p2.add(l4);
		p2.add(text2);

		b1 = new JButton("确认");
		b3 = new JButton("返回主界面");
		b4 = new JButton("退出");

		b1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				WordLibrary selectedLibrary = SelectStartView.this.controller.getSelectedLibrary();
				int libraryIndex = selectedLibrary.getLibraryIndex();
				ArrayList<SingleWord> wordlist = selectedLibrary.getWordlist();
				for (SingleWord word : wordlist) {
					int status = word.getStatus();
					status &= ~(1 << (11 - libraryIndex));
					word.setStatus(status);
				}
				selectedLibrary.setWordlist(wordlist);
				InputOutputXml.outputXml(wordlist, "statistics.xml");

				int input = Integer.parseInt(text2.getText());
				int max = SelectStartView.this.controller
						.checkInputNumber(input);
				if (max < input)
					text2.setText(String.valueOf(max));
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
		JLabel l5 = new JLabel(x);
		p2.add(l5);
		JLabel l6 = new JLabel(x);
		p2.add(l6);

		frame.validate();
	}
}
