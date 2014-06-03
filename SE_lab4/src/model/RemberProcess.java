package model;

import java.util.ArrayList;

import controller.CompactStat;
import controller.UIinputAndoutput;

public class RemberProcess implements CompactStat, UIinputAndoutput{

	private int rememberWordNumber;
	private int currentWordNumber;
	private ArrayList<WordLibrary> libraryList;
	private WordLibrary selectedLibrary;
	
	@Override
	public int calcCorrectWordNumber() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public int calcWrongWordNumber() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public double calcCorrectRate() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public void selectedLibrary(String libraryname) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public String[] matchWordLibrary(String inputWord) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public boolean checkWordNumber(String inputNumber) {
		// TODO Auto-generated method stub
		return false;
	}
	
}
