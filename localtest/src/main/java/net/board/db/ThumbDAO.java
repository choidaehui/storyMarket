package net.board.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ThumbDAO {
	
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
			
			//insertFile(tb)
			public void insertFile(ThumbBean tb) {
				System.out.println("DAO : insertFile(tb) 호출");
				//게시판 글번호저장
				int bno = 0;
				try {
					//디비연결메서드 호출(1,2)
					getCon();
					
					//3.sql 구문작성(insert)& pstmt객체생성
					sql ="insert into thumbs values(?,?)";
					
					pstmt = con.prepareStatement(sql);
		
					pstmt.setString(1, tb.getImgname());
					pstmt.setString(2, tb.getThumbname());

					
					//4.sql구문 실행
					pstmt.executeUpdate();
					System.out.println("DAO : SQL구문 실행완료!");
					
				} catch (Exception e) {
					e.printStackTrace();
				}finally {
					closeDB();
				}
			}
			//insertFile(tb)
			
			//getThumbList()-글정보 전부를 가지고 오기 (리턴)
			public List<ThumbBean> getThumbList(){
				System.out.println("DAO : getThumbList() 호출");
				
				List<ThumbBean> thumbList = new ArrayList<ThumbBean>();
				
				//디비연결(1,2)
				try {
					getCon();
					
				
				//3. sql 구문 & pstmt객체 생성
					sql = "select * from Thumbs";
					pstmt = con.prepareStatement(sql);
				//4. sql실행
					rs = pstmt.executeQuery();
				//5.데이터 처리 (DB -> ThumbBean -> List)
					//if 문이아니라 계속 반복해야해서 while문을 사용해야함
					while(rs.next()) {
						ThumbBean tb = new ThumbBean();
						
						//글하나의 정보를 BoardBean 저장
						tb.setImgname(rs.getString("imgname"));
						tb.setThumbname(rs.getString("thumbname"));
					
						
						//글 하나 정보를 저장 완료
						//->리스트 한칸에 저장
						thumbList.add(tb);
						
						System.out.println("이미지의 정보를 리스트 한칸에 저장 완료!");
					}//while
					
					System.out.println("DAO : SQL 구문실행 완료!");
					System.out.println("DB에 있는 모든 이미지를 리스트에 저장");
					System.out.println(thumbList); //확인용
				} catch (Exception e) {
					e.printStackTrace();
				}finally {
					closeDB();
				}
				return thumbList;
			}
			//getBoardList()-글정보 전부를 가지고 오기 (리턴)
}
