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
		String wordMeaning1 = "n.���壬��Χ������";
		expectedPosSet1.add("n.");
		assertEquals(expectedPosSet1, bigLibrary.getPos(wordMeaning1));
		HashSet<String> expectedPosSet2 = new HashSet<String>();
		String wordMeaning2 = "n.,v.ί�У����Σ�����";
		expectedPosSet2.add("n.");
		expectedPosSet2.add("v.");
		assertEquals(expectedPosSet2, bigLibrary.getPos(wordMeaning2));
		HashSet<String> expectedPosSet3 = new HashSet<String>();
		String wordMeaning3 = "n.��adj.,n.������";
		expectedPosSet3.add("n.");
		expectedPosSet3.add("adj.");
		assertEquals(expectedPosSet3, bigLibrary.getPos(wordMeaning3));
	}

	@Test
	public void testRememberWord() throws Exception {
		bigLibrary.init("dictionary.xml", "statistics.xml");
		boolean result1 = false;
		String str = "abando";// �û�������ʱ���������ȷΪabandon. ���ʴʿ��һ������
		bigLibrary.selectedLibrary("v.");// ��ʼ��Ϊ���ʴʿ�
		bigLibrary.setCurrentWordNumber(0);// ��һ������
		assertEquals(result1, bigLibrary.rememberWord(str));

		boolean result2 = true;
		str = "abandonment";// �û�������ʱ������ȷ ���ʴʿ��һ������
		bigLibrary.selectedLibrary("n.");// ��ʼ��Ϊ���ʴʿ�
		bigLibrary.setCurrentWordNumber(0);// ��һ������
		assertEquals(result2, bigLibrary.rememberWord(str));

		boolean result3 = false;
		str = "abl";// �û�������ʱ���������ȷΪable ���ݴʴʿ��һ������
		bigLibrary.selectedLibrary("adj.");// ��ʼ��Ϊ���ݴʴʿ�
		bigLibrary.setCurrentWordNumber(0);// ��һ������
		assertEquals(result3, bigLibrary.rememberWord(str));
	}

	@After
	public void tearDown() throws Exception {
	}
}