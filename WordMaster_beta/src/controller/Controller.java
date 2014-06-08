package controller;

import java.io.IOException;

import view.MainView;
import view.ReciteView;
import view.SelectStartView;
import view.StartView;
import view.StatisticView;

import model.RemberProcess;


public class Controller {
	private static final int WORD_NUM = 7989;//词库总数

	private RemberProcess rp = new RemberProcess();  //模型：大的词库。储存所有词库的信息和
	private MainView mainview;//主界面
	private StartView startview;//第二个界面，主要是用于选择相应的模式
	private SelectStartView selectstartview;//
	private ReciteView reciteview;
	private StatisticView statisticView;
	
	public Controller()
	{
		try {
			fillWordLibrary();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		mainview = new MainView(this);
	}
	
	public RemberProcess getProcess() {
		return rp;
	}
	
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
	
	public int getNumber(String word)
	{
		return rp.getNumber(word);
	}
	
	public void startNumber(String word)
	{
		rp.startNumber(word);
	}
	
	public int checkInputNumber(int input)
	{
		return rp.checkWordNumber(input);
	}
	
	public String[] matchInputString(String str)
	{
		return rp.matchWordLibrary(str);
	}
	
	public boolean rememberWord(String str)
	{
		return rp.rememberWord(str);
	}
	
	public void fillWordLibrary() throws IOException
	{
        rp.fillWordLibrary();
	}
	
	public void next(){
		rp.next();
	}
	
	public void changeView(int flag)
	{
		switch (flag){
		case 0:
			mainview = new MainView(this);
			break;
		case 1:
			startview = new StartView(this);;
			break;
		case 2:
			selectstartview = new SelectStartView(this);
			break;
		case 3:
			reciteview = new ReciteView(this);
			break;
		case 4:
			statisticView = new StatisticView(this, 1);
			break;
		case 5:
			statisticView = new StatisticView(this, 0);
			break;
		}
	}
	
	public static void main(String[] args) throws IOException {
		Controller controller = new Controller();
	}
}