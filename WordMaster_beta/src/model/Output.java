package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Output {

	static FileWriter fw;
	static FileReader fr ;
	static BufferedReader buf;
	
	public Output() throws IOException{
		fr	=	new FileReader("dictionary.txt");
		buf =	new BufferedReader(fr);
	}
	
	/**
	 *  This is the test function which it is used to initialize the statistics.txt
	 *  @param : fw
	 *  fill every line as 0
	 * */
	public static void fillZero(FileWriter fw) throws IOException{
		  
		for (int i = 0; i < RemberProcess.WORD_NUM; i++) {   
			   	
			   fw.write("0\r\n");   

           }   

        fw.close(); 
	}
	
	/**
	 * @param : wordlist_1 : input a int[] array, such as the statistics.txt, and libraries.txt 
	 * this function is used to print the content to the file
	 * */
	public void print_list(int[]wordlist_1,String path,int size) throws IOException{
		fw	=	new FileWriter(path);
		for (int i = 0; i < size; i++) 
		{   
			   fw.write(wordlist_1[i]+"\r\n");   

        }   

     fw.close(); 
	}
	
	/**
	 * 	@param path: the input file
	 *  @param size: the input array size
	 *  @return int[] array which is the txt's content 
	 * */
	public static int[] get_array(String path, int size) throws IOException{
		fr	=	new FileReader(path);
		buf =	new BufferedReader(fr);
		int [] array = new int[size];
		int i = 0;
		String str = "";
		
		while( (str = buf.readLine())!= null && i < size){
			array[i] = Integer.parseInt(str);
			System.out.println(array[i]);
			i++;
		}
		
		return array;
	}
	
	/**
	 * @param path : the input file path
	 * @param size : the input file line number
	 * @return the wordlist(English and Chinese CONTENT)
	 * */
	public static String[] get_word_list(String path,int size) throws IOException{
		fr	=	new FileReader(path);
		buf =	new BufferedReader(fr);
		String[] array = new String[size];
		int i = 0;
		String str = "";
		
		while( i < size){
			str = buf.readLine();
			array[i] = str.split(" ")[0];
			i++;
		}
		
		return array;
	}
}
