package pojo;

public class Notice {
	private Integer noticeId;
	private String title;
	private String content;
	private String time;
	
	public Notice(){
		super();
	}
	
	public Notice(String title, String content) {
		super();
		this.title = title;
		this.content = content;
	}
	
	public Notice(Integer noticeId, String title, String content, String time) {
		super();
		this.noticeId = noticeId;
		this.title = title;
		this.content = content;
		this.time = time;
	}

	public Integer getNoticeId() {
		return noticeId;
	}
	public void setNoticeId(Integer noticeId) {
		this.noticeId = noticeId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	
	@Override
	public String toString() {
		return "Notice [noticeId=" + noticeId + ", title=" + title
				+ ", content=" + content + ", time=" + time + "]";
	}
	
}
