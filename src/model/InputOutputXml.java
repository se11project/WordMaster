package model;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

public class InputOutputXml {
	private final int ALL_WORDS_NUM = 7989;

	public InputOutputXml() {

	}

	/* 从dictionary.xml读入单词的英语和中文，从statistics.xml读入单词的英语和统计信息 */
	public SingleWord[] inputXml(String fileName1, String fileName2)
			throws Exception {
		// 从xml文件读入单词
		SingleWord[] allWords = new SingleWord[ALL_WORDS_NUM];
		DocumentBuilderFactory dbf1 = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder1 = dbf1.newDocumentBuilder();
		Document document1 = builder1.parse(fileName1);

		Element root1 = document1.getDocumentElement();
		NodeList words1 = root1.getElementsByTagName("word");

		boolean hasStatus = false;
		for (int i = 0; i < words1.getLength(); i++) {
			SingleWord singleWord;
			Element element = (Element) words1.item(i);

			NodeList englishList = element.getElementsByTagName("english");
			NodeList chineseList = element.getElementsByTagName("chinese");

			Element englishElement = (Element) englishList.item(0);
			String english;
			if (englishElement == null)
				english = "";
			else {
				Node englishNode = englishElement.getFirstChild();
				english = englishNode.getNodeValue();
			}
			Element chineseElement = (Element) chineseList.item(0);
			String chinese;
			if (chineseElement == null)
				chinese = "";
			else {
				Node chineseNode = chineseElement.getFirstChild();
				chinese = chineseNode.getNodeValue();
			}

			singleWord = new SingleWord(english, chinese, 0);
			allWords[i] = singleWord;
		}

		File file2 = new File(fileName2);

		if (!file2.exists()) {// 如果statistics.xml文件不存在，则初始化该文件，status全部是0
			file2.createNewFile();
			writeXmlFile(allWords, fileName2);
		} else {
			DocumentBuilderFactory dbf2 = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder2 = dbf2.newDocumentBuilder();
			Document document2 = builder2.parse(fileName2); // 获取到xml文件

			Element root2 = document2.getDocumentElement(); // 获取根元素
			NodeList words2 = root2.getElementsByTagName("word");

			for (int i = 0; i < words2.getLength(); i++) {
				// 每次获取一个单词的信息
				Element element = (Element) words2.item(i);

				NodeList statusList = element.getElementsByTagName("status");

				Element statusElement = (Element) statusList.item(0);
				int status;
				if (statusElement == null)
					status = 0;
				else {
					hasStatus = true;
					Node statusNode = statusElement.getFirstChild();
					String statusStr = statusNode.getNodeValue();
					status = Integer.parseInt(statusStr);
				}
				allWords[i].setStatus(status);
			}
		}

		// 如果xml文件中没有status，则重新写入xml，加上status属性
		if (!hasStatus)
			writeXmlFile(allWords, fileName2);
		return allWords;
	}

	private void callWriteXmlFile(Document doc, Writer w, String encoding) {
		try {
			Source source = new DOMSource(doc);

			Result result = new StreamResult(w);

			Transformer xformer = TransformerFactory.newInstance()
					.newTransformer();
			xformer.setOutputProperty(OutputKeys.ENCODING, encoding);
			xformer.transform(source, result);

		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerException e) {
			e.printStackTrace();
		}
	}

	/* 将整个数组allWords中所有单词的english和status写入statistics.xml文件 */
	private void writeXmlFile(SingleWord[] allWords, String fileName) {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = null;
		try {
			builder = dbf.newDocumentBuilder();
		} catch (Exception e) {
		}
		Document doc = builder.newDocument();

		Element root = doc.createElement("words");
		doc.appendChild(root);

		int length = allWords.length;
		for (int i = 0; i < length; i++) {
			SingleWord s = allWords[i];
			Element word = doc.createElement("word");
			root.appendChild(word);

			Element english = doc.createElement("english");
			word.appendChild(english);
			Text tEnglish = doc.createTextNode(s.getEnglish());
			english.appendChild(tEnglish);

			Element status = doc.createElement("status");
			word.appendChild(status);
			Text tStatus = doc.createTextNode(String.valueOf(s.getStatus()));
			status.appendChild(tStatus);
		}
		try {
			FileOutputStream fos = new FileOutputStream(fileName);
			OutputStreamWriter outwriter = new OutputStreamWriter(fos);
			callWriteXmlFile(doc, outwriter, "gb2312");
			outwriter.close();
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/* 将一个特定单词的状态更改情况写入statistics.xml文件 */
	public static void outputXml(SingleWord word, String fileName) {
		String english = word.getEnglish();
		int status = word.getStatus();
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = dbf.newDocumentBuilder();
			Document document = builder.parse(fileName);
			NodeList nodeList = document.getElementsByTagName("word");
			for (int i = 0; i < nodeList.getLength(); i++) {
				String currentEnglish = document
						.getElementsByTagName("english").item(i)
						.getFirstChild().getNodeValue();
				if (currentEnglish.equals(english)) {
					document.getElementsByTagName("status").item(i)
							.getFirstChild()
							.setNodeValue(String.valueOf(status));
					break;
				}
			}
			saveXML(document, fileName);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/* 将多个特定单词的状态更改情况写入statistics.xml文件 */
	public static void outputXml(ArrayList<SingleWord> words, String fileName) {
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = dbf.newDocumentBuilder();
			Document document = builder.parse(fileName);
			NodeList nodeList = document.getElementsByTagName("word");
			int arrayIndex, nodeIndex;
			int wordsNum = words.size();
			int nodesNum = nodeList.getLength();
			for (arrayIndex = 0, nodeIndex = 0; arrayIndex < wordsNum
					&& nodeIndex < nodesNum; arrayIndex++) {
				SingleWord word = words.get(arrayIndex);
				String english = word.getEnglish();
				int status = word.getStatus();

				boolean foundWord = false;
				while (!foundWord) {
					String currentEnglish = document.getElementsByTagName("english")
							.item(nodeIndex).getFirstChild().getNodeValue();
					if (currentEnglish.equals(english)) {
						document.getElementsByTagName("status").item(nodeIndex)
								.getFirstChild()
								.setNodeValue(String.valueOf(status));
						foundWord = true;
					}
					nodeIndex++;
				}
			}
			saveXML(document, fileName);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static boolean saveXML(Document document, String filePath) {
		try {
			TransformerFactory tFactory = TransformerFactory.newInstance();
			Transformer transformer = tFactory.newTransformer();

			DOMSource source = new DOMSource(document);
			StreamResult result = new StreamResult(new File(filePath));
			transformer.transform(source, result);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}