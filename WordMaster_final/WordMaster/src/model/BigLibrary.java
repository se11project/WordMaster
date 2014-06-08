package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class BigLibrary {

	public static final int WORD_NUM = 7989;

	private int rememberWordNumber = 1;
	private static int currentWordNumber;
	public static WordLibrary[] libraryList = new WordLibrary[26];
	private static WordLibrary selectedLibrary;
	private int currentReciteNumber = 0;
	private int currentCorrectNumber = 0;
	private int currentWrongNumber = 0;

	public static ArrayList<SingleWord>[] wordList = new ArrayList[26];
	public static int[] librarylist = new int[26];

	public static void fillWordList() throws IOException {
		wordList = Output.get_word_list();
	}

	public static void fillLibraryList() throws IOException {
		librarylist = Output.get_array("libraries.txt", 26);
		currentWordNumber = librarylist[selectedLibrary.getLibraryName()
				.charAt(0) - 'A'];
	}

	public static int findWordIndex(String word, int size) {
		int libraryIndex = word.charAt(0) - 'a';
		int index = 0;
		for (; index < size; index++) {
			if (libraryList[libraryIndex].getWordlist().get(index).getContent()
					.equals(word))
				break;
		}
		System.out.println(index + " fuzhiyue1");
		return index;
	}

	public int getLibrary() {
		return selectedLibrary.getLibraryName().toCharArray()[0] - 'A';
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
	}

	public WordLibrary[] getLibraryList() {
		return libraryList;
	}

	public void setLibraryList(WordLibrary[] libraryList) {
		this.libraryList = libraryList;
	}

	public static WordLibrary getSelectedLibrary() {
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

	public int calcCorrectWordNumber(int arg) {

		if (arg == 0) {
			int sum = 0;
			for (WordLibrary wl : libraryList) {
				sum += wl.calcCorrectWordNumber();
			}
			return sum;
		} else
			return selectedLibrary.calcCorrectWordNumber();

	}

	public int calcWrongWordNumber(int arg) {
		if (arg == 0) {
			int sum = 0;
			for (WordLibrary wl : libraryList) {
				sum += wl.calcWrongWordNumber();
			}
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
			if (wl.getLibraryName().compareTo(libraryname) == 0) {
				selectedLibrary = libraryList[i];
				break;
			}
		}
	}

	public String[] matchWordLibrary(String inputWord) {
		ArrayList<String> list = selectedLibrary.matchWordLibrary(inputWord);

		if (list.size() >= 10) {
			String[] result = new String[10];
			for (int i = 0; i < 10; i++)
				result[i] = list.get(i);
			return result;
		} else {
			String[] result = new String[list.size()];
			for (int i = 0; i < list.size(); i++)
				result[i] = list.get(i);
			return result;
		}
	}

	public int getNumber(String word) {
		int size = selectedLibrary.getLibraryLength();
		for (int i = 0; i < size; i++) {
			if (selectedLibrary.getWordlist().get(i).getContent().equals(word)) {
				currentWordNumber = i;
				return i;
			}
		}
		return 0;
	}

	public void startNumber(String word) {
		int size = selectedLibrary.getLibraryLength();
		for (int i = 0; i < size; i++) {
			if (selectedLibrary.getWordlist().get(i).getContent().equals(word)) {
				currentWordNumber = i;
				return;
			}
		}
		currentWordNumber = 0;
	}

	public int checkWordNumber(int input) {
		if (input < 1)
			return 1;
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

		if (selectedLibrary.update(str, currentWordNumber)) {
			currentCorrectNumber++;
		} else {
			currentWrongNumber++;
		}
		return selectedLibrary.update(str, currentWordNumber);

	}

	public void fillWordLibrary() throws IOException {
		File statisticsFile = new File("statistics.txt");
		if (!statisticsFile.exists()) {
			statisticsFile.createNewFile();
			PrintWriter statisticsWriter = new PrintWriter(statisticsFile);
			for (int i = 0; i < WORD_NUM; i++)
				statisticsWriter.println(0);
			statisticsWriter.close();
		}
		File librariesFile = new File("libraries.txt");
		if (!librariesFile.exists()) {
			librariesFile.createNewFile();
			PrintWriter librariesWriter = new PrintWriter(librariesFile);
			for (int i = 0; i < 26; i++)
				librariesWriter.println(-1);
			librariesWriter.close();
		}

		WordLibrary[] libraryList = new WordLibrary[26];

		Scanner librariesScanner = new Scanner(librariesFile);
		for (int i = 0; i < 26; i++) {
			try {
				int lastWordNumber = librariesScanner.nextInt();
				ArrayList<SingleWord> words = new ArrayList<SingleWord>();
				WordLibrary currentLibrary = new WordLibrary(words,
						lastWordNumber, String.valueOf((char) ('A' + i)));
				libraryList[i] = currentLibrary;
			} catch (NoSuchElementException e) {
				int lastWordNumber = -1;
				ArrayList<SingleWord> words = new ArrayList<SingleWord>();
				WordLibrary currentLibrary = new WordLibrary(words,
						lastWordNumber, String.valueOf((char) ('A' + i)));
				libraryList[i] = currentLibrary;
			}
		}
		librariesScanner.close();

		InputStream dictionaryIs = this.getClass().getResourceAsStream(
				"/lib/dictionary.txt");
		Scanner statusScanner = new Scanner(statisticsFile);
		BufferedReader br = new BufferedReader(new InputStreamReader(
				dictionaryIs));
		String s = "", content = "", chinese = "";
		while ((s = br.readLine()) != null) {
			try {
				content = s.substring(0, s.indexOf(' '));
				chinese = s.substring(s.lastIndexOf(' ') + 1);
				int status = statusScanner.nextInt();
				int libraryNum = (int) (s.charAt(0) - 'a');
				SingleWord word = new SingleWord(content, chinese, status);
				ArrayList<SingleWord> wordList = libraryList[libraryNum]
						.getWordlist();
				wordList.add(word);
				libraryList[libraryNum].setWordlist(wordList);
			} catch (NoSuchElementException e) {
				int status = 0;
				int libraryNum = (int) (s.charAt(0) - 'a');
				SingleWord word = new SingleWord(content, chinese, status);
				ArrayList<SingleWord> wordList = libraryList[libraryNum]
						.getWordlist();
				wordList.add(word);
				libraryList[libraryNum].setWordlist(wordList);
			}
		}
		setLibraryList(libraryList);
	}

	public void next() {
		currentWordNumber++;
	}
}
