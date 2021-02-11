package kr.or.iei.member.model.vo;

import kr.or.iei.img.model.vo.Img;
import kr.or.iei.write.model.vo.Write;

public class MemberWrite {
	private Write w;
	private Img i;
	private int commentCount;
	public MemberWrite() {
		super();
		// TODO Auto-generated constructor stub
	}
	public MemberWrite(Write w, Img i, int commentCount) {
		super();
		this.w = w;
		this.i = i;
		this.commentCount = commentCount;
	}
	public Write getW() {
		return w;
	}
	public void setW(Write w) {
		this.w = w;
	}
	public Img getI() {
		return i;
	}
	public void setI(Img i) {
		this.i = i;
	}
	public int getCommentCount() {
		return commentCount;
	}
	public void setCommentCount(int commentCount) {
		this.commentCount = commentCount;
	}
	
	
}
