package net.board.db;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.omg.CORBA.REBIND;

import net.board.db.reviewBean;

public class reviewDAO {
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String sql = "";
	
	//DB연결
	private Connection getCon() throws Exception {
		Context init = new InitialContext();
		DataSource ds = (DataSource)init.lookup("java:comp/env/jdbc/stroymarketdb");
		con = ds.getConnection();
		
		return con;
	}//DB연결 끝
	
	//DB자원해제
	public void closeDB() {
		try {
			if(rs != null) {rs.close();}
			if(pstmt != null) {pstmt.close();}
			if(con != null) {con.close();}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	//DB자원해제 끝
	
	//insertBoard(bb);
	public void inserBoard(reviewBean rb) {
		try {
			
			//1.2디비연결
			con = getCon();
			//3. sql insert구문실행, & pstmt 객체
			sql = "insert into board(num,name,pass,subject,content,readcount,"
					+ "re_ref,re_lev,re_seq,date,ip,file,rating,likeCount,regionDivied)"
					+ "values(null,?,?,?,?,?,?,?,?,now(),?,?,?,?,?)";
			pstmt=con.prepareStatement(sql);

			pstmt.setString(1, rb.getName());
			pstmt.setString(2, rb.getPass());
			pstmt.setString(3, rb.getSubject());
			pstmt.setString(4, rb.getContent());
			pstmt.setInt(5, 0); //조회수는 0으로 처리 : readcount
			pstmt.setInt(6, 0); //re_ref => 글쓰기 동작 이후에 UPDATE 하도록 처리
			pstmt.setInt(7, 0); //re_lev
			pstmt.setInt(8, 0); // re_seq
			pstmt.setString(9,rb.getIp());
			pstmt.setString(10, rb.getFile());
			pstmt.setString(11, rb.getRating());
			pstmt.setString(12, rb.getLikeCount());
			pstmt.setString(13, rb.getRegionDivied());
			
			pstmt.executeUpdate();
			//4. sql 실행
			System.out.println("DAO : 글쓰기 완료!");
			
			//re_ref 값 수정! -> 해당 글번호의 정보로 수정
			sql = "select max(num) from board"; //글번호 컬럼을 선택
			pstmt=con.prepareStatement(sql);
			rs = pstmt.executeQuery(); //rs = 마지막 글번호가 저장됨
			
			if(rs.next()) {
				sql = "update board set re_ref=? where num =?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, rs.getInt(1));
				pstmt.setInt(2, rs.getInt(1));
				
				pstmt.executeUpdate();
				System.out.println("DAO : re_ref 수정완료!");
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			closeDB();
		}
		
		
		
		//4. sql 실행
	}//insertBoard(bb);
	
	
	
	//게시판의 전체 글 개수 계산 매서드 getBoardCount()
	public int getBoardCount() {
		int result = 0;
		
		try {
		//1.2. 디비연걸
		con = getCon();
		//3.sql구문 & pstmt
		sql = "select count(*) from board";
		pstmt=con.prepareStatement(sql);
		
		//4. sql구문 실행!
		rs = pstmt.executeQuery();		//셀렉트 결과를  rs에 담아주자.
		
		//5.데이터처리
		if(rs.next()) {
			result = rs.getInt(1); //방금 만든 쿼리의 첫번째 컬럼 == rs.getInt("count(*)");
		}
		
		System.out.println("DAO : 글개수 계산 완료 -> "+ result+"개");
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			closeDB();
		}
		
		return result;
	}//게시판의 전체 글 개수 계산 매서드 getBoardCount() 끝
	
	
	//원하는 만큼 글 정보 글고오기 bdao.getBoardList(startPage, pageSize);
		public ArrayList getBoardList(int startPage, int pageSize) {
			ArrayList boardList= new ArrayList();
			
			
			try {
			//1.2 디비연결
			con = getCon();
			//3. sql작성& pstmt
			//정렬 re_ref(내림차순), re_seq(오름차순)
			//글잘라서 가져오기 limit
			sql = "select * from board "
					+"order by re_ref desc, re_seq asc "
					+"limit ?,?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, startPage-1);//시작행 -1
			pstmt.setInt(2, pageSize);//개수
			
			//4. 구문실행
			rs= pstmt.executeQuery();
			
			//5. 데이터처리
			while(rs.next()) {
				//DB1줄 -> BoardBean객체 1개에 담기
				reviewBean rb = new reviewBean();
				
				rb.setContent(rs.getString("content"));
				rb.setDate(rs.getDate("date"));
				rb.setFile(rs.getString("file"));
				rb.setIp(rs.getString("ip"));
				rb.setName(rs.getString("name"));
				rb.setNum(rs.getInt("num"));
				rb.setPass(rs.getString("pass"));
				rb.setRe_lev(rs.getInt("re_lev"));
				rb.setRe_ref(rs.getInt("re_ref"));
				rb.setRe_seq(rs.getInt("re_seq"));
				rb.setReadcount(rs.getInt("readcount"));
				rb.setSubject(rs.getString("subject"));
				rb.setLikeCount(rs.getString("likeCount"));
				rb.setRegionDivied(rs.getString("regionDivied"));
				
				//보드빈 객체 1개를 arrayList한칸에 저장
				boardList.add(rb);
				}//while
				System.out.println();
				
			}catch(Exception e){
				e.printStackTrace();
			return null;
		}finally {
			closeDB();
		}
			return boardList;
		}
		//원하는 만큼 글 정보 글고오기 bdao.getBoardList(startPage, pageSize);
		
	
	// 특정 게시글의 조회수를 증가시키는 메서드 updateReadCount(num);
	public void updateReadCount(int num) {
		//1.2. 디비연결
		try {
		con=getCon();
		
		//3.slq쿼리 &pstmt  객체
		//조회수 1증가
		sql = "update board set readCount=readCount+1 where num=?";
		pstmt= con.prepareStatement(sql);
		pstmt.setInt(1, num);
		
		
		//4. sql 실행
		pstmt.executeUpdate();
		
		System.out.println("DAO: "+ num +"번글 조회수 1증가!");
		
		}catch(Exception e) {
			e.printStackTrace();
		}finally{
			closeDB();
		}//
	}
	//특정 게시글의 조회수를 증가시키는 메서드 updateReadCount(num); 끝

	//특정 번호의 게시글에 대한 정보를 반환하는 메서드 bdao.getBoard(num);
	public reviewBean getBoard(int num) {
		
		reviewBean rb= null;
		
		try {
		//1,2 디비연결
			con= getCon();
		//3.  sql pstmt
			sql= "select * from board where num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
							
		//4 sql실행  //rs=한줄의 글 정보가 들어있거나...안들어있거나
			rs = pstmt.executeQuery();
			
		//5.데이터처리
			if(rs.next()) {
				rb = new reviewBean(); //객체를 생성하여 정보를 담아준다. 
				
				rb.setContent(rs.getString("content"));
				rb.setDate(rs.getDate("date"));
				rb.setFile(rs.getString("file"));
				rb.setIp(rs.getString("ip"));
				rb.setName(rs.getString("name"));
				rb.setNum(rs.getInt("num"));
				rb.setPass(rs.getString("pass"));
				rb.setRe_lev(rs.getInt("re_lev"));
				rb.setRe_ref(rs.getInt("re_ref"));
				rb.setRe_seq(rs.getInt("re_seq"));
				rb.setReadcount(rs.getInt("readcount"));
				rb.setSubject(rs.getString("subject"));
				rb.setLikeCount(rs.getString("likeCount"));
				rb.setRegionDivied(rs.getString("regionDivied"));
			}
			
			System.out.println("DAO : 게시글 정보 저장 완료!" + rb);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return rb;
	}
	
	//특정 번호의 게시글에 대한 정보를 반환하는 메서드 bdao.getBoard(num); 끝

	//게시글 정보수정 메서드updateBoard(bb)
	public int updateBoard(reviewBean rb) {
		System.out.println("DAO : updateBoard() 실행");
		int result=1;
		
		try {
			//1,2 디비연결
			con = getCon();
			
			//3. sql작성(select) & pstmt객체
			sql = "select pass from board where num=?";
			pstmt= con.prepareStatement(sql);
			pstmt.setInt(1, rb.getNum());
			
			//4. sql실행
			rs = pstmt.executeQuery();
			
			//5. 데이터처리
			if(rs.next()) {
				if(rb.getPass().equals(rs.getString("pass"))) {
					//3. sql(update) & pstmt객체
					sql = "update board set name=?,subject=?,content=? likeCount=? where num=?";
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, rb.getName());
					pstmt.setString(2, rb.getSubject());
					pstmt.setString(3, rb.getContent());
					pstmt.setInt(4, rb.getNum());
					pstmt.setString(5, rb.getLikeCount());
					
					//4. sql실행
					pstmt.executeUpdate();
					result = 1;
					
					
				}else {
					// 글이 있지만 비밀번호 오류
					 result = 0;
				}	
					
					
			}else {
				//글이 없는 경우
				result = -1;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return result;
	}
	//게시글 정보수정 메서드updateBoard(bb) 끝

	//게시글 삭제 메서드 deleteBoard()
	public int deleteBoard(int num, String pass) {
		System.out.println("DAO : deleteBoard실행");
		int result= -1;
		
		try {
			//1,2 디비연결
			con = getCon();
			
			//3. sql구문, pstmt작성
			sql = "select pass from board where num=? ";
			pstmt = con.prepareStatement(sql);		
			pstmt.setInt(1, num);
			
			//4. sql실행
			rs = pstmt.executeQuery();
			
			//5. 정보처리 	
			if(rs.next()) {//글 있을 때
				if(pass.equals(rs.getString("pass"))) {
					//3. sql구문, pstmt작성
					sql = "delete from board where num=?";
					pstmt = con.prepareStatement(sql);
					pstmt.setInt(1, num);
					
					//4. sql실행
					result = pstmt.executeUpdate(); // 적용된 줄 수가 "1" 이므로 적용가능
					
				}else {//비밀번호 오류
					result= 0;
					
				}
				
			}else {//글 없을때
				result= -1;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		
		return result;
	}
	
	//게시글 삭제 메서드 deleteBoard() 끝
	public ArrayList<reviewBean> getList(String regionDivied, String searchType, String search, int pageNumber_) {

		if(regionDivied.equals("전체")) {
			regionDivied = "";
		}

		ArrayList<reviewBean> list = null;
		PreparedStatement pstmt = null;
		String SQL = "";

		try {
			if(searchType.equals("최신순")) {
				SQL = "SELECT * FROM board WHERE regionDivied LIKE ? AND CONCAT(name, title, content) LIKE ? ORDER BY num DESC LIMIT " + pageNumber_ * 5 + ", " + pageNumber_ * 5 + 6;
			} else if(searchType.equals("추천순")) {
				SQL = "SELECT * FROM board WHERE regionDivied LIKE ? AND CONCAT(name, title, content) LIKE ? ORDER BY likeCount DESC LIMIT " + pageNumber_ * 5 + ", " + pageNumber_ * 5 + 6;
			}

			pstmt = con.prepareStatement(SQL);

			pstmt.setString(1, "%" + regionDivied + "%");
			pstmt.setString(2, "%" + search + "%");
			rs = pstmt.executeQuery();

			list = new ArrayList<reviewBean>();
			
			while(rs.next()) {
				reviewBean rb = new reviewBean(); 
				pstmt.setInt(1, rb.getNum());	
				pstmt.setString(2, rb.getName());
				pstmt.setString(3, rb.getPass());
				pstmt.setString(4, rb.getSubject());
				pstmt.setString(5, rb.getContent());
				pstmt.setInt(6, rb.getReadcount());
				pstmt.setInt(7, rb.getRe_ref());
				pstmt.setInt(8, rb.getRe_lev());
				pstmt.setInt(9, rb.getRe_seq());
				pstmt.setString(10, rb.getIp());
				pstmt.setString(11, rb.getFile());
				pstmt.setString(12, rb.getLikeCount());
				pstmt.setString(13, rb.getRegionDivied());
				
				
				
				list.add(rb);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		return list;
	}//게시글 검색 메서드 
	
	
	//content search 
	//페이지 요청 메소드	
	public ArrayList getBoardList(String field, String query, int page) {
		System.out.println("DAO :getBoardList(field,query,page) 실행! ");
		ArrayList boardList= new ArrayList();
	
		
		        sql = "select * from (" +
				 " 	select ROWNUM num, n.* " +
				 "  from (select * from board where "+field+" LIKE ? order by date desc) N" +
				 ") " +
				 "where num between ? and ?";
		
		
		try {
		
			con= getCon();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%"+query+"%");
			pstmt.setInt(2, 1+(page-1)*10);
			pstmt.setInt(3, page*10);
			
			rs=pstmt.executeQuery();
			
			//5. 데이터처리
			while(rs.next()) {
				//DB1줄 -> BoardBean객체 1개에 담기
				reviewBean rb = new reviewBean();
				
				rb.setContent(rs.getString("content"));
				rb.setDate(rs.getDate("date"));
				rb.setFile(rs.getString("file"));
				rb.setIp(rs.getString("ip"));
				rb.setName(rs.getString("name"));
				rb.setNum(rs.getInt("num"));
				rb.setPass(rs.getString("pass"));
				rb.setRe_lev(rs.getInt("re_lev"));
				rb.setRe_ref(rs.getInt("re_ref"));
				rb.setRe_seq(rs.getInt("re_seq"));
				rb.setReadcount(rs.getInt("readcount"));
				rb.setSubject(rs.getString("subject"));
				rb.setLikeCount(rs.getString("likeCount"));
				rb.setRegionDivied(rs.getString("regionDivied"));
				
				//보드빈 객체 1개를 arrayList한칸에 저장
				boardList.add(rb);
				System.out.println("boardList : " + boardList);
			}	
			}//while
			
			catch(Exception e) {
				e.printStackTrace();
			}finally {
				closeDB();
			}
			
			return boardList;
		}
		
	}
	//new end

	
	
	
	