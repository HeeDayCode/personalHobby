package kr.or.iei.write.model.vo;

public class MyHeart {
	private int member_no;
	private int write_no;

	
	
	public MyHeart() {
		super();
		// TODO Auto-generated constructor stub
	}
	public MyHeart(int member_no, int write_no) {
		super();
		this.member_no = member_no;
		this.write_no = write_no;
	}
	public int getMember_no() {
		return member_no;
	}
	public void setMember_no(int member_no) {
		this.member_no = member_no;
	}
	public int getWrite_no() {
		return write_no;
	}
	public void setWrite_no(int write_no) {
		this.write_no = write_no;
	}

}
