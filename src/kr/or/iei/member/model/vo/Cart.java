package kr.or.iei.member.model.vo;

public class Cart {
	private int memberNo;
	private int productNo;
	public Cart() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Cart(int memberNo, int productNo) {
		super();
		this.memberNo = memberNo;
		this.productNo = productNo;
	}
	public int getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}
	public int getProductNo() {
		return productNo;
	}
	public void setProductNo(int productNo) {
		this.productNo = productNo;
	}
	
}
