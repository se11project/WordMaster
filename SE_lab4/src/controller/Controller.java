package controller;

import model.RemberProcess;

public class Controller {

	RemberProcess rp = new RemberProcess();
	
	public void selectedLibrary(String firstName)
	{
		rp.selectedLibrary(firstName);
	}
	
	public int calcCorrectWordNumber(int arg)
	{
		return rp.calcCorrectWordNumber(arg);
	}
	
	public int calcWrongWordNumber(int arg)
	{
		return rp.calcWrongWordNumber(arg);
	}
	
	public double calcCorrectRate(int arg)
	{
		return rp.calcCorrectRate(arg);
	}
	
	public boolean checkInputNumber(int input)
	{
		return rp.checkWordNumber(input);
	}
	
	public String[] matchInputString(String str)
	{
		return rp.matchWordLibrary(str);
	}
	
	public void rememberWord(String str)
	{
		rp.rememberWord(str);
	}
	
	public void fillWordLibrary()
	{
		
	}
}
