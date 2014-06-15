package model;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;

import javax.swing.JOptionPane;

public class BigLibrary {

	public static final int WORD_NUM = 7989;
	private static final int POS_NUM = 10;

	private int rememberWordNumber = 1;
	private int currentWordNumber = 0;
	public WordLibrary[] libraryList = new WordLibrary[POS_NUM];
	private WordLibrary selectedLibrary;
	private int currentReciteNumber = 0;
	private int currentCorrectNumber = 0;
	private int currentWrongNumber = 0;

	public int[] librarylist = new int[POS_NUM];

	private static final String[] POS_CHINESE = new String[] { "名词", "动词", "形容词", "副词", "代词", "数量词", "感叹词", "介词", "连词", "助动词" };
	private static final String[] POS_ABBR = new String[] { "n.", "v.", "adj.", "adv.", "pron.", "num.", "int.", "prep.", "conj.", "aux." };
	private ArrayList<String> pos = new ArrayList<String>();
	@SuppressWarnings("unchecked")
	private ArrayList<SingleWord>[] wordList = new ArrayList[POS_NUM];

	public BigLibrary() {
		for (int i = 0; i < POS_NUM; i++)
			pos.add(POS_ABBR[i]);
		/*
		 * 名词n. (nn.) 动词v. 形容词adj. 副词adv. (ad.) 代词pron. 数量词num. 感叹词int. 介词prep.
		 * 连词conj. 助动词aux.
		 */
		for (int i = 0; i < POS_NUM; i++)
			wordList[i] = new ArrayList<SingleWord>();
	}

	/* 初始化词库，将读入的单词根据词性存入不同的词库 */
	public void init(String fileName1, String fileName2) throws Exception {
		try {
			// 从xml文件读入单词
			InputOutputXml io = new InputOutputXml();
			SingleWord[] allWords = io.inputXml(fileName1, fileName2);
			int wordsNum = allWords.length;
			// 根据词性，将单词存入wordList
			for (int i = 0; i < wordsNum; i++) {
				SingleWord word = allWords[i];
				String wordMeaning = word.getChinese();
				HashSet<String> posSet = getPos(wordMeaning);
				for (String currentPos : posSet) {
					int libraryIndex = pos.indexOf(currentPos);
					wordList[libraryIndex].add(word);
				}
			}
			// 将wordList中的单词存入libraryList
			for (int i = 0; i < POS_NUM; i++) {
				int size = wordList[i].size();
				int lastIndex = -1;
				for (int j = 0; j < size; j++) {
					if (isLast(wordList[i].get(j).getStatus(), i)) {
						lastIndex = j;
						break;
					}
				}
				libraryList[i] = new WordLibrary(wordList[i], lastIndex, pos.get(i), i);
			}
		} catch (FileNotFoundException ex) {
			JOptionPane
					.showMessageDialog(null,
							"You should put the file dictionary.xml with this application.");
			System.exit(0);
		}
	}

	/* 根据中文解释，读取词性 */
	private HashSet<String> getPos(String wordMeaning) {
		HashSet<String> posSet = new HashSet<String>();
		int length = wordMeaning.length();
		boolean isProperty = false;
		String currentProperty = "";
		for (int i = 0; i < length; i++) {
			if (isAlphabet(wordMeaning.charAt(i)) && wordMeaning.charAt(i) != 'g' && wordMeaning.charAt(i) != 's'
					&& (i == 0 || i > 0 && !isUpperCaseAlphabet(wordMeaning.charAt(i - 1)))) {
				currentProperty += wordMeaning.charAt(i);
				isProperty = true;
			} else if (isProperty
					&& (wordMeaning.charAt(i) == '.' || wordMeaning.charAt(i) == ',')) {
				currentProperty += '.';
				if (currentProperty.equals("nn."))
					currentProperty = "n.";
				else if (currentProperty.equals("ad."))
					currentProperty = "adv.";
				posSet.add(currentProperty);
				isProperty = false;
				currentProperty = "";
			} else {
				isProperty = false;
				currentProperty = "";
			}
		}
		return posSet;
	}

	private boolean isUpperCaseAlphabet(char c) {
		return c >= 'A' && c <= 'Z';
	}

	private boolean isAlphabet(char c) {
		return c >= 'a' && c <= 'z';
	}

	private boolean isLast(int status, int libraryIndex) {
		byte currentBit = (byte) ((status >>> (11 - libraryIndex)) & 0x1);
		return currentBit == 1;
	}

	public int findWordIndex(String word) {
		System.out.println(word);
		int libraryIndex = selectedLibrary.getLibraryIndex();
		int size = selectedLibrary.getLibraryLength();
		for (int index = 0; index < size; index++) {
			if (libraryList[libraryIndex].getWordlist().get(index).getEnglish()
					.equals(word)) {
				System.out.println(index);
				return index;
			}
		}
		return -1;
	}

	public static String[] getPosChinese() {
		return POS_CHINESE;
	}

