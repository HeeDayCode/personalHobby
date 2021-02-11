package kr.or.iei.notice.model.vo;

public class NoticeCategory {
	private int noticeCategoryNo;
	private String noticeCategoryName;
	public NoticeCategory() {
		super();
		// TODO Auto-generated constructor stub
	}
	public NoticeCategory(int noticeCategoryNo, String noticeCategoryName) {
		super();
		this.noticeCategoryNo = noticeCategoryNo;
		this.noticeCategoryName = noticeCategoryName;
	}
	public int getNoticeCategoryNo() {
		return noticeCategoryNo;
	}
	public void setNoticeCategoryNo(int noticeCategoryNo) {
		this.noticeCategoryNo = noticeCategoryNo;
	}
	public String getNoticeCategoryName() {
		return noticeCategoryName;
	}
	public void setNoticeCategoryName(String noticeCategoryName) {
		this.noticeCategoryName = noticeCategoryName;
	}
	
}
