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
		// ��ʼ��selectLibraryΪ���ʴʿ�
		bigLibrary.selectedLibrary("v.");
		selectedLibrary = bigLibrary.getSelectedLibrary();
		boolean result1 = false;
		String str = "abando";// �û�������ʱ���������ȷΪabandon. ���ʴʿ��һ������
		assertEquals(result1, selectedLibrary.update(str, selectedLibrary, 0));

		// ��ʼ��selectLibraryΪ���ʴʿ�
		bigLibrary.selectedLibrary("n.");
		selectedLibrary = bigLibrary.getSelectedLibrary();
		boolean result2 = true;
		str = "abandonment";// �û�������ʱ������ȷ ���ʴʿ��һ������
		assertEquals(result2, selectedLibrary.update(str, selectedLibrary, 0));

		// ��ʼ��selectLibraryΪ���ݴʴʿ�
		bigLibrary.selectedLibrary("adj.");
		selectedLibrary = bigLibrary.getSelectedLibrary();
		boolean result3 = false;
		str = "abl";// �û�������ʱ���������ȷΪable ���ݴʴʿ��һ������
		assertEquals(result3, selectedLibrary.update(str, selectedLibrary, 0));
	}

	@After
	public void tearDown() throws Exception {
	}
}