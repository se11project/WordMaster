package model;

import java.util.ArrayList;

public class RemberProcess{

	private int rememberWordNumber;
	private int currentWordNumber;
	private ArrayList<WordLibrary> libraryList;
	private WordLibrary selectedLibrary;
	
	public int getRememberWordNumber() {
		return rememberWordNumber;
	}
	
	public void setRememberWordNumber(int rememberWordNumber) {
		this.rememberWordNumber = rememberWordNumber;
	}
	
	public int getCurrentWordNumber() {
		return currentWordNumber;
	}
	
	public void setCurrentWordNumber(int currentWordNumber) {
		this.currentWordNumber = currentWordNumber;
	}
	
	public int calcCorrectWordNumber(int arg) {

		if (arg == 0)
		{
			int sum = 0;
			for (WordLibrary wl:libraryList)
			{
				sum += wl.calcCorrectWordNumber();
			}
			return sum;
		}
		else
			return selectedLibrary.calcCorrectWordNumber();
		
	}
	
	public int calcWrongWordNumber(int arg) {
		// TODO Auto-generated method stub
		if (arg == 0)
		{
			int sum = 0;
			for (WordLibrary wl:libraryList)
			{
				sum += wl.calcWrongWordNumber();
			}
			return sum;
		}
		else
			return selectedLibrary.calcWrongWordNumber();
		
	}
	
	public double calcCorrectRate(int arg) {
		
		if (arg == 0)
		{
			double rate = this.calcCorrectRate(0) / (this.calcCorrectWordNumber(0) + this.calcWrongWordNumber(0));
			return rate;
		}
		else
		    return selectedLibrary.calcCorrectRate();
	}
	
	public void selectedLibrary(String libraryname) {
		for (int i=0; i<libraryList.size(); i++)
		{
			WordLibrary wl = libraryList.get(i);
			if (wl.getLibraryName().compareTo(libraryname) == 0)
			{
				selectedLibrary = libraryList.get(i);
				break;
			}
		}
	}
	
	public String[] matchWordLibrary(String inputWord) {
		// TODO Auto-generated method stub
		ArrayList<String> list = selectedLibrary.matchWordLibrary(inputWord);
		
		if (list.size() >= 10)
		{
			String[] result = new String[10];
			for (int i=0; i<10; i++)
				result[i] = list.get(i);
			return result;
		}
		else
		{
			String[] result = new String[list.size()];
			for (int i=0; i<list.size(); i++)
				result[i] = list.get(i);
			return result;
		}
	}
	
	public boolean checkWordNumber(int input) {
		// TODO Auto-generated method stub
		int size = selectedLibrary.getLibraryLength();
		if (currentWordNumber + input <= size)
			return true;
		else
		    return false;
	}

	public void rememberWord(String str) {

		selectedLibrary.update(str, currentWordNumber);
		currentWordNumber++;
	}
	
}
