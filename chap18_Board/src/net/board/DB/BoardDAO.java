package net.board.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class BoardDAO {
	DataSource ds;
	Connection con;
	PreparedStatement pstmt;
	String sql;
	ResultSet rs;

	public BoardDAO() {
		try {
			Context init = new InitialContext();// 사용계정, DB 등의 정보를 담아내기 위한 인스턴스
			ds = (DataSource) init.lookup("java:comp/env/jdbc/OracleDB"); // 검색한 값은 object 반환이니까 downcasting
		} catch (NamingException e) {
			e.printStackTrace();
			return;
		}
	}

	// 게시판 데이터 저장
	public boolean boardInsert(BoardDTO boardDTO) {
		sql = "select max(BOARD_NUM) from board";
		int num = 0;
		
		try {
			con = ds.getConnection();// 엥간하면 객체에 담겨져 있다
			pstmt = con.prepareStatement(sql);
			pstmt.executeQuery(sql); //select 문 이니까!
			rs = pstmt.getResultSet();
			
			if(rs.next()) {
				//boardDTO.setBOARD_NUM(rs.getInt(0));
				num = rs.getInt(1) + 1;
			}else {
				num = 1;
			}
			
			sql ="insert into board values(?,?,?,?,?,?,?,?,?,?,sysdate)"; //DATE 는 사용자입력정보 미활용..sysdate 만 있으면 된다
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.setString(2, boardDTO.getBOARD_NAME());
			pstmt.setString(3, boardDTO.getBOARD_PASS());
			pstmt.setString(4, boardDTO.getBOARD_SUBJECT());
			pstmt.setString(5, boardDTO.getBOARD_CONTENT());
			pstmt.setString(6, boardDTO.getBOARD_FILE());
			pstmt.setInt(7, 0);
			pstmt.setInt(8, 0);
			pstmt.setInt(9, 0);
			pstmt.setInt(10, 0);
			
			pstmt.executeUpdate();
			
			return true;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false; //isRedirect 와 연동위함
		} finally {
			try {
				if(con != null) {con.close();} //반환을 해서 connection 쓰겠다는 뜻.
				if(pstmt != null) {pstmt.close();}	
				if(rs != null) {rs.close();}
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
	}
	
	//읽기
	public void getBoardList() {
		
	}
}
