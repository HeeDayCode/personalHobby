package kr.or.iei.write.model.vo;

import java.util.ArrayList;

public class WritePageData {
	private ArrayList<WriteListAll> list;
	private String pageNavi;
	private int totalPage;
	public WritePageData() {
		super();
		// TODO Auto-generated constructor stub
	}
	public WritePageData(ArrayList<WriteListAll> list, String pageNavi, int totalPage) {
		super();
		this.list = list;
		this.pageNavi = pageNavi;
		this.totalPage = totalPage;
	}
	public ArrayList<WriteListAll> getList() {
		return list;
	}
	public void setList(ArrayList<WriteListAll> list) {
		this.list = list;
	}
	public String getPageNavi() {
		return pageNavi;
	}
	public void setPageNavi(String pageNavi) {
		this.pageNavi = pageNavi;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	
	
}
