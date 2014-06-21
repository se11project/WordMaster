package model;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class WordLibraryTest {
	private static BigLibrary bigLibrary = new BigLibrary();
	private WordLibrary selectedLibrary;

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testUpdate() throws Exception {
		bigLibrary.init("dictionary.xml", "statistics.xml");
		// 初始化selectLibrary为动词词库
		bigLibrary.selectedLibrary("v.");
		selectedLibrary = bigLibrary.getSelectedLibrary();
		boolean result1 = false;
		String str = "abando";// 用户背单词时输入错误，正确为abandon. 动词词库第一个单词
		assertEquals(result1, selectedLibrary.update(str, selectedLibrary, 0));

		// 初始化selectLibrary为名词词库
		bigLibrary.selectedLibrary("n.");
		selectedLibrary = bigLibrary.getSelectedLibrary();
		boolean result2 = true;
		str = "abandonment";// 用户背单词时输入正确 名词词库第一个单词
		assertEquals(result2, selectedLibrary.update(str, selectedLibrary, 0));

		// 初始化selectLibrary为形容词词库
		bigLibrary.selectedLibrary("adj.");
		selectedLibrary = bigLibrary.getSelectedLibrary();
		boolean result3 = false;
		str = "abl";// 用户背单词时输入错误，正确为able 形容词词库第一个单词
		assertEquals(result3, selectedLibrary.update(str, selectedLibrary, 0));
	}

	@After
	public void tearDown() throws Exception {
	}
}