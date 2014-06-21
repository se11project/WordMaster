package model;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SingleWordTest {
	private static BigLibrary bigLibrary = new BigLibrary();
	private WordLibrary selectedLibrary;
	private SingleWord singleWord;

	@Before
	public void setUp() throws Exception {
	}

	@Test
	// 测试 “更新单词的状态”的方法
	public void testUpdateStatus1() throws Exception {
		// 初始化词库
		bigLibrary.init("dictionary.xml", "statistics.xml");
		// 初始化selectLibrary为动词词库
		bigLibrary.selectedLibrary("v.");
		selectedLibrary = bigLibrary.getSelectedLibrary();
		// 初始化singleWord
		singleWord = new SingleWord("abandon", "v.抛弃，放弃", 0);
		boolean result1 = false;
		String str = "abando";// 用户背单词时输入错误，正确为abandon. 动词词库第一个单词
		assertEquals(result1, singleWord.updateStatus(str, selectedLibrary));

		// 初始化selectLibrary为名词词库
		bigLibrary.selectedLibrary("n.");
		selectedLibrary = bigLibrary.getSelectedLibrary();
		// 初始化singleWord
		singleWord = new SingleWord("abbreviation", "n.缩写", 0);
		boolean result2 = true;
		str = "abbreviation";// 用户背单词时输入正确 名词词库第一个单词
		assertEquals(result2, singleWord.updateStatus(str, selectedLibrary));

		// 初始化selectLibrary为形容词词库
		bigLibrary.selectedLibrary("adj.");
		selectedLibrary = bigLibrary.getSelectedLibrary();
		// 初始化singleWord
		singleWord = new SingleWord("able", "adj.有能力的，能干的", 0);
		boolean result3 = false;
		str = "abl";// 用户背单词时输入错误，正确为able 形容词词库第一个单词
		assertEquals(result3, selectedLibrary.update(str, selectedLibrary, 0));
	}

	@Test
	// 测试 “更新前一个单词的背诵的状态为非该词库最后一个单词”的方法
	public void testUpdateStatus2() throws Exception {
		// 初始化词库
		bigLibrary.init("dictionary.xml", "statistics.xml");
		// 初始化singleWord
		singleWord = new SingleWord("abandon", "v.抛弃，放弃", 1);
		// 初始化selectLibrary为动词词库
		bigLibrary.selectedLibrary("v.");
		selectedLibrary = bigLibrary.getSelectedLibrary();
		int expectedStatus1 = 1;
		// 测试方法调用
		singleWord.updateStatus(selectedLibrary);
		// 记录方法调用后的status
		int result1 = singleWord.getStatus();
		assertEquals(expectedStatus1, result1);

		// 初始化singleWord
		singleWord = new SingleWord("abbreviation", "n.缩写", 3);
		// 初始化selectLibrary为名词词库
		bigLibrary.selectedLibrary("n.");
		selectedLibrary = bigLibrary.getSelectedLibrary();
		int expectedStatus2 = 3;
		// 测试方法调用
		singleWord.updateStatus(selectedLibrary);
		// 记录方法调用后的status
		int result2 = singleWord.getStatus();
		assertEquals(expectedStatus2, result2);

		// 初始化singleWord
		singleWord = new SingleWord("able", "adj.有能力的，能干的", 1);
		// 初始化selectLibrary为形容词词库
		bigLibrary.selectedLibrary("adj.");
		selectedLibrary = bigLibrary.getSelectedLibrary();
		int expectedStatus3 = 1;
		// 测试方法调用
		singleWord.updateStatus(selectedLibrary);
		// 记录方法调用后的status
		int result3 = singleWord.getStatus();
		assertEquals(expectedStatus3, result3);
	}

	@After
	public void tearDown() throws Exception {
	}
}