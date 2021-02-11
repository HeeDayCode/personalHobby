package kr.or.iei.member.model.vo;

public class MyHeart {
	private int memberNo;
	private int writeNo;
	public MyHeart() {
		super();
		// TODO Auto-generated constructor stub
	}
	public MyHeart(int memberNo, int writeNo) {
		super();
		this.memberNo = memberNo;
		this.writeNo = writeNo;
	}
	public int getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}
	public int getWriteNo() {
		return writeNo;
	}
	public void setWriteNo(int writeNo) {
		this.writeNo = writeNo;
	}
	
}
