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

	// ������ʾͼ������
	public static JPanel createDemoBar() {
		JFreeChart jfreechart = createChart(createDataset());
		return new ChartPanel(jfreechart);
	}

	// ����ͼ��������JFreeChart
	@SuppressWarnings("deprecation")
	public static JFreeChart createChart(CategoryDataset dataset) {
		JFreeChart chart = ChartFactory.createBarChart3D("�ѱ���������ͳ��ͼ", // ����3D��״ͼ
				"�ʿ�",// ��������
				"������",// ��������
				dataset,// ���ݼ�
				PlotOrientation.VERTICAL,// ������ʾ
				true,// ��ʾÿ����ɫ���ӵ�����
				false, false);
		CategoryPlot plot = chart.getCategoryPlot();// ����ͼ�ĸ߼�����
		BarRenderer3D renderer = new BarRenderer3D();// 3D�����޸�
		renderer.setBaseOutlinePaint(Color.BLACK);// ���ñ߿���ɫΪblack
		renderer.setWallPaint(Color.gray); // ����wall����ɫΪgray
		renderer.setItemLabelGenerator(new StandardCategoryItemLabelGenerator());// ������������,API�о�Ȼû��StandardCategoryItemLabelGenerator�����
		// renderer.setItemLabelFont(new Font("����",Font.PLAIN,12));//����������������
		renderer.setItemLabelsVisible(true);// ��ItemLabel����
		plot.setRenderer(renderer);// ���޸ĺ������ֵ���浽ͼ��
		plot.setForegroundAlpha(0.6f);// ����͸����

		return chart;
	}

	// ��������
	public static CategoryDataset createDataset() {

		String[] rowKeys = { "��������", "�ѱ�����", "��ȷ����" };// �б�־
		String[] columnKeys = { "n.", "v.", "adj.", "adv.", "pron.", "num.", "int.", "prep.", "conj.", "aux." };// �б�־
		CategoryDataset linedataset = DatasetUtilities.createCategoryDataset(
				rowKeys, columnKeys, data); // �������ݼ�

		return linedataset;
	}
}
