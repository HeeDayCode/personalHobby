package kr.or.iei.member.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import kr.or.iei.common.JDBCTemplate;
import kr.or.iei.member.model.dao.MemberDAO;
import kr.or.iei.member.model.vo.Member;
import kr.or.iei.member.model.vo.MemberAddress;
import kr.or.iei.member.model.vo.MemberAll;
import kr.or.iei.member.model.vo.MemberCommentPageData;
import kr.or.iei.member.model.vo.MemberComments;
import kr.or.iei.member.model.vo.MemberHeartPageData;
import kr.or.iei.member.model.vo.MemberImg;
import kr.or.iei.member.model.vo.MemberPageData;
import kr.or.iei.member.model.vo.MemberStatistics;
import kr.or.iei.member.model.vo.MemberWrite;
import kr.or.iei.member.model.vo.MemberWritePageData;
import kr.or.iei.orders.model.vo.OrdersWithImg;

public class MemberService {

	MemberDAO mDAO = new MemberDAO();

	public boolean insertMember(Member m, MemberAddress ma) {
		Connection conn = JDBCTemplate.getConnection();
		boolean result = mDAO.insertMember(conn, m, ma);
		if (result == true) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;

	}

	public String findId(String phone, String userName) {
		Connection conn = JDBCTemplate.getConnection();
		String userId = mDAO.findId(conn, phone, userName);
		JDBCTemplate.close(conn);
		return userId;
	}

	public MemberAll loginMember(String userId, String userPw) {
		Connection conn = JDBCTemplate.getConnection();
		MemberAll mAll = mDAO.loginMember(conn, userId, userPw);
		JDBCTemplate.close(conn);
		return mAll;
	}

	public int idCheck(String userId) {
		Connection conn = JDBCTemplate.getConnection();
		int result = mDAO.idCheck(conn, userId);
		JDBCTemplate.close(conn);
		return result;
	}

	public int deleteMember(String userId) {
		Connection conn = JDBCTemplate.getConnection();
		int result = mDAO.deleteMember(conn, userId);
		if (result > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);

		return result;
	}

	public int nickCheck(String userNickname) {
		Connection conn = JDBCTemplate.getConnection();
		int result = mDAO.nickCheck(conn, userNickname);
		JDBCTemplate.close(conn);
		return result;
	}

