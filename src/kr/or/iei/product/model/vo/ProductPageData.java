package kr.or.iei.product.model.vo;

import java.util.ArrayList;

public class ProductPageData {
	private ArrayList<ProductAll> list;
	private String pageNavi;
	
	public ProductPageData() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ProductPageData(ArrayList<ProductAll> list, String pageNavi) {
		super();
		this.list = list;
		this.pageNavi = pageNavi;
	}
	public ArrayList<ProductAll> getList() {
		return list;
	}
	public void setList(ArrayList<ProductAll> list) {
		this.list = list;
	}
	public String getPageNavi() {
		return pageNavi;
	}
	public void setPageNavi(String pageNavi) {
		this.pageNavi = pageNavi;
	}

	
	
	
}
