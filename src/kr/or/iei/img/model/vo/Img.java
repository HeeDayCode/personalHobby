package kr.or.iei.img.model.vo;

import java.sql.Timestamp;

public class Img {
	private int imgNo;
	private String originalName;
	private String changedName;
	private String imgPath;
	private Timestamp uploadTime;
	private char imgDeleteYN;
	public Img() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Img(int imgNo, String originalName, String changedName, String imgPath, Timestamp uploadTime,
			char imgDeleteYN) {
		super();
		this.imgNo = imgNo;
		this.originalName = originalName;
		this.changedName = changedName;
		this.imgPath = imgPath;
		this.uploadTime = uploadTime;
		this.imgDeleteYN = imgDeleteYN;
	}
	public int getImgNo() {
		return imgNo;
	}
	public void setImgNo(int imgNo) {
		this.imgNo = imgNo;
	}
	public String getOriginalName() {
		return originalName;
	}
	public void setOriginalName(String originalName) {
		this.originalName = originalName;
	}
	public String getChangedName() {
		return changedName;
	}
	public void setChangedName(String changedName) {
		this.changedName = changedName;
	}
	public String getImgPath() {
		return imgPath;
	}
	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}
	public Timestamp getUploadTime() {
		return uploadTime;
	}
	public void setUploadTime(Timestamp uploadTime) {
		this.uploadTime = uploadTime;
	}
	public char getImgDeleteYN() {
		return imgDeleteYN;
	}
	public void setImgDeleteYN(char imgDeleteYN) {
		this.imgDeleteYN = imgDeleteYN;
	}
	
}
