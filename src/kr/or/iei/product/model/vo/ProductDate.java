package kr.or.iei.product.model.vo;

import java.sql.Date;

public class ProductDate {
	private int productNo;
	private Date productEnrollDate;
	private Date productDeleteDate;
	private char productYN;
	public ProductDate() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ProductDate(int productNo, Date productEnrollDate, Date productDeleteDate, char productYN) {
		super();
		this.productNo = productNo;
		this.productEnrollDate = productEnrollDate;
		this.productDeleteDate = productDeleteDate;
		this.productYN = productYN;
	}
	public int getProductNo() {
		return productNo;
	}
	public void setProductNo(int productNo) {
		this.productNo = productNo;
	}
	public Date getProductEnrollDate() {
		return productEnrollDate;
	}
	public void setProductEnrollDate(Date productEnrollDate) {
		this.productEnrollDate = productEnrollDate;
	}
	public Date getProductDeleteDate() {
		return productDeleteDate;
	}
	public void setProductDeleteDate(Date productDeleteDate) {
		this.productDeleteDate = productDeleteDate;
	}
	public char getProductYN() {
		return productYN;
	}
	public void setProductYN(char productYN) {
		this.productYN = productYN;
	}
	
}