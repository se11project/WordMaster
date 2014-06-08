package view;

import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

public class PieCharts extends JFrame {
	private static final long serialVersionUID = 1L;
	private static double[] d;

	public PieCharts(String s, double[] d) {
		super(s);
		this.setD(d);
		setContentPane(createDemoPanel());
	}

	// ������ʾͼ������
	public static JPanel createDemoPanel() {
		JFreeChart jfreechart = createChart(createDataset());
		return new ChartPanel(jfreechart);
	}

	// ���ɱ�ͼ���ݼ�����
	public static PieDataset createDataset() {
		DefaultPieDataset defaultpiedataset = new DefaultPieDataset();
		defaultpiedataset.setValue("��ȷ����", d[0]);
		defaultpiedataset.setValue("�������", d[1]);

		return defaultpiedataset;
	}

	// ����ͼ��������JFreeChart
	public static JFreeChart createChart(PieDataset piedataset) {
		// ����ͼ�����
		JFreeChart jfreechart = ChartFactory.createPieChart("��ȷ�������",
				piedataset, true, true, false);
		// ���ͼ����ʾ����
		PiePlot pieplot = (PiePlot) jfreechart.getPlot();
		// ����ͼ���ǩ����
		pieplot.setLabelFont(new Font("SansSerif", Font.BOLD, 12));
		pieplot.setNoDataMessage("No data available");
		pieplot.setCircular(true);
		pieplot.setLabelGap(0.01D);// ���
		pieplot.setLabelGenerator(new StandardPieSectionLabelGenerator());
		return jfreechart;
	}

/*	public static void main(String[] args) {
		double[] x = new double[2];
		x[0] = Math.random();
		x[1] = 1 - x[0];
		PieCharts fjc = new PieCharts("���ʿ���ȷ�������", x);
		fjc.pack();
		RefineryUtilities.centerFrameOnScreen(fjc);
		fjc.setVisible(true);
	}
*/

	public void setD(double[] d) {
		PieCharts.d = d;
	}

	public static double[] getD() {
		return d;
	}
}
