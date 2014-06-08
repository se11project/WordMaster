package controller;

import gui.BarCharts;
import gui.Frame1;
import gui.Frame2;
import gui.Frame3;
import gui.Frame4;
import gui.PieCharts;
import gui.PieCharts2;
import gui.Project;

import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

import model.WordLibrary;

import org.jfree.ui.RefineryUtilities;

import controller.Controller;

public class ActionListener implements java.awt.event.ActionListener {
	private static Controller controller;
	private JComboBox dbtype;
	private int startNumber;
	private int reciteNumber;
	
	public ActionListener() {
		
	}
	
	public ActionListener(Controller controller, JComboBox dbtype) {
		this.controller = controller;
		this.dbtype = dbtype;
	}
	
	public ActionListener(Controller controller, JComboBox dbtype, int startNumber, int reciteNumber) {
		this.controller = controller;
		this.dbtype = dbtype;
		this.startNumber = startNumber;
		this.reciteNumber = controller.checkInputNumber(reciteNumber);
		System.out.println("actionlistener:"+this.reciteNumber);
		
		if (startNumber == 0) {
			WordLibrary libraryList = controller.getProcess().getSelectedLibrary();
			String selectedWord = libraryList.getWordlist().get(0).getContent();
			controller.startNumber(selectedWord);
		} else if (startNumber == 1) {
			controller.getProcess().setCurrentWordNumber(controller.getProcess().getCurrentWordNumber() + 1);
		} else if (startNumber == 2) {
			WordLibrary libraryList = controller.getProcess().getSelectedLibrary();
			String selectedWord = libraryList.getWordlist().get(0).getContent();
			controller.startNumber(selectedWord);
			controller.startNumber(MouseClickListener.selectedWord);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == Project.confirm) {
			String type = dbtype.getSelectedItem().toString();
			Project.frame.setVisible(false);
			controller.selectedLibrary(type);
			Frame1 f1 = new Frame1(controller, dbtype);
		}
		if (e.getSource() == Project.exitb || e.getSource() == Frame1.b4
				|| e.getSource() == Frame2.b4 || e.getSource() == Frame3.b4
				|| e.getSource() == Frame3.f2b4 || e.getSource() == Frame4.b4) {
			System.exit(0);
		}

		if (e.getSource() == Frame1.b1) {
			//
			Frame1.frame.setVisible(false);
			Frame4 f4 = new Frame4();
		}
		if (e.getSource() == Frame3.f2b1) {
			// 查看统计信息
			Frame3.frame2.setVisible(false);
			Frame4 f4 = new Frame4();
		}

		if (e.getSource() == Frame1.b2) {
			Frame1.frame.setVisible(false);
			Frame2 f2 = new Frame2(controller, dbtype);
		}
		if (e.getSource() == Frame1.b3) {
			Project.frame.setVisible(true);
			Frame1.frame.setVisible(false);
		}
		if (e.getSource() == Frame4.b3) {
			Project.frame.setVisible(true);
			Frame4.frame.setVisible(false);
		}
		if (e.getSource() == Frame2.j1) {
			Frame2.startNumber = 0;
		}
		if (e.getSource() == Frame2.j2) {
			Frame2.startNumber = 1;
		}
		if (e.getSource() == Frame2.j3) {
			Frame2.startNumber = 2;
		}
		if (e.getSource() == Frame2.b1) {
			// 还需要进行判断
			Frame2.frame.setVisible(false);
			Frame2.n = Frame2.text2.getText();
			Frame3 f3 = new Frame3(controller.getProcess(), dbtype, Integer.parseInt(Frame2.n));/* set controller as controller.getProcess*/
			System.out.println("c5:"+reciteNumber);
		}
		if (e.getSource() == Frame2.b3) {
			Project.frame.setVisible(true);
			Frame2.frame.setVisible(false);
		}
		if (e.getSource() == Frame3.b1) {
			System.out.println(Frame3.test==null);
			System.out.println(Frame3.test.getText());
			if(controller.rememberWord(Frame3.test.getText()))
			{
			Frame3.l4.setText("恭喜你！你回答正确了");	
			Frame3.num3++;
			}
			else{
			Frame3.l4.setText("你答错了！再试试看");
			Frame3.num4++;
			}
		}
		if (e.getSource() == Frame3.b2) {
			// 已背完情况
			if (Frame3.num1 == Frame3.num2) {
				Frame3.frame.setVisible(false);
				Frame3.haveFinished();
			}
			//还没有背完的情况
			else{
				controller.next();
				//Frame3.updateContent();
			}

		}
		if (e.getSource() == Frame3.b3) {
			Project.frame.setVisible(true);
			Frame3.frame.setVisible(false);
		}

		if (e.getSource() == Frame3.f2b3) {
			Project.frame.setVisible(true);
			Frame3.frame2.setVisible(false);
		}

		if (e.getSource() == Frame4.g1) {
			Frame4.showg1();
		}
		if (e.getSource() == Frame4.g3) {
			// 需要真实数据
			double[][] x = new double[3][27];
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 27; j++) {
					x[i][j] = Math.random();
				}
			}
			BarCharts fjc = new BarCharts("柱状图", x);
			fjc.pack();
			RefineryUtilities.centerFrameOnScreen(fjc);
			fjc.setVisible(true);
		}
		if (e.getSource() == Frame4.g2) {
			// 需要真实数据
			double[] x = new double[2];
			x[0] = Math.random();
			x[1] = 1 - x[0];
			PieCharts fjc = new PieCharts("本词库正确错误比例", x);

			fjc.pack();
			RefineryUtilities.centerFrameOnScreen(fjc);
			fjc.setVisible(true);

			PieCharts2 fjc2 = new PieCharts2("全部词库已背/总数比例", x);
			fjc2.pack();
			RefineryUtilities.centerFrameOnScreen(fjc2);
			fjc2.setVisible(true);

			PieCharts2 fjc3 = new PieCharts2("本词库词库已背/总数比例", x);

			fjc3.pack();
			RefineryUtilities.centerFrameOnScreen(fjc3);
			fjc3.setVisible(true);
		}
	}
}