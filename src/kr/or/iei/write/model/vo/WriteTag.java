package kr.or.iei.write.model.vo;

public class WriteTag {
	private int writeNo;
	private String tag;
	public WriteTag() {
		super();
		// TODO Auto-generated constructor stub
	}
	public WriteTag(int writeNo, String tag) {
		super();
		this.writeNo = writeNo;
		this.tag = tag;
	}
	public int getWriteNo() {
		return writeNo;
	}
	public void setWriteNo(int writeNo) {
		this.writeNo = writeNo;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	
}