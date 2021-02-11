package kr.or.iei.member.model.vo;

import kr.or.iei.write.model.vo.Comments;
import kr.or.iei.write.model.vo.Write;

public class MemberComments {
	private Comments c;
	private Member m;
	private Write w;
	public MemberComments() {
		super();
		// TODO Auto-generated constructor stub
	}
	public MemberComments(Comments c, Member m, Write w) {
		super();
		this.c = c;
		this.m = m;
		this.w = w;
	}
	public Comments getC() {
		return c;
	}
	public void setC(Comments c) {
		this.c = c;
	}
	public Member getM() {
		return m;
	}
	public void setM(Member m) {
		this.m = m;
	}
	public Write getW() {
		return w;
	}
	public void setW(Write w) {
		this.w = w;
	}
	
}
