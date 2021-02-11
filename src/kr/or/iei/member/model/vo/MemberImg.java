package kr.or.iei.member.model.vo;

import java.sql.Timestamp;

public class MemberImg {
	private int memberNo;
	private String originalFileName;
	private String changedFileName;
	private String filePath;
	private Timestamp uploadTime;
	private char delYn;
	
	
	public MemberImg() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public MemberImg(int memberNo, String originalFileName, String changedFileName, String filePath,
			Timestamp uploadTime, char delYn) {
		super();
		this.memberNo = memberNo;
		this.originalFileName = originalFileName;
		this.changedFileName = changedFileName;
		this.filePath = filePath;
		this.uploadTime = uploadTime;
		this.delYn = delYn;
	}

	public int getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}
	public String getOriginalFileName() {
		return originalFileName;
	}
	public void setOriginalFileName(String originalFileName) {
		this.originalFileName = originalFileName;
	}
	public String getChangedFileName() {
		return changedFileName;
	}
	public void setChangedFileName(String changedFileName) {
		this.changedFileName = changedFileName;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public Timestamp getUploadTime() {
		return uploadTime;
	}
	public void setUploadTime(Timestamp uploadTime) {
		this.uploadTime = uploadTime;
	}
	public char getDelYn() {
		return delYn;
	}
	public void setDelYn(char delYn) {
		this.delYn = delYn;
	}
	
	
}
