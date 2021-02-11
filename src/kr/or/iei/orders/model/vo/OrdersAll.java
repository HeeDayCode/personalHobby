package kr.or.iei.orders.model.vo;

import kr.or.iei.product.model.vo.Product;

public class OrdersAll {
	private Orders orders;
	private Product product;

	
	public OrdersAll() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public OrdersAll(Orders orders, Product prodict) {
		super();
		this.orders = orders;
		this.product = prodict;
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
	
	
}