	public static String[] getPosAbbr() {
		return POS_ABBR;
	}

	public int getLibrary() {
		return selectedLibrary.getLibraryIndex();
	}

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
		int selectedLibraryLength = getSelectedLibrary().getLibraryLength();
		int lastWordNumber = (currentWordNumber - 1 + selectedLibraryLength) % selectedLibraryLength;
		System.out.println(currentWordNumber + " " + selectedLibraryLength + " " + lastWordNumber);
		selectedLibrary.setLastWordNumber(lastWordNumber);
	}

	public WordLibrary[] getLibraryList() {
		return libraryList;
	}

	public void setLibraryList(WordLibrary[] libraryList) {
		this.libraryList = libraryList;
	}

	public WordLibrary getSelectedLibrary() {
		return selectedLibrary;
	}

	public void setLibraryList(WordLibrary selectedLibrary) {
		this.selectedLibrary = selectedLibrary;
	}

	public int getCurrentReciteNumber() {
		return currentReciteNumber;
	}

	public void setCurrentReciteNumber(int currentReciteNumber) {
		this.currentReciteNumber = currentReciteNumber;
	}

	public int getCurrentCorrectNumber() {
		return currentCorrectNumber;
	}

	public void setCurrentCorrectNumber(int currentCorrectNumber) {
		this.currentCorrectNumber = currentCorrectNumber;
	}

	public int getCurrentWrongNumber() {
		return currentWrongNumber;
	}

	public void setCurrentWrongNumber(int currentWrongNumber) {
		this.currentWrongNumber = currentWrongNumber;
	}

	public int getSelectedLibraryLength() {
		return selectedLibrary.getLibraryLength();
	}

	public int calcCorrectWordNumber(int arg) {
		if (arg == 0) {
			int sum = 0;
			for (WordLibrary wl : libraryList)
				sum += wl.calcCorrectWordNumber();
			return sum;
		} else
			return selectedLibrary.calcCorrectWordNumber();

	}

	public int calcWrongWordNumber(int arg) {
		if (arg == 0) {
			int sum = 0;
			for (WordLibrary wl : libraryList)
				sum += wl.calcWrongWordNumber();
			return sum;
		} else
			return selectedLibrary.calcWrongWordNumber();

	}

	public double calcCorrectRate(int arg) {
		if (arg == 0) {
			double rate = (double) this.calcCorrectWordNumber(0)
					/ (this.calcCorrectWordNumber(0) + this
							.calcWrongWordNumber(0));
			return rate;
		} else
			return selectedLibrary.calcCorrectRate();
	}

	public void selectedLibrary(String libraryname) {
		for (int i = 0; i < libraryList.length; i++) {
			WordLibrary wl = libraryList[i];
			if (wl.getLibraryName().equals(libraryname)) {
				System.out.println(i + ": " + libraryname);
				System.out.println(libraryList[i].getLastWordNumber());
				selectedLibrary = libraryList[i];
				break;
			}
		}
		currentWordNumber = (selectedLibrary.getLastWordNumber() + 1) % selectedLibrary.getLibraryLength();
	}

	public String[] matchWordLibrary(String inputWord) {
		ArrayList<String> list = selectedLibrary.matchWordLibrary(inputWord);
		int returnSize = Math.min(list.size(), 10);
		String[] result = new String[returnSize];
		for (int i = 0; i < returnSize; i++)
			result[i] = list.get(i);
		return result;
	}

	public int getNumber(String word) {
		int size = selectedLibrary.getLibraryLength();
		for (int i = 0; i < size; i++) {
			if (selectedLibrary.getWordlist().get(i).getEnglish().equals(word)) {
				currentWordNumber = i;
				return i;
			}
		}
		return 0;
	}

	public int startNumber(String word) {
		int size = selectedLibrary.getLibraryLength();
		for (int i = 0; i < size; i++) {
			if (selectedLibrary.getWordlist().get(i).getEnglish().equals(word)) {
				currentWordNumber = i;
				return i;
			}
		}
		currentWordNumber = -1;
		return -1;
	}

	public int checkWordNumber(int input) {
		if (input < 1)
			return 1;
		int lastWordNumber = selectedLibrary.getLastWordNumber();
		int selectedLibraryLength = selectedLibrary.getLibraryLength();
		currentWordNumber = (lastWordNumber + 1) % selectedLibraryLength;
		
		int size = selectedLibrary.getLibraryLength();
		if (currentWordNumber + input <= size) {
			rememberWordNumber = input;
			return input;
		} else {
			rememberWordNumber = size - currentWordNumber;
			return size - currentWordNumber;
		}
	}

	public boolean rememberWord(String str) {
		boolean rememberResult = selectedLibrary.update(str, selectedLibrary, currentWordNumber);
		if (rememberResult)
			currentCorrectNumber++;
		else
			currentWrongNumber++;
		return rememberResult;
	}
}