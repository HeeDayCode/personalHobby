package kr.or.iei.notice.model.vo;

import java.sql.Date;

public class Notice {
	private int noticeNo;
	private int noticeCategoryNo;
	private String noticeText;
	private int noticeViewCount;
	private Date noticeDate;
	private Date noticeDeleteDate;
	private char noticeDeleteYN;
	private String noticeName;
	
	public Notice() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Notice(int noticeNo, int noticeCategoryNo, String noticeText, int noticeViewCount, Date noticeDate,
			Date noticeDeleteDate, char noticeDeleteYN, String noticeName) {
		super();
		this.noticeNo = noticeNo;
		this.noticeCategoryNo = noticeCategoryNo;
		this.noticeText = noticeText;
		this.noticeViewCount = noticeViewCount;
		this.noticeDate = noticeDate;
		this.noticeDeleteDate = noticeDeleteDate;
		this.noticeDeleteYN = noticeDeleteYN;
		this.noticeName = noticeName;
	}
	public int getNoticeNo() {
		return noticeNo;
	}
	public void setNoticeNo(int noticeNo) {
		this.noticeNo = noticeNo;
	}
	public int getNoticeCategoryNo() {
		return noticeCategoryNo;
	}
	public void setNoticeCategoryNo(int noticeCategoryNo) {
		this.noticeCategoryNo = noticeCategoryNo;
	}
	public String getNoticeText() {
		return noticeText;
	}
	public void setNoticeText(String noticeText) {
		this.noticeText = noticeText;
	}
	public int getNoticeViewCount() {
		return noticeViewCount;
	}
	public void setNoticeViewCount(int noticeViewCount) {
		this.noticeViewCount = noticeViewCount;
	}
	public Date getNoticeDate() {
		return noticeDate;
	}
	public void setNoticeDate(Date noticeDate) {
		this.noticeDate = noticeDate;
	}
	public Date getNoticeDeleteDate() {
		return noticeDeleteDate;
	}
	public void setNoticeDeleteDate(Date noticeDeleteDate) {
		this.noticeDeleteDate = noticeDeleteDate;
	}
	public char getNoticeDeleteYN() {
		return noticeDeleteYN;
	}
	public void setNoticeDeleteYN(char noticeDeleteYN) {
		this.noticeDeleteYN = noticeDeleteYN;
	}
	public String getNoticeName() {
		return noticeName;
	}
	public void setNoticeName(String noticeName) {
		this.noticeName = noticeName;
	}
	
	
	
}