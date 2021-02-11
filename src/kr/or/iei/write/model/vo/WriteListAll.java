package kr.or.iei.write.model.vo;

import java.util.ArrayList;

import kr.or.iei.member.model.vo.Member;
import kr.or.iei.product.model.vo.Product;

public class WriteListAll {
	private Member member;
	private Write write;
	private ArrayList<WriteTag> writeTagList;
	private Product product;
	public WriteListAll() {
		super();
		// TODO Auto-generated constructor stub
	}
	public WriteListAll(Member member, Write write, ArrayList<WriteTag> writeTagList, Product product) {
		super();
		this.member = member;
		this.write = write;
		this.writeTagList = writeTagList;
		this.product = product;
	}
	public Member getMember() {
		return member;
	}
	public void setMember(Member member) {
		this.member = member;
	}
	public Write getWrite() {
		return write;
	}
	public void setWrite(Write write) {
		this.write = write;
	}
	public ArrayList<WriteTag> getWriteTag() {
		return writeTagList;
	}
	public void setWriteTag(ArrayList<WriteTag> writeTagList) {
		this.writeTagList = writeTagList;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	
	
}
