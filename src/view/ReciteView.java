package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import model.Output;
import model.BigLibrary;
import controller.Controller;

public class ReciteView extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public JFrame frame;
	public JFrame frame2;// �ѱ���
	private JButton b1;
	private JButton b2;
	private JButton b3;
	private JButton b4;

	private Label l4;
	public int num1 = 1;// �ѱ�/��Ҫ��������
	public int num2 = 0;
	public int num3 = 0;// ��/����
	public int num4 = 0;
	private String s3 = "chinese";// ��ʾ������
	private String s4 = "��ʾ��Ϣ";
	private String s5 = "";
	private TextField test = new TextField("");

	private JButton f2b1;
	private JButton f2b3;
	private JButton f2b4;

	private BigLibrary rp;
	private Controller controller;
	private Label l2;
	private Label l3;

	private static boolean flag_b1 = false;
	private int currentNum = 0;
	private int current_lib = 0;

	public ReciteView(Controller controller) {
		this.controller = controller;
		this.rp = controller.getProcess();

		final TimerPanel p22 = new TimerPanel(new FlowLayout(FlowLayout.CENTER, 1, 1));
		p22.start();
		num1 = 1;
		num2 = rp.getRememberWordNumber();
		rp.setCurrentCorrectNumber(0);
		rp.setCurrentWrongNumber(0);

		int startNum = rp.getCurrentWordNumber();
		s3 = rp.getSelectedLibrary().getWordlist().get(startNum).getChinese();
		s5 = rp.getSelectedLibrary().getWordlist().get(startNum).getContent();

		frame = new JFrame("������");
		frame.setSize(500, 500);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setLayout(new BorderLayout());

		JPanel p1 = new JPanel();
		JPanel p21 = new JPanel();
		JPanel p23 = new JPanel();
		JPanel p3 = new JPanel();

		frame.add(p1, BorderLayout.NORTH);

		frame.add(p3, BorderLayout.SOUTH);

		p1.setLayout(new GridLayout(3, 1));
		p1.add(p21);
		p1.add(p22);
		p1.add(p23);

		p21.setLayout(new GridLayout(1, 2));
		Label l1 = new Label("��ʼ������");
		l2 = new Label(num1 + "/" + num2);
		p21.add(l1);
		p21.add(l2);

		p23.setLayout(new GridLayout(3, 2));
		l3 = new Label(s3);
		l4 = new Label(s4);
		b1 = new JButton("ȷ��");
		currentNum = startNum;
		current_lib = rp.getLibrary();

		System.out.println("str3 " + flag_b1);

		b1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (!ReciteView.flag_b1) {
					ReciteView.flag_b1 = true;
					if (ReciteView.this.controller.rememberWord(test.getText())) {
						l4.setText("��ϲ�㣡�ش���ȷ��");

						System.out.println("str2 " + flag_b1);

						BigLibrary.getSelectedLibrary().getWordlist()
								.get(currentNum).setStatus(3);
						BigLibrary.librarylist[current_lib] = (currentNum + 1)
								% BigLibrary.getSelectedLibrary()
										.getLibraryLength();

						try {
							(new Output()).print_list(BigLibrary.librarylist,
									"libraries.txt", 26);

							(new Output()).print_list(BigLibrary.libraryList,
									"statistics.txt");
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					}

					else {
						l4.setText("���ź��������ˡ�");

						if (!ReciteView.flag_b1) {
							ReciteView.flag_b1 = true;
							num4++;

							if (BigLibrary.getSelectedLibrary().getWordlist()
									.get(currentNum).getStatus() != 3)
								BigLibrary.getSelectedLibrary().getWordlist()
										.get(currentNum).setStatus(1);
							BigLibrary.librarylist[current_lib] = (currentNum + 1)
									% BigLibrary.getSelectedLibrary()
											.getLibraryLength();

							try {
								(new Output()).print_list(
										BigLibrary.librarylist,
										"libraries.txt", 26);

								(new Output()).print_list(
										BigLibrary.libraryList,
										"statistics.txt");
							} catch (Exception e1) {
								e1.printStackTrace();
							}
						}
					}
				}
			}
		});

		b2 = new JButton("��һ��");

		b2.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if (ReciteView.flag_b1) {
					if (num1 == num2) {
						updateContent();
						p22.stop();
						JOptionPane.showMessageDialog(null, "���ε��ʱ��н�����\n�����ʱ�ǣ�" + p22.getTime() / 60 + "��" + p22.getTime() % 60 + "�롣");
						frame.dispose();
						haveFinished();
					} else {
						ReciteView.this.controller.next();
						updateContent();
					}
					ReciteView.flag_b1 = false;
				}
			}

		});

		p23.add(l3);
		p23.add(test);
		p23.add(b1);
		p23.add(b2);
		
		p23.add(l4);

		p3.setLayout(new GridLayout(1, 2));

		b3 = new JButton("����������");
		b4 = new JButton("�˳�");

		b3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				ReciteView.this.controller.changeView(0);
				frame.dispose();
			}
		});

		b4.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				System.exit(0);
			}
		});

		p3.add(b3);
		p3.add(b4);

		frame.validate();
	}

	private void updateContent() {
		if (num1 < num2)
			num1++;
		int startNum = rp.getCurrentWordNumber();
		l2.setText(num1 + "/" + num2);
		s3 = rp.getSelectedLibrary().getWordlist().get(startNum).getChinese();
		l3.setText(s3);
		l4.setText("��ʾ��Ϣ");

		BigLibrary.librarylist[current_lib] = startNum;

		s5 = rp.getSelectedLibrary().getWordlist().get(startNum).getContent();
		currentNum = BigLibrary.findWordIndex(s5, BigLibrary.WORD_NUM);

		try {
			(new Output()).print_list(BigLibrary.librarylist, "libraries.txt",
					26);
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		try {
			(new Output()).print_list(BigLibrary.libraryList, "statistics.txt");
		} catch (IOException e) {
			e.printStackTrace();
		}

		test.setText("");
		frame.validate();
	}

	public void haveFinished() {
		frame2 = new JFrame("������");
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
		p1.add(new Label("�ѱ���"));

		f2b1 = new JButton("�鿴ͳ����Ϣ");
		f2b1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				ReciteView.this.controller.changeView(5);
				frame2.dispose();
			}

		});

		p2.add(f2b1);

		f2b3 = new JButton("����������");
		f2b3.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				ReciteView.this.controller.changeView(0);
				frame2.dispose();
			}
		});

		f2b4 = new JButton("�˳�");
		f2b4.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				System.exit(0);
			}
		});

		p3.add(f2b3);
		p3.add(f2b4);

		frame2.validate();
	}
}