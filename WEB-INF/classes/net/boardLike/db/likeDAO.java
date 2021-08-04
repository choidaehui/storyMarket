package net.boardLike.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class likeDAO {
	
	Connection con = null;
	PreparedStatement pstmt=null;
	ResultSet rs = null;
	String sql ="";
	
	public Connection getCon() throws Exception {
		Context init = new InitialContext();
		DataSource ds = (DataSource)init.lookup("java:comp/env/jdbc/storymarketdb");
		con = ds.getConnection();
		
		return con;
	}//DB연결
	
	//DB연결해제
	public void closeDB() {
		try {
			if(rs != null) {rs.close();}
			if(pstmt != null) {pstmt.close();}
			if(con != null) {con.close();}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	//DB연결해제 끝
	
	//추천수 테이블에 저장하기 메서드
	public void like(int num) {
		System.out.println("DAO : likeDAO 호출");
		try {
			//1.2. 
			con = getCon();
			
			//sql , pstmt
			sql="update board set likeCount=likeCount+1 where num=?";
			pstmt= con.prepareStatement(sql);
			pstmt.setInt(1, num);
			
			
			System.out.println("@@@@@@@@");
			//실행
			pstmt.executeUpdate();
			System.out.println("DAO: "+ num +"번글 추천수 1증가!");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
	}

	

}


