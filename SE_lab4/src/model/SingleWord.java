package model;

public class SingleWord {

	private String content;
	private String chinese;
	private int status = 0;
	
	public String getContent() {
		return content;
	}
	
	public String getChinese() {
		return chinese;
	}
	
	public int getStatus() {
		return status;
	}
	
	public void setContent(String content) {
		this.content = content;
	}

	public void setChinese(String chinese) {
		this.chinese = chinese;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public void updateStatus(String str) {
		if (content.compareTo(str) == 0)
			this.status = 3;
		else
			this.status = 1;
	}
	
}
