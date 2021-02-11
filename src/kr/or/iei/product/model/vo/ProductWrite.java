package kr.or.iei.product.model.vo;

import java.util.ArrayList;

import kr.or.iei.img.model.vo.Img;
import kr.or.iei.write.model.vo.Write;
import kr.or.iei.write.model.vo.WriteTag;

public class ProductWrite {
	private Product product;
	private Write write;
	private ArrayList<WriteTag> writeTagList;
	private Img img;

	public ProductWrite() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ProductWrite(Product product, Write write, ArrayList<WriteTag> writeTagList, Img img) {
		super();
		this.product = product;
		this.write = write;
		this.writeTagList = writeTagList;
		this.img = img;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Write getWrite() {
		return write;
	}

	public void setWrite(Write write) {
		this.write = write;
	}

	public ArrayList<WriteTag> getWriteTagList() {
		return writeTagList;
	}

	public void setWriteTagList(ArrayList<WriteTag> writeTagList) {
		this.writeTagList = writeTagList;
	}

	public Img getImg() {
		return img;
	}

	public void setImg(Img img) {
		this.img = img;
	}

}
