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
	// ���� �����µ��ʵ�״̬���ķ���
	public void testUpdateStatus1() throws Exception {
		// ��ʼ���ʿ�
		bigLibrary.init("dictionary.xml", "statistics.xml");
		// ��ʼ��selectLibraryΪ���ʴʿ�
		bigLibrary.selectedLibrary("v.");
		selectedLibrary = bigLibrary.getSelectedLibrary();
		// ��ʼ��singleWord
		singleWord = new SingleWord("abandon", "v.����������", 0);
		boolean result1 = false;
		String str = "abando";// �û�������ʱ���������ȷΪabandon. ���ʴʿ��һ������
		assertEquals(result1, singleWord.updateStatus(str, selectedLibrary));

		// ��ʼ��selectLibraryΪ���ʴʿ�
		bigLibrary.selectedLibrary("n.");
		selectedLibrary = bigLibrary.getSelectedLibrary();
		// ��ʼ��singleWord
		singleWord = new SingleWord("abbreviation", "n.��д", 0);
		boolean result2 = true;
		str = "abbreviation";// �û�������ʱ������ȷ ���ʴʿ��һ������
		assertEquals(result2, singleWord.updateStatus(str, selectedLibrary));

		// ��ʼ��selectLibraryΪ���ݴʴʿ�
		bigLibrary.selectedLibrary("adj.");
		selectedLibrary = bigLibrary.getSelectedLibrary();
		// ��ʼ��singleWord
		singleWord = new SingleWord("able", "adj.�������ģ��ܸɵ�", 0);
		boolean result3 = false;
		str = "abl";// �û�������ʱ���������ȷΪable ���ݴʴʿ��һ������
		assertEquals(result3, selectedLibrary.update(str, selectedLibrary, 0));
	}

	@Test
	// ���� ������ǰһ�����ʵı��е�״̬Ϊ�Ǹôʿ����һ�����ʡ��ķ���
	public void testUpdateStatus2() throws Exception {
		// ��ʼ���ʿ�
		bigLibrary.init("dictionary.xml", "statistics.xml");
		// ��ʼ��singleWord
		singleWord = new SingleWord("abandon", "v.����������", 1);
		// ��ʼ��selectLibraryΪ���ʴʿ�
		bigLibrary.selectedLibrary("v.");
		selectedLibrary = bigLibrary.getSelectedLibrary();
		int expectedStatus1 = 1;
		// ���Է�������
		singleWord.updateStatus(selectedLibrary);
		// ��¼�������ú��status
		int result1 = singleWord.getStatus();
		assertEquals(expectedStatus1, result1);

		// ��ʼ��singleWord
		singleWord = new SingleWord("abbreviation", "n.��д", 3);
		// ��ʼ��selectLibraryΪ���ʴʿ�
		bigLibrary.selectedLibrary("n.");
		selectedLibrary = bigLibrary.getSelectedLibrary();
		int expectedStatus2 = 3;
		// ���Է�������
		singleWord.updateStatus(selectedLibrary);
		// ��¼�������ú��status
		int result2 = singleWord.getStatus();
		assertEquals(expectedStatus2, result2);

		// ��ʼ��singleWord
		singleWord = new SingleWord("able", "adj.�������ģ��ܸɵ�", 1);
		// ��ʼ��selectLibraryΪ���ݴʴʿ�
		bigLibrary.selectedLibrary("adj.");
		selectedLibrary = bigLibrary.getSelectedLibrary();
		int expectedStatus3 = 1;
		// ���Է�������
		singleWord.updateStatus(selectedLibrary);
		// ��¼�������ú��status
		int result3 = singleWord.getStatus();
		assertEquals(expectedStatus3, result3);
	}

	@After
	public void tearDown() throws Exception {
	}
}