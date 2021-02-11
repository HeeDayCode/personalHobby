package kr.or.iei.orders.model.vo;

import java.util.ArrayList;

public class OrderPageData {
	private ArrayList<OrderAll> list;
	private String pageNavi;
	private int total;
	public OrderPageData() {
		super();
		// TODO Auto-generated constructor stub
	}
	public OrderPageData(ArrayList<OrderAll> list, String pageNavi, int total) {
		super();
		this.list = list;
		this.pageNavi = pageNavi;
		this.total = total;
	}
	public ArrayList<OrderAll> getList() {
		return list;
	}
	public void setList(ArrayList<OrderAll> list) {
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
