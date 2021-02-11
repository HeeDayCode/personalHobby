package kr.or.iei.member.model.vo;

public class LoadData {
	private int memberNum;
	private int salesNum;
	public int getMemberNum() {
		return memberNum;
	}
	public void setMemberNum(int memberNum) {
		this.memberNum = memberNum;
	}
	public int getSalesNum() {
		return salesNum;
	}
	public void setSalesNum(int salesNum) {
		this.salesNum = salesNum;
	}
	public LoadData(int memberNum, int salesNum) {
		super();
		this.memberNum = memberNum;
		this.salesNum = salesNum;
	}
	public LoadData() {
		super();
		// TODO Auto-generated constructor stub
	}
}
