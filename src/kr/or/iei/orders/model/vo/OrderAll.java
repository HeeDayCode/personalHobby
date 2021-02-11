package kr.or.iei.orders.model.vo;


import kr.or.iei.member.model.vo.Member;
import kr.or.iei.product.model.vo.Product;

public class OrderAll {
	private Member m;
	private Product p;
	private Orders o;
	public OrderAll() {
		super();
		// TODO Auto-generated constructor stub
	}
	public OrderAll(Member m, Product p, Orders o) {
		super();
		this.m = m;
		this.p = p;
		this.o = o;
	}
	public Member getM() {
		return m;
	}
	public void setM(Member m) {
		this.m = m;
	}
	public Product getP() {
		return p;
	}
	public void setP(Product p) {
		this.p = p;
	}
	public Orders getO() {
		return o;
	}
	public void setO(Orders o) {
		this.o = o;
	}
	
	
	
}
