package model;

import java.util.ArrayList;

public class WordLibrary {
	private ArrayList<SingleWord> wordlist;
	private int lastWordNumber;
	private String libraryName;
	private String libraryNameChinese;
	private int libraryIndex;
	
	public WordLibrary(ArrayList<SingleWord> wordlist, int lastWordNumber,
			String libraryName, int libraryIndex) {
		this.wordlist = wordlist;
		this.lastWordNumber = lastWordNumber;
		this.libraryName = libraryName;
		this.libraryIndex = libraryIndex;
		final String[] POS_CHINESE = BigLibrary.getPosChinese();
		this.libraryNameChinese = POS_CHINESE[libraryIndex];
	}

	public String getLibraryName() {
		return libraryName;
	}

	public String getLibraryNameChinese() {
		return libraryNameChinese;
	}

	public ArrayList<SingleWord> getWordlist() {
		return wordlist;
	}

	public int getLastWordNumber() {
		return lastWordNumber;
	}

	public int getLibraryIndex() {
		return libraryIndex;
	}

	public void setWordlist(ArrayList<SingleWord> wordlist) {
		this.wordlist = wordlist;
	}

	public void setLastWordNumber(int lastWordNumber) {
		this.lastWordNumber = lastWordNumber;
	}

	public void setLibraryName(String libraryName) {
		this.libraryName = libraryName;
	}

	public void setLibraryIndex(int libraryIndex) {
		this.libraryIndex = libraryIndex;
	}

	public int getLibraryLength() {
		return wordlist.size();
	}

	public int calcCorrectWordNumber() {
		int correctNum = 0;
		for (int i = 0; i < wordlist.size(); i++) {
			if ((wordlist.get(i).getStatus() & 0x3) == 0x3)
				correctNum++;
		}
		return correctNum;
	}

	public int calcWrongWordNumber() {

		int wrongNum = 0;
		for (int i = 0; i < wordlist.size(); i++) {
			if ((wordlist.get(i).getStatus() & 0x3) == 0x1)
				wrongNum++;
		}
		return wrongNum;
	}

	public double calcCorrectRate() {
		int correctNum = calcCorrectWordNumber();
		int wrongNum = calcWrongWordNumber();
		double correctRate;

		if ((correctNum + wrongNum) != 0)
			correctRate = (double) correctNum / (correctNum + wrongNum);
		else
			correctRate = 0;

		return correctRate;
	}

	public ArrayList<String> matchWordLibrary(String inputWord) {
		ArrayList<String> list = new ArrayList<String>();
		int length = inputWord.length();
		if (length > 0) {
			for (int i = 0; i < wordlist.size(); i++) {
				SingleWord word = wordlist.get(i);
				String str = word.getEnglish();
				if (str.indexOf(inputWord) == 0)
					list.add(str);
			}
		}
		return list;
	}

	public boolean update(String str, WordLibrary selectedLibrary, int currentWordNumber) {
		return wordlist.get(currentWordNumber).updateStatus(str, selectedLibrary);
	}
}