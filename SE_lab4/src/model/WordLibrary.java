package model;

import java.util.ArrayList;

public class WordLibrary{

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
	
	public ArrayList<SingleWord> getWordlist() {
		return wordlist;
	}

	public void setWordlist(ArrayList<SingleWord> wordlist) {
		this.wordlist = wordlist;
	}

	public void setLastWordNumber(int lastWordNumber) {
		LastWordNumber = lastWordNumber;
	}

	public void setLibraryName(String libraryName) {
		LibraryName = libraryName;
	}

	public int getLibraryLength()
	{
		return wordlist.size();
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

	public ArrayList<String> matchWordLibrary(String inputWord)
	{
		
		ArrayList<String> list = new ArrayList<String>();
		for (int i=0; i<wordlist.size(); i++)
		{
			SingleWord word = wordlist.get(i);
			int length = inputWord.length();
			String str = word.getContent();
			
			if (str.substring(0, length-1).compareTo(str) == 0)
				list.add(str);
		}
		
		return list;
	}

	public void update(String str, int currentWordNumber) {
		wordlist.get(currentWordNumber).updateStatus(str);
	}
}
