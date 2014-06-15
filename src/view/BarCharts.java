package view;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer3D;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.general.DatasetUtilities;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

public class BarCharts extends JFrame {

	/**
*
*/
	private static final long serialVersionUID = 1L;
	private static double[][] data;

	public BarCharts(String s, double[][] data) {
		super(s);
		this.data = data;
		setContentPane(createDemoBar());
	}

	// 生成显示图表的面板
	public static JPanel createDemoBar() {
		JFreeChart jfreechart = createChart(createDataset());
		return new ChartPanel(jfreechart);
	}

	// 生成图表主对象JFreeChart
	@SuppressWarnings("deprecation")
	public static JFreeChart createChart(CategoryDataset dataset) {
		JFreeChart chart = ChartFactory.createBarChart3D("已背单词数量统计图", // 建立3D柱状图
				"词库",// 横轴名称
				"单词数",// 纵轴名称
				dataset,// 数据集
				PlotOrientation.VERTICAL,// 纵向显示
				true,// 显示每个颜色柱子的柱名
				false, false);
		CategoryPlot plot = chart.getCategoryPlot();// 设置图的高级属性
		BarRenderer3D renderer = new BarRenderer3D();// 3D属性修改
		renderer.setBaseOutlinePaint(Color.BLACK);// 设置边框颜色为black
		renderer.setWallPaint(Color.gray); // 设置wall的颜色为gray
		renderer.setItemLabelGenerator(new StandardCategoryItemLabelGenerator());// 设置柱顶数据,API中居然没有StandardCategoryItemLabelGenerator这个类
		// renderer.setItemLabelFont(new Font("黑体",Font.PLAIN,12));//设置柱顶数据字体
		renderer.setItemLabelsVisible(true);// 打开ItemLabel开关
		plot.setRenderer(renderer);// 将修改后的属性值保存到图中
		plot.setForegroundAlpha(0.6f);// 柱的透明度

		return chart;
	}

	// 生成数据
	public static CategoryDataset createDataset() {

		String[] rowKeys = { "单词总数", "已背数量", "正确数量" };// 行标志
		String[] columnKeys = { "n.", "v.", "adj.", "adv.", "pron.", "num.", "int.", "prep.", "conj.", "aux." };// 列标志
		CategoryDataset linedataset = DatasetUtilities.createCategoryDataset(
				rowKeys, columnKeys, data); // 建立数据集

		return linedataset;
	}
}
