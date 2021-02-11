package kr.or.iei.write.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import kr.or.iei.common.JDBCTemplate;
import kr.or.iei.write.model.dao.CommentsDAO;
import kr.or.iei.write.model.vo.CommentsMember;
import kr.or.iei.write.model.vo.CommentsPageData;
import kr.or.iei.write.model.vo.WriteListAll;
import kr.or.iei.write.model.vo.WritePageData;

public class CommentsService {
	CommentsDAO cDAO = new CommentsDAO();

	public CommentsPageData selectAllCommentsPage(int currentPage) {
		Connection conn = JDBCTemplate.getConnection();
		int recordCountPerPage = 10;
		ArrayList<CommentsMember> list = cDAO.selectAllCommentsPage(conn, currentPage, recordCountPerPage);
		int naviCountPerPage = 5;
		String pageNavi = cDAO.getPageNavi(conn, currentPage, recordCountPerPage, naviCountPerPage);
		int total = cDAO.commentsTotalCount(conn);
		CommentsPageData cpd = new CommentsPageData();
		cpd.setList(list);
		cpd.setPageNavi(pageNavi);
		cpd.setTotal(total);
		JDBCTemplate.close(conn);
		return cpd;
	}

	public CommentsPageData searchCommentsPage(int currentPage, String commentsSelectOpt, String keyword) {
		Connection conn = JDBCTemplate.getConnection();
		int recordcountPerPage = 10;
		int naviCountPerPage = 5; // pageNavi 값이 몇개씩 보여줄 것인지 String pageNavi
		ArrayList<CommentsMember> list = null;
		String CommentsOpt = "";
		String MemberOpt = "";
		String pageNavi = "";
		int total = 0;
		if (commentsSelectOpt.equals("comments_text") || commentsSelectOpt.equals("comments_date") || commentsSelectOpt.equals("write_no")) {
			CommentsOpt = commentsSelectOpt;
			list = cDAO.searchCommentsC(conn, currentPage, recordcountPerPage, CommentsOpt, keyword);
			pageNavi = cDAO.getPageNaviComments(conn, currentPage, recordcountPerPage, naviCountPerPage, CommentsOpt,
					keyword);
			total = cDAO.commentsTotalCountComments(conn, keyword, CommentsOpt);
		} else if (commentsSelectOpt.equals("member_Id") || commentsSelectOpt.equals("member_Nickname")
				|| commentsSelectOpt.equals("member_Name")) {
			MemberOpt = commentsSelectOpt;
			list = cDAO.searchCommentsM(conn, currentPage, recordcountPerPage, MemberOpt, keyword);
			pageNavi = cDAO.getPageNaviMember(conn, currentPage, recordcountPerPage, naviCountPerPage, MemberOpt,
					keyword);
			total = cDAO.commentsTotalCountMember(conn, keyword, MemberOpt);
		}

		CommentsPageData cpd = new CommentsPageData();
		cpd.setList(list);
		cpd.setPageNavi(pageNavi);
		cpd.setTotal(total);
		JDBCTemplate.close(conn);
		return cpd;
	}

	public int CommentsDelete(int commentNo) {
		Connection conn = JDBCTemplate.getConnection();
		int result = cDAO.CommentsDelete(conn, commentNo);
		if (result > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);

		return result;
	}

}
