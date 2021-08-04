package net.board.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class boardFileDAO {

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
	
	
}
