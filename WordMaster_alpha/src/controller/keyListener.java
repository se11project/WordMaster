package controller;

import gui.Frame2;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;

import model.WordLibrary;

import controller.Controller;

public class keyListener implements KeyListener {
	private Controller controller;
	private JComboBox dbtype;
	
	public keyListener() {	
	}
	
	public keyListener(Controller controller, JComboBox dbtype) {
		this.controller = controller;
		this.dbtype = dbtype;
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getSource() == Frame2.text1) {
			Frame2.j3.setSelected(true);
			String str = Frame2.text1.getText();
			System.out.println(str);// 输入的字母，后台处理
			Frame2.model.removeAllElements();
			String[] matchedWords = controller.matchInputString(str);
			for (int i = 0; i < matchedWords.length; i++)
				Frame2.model.addElement(matchedWords[i]);
		} else if (e.getSource() == Frame2.text2) {
			int keyChar = e.getKeyChar();
			if (keyChar >= KeyEvent.VK_0 && keyChar <= KeyEvent.VK_9 || e.getKeyChar() == KeyEvent.VK_BACK_SPACE || e.getKeyChar() == KeyEvent.VK_DELETE 
				      || e.getKeyChar() == KeyEvent.VK_LEFT || e.getKeyChar() == KeyEvent.VK_RIGHT) {
				if (Frame2.text2.getText().equals(""))
					Frame2.text2.setText("1");
				int input = Integer.parseInt(Frame2.text2.getText());
				int max = controller.checkInputNumber(input);
				Frame2.text2.setText(String.valueOf(max));
			} else {
				e.consume();
			}
		}
	}
}