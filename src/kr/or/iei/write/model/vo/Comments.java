package kr.or.iei.write.model.vo;

import java.sql.Date;

public class Comments {
	private int commenstNo;
	private int writeNo;
	private int memberNo;
	private String commentsText;
	private Date commentsDate;
	private Date commentsDeleteDate;
	private char commentsDeleteYN;
	public Comments() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Comments(int commenstNo, int writeNo, int memberNo, String commentsText, Date commentsDate,
			Date commentsDeleteDate, char commentsDeleteYN) {
		super();
		this.commenstNo = commenstNo;
		this.writeNo = writeNo;
		this.memberNo = memberNo;
		this.commentsText = commentsText;
		this.commentsDate = commentsDate;
		this.commentsDeleteDate = commentsDeleteDate;
		this.commentsDeleteYN = commentsDeleteYN;
	}
	public int getCommenstNo() {
		return commenstNo;
	}
	public void setCommenstNo(int commenstNo) {
		this.commenstNo = commenstNo;
	}
	public int getWriteNo() {
		return writeNo;
	}
	public void setWriteNo(int writeNo) {
		this.writeNo = writeNo;
	}
	public int getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}
	public String getCommentsText() {
		return commentsText;
	}
	public void setCommentsText(String commentsText) {
		this.commentsText = commentsText;
	}
	public Date getCommentsDate() {
		return commentsDate;
	}
	public void setCommentsDate(Date commentsDate) {
		this.commentsDate = commentsDate;
	}
	public Date getCommentsDeleteDate() {
		return commentsDeleteDate;
	}
	public void setCommentsDeleteDate(Date commentsDeleteDate) {
		this.commentsDeleteDate = commentsDeleteDate;
	}
	public char getCommentsDeleteYN() {
		return commentsDeleteYN;
	}
	public void setCommentsDeleteYN(char commentsDeleteYN) {
		this.commentsDeleteYN = commentsDeleteYN;
	}

	
}