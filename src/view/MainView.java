package view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;

import model.BigLibrary;

import controller.Controller;

public class MainView {

	private JFrame frame;
	private JButton exitb;
	private JButton confirm;
	private JPanel panel;
	private JComboBox dbtype = new JComboBox();

	private Controller controller;

	public MainView(Controller controller) {
		final String[] POS_CHINESE = BigLibrary.getPosChinese();
		final String[] POS_ABBR = BigLibrary.getPosAbbr();

		this.controller = controller;

		frame = new JFrame("������");
		frame.setSize(500, 500);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

		panel = new JPanel();
		frame.setLayout(new BorderLayout());
		frame.add(panel, BorderLayout.CENTER);

		JLabel text = new JLabel("ѡ��ʿ�");
		panel.add(text);

		// ������Ϊ���ݴ��Ի��ֵĴʿ⣬�˴�����ʾ���ģ�Ĭ��ѡ��Ϊ���ʴʿ�
		for (String pos : POS_CHINESE)
			dbtype.addItem(pos);
		dbtype.setSelectedItem("����");
		MainView.this.controller.selectedLibrary("n.");
		dbtype.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					String chinese = e.getItem().toString();
					int index = getIndex(chinese, POS_CHINESE);
					String abbr = POS_ABBR[index];
					MainView.this.controller.selectedLibrary(abbr);
				}
			}

			private int getIndex(String chinese, String[] POS_CHINESE) {
				int length = POS_CHINESE.length;
				for (int i = 0; i < length; i++)
					if (chinese.equals(POS_CHINESE[i]))
						return i;
				return -1;
			}
		});

		panel.add(dbtype);

		confirm = new JButton("ȷ��");

		confirm.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				String type = dbtype.getSelectedItem().toString();
				MainView.this.controller.selectedLibrary(type);
				MainView.this.controller.changeView(1);
				frame.dispose();
			}
		});
		panel.add(confirm);

		exitb = new JButton("�˳�");

		exitb.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		panel.add(exitb);

		panel.validate();
		frame.validate();
		frame.repaint();
	}
}