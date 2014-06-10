package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import model.BigLibrary;

import controller.Controller;

public class StartView {

	public JFrame frame;
	private JButton statisticButton;
	private JButton reciteButton;
	private JButton backToMainButton;
	private JButton exitButton;

	private Controller controller;
	private BigLibrary remberProcess;

	public StartView(Controller controller) {

		this.controller = controller;
		this.remberProcess = controller.getProcess();

		frame = new JFrame("背单词");
		frame.setSize(500, 500);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setLayout(new BorderLayout());

		JPanel p = new JPanel();
		frame.add(p, BorderLayout.CENTER);
		p.setLayout(new GridLayout(2, 2));

		statisticButton = new JButton("查看统计信息");
		reciteButton = new JButton("背单词");
		backToMainButton = new JButton("返回主界面");
		exitButton = new JButton("退出");

		statisticButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				StartView.this.controller.changeView(4);
				frame.dispose();
			}

		});

		reciteButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				StartView.this.controller.changeView(2);
				frame.dispose();
			}

		});

		backToMainButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				StartView.this.controller.changeView(0);
				frame.dispose();
			}

		});

		exitButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				System.exit(0);

			}

		});

		p.add(statisticButton);
		p.add(reciteButton);
		p.add(backToMainButton);
		p.add(exitButton);
		p.validate();
		frame.validate();
	}

}
