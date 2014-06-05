import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.jfree.ui.RefineryUtilities;

public class ActionListener implements java.awt.event.ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == Project.confirm) {
			Project.frame.setVisible(false);
			Frame1 f1 = new Frame1();
		}
		if (e.getSource() == Project.exitb || e.getSource() == Frame1.b4
				|| e.getSource() == Frame2.b4 || e.getSource() == Frame3.b4
				|| e.getSource() == Frame3.f2b4 || e.getSource() == Frame4.b4) {
			System.exit(0);
		}

		if (e.getSource() == Frame1.b1) {
			//
			Frame1.frame.setVisible(false);
			Frame4 f4=new Frame4();
		}
		if (e.getSource() == Frame3.f2b1) {
			//查看统计信息
			Frame3.frame2.setVisible(false);
			Frame4 f4=new Frame4();
		}

		if (e.getSource() == Frame1.b2) {
			Frame1.frame.setVisible(false);
			Frame2 f2 = new Frame2();
		}
		if (e.getSource() == Frame1.b3) {
			Project.frame.setVisible(true);
			Frame1.frame.setVisible(false);
		}
		if (e.getSource() == Frame4.b3) {
			Project.frame.setVisible(true);
			Frame4.frame.setVisible(false);
		}
		if (e.getSource() == Frame2.b1) {
			// 还需要进行判断
			Frame2.frame.setVisible(false);
			Frame3 f3 = new Frame3();
			Frame2.n=Frame2.text2.getText();
		}
		if (e.getSource() == Frame2.b3) {
			Project.frame.setVisible(true);
			Frame2.frame.setVisible(false);
		}
		if (e.getSource() == Frame3.b1) {
			//
		}
		if (e.getSource() == Frame3.b2) {
			// 已背完情况
			if (Integer.parseInt(Frame3.s1) == Integer.parseInt(Frame3.s2)) {
				Frame3.frame.setVisible(false);
				Frame3.haveFinished();
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
			//需要真实数据
			double[][]x=new double[3][27];
			for(int i=0;i<3;i++){
				for(int j=0;j<27;j++){
					x[i][j]=Math.random();
				}
			}
			BarCharts fjc = new BarCharts("柱状图",x);
			fjc.pack();
			RefineryUtilities.centerFrameOnScreen(fjc);
			fjc.setVisible(true);
		}
		if (e.getSource() == Frame4.g2) {
			//需要真实数据
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
