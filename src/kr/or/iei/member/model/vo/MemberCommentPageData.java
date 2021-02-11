package kr.or.iei.member.model.vo;

import java.util.ArrayList;

public class MemberCommentPageData {
	private ArrayList<MemberComments> list;
	private String pageNavi;
	private int total;
	public MemberCommentPageData() {
		super();
		// TODO Auto-generated constructor stub
	}
	public MemberCommentPageData(ArrayList<MemberComments> list, String pageNavi, int total) {
		super();
		this.list = list;
		this.pageNavi = pageNavi;
		this.total = total;
	}
	public ArrayList<MemberComments> getList() {
		return list;
	}
	public void setList(ArrayList<MemberComments> list) {
		this.list = list;
	}
	public String getPageNavi() {
		return pageNavi;
	}
	public void setPageNavi(String pageNavi) {
		this.pageNavi = pageNavi;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}

}
