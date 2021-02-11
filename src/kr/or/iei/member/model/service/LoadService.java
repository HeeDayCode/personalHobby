package kr.or.iei.member.model.service;

import java.sql.Connection;

import kr.or.iei.common.JDBCTemplate;
import kr.or.iei.member.model.dao.LoadDAO;
import kr.or.iei.member.model.vo.LoadData;

public class LoadService {
	LoadDAO lDAO = new LoadDAO();

	public LoadData Statistic() {

		Connection conn = JDBCTemplate.getConnection();
		LoadData lData = lDAO.LoadService(conn);
		JDBCTemplate.close(conn);
		return lData;
	}

}
