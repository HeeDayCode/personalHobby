package kr.or.iei.member.model.vo;

import java.sql.Date;

public class MemberDate {

	private int memberNo;
	private Date memberJoinDate;
	private Date memberWithdrawDate;
	private char memberWithdrawYN;
	public MemberDate() {
		super();
		// TODO Auto-generated constructor stub
	}
	public MemberDate(int memberNo, Date memberJoinDate, Date memberWithdrawDate, char memberWithdrawYN) {
		super();
		this.memberNo = memberNo;
		this.memberJoinDate = memberJoinDate;
		this.memberWithdrawDate = memberWithdrawDate;
		this.memberWithdrawYN = memberWithdrawYN;
	}
	public int getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}
	public Date getMemberJoinDate() {
		return memberJoinDate;
	}
	public void setMemberJoinDate(Date memberJoinDate) {
		this.memberJoinDate = memberJoinDate;
	}
	public Date getMemberWithdrawDate() {
		return memberWithdrawDate;
	}
	public void setMemberWithdrawDate(Date memberWithdrawDate) {
		this.memberWithdrawDate = memberWithdrawDate;
	}
	public char getMemberWithdrawYN() {
		return memberWithdrawYN;
	}
	public void setMemberWithdrawYN(char memberWithdrawYN) {
		this.memberWithdrawYN = memberWithdrawYN;
	}
	
}
