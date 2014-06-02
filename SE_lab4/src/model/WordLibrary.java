package model;

import java.util.ArrayList;

import controller.CompactStat;

public class WordLibrary implements CompactStat{

	private ArrayList<SingleWord> wordlist;
	private int LastWordNumber;
	private String LibraryName;
	
	public String getLibraryName() {
		return LibraryName;
	}
	
	public SingleWord getLastWordNumber()
	{
		return wordlist.get(LastWordNumber);
	}

	public int calcCorrectWordNumber() {

		return 0;
	}

	public int calcWrongWordNumber() {

		return 0;
	}

	public double calcCorrectRate() {

		return 0;
	}

}
