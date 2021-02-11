package kr.or.iei.write.model.vo;

public class WriteImage {
	private int writeNo;
	private int imgNo;
	public WriteImage() {
		super();
		// TODO Auto-generated constructor stub
	}
	public WriteImage(int writeNo, int imgNo) {
		super();
		this.writeNo = writeNo;
		this.imgNo = imgNo;
	}
	public int getWriteNo() {
		return writeNo;
	}
	public void setWriteNo(int writeNo) {
		this.writeNo = writeNo;
	}
	public int getImgNo() {
		return imgNo;
	}
	public void setImgNo(int imgNo) {
		this.imgNo = imgNo;
	}
	
}
