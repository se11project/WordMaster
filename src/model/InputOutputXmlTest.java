package model;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class InputOutputXmlTest {

	private static InputOutputXml test;

	@Before
	public void setUp() throws Exception {
		test = new InputOutputXml();
	}

	@Test
	public void testInputXml() throws Exception {
		SingleWord[] s = test.inputXml("dictionary.xml", "statistics_test.xml");
		assertEquals("abandon", s[0].getEnglish());
		assertEquals("adj.反常的，变态的", s[7].getChinese());
	}

	@Test
	public void testOutputXml1() throws Exception {
		SingleWord word_absence = new SingleWord("absence", "n.缺席，缺乏",
				(1 << 11) + 1);
		InputOutputXml.outputXml(word_absence, "statistics_test.xml");
		SingleWord word_wholly = new SingleWord("wholly", "adv.完全，一概", 3);
		InputOutputXml.outputXml(word_wholly, "statistics_test.xml");
		SingleWord[] testWords = test.inputXml("dictionary.xml",
				"statistics_test.xml");
		int length = testWords.length;
		int status_absence = 0, status_wholly = 0;
		for (int i = 0; i < length; i++) {
			if (testWords[i].getEnglish().equals(word_absence.getEnglish()))
				status_absence = testWords[i].getStatus();
			if (testWords[i].getEnglish().equals(word_wholly.getEnglish()))
				status_wholly = testWords[i].getStatus();
		}
		assertEquals(status_absence, (1 << 11) + 1);
		assertEquals(status_wholly, 3);
	}

	@Test
	public void testOutputXml2() throws Exception {
		SingleWord word_absence = new SingleWord("absence", "n.缺席，缺乏",
				(1 << 11) + 1);
		SingleWord word_wholly = new SingleWord("wholly", "adv.完全，一概", 3);
		ArrayList<SingleWord> list = new ArrayList<SingleWord>();
		list.add(word_absence);
		list.add(word_wholly);
		InputOutputXml.outputXml(list, "statistics_test.xml");
		SingleWord[] testWords = test.inputXml("dictionary.xml",
				"statistics_test.xml");
		int length = testWords.length;
		int status_absence = 0, status_wholly = 0;
		for (int i = 0; i < length; i++) {
			if (testWords[i].getEnglish().equals(word_absence.getEnglish()))
				status_absence = testWords[i].getStatus();
			if (testWords[i].getEnglish().equals(word_wholly.getEnglish()))
				status_wholly = testWords[i].getStatus();
		}
		assertEquals(status_absence, (1 << 11) + 1);
		assertEquals(status_wholly, 3);
	}

	@After
	public void tearDown() throws Exception {
	}
}