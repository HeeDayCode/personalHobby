package kr.or.iei.product.model.vo;

public class ProductDetail {
	private int productNo;
	private String productDescription;
	private int imgNo;
	

	public ProductDetail() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ProductDetail(int productNo, String productDescription, int imgNo) {
		super();
		this.productNo = productNo;
		this.productDescription = productDescription;
		this.imgNo = imgNo;
	}
	public int getProductNo() {
		return productNo;
	}
	public void setProductNo(int productNo) {
		this.productNo = productNo;
	}
	public String getProductDescription() {
		return productDescription;
	}
	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}
	public int getImgNo() {
		return imgNo;
	}
	public void setImgNo(int imgNo) {
		this.imgNo = imgNo;
	}
	
	
}