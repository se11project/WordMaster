package model;

import java.util.HashSet;

import static org.junit.Assert.assertEquals;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class BigLibraryTest {
	private static BigLibrary bigLibrary = new BigLibrary();

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testGetPos() throws Exception {
		HashSet<String> expectedPosSet1 = new HashSet<String>();
		String wordMeaning1 = "n.球体，范围，领域";
		expectedPosSet1.add("n.");
		assertEquals(expectedPosSet1, bigLibrary.getPos(wordMeaning1));
		HashSet<String> expectedPosSet2 = new HashSet<String>();
		String wordMeaning2 = "n.,v.委托，信任，信托";
		expectedPosSet2.add("n.");
		expectedPosSet2.add("v.");
		assertEquals(expectedPosSet2, bigLibrary.getPos(wordMeaning2));
		HashSet<String> expectedPosSet3 = new HashSet<String>();
		String wordMeaning3 = "n.火鸡adj.,n.土耳其";
		expectedPosSet3.add("n.");
		expectedPosSet3.add("adj.");
		assertEquals(expectedPosSet3, bigLibrary.getPos(wordMeaning3));
	}

	@Test
	public void testRememberWord() throws Exception {
		bigLibrary.init("dictionary.xml", "statistics.xml");
		boolean result1 = false;
		String str = "abando";// 用户背单词时输入错误，正确为abandon. 动词词库第一个单词
		bigLibrary.selectedLibrary("v.");// 初始化为动词词库
		bigLibrary.setCurrentWordNumber(0);// 第一个单词
		assertEquals(result1, bigLibrary.rememberWord(str));

		boolean result2 = true;
		str = "abandonment";// 用户背单词时输入正确 名词词库第一个单词
		bigLibrary.selectedLibrary("n.");// 初始化为名词词库
		bigLibrary.setCurrentWordNumber(0);// 第一个单词
		assertEquals(result2, bigLibrary.rememberWord(str));

		boolean result3 = false;
		str = "abl";// 用户背单词时输入错误，正确为able 形容词词库第一个单词
		bigLibrary.selectedLibrary("adj.");// 初始化为形容词词库
		bigLibrary.setCurrentWordNumber(0);// 第一个单词
		assertEquals(result3, bigLibrary.rememberWord(str));
	}

	@After
	public void tearDown() throws Exception {
	}
}