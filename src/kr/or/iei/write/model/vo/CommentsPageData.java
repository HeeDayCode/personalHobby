package kr.or.iei.write.model.vo;

import java.util.ArrayList;

public class CommentsPageData {
	private ArrayList<CommentsMember> list;
	private String pageNavi;
	private int total;
	public CommentsPageData() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CommentsPageData(ArrayList<CommentsMember> list, String pageNavi, int total) {
		super();
		this.list = list;
		this.pageNavi = pageNavi;
		this.total = total;
	}
	public ArrayList<CommentsMember> getList() {
		return list;
	}
	public void setList(ArrayList<CommentsMember> list) {
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
