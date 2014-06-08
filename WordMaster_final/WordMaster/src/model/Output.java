package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Output {

	static FileWriter fw;
	static FileReader fr;
	static BufferedReader buf1, buf2;

	public Output() throws IOException {
		InputStream is = this.getClass().getResourceAsStream("/lib/dictionary.txt");
		fr = new FileReader("statistics.txt");
		buf1 = new BufferedReader(new InputStreamReader(is));  
		buf2 = new BufferedReader(fr);
	}

	/**
	 * This is the test function which it is used to initialize the
	 * statistics.txt
	 * 
	 * @param : fw fill every line as 0
	 * */
	public static void fillZero(FileWriter fw) throws IOException {

		for (int i = 0; i < BigLibrary.WORD_NUM; i++) {

			fw.write("0\r\n");

		}

		fw.close();
	}

	/**
	 * @param : wordlist_1 : input a int[] array, such as the statistics.txt,
	 *        and libraries.txt this function is used to print the content to
	 *        the file
	 * */
	public void print_list(int[] wordlist_1, String path, int size)
			throws IOException {
		fw = new FileWriter(path);
		for (int i = 0; i < size; i++) {
			fw.write(wordlist_1[i] + "\r\n");

		}

		fw.close();
	}

	public void print_list(WordLibrary[] wordlist_1, String path)
			throws IOException {
		fw = new FileWriter(path);
		for (int i = 0; i < 26; i++) {
			WordLibrary wordLibrary = wordlist_1[i];
			ArrayList<SingleWord> words = wordLibrary.getWordlist();
			for (SingleWord word : words)
				fw.write(word.getStatus() + "\r\n");

		}

		fw.close();
	}

	/**
	 * @param path
	 *            : the input file
	 * @param size
	 *            : the input array size
	 * @return int[] array which is the txt's content
	 * */
	public static int[] get_array(String path, int size) throws IOException {
		int[] array = new int[size];
		int i = 0;
		String str = "";

		while ((str = buf1.readLine()) != null && i < size) {
			array[i] = Integer.parseInt(str);
			System.out.println(array[i]);
			i++;
		}

		return array;
	}

	/**
	 * @param path
	 *            : the input file path
	 * @param size
	 *            : the input file line number
	 * @return the wordlist(English and Chinese CONTENT)
	 * */
	public static ArrayList<SingleWord>[] get_word_list() throws IOException {
		ArrayList<SingleWord>[] words = new ArrayList[26];
		for (int i = 0; i < 26; i++) {
			words[i] = new ArrayList<SingleWord>();
		}
		buf2 = new BufferedReader(fr);
		String str = "";

		while ((str = buf1.readLine()) != null) {
			int status = Integer.parseInt(buf2.readLine());
			String word = str.substring(0, str.indexOf(' '));
			String meaning = str.substring(str.lastIndexOf(' '));
			char beginLetter = word.charAt(0);
			SingleWord singleWord = new SingleWord(word, meaning, status);
			words[beginLetter - 'a'].add(singleWord);
		}

		return words;
	}
}
