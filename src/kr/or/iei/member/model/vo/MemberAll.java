package kr.or.iei.member.model.vo;


public class MemberAll {
	private Member m;
	private MemberAddress ma;
	private MemberDate md;
	private MemberImg mImg;
	
	public MemberAll() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MemberAll(Member m, MemberAddress ma, MemberDate md, MemberImg mImg) {
		super();
		this.m = m;
		this.ma = ma;
		this.md = md;
		this.mImg = mImg;
	}
	public Member getM() {
		return m;
	}
	public void setM(Member m) {
		this.m = m;
	}
	public MemberAddress getMa() {
		return ma;
	}
	public void setMa(MemberAddress ma) {
		this.ma = ma;
	}
	public MemberDate getMd() {
		return md;
	}
	public void setMd(MemberDate md) {
		this.md = md;
	}
	public MemberImg getmImg() {
		return mImg;
	}
	public void setmImg(MemberImg mImg) {
		this.mImg = mImg;
	}
	
	
}