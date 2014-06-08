package controller;

import gui.Frame2;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JComboBox;

import model.WordLibrary;

import controller.Controller;

public class MouseClickListener implements MouseListener {
	private Controller controller;
	private JComboBox dbtype;
	static String selectedWord;
	
	public MouseClickListener() {	
	}
	
	public MouseClickListener(Controller controller, JComboBox dbtype) {
		this.controller = controller;
		this.dbtype = dbtype;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (e.getSource() == Frame2.text1) {
			Frame2.j3.setSelected(true);
			String str = Frame2.text1.getText();
			System.out.println(str);// 输入的字母，后台处理
			Frame2.cmb.removeAllItems();
//			Frame2.model.removeAllElements();
			String[] matchedWords = controller.matchInputString(str);
			for (int i = 0; i < matchedWords.length; i++) {
//				Frame2.model.addElement(matchedWords[i]);
				Frame2.cmb.addItem(matchedWords[i]);
			}
		}
		if (e.getSource() == Frame2.text2) {
			selectedWord = "unselected";
			try {
				selectedWord = Frame2.cmb.getSelectedItem().toString();
			} catch (NullPointerException ex) {
				WordLibrary libraryList = controller.getProcess().getSelectedLibrary();
				selectedWord = libraryList.getWordlist().get(0).getContent();
			}
			System.out.println(selectedWord);
			Frame2.cmb.setSelectedItem(selectedWord);
			Frame2.manuNumber = controller.getNumber(selectedWord);
//			controller.startNumber(selectedWord);
		}
		int max = controller.checkInputNumber(Integer.parseInt(Frame2.text2.getText()));
		Frame2.text2.setText(String.valueOf(max));
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}
}