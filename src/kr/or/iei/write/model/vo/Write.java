package kr.or.iei.write.model.vo;

import java.sql.Date;

public class Write {
	private int writeNo;
	private int memberNo;
	private int productNo;
	private int writeLike;
	private Date writeDate;
	private String writeExplain;
	private int writeViewCount;
	private Date writeDeleteDate;
	private char writeDeleteYN;
	public Write() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Write(int writeNo, int memberNo, int productNo, int writeLike, Date writeDate, String writeExplain,
			int writeViewCount, Date writeDeleteDate, char writeDeleteYN) {
		super();
		this.writeNo = writeNo;
		this.memberNo = memberNo;
		this.productNo = productNo;
		this.writeLike = writeLike;
		this.writeDate = writeDate;
		this.writeExplain = writeExplain;
		this.writeViewCount = writeViewCount;
		this.writeDeleteDate = writeDeleteDate;
		this.writeDeleteYN = writeDeleteYN;
	}
	public int getWriteNo() {
		return writeNo;
	}
	public void setWriteNo(int writeNo) {
		this.writeNo = writeNo;
	}
	public int getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}
	public int getProductNo() {
		return productNo;
	}
	public void setProductNo(int productNo) {
		this.productNo = productNo;
	}
	public int getWriteLike() {
		return writeLike;
	}
	public void setWriteLike(int writeLike) {
		this.writeLike = writeLike;
	}
	public Date getWriteDate() {
		return writeDate;
	}
	public void setWriteDate(Date writeDate) {
		this.writeDate = writeDate;
	}
	public String getWriteExplain() {
		return writeExplain;
	}
	public void setWriteExplain(String writeExplain) {
		this.writeExplain = writeExplain;
	}
	public int getWriteViewCount() {
		return writeViewCount;
	}
	public void setWriteViewCount(int writeViewCount) {
		this.writeViewCount = writeViewCount;
	}
	public Date getWriteDeleteDate() {
		return writeDeleteDate;
	}
	public void setWriteDeleteDate(Date writeDeleteDate) {
		this.writeDeleteDate = writeDeleteDate;
	}
	public char getWriteDeleteYN() {
		return writeDeleteYN;
	}
	public void setWriteDeleteYN(char writeDeleteYN) {
		this.writeDeleteYN = writeDeleteYN;
	}
}
