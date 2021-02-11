package kr.or.iei.member.model.vo;

public class MemberAddress {
	private int memberNo;
	private int postNum;
	private String address;
	public MemberAddress() {
		super();
		// TODO Auto-generated constructor stub
	}
	public MemberAddress(int memberNo, int postNum, String address) {
		super();
		this.memberNo = memberNo;
		this.postNum = postNum;
		this.address = address;
	}
	public int getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}
	public int getPostNum() {
		return postNum;
	}
	public void setPostNum(int postNum) {
		this.postNum = postNum;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	
}
