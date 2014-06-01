package model;

import java.util.ArrayList;

public class WordLibrary {

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
	
	
}
