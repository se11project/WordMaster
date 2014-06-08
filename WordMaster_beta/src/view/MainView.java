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

import model.RemberProcess;

import controller.Controller;

public class MainView {

	private JFrame frame;
	private JButton exitb;
    private JButton confirm;
    private JPanel panel;
    private String type = "";
    private JComboBox<String> dbtype;
    
    private Controller controller;
    
    public MainView(Controller controller)
    {
        
    	this.controller = controller;
    	
    	frame = new JFrame("背单词");
		frame.setSize(500, 500);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

		panel = new JPanel();
		frame.setLayout(new BorderLayout());
		frame.add(panel, BorderLayout.CENTER);
		
		JLabel text=new JLabel("选择词库");
		panel.add(text);
		
		dbtype = new JComboBox<String>();
		dbtype.addItem("A");
		dbtype.addItem("B");
		dbtype.addItem("C");
		dbtype.addItem("D");
		dbtype.addItem("E");
		dbtype.addItem("F");
		dbtype.addItem("G");
		dbtype.addItem("H");
		dbtype.addItem("I");
		dbtype.addItem("J");
		dbtype.addItem("K");
		dbtype.addItem("L");
		dbtype.addItem("M");
		dbtype.addItem("N");
		dbtype.addItem("O");
		dbtype.addItem("P");
		dbtype.addItem("Q");
		dbtype.addItem("R");
		dbtype.addItem("S");
		dbtype.addItem("T");
		dbtype.addItem("U");
		dbtype.addItem("V");
		dbtype.addItem("W");
		dbtype.addItem("X");
		dbtype.addItem("Y");
		dbtype.addItem("Z");
		dbtype.setSelectedItem("A");
		dbtype.addItemListener(new ItemListener(){

			@Override
			public void itemStateChanged(ItemEvent e) {
				
				if (e.getStateChange() == ItemEvent.SELECTED) {
					String s = e.getItem().toString();
					MainView.this.controller.selectedLibrary(s);
				}
			}
			
		});
		
		panel.add(dbtype);
		
		confirm=new JButton("确认");
		
		confirm.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent e){
				String type = dbtype.getSelectedItem().toString();
				MainView.this.controller.selectedLibrary(type);
				MainView.this.controller.changeView(1);
				frame.dispose();
				
				try{
					RemberProcess.fillWordarray();
				
					RemberProcess.fillWordList();
					
					RemberProcess.fillLibraryList();

					}catch(Exception e1){
						e1.printStackTrace();
				}
			}
		});
		panel.add(confirm);
		
		exitb = new JButton("退出");
		
        exitb.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent e){
				System.exit(0);
			}
		});
		panel.add(exitb);
		
		panel.validate();
		frame.validate();
		frame.repaint();
    }
}
