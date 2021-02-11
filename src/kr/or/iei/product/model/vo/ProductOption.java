package kr.or.iei.product.model.vo;

public class ProductOption {
	private int productNo;
	private String options;
	public ProductOption() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ProductOption(int productNo, String options) {
		super();
		this.productNo = productNo;
		this.options = options;
	}
	public int getProductNo() {
		return productNo;
	}
	public void setProductNo(int productNo) {
		this.productNo = productNo;
	}
	public String getOptions() {
		return options;
	}
	public void setOptions(String options) {
		this.options = options;
	}
}
