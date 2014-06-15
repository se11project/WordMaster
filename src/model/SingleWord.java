package model;

public class SingleWord {
	private String english;
	private String chinese;
	private int status;
	/*
	 * status为一个十二位二进制数，转化成十进制数范围在0到4095。最高十位从高到低依次表示是否为相应词库的最后一个单词，右边第一位表示是否背过，
	 * 右边第二位表示是否背对。
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

	/* 更新背诵的单词的状态 */
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

	/* 更新前一个单词的背诵的状态为非该词库最后一个单词 */
	public void updateStatus(WordLibrary selectedLibrary) {
		int libraryIndex = selectedLibrary.getLibraryIndex();
		int changeStatus = ~(1 << (11 - libraryIndex));
		this.status &= changeStatus;
	}
}