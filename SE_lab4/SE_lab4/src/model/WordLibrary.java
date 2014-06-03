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
        int correctNum=0;
        for(int i=0;i<=wordlist.size();i++){
        	if(wordlist.get(i).getStatus()==3)
        		correctNum+=1;
        }
		return correctNum;
	}

	public int calcWrongWordNumber() {
		int wrongNum=0;
        for(int i=0;i<=wordlist.size();i++){
        	if(wordlist.get(i).getStatus()==1)
        		wrongNum+=1;
        }
		return wrongNum;
	}

	public double calcCorrectRate() {
		double CorrectRate=0;
		int correctNum=0;
		int wrongNum=0;
        for(int i=0;i<=wordlist.size();i++){
        	if(wordlist.get(i).getStatus()==3)
        		correctNum+=1;
        }
        for(int i=0;i<=wordlist.size();i++){
        	if(wordlist.get(i).getStatus()==1)
        		wrongNum+=1;
        }
        CorrectRate=correctNum/(correctNum+wrongNum);
		return CorrectRate;
	}

}
