package entity;

public class Comment {
	private int commentId;
	private String title;
	private String content;
	private String author;
	private String time;
	
	public Comment(){
		super();
	}
	
	public Comment(String title, String content, String author) {
		super();
		this.title = title;
		this.content = content;
		this.author = author;
	}

	public Comment(int commentId, String title, String content, String author,
			String time) {
		super();
		this.commentId = commentId;
		this.title = title;
		this.content = content;
		this.author = author;
		this.time = time;
	}
	
	public Comment(int commentId, String content, String author,
			String time) {
		super();
		this.commentId = commentId;
		this.content = content;
		this.author = author;
		this.time = time;
	}

	public int getCommentId() {
		return commentId;
	}
	public void setCommentId(int commentId) {
		this.commentId = commentId;
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
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	@Override
	public String toString() {
		return "Comment [commentId=" + commentId + ", title=" + title
				+ ", content=" + content + ", author=" + author + ", time="
				+ time + "]";
	}
}
