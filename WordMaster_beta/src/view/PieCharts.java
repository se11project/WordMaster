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

	// 生成显示图表的面板
	public static JPanel createDemoPanel() {
		JFreeChart jfreechart = createChart(createDataset());
		return new ChartPanel(jfreechart);
	}

	// 生成饼图数据集对象
	public static PieDataset createDataset() {
		DefaultPieDataset defaultpiedataset = new DefaultPieDataset();
		defaultpiedataset.setValue("正确比例", d[0]);
		defaultpiedataset.setValue("错误比例", d[1]);

		return defaultpiedataset;
	}

	// 生成图表主对象JFreeChart
	public static JFreeChart createChart(PieDataset piedataset) {
		// 定义图表对象
		JFreeChart jfreechart = ChartFactory.createPieChart("正确错误比例",
				piedataset, true, true, false);
		// 获得图表显示对象
		PiePlot pieplot = (PiePlot) jfreechart.getPlot();
		// 设置图表标签字体
		pieplot.setLabelFont(new Font("SansSerif", Font.BOLD, 12));
		pieplot.setNoDataMessage("No data available");
		pieplot.setCircular(true);
		pieplot.setLabelGap(0.01D);// 间距
		pieplot.setLabelGenerator(new StandardPieSectionLabelGenerator());
		return jfreechart;
	}

/*	public static void main(String[] args) {
		double[] x = new double[2];
		x[0] = Math.random();
		x[1] = 1 - x[0];
		PieCharts fjc = new PieCharts("本词库正确错误比例", x);
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
