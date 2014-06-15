package controller;

import java.io.IOException;

import view.MainView;
import view.ReciteView;
import view.SelectStartView;
import view.StartView;
import view.StatisticView;

import model.BigLibrary;
import model.WordLibrary;

public class Controller {
	private BigLibrary rp = new BigLibrary(); // 模型：大的词库。储存所有词库的信息和

	public Controller() {
		try {
			fillWordLibrary();
		} catch (Exception e) {
			e.printStackTrace();
		}

		new MainView(this);
	}

	public BigLibrary getProcess() {
		return rp;
	}

	public int findWordIndex(String word) {
		return rp.findWordIndex(word);
	}

	public int getLibrary() {
		return rp.getLibrary();
	}

	public void selectedLibrary(String firstName) {
		rp.selectedLibrary(firstName);
	}

	public WordLibrary getSelectedLibrary() {
		return rp.getSelectedLibrary();
	}

	public int getRememberWordNumber() {
		return rp.getRememberWordNumber();
	}

	public int getCurrentWordNumber() {
		return rp.getCurrentWordNumber();
	}

	public void setCurrentWordNumber(int currentWordNumber) {
		rp.setCurrentWordNumber(currentWordNumber);
	}

	public void setCurrentCorrectNumber(int currentWordNumber) {
		rp.setCurrentCorrectNumber(currentWordNumber);
	}

	public void setCurrentWrongNumber(int currentWordNumber) {
		rp.setCurrentWrongNumber(currentWordNumber);
	}

	public int calcCorrectWordNumber(int arg) {
		return rp.calcCorrectWordNumber(arg);
	}

	public int calcWrongWordNumber(int arg) {
		return rp.calcWrongWordNumber(arg);
	}

	public double calcCorrectRate(int arg) {
		return rp.calcCorrectRate(arg);
	}

	public int getNumber(String word) {
		return rp.getNumber(word);
	}

	public int startNumber(String word) {
		return rp.startNumber(word);
	}

	public int checkInputNumber(int input) {
		return rp.checkWordNumber(input);
	}

	public String[] matchInputString(String str) {
		return rp.matchWordLibrary(str);
	}

	public boolean rememberWord(String str) {
		return rp.rememberWord(str);
	}

	public void fillWordLibrary() throws Exception {
		rp.init("dictionary.xml", "statistics.xml");
	}

	public void changeView(int flag) {
		switch (flag) {
		case 0:
			new MainView(this);
			break;
		case 1:
			new StartView(this);
			break;
		case 2:
			new SelectStartView(this);
			break;
		case 3:
			new ReciteView(this);
			break;
		case 4:
			new StatisticView(this, 1);
			break;
		case 5:
			new StatisticView(this, 0);
			break;
		}
	}

	public static void main(String[] args) throws IOException {
		new Controller();
	}
}