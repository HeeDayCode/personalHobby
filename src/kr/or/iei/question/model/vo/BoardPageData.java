package kr.or.iei.question.model.vo;

import java.util.ArrayList;

public class BoardPageData {
	private ArrayList<Board> list;
	private String pageNavi;
	
	public BoardPageData() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public BoardPageData(ArrayList<Board> list, String pageNavi) {
		super();
		this.list = list;
		this.pageNavi = pageNavi;
	}
	
	public ArrayList<Board> getList() {
		return list;
	}
	public void setList(ArrayList<Board> list) {
		this.list = list;
	}
	public String getPageNavi() {
		return pageNavi;
	}
	public void setPageNavi(String pageNavi) {
		this.pageNavi = pageNavi;
	}
}
