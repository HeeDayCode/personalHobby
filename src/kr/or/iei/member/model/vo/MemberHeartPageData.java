package kr.or.iei.member.model.vo;

import java.util.ArrayList;

public class MemberHeartPageData {
	private ArrayList<MemberWrite> list;
	private String pageNavi;
	private int total;

	public MemberHeartPageData() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MemberHeartPageData(ArrayList<MemberWrite> list, String pageNavi, int total) {
		super();
		this.list = list;
		this.pageNavi = pageNavi;
		this.total = total;
	}

	public ArrayList<MemberWrite> getList() {
		return list;
	}

	public void setList(ArrayList<MemberWrite> list) {
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
