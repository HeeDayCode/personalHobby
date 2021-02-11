package kr.or.iei.member.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import kr.or.iei.common.JDBCTemplate;
import kr.or.iei.member.model.vo.LoadData;

public class LoadDAO {

	public LoadData LoadService(Connection conn) {
		PreparedStatement pstmt1 = null;
		ResultSet rset1 = null;
		PreparedStatement pstmt2 = null;
		ResultSet rset2 = null;
		LoadData lData = null;

		String query1 = "select count(*) as membercount from member_date where member_join_date like sysdate";
		String query2 = "select sum(price) from(select sum(orders_num*product_price)as price " + 
				"from orders left join product using(product_no) left join product_date using(product_no) " + 
				"where orders_pay='Y' and orders_date like sysdate group by orders_date)";

		try {
			pstmt1 = conn.prepareStatement(query1);
			rset1 = pstmt1.executeQuery();
			pstmt2 = conn.prepareStatement(query2);
			rset2 = pstmt2.executeQuery();
			lData = new LoadData();
			if (rset1.next()) {
				lData.setMemberNum(rset1.getInt("membercount"));
			}
			if (rset2.next()) {
				lData.setSalesNum(rset2.getInt("sum(price)"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset2);
			JDBCTemplate.close(pstmt2);
			JDBCTemplate.close(rset1);
			JDBCTemplate.close(pstmt1);
		}
		return lData;

	}

}
