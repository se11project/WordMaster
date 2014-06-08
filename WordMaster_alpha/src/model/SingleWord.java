package model;

public class SingleWord {

	private String content;
	private String chinese;
	private int status = 0;
	
	public SingleWord(String content, String chinese, int status) {
		this.content = content;
		this.chinese = chinese;
		this.status = status;
	}
	
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

	public boolean updateStatus(String str) {
		if (content.compareTo(str) == 0){
			this.status = 3;
			System.out.println(content+" 0p0");
			return true;
		}
		else{
			if (this.status != 3)
			    this.status = 1;
		    System.out.println(content+" 0p0");
			return false;
		}
	}	
	
}
