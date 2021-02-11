package kr.or.iei.write.model.vo;

import java.util.ArrayList;

import kr.or.iei.img.model.vo.Img;

public class WriteAll {
	private Write write;
	private ArrayList<WriteTag> writeTagList;
	private Img img;
	public WriteAll() {
		super();
		// TODO Auto-generated constructor stub
	}
	public WriteAll(Write write, ArrayList<WriteTag> writeTagList, Img img) {
		super();
		this.write = write;
		this.writeTagList = writeTagList;
		this.img = img;
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
