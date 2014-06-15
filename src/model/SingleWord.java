package model;

public class SingleWord {
	private String english;
	private String chinese;
	private int status;
	/*
	 * statusΪһ��ʮ��λ����������ת����ʮ��������Χ��0��4095�����ʮλ�Ӹߵ������α�ʾ�Ƿ�Ϊ��Ӧ�ʿ�����һ�����ʣ��ұߵ�һλ��ʾ�Ƿ񱳹���
	 * �ұߵڶ�λ��ʾ�Ƿ񱳶ԡ�
	 */

	public SingleWord(String english, String chinese, int status) {
		this.english = english;
		this.chinese = chinese;
		this.status = status;
	}

	public String getEnglish() {
		return english;
	}

	public String getChinese() {
		return chinese;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	/* ���±��еĵ��ʵ�״̬ */
	public boolean updateStatus(String str, WordLibrary selectedLibrary) {
		int libraryIndex = selectedLibrary.getLibraryIndex();
		int changeStatus = 1 << (11 - libraryIndex);
		this.status |= changeStatus;
		if (english.equals(str)) {
			this.status |= 0x3;
			return true;
		} else {
			this.status |= 0x1;
			return false;
		}
	}

	/* ����ǰһ�����ʵı��е�״̬Ϊ�Ǹôʿ����һ������ */
	public void updateStatus(WordLibrary selectedLibrary) {
		int libraryIndex = selectedLibrary.getLibraryIndex();
		int changeStatus = ~(1 << (11 - libraryIndex));
		this.status &= changeStatus;
	}
}