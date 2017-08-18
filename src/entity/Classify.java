package entity;

public class Classify {
	private int classifyId;
	private String classifyName;
	private int fatherId;
	public Classify(){
		super();
	}
	public Classify(int classifyId, String classifyName, int fatherId) {
		super();
		this.classifyId = classifyId;
		this.classifyName = classifyName;
		this.fatherId = fatherId;
	}
	public int getClassifyId() {
		return classifyId;
	}
	public void setClassifyId(int classifyId) {
		this.classifyId = classifyId;
	}
	public String getClassifyName() {
		return classifyName;
	}
	public void setClassifyName(String classifyName) {
		this.classifyName = classifyName;
	}
	public int getFatherId() {
		return fatherId;
	}
	public void setFatherId(int fatherId) {
		this.fatherId = fatherId;
	}
	@Override
	public String toString() {
		return "Classify [classifyId=" + classifyId + ", classifyName="
				+ classifyName + ", fatherId=" + fatherId + "]";
	}
}
