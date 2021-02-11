package kr.or.iei.write.model.vo;

import kr.or.iei.member.model.vo.Member;

public class CommentsMember {
	private Comments c;
	private Member m;

	public CommentsMember() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CommentsMember(Comments c, Member m) {
		super();
		this.c = c;
		this.m = m;

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

}
