package kr.or.iei.orders.model.vo;

import kr.or.iei.img.model.vo.Img;
import kr.or.iei.product.model.vo.Product;

public class OrdersWithImg {
	private Orders orders;
	private Product product;
	private Img img;
	
	public OrdersWithImg() {
		super();
		// TODO Auto-generated constructor stub
	}

	public OrdersWithImg(Orders orders, Product product, Img img) {
		super();
		this.orders = orders;
		this.product = product;
		this.img = img;
	}
	
	public Orders getOrders() {
		return orders;
	}
	public void setOrders(Orders orders) {
		this.orders = orders;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public Img getImg() {
		return img;
	}
	public void setImg(Img img) {
		this.img = img;
	}
}