	public int updateMember(MemberAll mAll) {
		Connection conn = JDBCTemplate.getConnection();
		int result = mDAO.updateMember(conn, mAll);
		if (result > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public String findIdPhone(String userNamePhone, String phone) {
		Connection conn = JDBCTemplate.getConnection();
		String memberId = mDAO.findIdPhone(conn, userNamePhone, phone);
		JDBCTemplate.close(conn);
		return memberId;
	}

	public String findIdEmail(String userNameEmail, String email) {
		Connection conn = JDBCTemplate.getConnection();
		String memberId = mDAO.findIdEmail(conn, userNameEmail, email);
		JDBCTemplate.close(conn);
		return memberId;
	}

	public Member findPwEmail(String userNameEmail, String userIdEmail, String email) {
		Connection conn = JDBCTemplate.getConnection();
		Member m = mDAO.findPwEmail(conn, userNameEmail, userIdEmail, email);
		JDBCTemplate.close(conn);
		return m;
	}

	public Member findPwPhone(String userNamePhone, String userIdPhone, String phone) {
		Connection conn = JDBCTemplate.getConnection();
		Member m = mDAO.findPwPhone(conn, userNamePhone, userIdPhone, phone);
		JDBCTemplate.close(conn);
		return m;
	}

	public int updateNewPw(String newPw, String userId) {
		Connection conn = JDBCTemplate.getConnection();
		int result = mDAO.updateNewPw(conn, newPw, userId);
		if (result > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);

		return result;
	}

	public int insertProfileImg(MemberImg mImg) {
		Connection conn = JDBCTemplate.getConnection();
		int result = mDAO.insertProfileImg(conn, mImg);
		if (result > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public int deleteProfileImg(int userNo, String fileName) {
		Connection conn = JDBCTemplate.getConnection();
		int result = mDAO.deleteProfileImg(conn, userNo, fileName);
		if (result > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public MemberImg selectProfileImg(int userNo) {
		Connection conn = JDBCTemplate.getConnection();
		MemberImg mImg = mDAO.selectProfileImg(conn, userNo);
		JDBCTemplate.close(conn);
		return mImg;
	}

	public MemberPageData selectAllMemberPage(int currentPage) {
		Connection conn = JDBCTemplate.getConnection();
		int recordCountPerPage = 10; // 한 페이지당 보여줄 회원 정보 수

		ArrayList<MemberAll> mAllList = mDAO.selectAllMemberPage(conn, currentPage, recordCountPerPage);

		int naviCountPerPage = 5;
		String pageNavi = mDAO.getPageNavi(conn, currentPage, recordCountPerPage, naviCountPerPage);

		MemberPageData mpd = new MemberPageData();
		mpd.setmAllList(mAllList);
		mpd.setPageNavi(pageNavi);
		JDBCTemplate.close(conn);

		return mpd;

	}

	public int memberStateUpdate(int memberNo, char memberWithdrawYN) {
		Connection conn = JDBCTemplate.getConnection();
		int result = mDAO.memberStateUpdate(conn, memberNo, memberWithdrawYN);

		if (result > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public MemberPageData memberOneListPage(int currentPage, String searchOpt, String keyword) {
		Connection conn = JDBCTemplate.getConnection();
		int recordcountPerPage = 10;
		ArrayList<MemberAll> mAllList = mDAO.memberOneListPage(conn, currentPage, recordcountPerPage, searchOpt,
				keyword);

		int naviCountPerPage = 5; // pageNavi 값이 몇개씩 보여줄 것인지
		String pageNavi = mDAO.getPageNavi(conn, currentPage, recordcountPerPage, naviCountPerPage, searchOpt, keyword);

		MemberPageData mpd = new MemberPageData();
		mpd.setmAllList(mAllList);
		mpd.setPageNavi(pageNavi);
		JDBCTemplate.close(conn);

		return mpd;
	}
	
	public ArrayList<OrdersWithImg> selectDeliveryInfo(int userNo) {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<OrdersWithImg> list=mDAO.selectDeliveryInfo(conn, userNo);
		JDBCTemplate.close(conn);
		return list;
	}

	public int updateDeliveryStatus(int orderNo) {
		Connection conn = JDBCTemplate.getConnection();
		int result=mDAO.updateDeliveryStatus(conn, orderNo);
		
		if(result>0) {
			JDBCTemplate.commit(conn);
		}
		else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		
		return result;
	}

	public ArrayList<MemberStatistics> memberStatistics() {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<MemberStatistics> mslist = mDAO.memberStatistics(conn);
		JDBCTemplate.close(conn);

		return mslist;
	}

	public ArrayList<MemberStatistics> memberStatisticsSales() {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<MemberStatistics> salesList = mDAO.memberStatisticsSales(conn);
		JDBCTemplate.close(conn);
		return salesList;
	}

	public MemberWritePageData loadWrite(int memberNo, int page1) {
		Connection conn = JDBCTemplate.getConnection();
		int recordCountPerPage = 6;
		ArrayList<MemberWrite> list = mDAO.loadWritePage(conn, memberNo, page1, recordCountPerPage);
		int naviCountPerPage = 5;
		String pageNavi = mDAO.loadWritePageNavi(conn, page1, recordCountPerPage, naviCountPerPage, memberNo);
		int total = mDAO.loadWriteTotalCount(conn, memberNo);
		MemberWritePageData mwpd = new MemberWritePageData();
		mwpd.setList(list);
		mwpd.setPageNavi(pageNavi);
		mwpd.setTotal(total);
		return mwpd;
	}

	public MemberCommentPageData loadComment(int memberNo, int page2) {
		Connection conn = JDBCTemplate.getConnection();
		int recordCountPerPage = 10;
		ArrayList<MemberComments> list = mDAO.loadCommentPage(conn, memberNo, page2, recordCountPerPage);
		int naviCountPerPage = 5;
		String pageNavi = mDAO.loadCommentPageNavi(conn, page2, recordCountPerPage, naviCountPerPage, memberNo);
		int total = mDAO.loadCommentsTotalCount(conn, memberNo);
		MemberCommentPageData mcpd = new MemberCommentPageData();

		mcpd.setList(list);
		mcpd.setPageNavi(pageNavi);
		mcpd.setTotal(total);
		return mcpd;

	}

	public MemberHeartPageData loadHeart(int memberNo, int page3) {
		Connection conn = JDBCTemplate.getConnection();
		int recordCountPerPage = 10;
		ArrayList<MemberWrite> list = mDAO.loadHeartPage(conn, memberNo, page3, recordCountPerPage);
		int naviCountPerPage = 5;
		String pageNavi = mDAO.loadHeartPageNavi(conn, page3, recordCountPerPage, naviCountPerPage, memberNo);
		int total = mDAO.loadHeartTotalCount(conn, memberNo);
		MemberHeartPageData mhpd = new MemberHeartPageData();
		mhpd.setList(list);
		mhpd.setPageNavi(pageNavi);
		mhpd.setTotal(total);
		return mhpd;

	}

}
