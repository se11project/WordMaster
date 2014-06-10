package view;

import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

public class TimerPanel extends JPanel {
	private String timerStr, minuteStr, secondStr;
	private int time;// 时间（秒）
	private JLabel jlblTime = new JLabel();
	private Timer timer = new Timer(1000, new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			time++;
			int minute = time / 60;
			int second = time % 60;
			minuteStr = minute < 10 ? "0" + minute : String.valueOf(minute);
			secondStr = second < 10 ? "0" + second : String.valueOf(second);
			timerStr = minuteStr + ":" + secondStr;
			jlblTime.setText(timerStr);
			validate();
		}
	});
	
	public TimerPanel() {
		super(true);
	}
	
	public TimerPanel(LayoutManager layout) {
		super(layout);
		this.setBorder(new TitledBorder("背单词用时"));
		jlblTime.setText("00:00");
		this.add(jlblTime);
		time = 0;
	}
	
	public int getTime() {
		return time;
	}
	
	public void start() {
		timer.start();
	}
	
	public void stop() {
		timer.stop();
	}
}