package kr.or.iei.member.model.vo;

import java.util.ArrayList;

public class MemberPageData {
	private ArrayList<Member> mList;
	private ArrayList<MemberDate> mdList;
	private ArrayList<MemberAll> mAllList;
	private String pageNavi;
	public MemberPageData() {
		super();
		// TODO Auto-generated constructor stub
	}
	public MemberPageData(ArrayList<Member> mList, ArrayList<MemberDate> mdList, ArrayList<MemberAll> mAllList,
			String pageNavi) {
		super();
		this.mList = mList;
		this.mdList = mdList;
		this.mAllList = mAllList;
		this.pageNavi = pageNavi;
	}
	public ArrayList<Member> getmList() {
		return mList;
	}
	public void setmList(ArrayList<Member> mList) {
		this.mList = mList;
	}
	public ArrayList<MemberDate> getMdList() {
		return mdList;
	}
	public void setMdList(ArrayList<MemberDate> mdList) {
		this.mdList = mdList;
	}
	public ArrayList<MemberAll> getmAllList() {
		return mAllList;
	}
	public void setmAllList(ArrayList<MemberAll> mAllList) {
		this.mAllList = mAllList;
	}
	public String getPageNavi() {
		return pageNavi;
	}
	public void setPageNavi(String pageNavi) {
		this.pageNavi = pageNavi;
	}
	
	
	
}
