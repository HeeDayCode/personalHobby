package kr.or.iei.member.model.vo;

public class MemberStatistics {
	private String month;
	private int mCount;
	private int pCount;
	
	public MemberStatistics() {
		super();
		// TODO Auto-generated constructor stub
	}
	public MemberStatistics(String month, int mCount, int pCount) {
		super();
		this.month = month;
		this.mCount = mCount;
		this.pCount = pCount;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public int getmCount() {
		return mCount;
	}
	public void setmCount(int mCount) {
		this.mCount = mCount;
	}
	public int getpCount() {
		return pCount;
	}
	public void setpCount(int pCount) {
		this.pCount = pCount;
	}
	

	
}
