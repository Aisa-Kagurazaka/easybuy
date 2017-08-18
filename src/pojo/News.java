package pojo;

public class News {
	private Integer newsId;
	private String newsTitle;
	private String author;
	private String createDate;
	private String content;
	
	public News(){
		super();
	}
	
	public News(Integer newsId, String newsTitle, String author,String content) {
		super();
		this.newsId = newsId;
		this.newsTitle = newsTitle;
		this.author = author;
		this.content = content;
	}
	
	public News(String newsTitle, String author, String content) {
		super();
		this.newsTitle = newsTitle;
		this.author = author;
		this.content = content;
	}
	
	public News(Integer newsId, String newsTitle, String author, String createDate,
			String content) {
		super();
		this.newsId = newsId;
		this.newsTitle = newsTitle;
		this.author = author;
		this.createDate = createDate;
		this.content = content;
	}

	public Integer getNewsId() {
		return newsId;
	}
	public void setNewsId(Integer newsId) {
		this.newsId = newsId;
	}
	public String getNewsTitle() {
		return newsTitle;
	}
	public void setNewsTitle(String newsTitle) {
		this.newsTitle = newsTitle;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	@Override
	public String toString() {
		return "News [newsId=" + newsId + ", newsTitle=" + newsTitle
				+ ", author=" + author + ", createDate=" + createDate
				+ ", content=" + content + "]";
	}
	
}
