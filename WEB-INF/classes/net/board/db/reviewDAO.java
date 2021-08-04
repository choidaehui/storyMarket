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
		DataSource ds = (DataSource)init.lookup("java:comp/env/jdbc/storymarketdb");
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
			int num = 0;
			
			//1.2디비연결
			con = getCon();
			//3. sql insert구문실행, & pstmt 객체
			sql = "insert into sy_board(num,name,pass,subject,content,readcount,"
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
			sql = "select max(num) from sy_board"; //글번호 컬럼을 선택
			pstmt=con.prepareStatement(sql);
			rs = pstmt.executeQuery(); //rs = 마지막 글번호가 저장됨
			
			if(rs.next()) {
				sql = "update sy_board set re_ref=? where num =?";
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
		sql = "select count(*) from sy_board";
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
			sql = "select * from sy_board "
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
				rb.setRating(rs.getString("rating"));
				
				//보드빈 객체 1개를 arrayList한칸에 저장
				boardList.add(rb);
				}//while
				System.out.println("DAO:getBoardList startPage,  pageSize :"+boardList);
				
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
		sql = "update sy_board set readCount=readCount+1 where num=?";
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
			sql= "select * from sy_board where num=?";
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
				rb.setRating(rs.getString("rating"));
			}
			
			System.out.println("DAO : 게시글 정보 저장 완료!" + rb);
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			closeDB();
		}
		
		return rb;
	}
	
	//특정 번호의 게시글에 대한 정보를 반환하는 메서드 bdao.getBoard(num); 끝

	//게시글 정보수정 메서드updateBoard(bb)
	public int updateBoard(reviewBean rb) {
		System.out.println("DAO : updateBoard() 실행");
		int result=-1;
		
		try {
			//1,2 디비연결
			con = getCon();
			
			//3. sql작성(select) & pstmt객체
			sql = "select pass from sy_board where num=?";
			pstmt= con.prepareStatement(sql);
			pstmt.setInt(1, rb.getNum());
			
			//4. sql실행
			rs = pstmt.executeQuery();
			
			//5. 데이터처리
			if(rs.next()) {
				if(rb.getPass().equals(rs.getString("pass"))) {
					//3. sql(update) & pstmt객체
					sql = "Update sy_board set name=?,subject=?,content=?,regionDivied=? where num=? ";
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, rb.getName());
					pstmt.setString(2, rb.getSubject());
					pstmt.setString(3, rb.getContent());
					pstmt.setString(4, rb.getRegionDivied());
					pstmt.setInt(5, rb.getNum());
					
					//4. sql실행
					pstmt.executeUpdate();
					result = 1;
					System.out.println("DAO : updateBoard() 수정완료: "+result);
				}else {
					// 글이 있지만 비밀번호 오류
					 result = 0;
				}	
					
					
			}else {
				//글이 없는 경우
				result = -1;
			}
			System.out.println("DAO : updateBoard() result : "+result);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeDB();
		}
		
		
		System.out.println("DAO : updateBoard() 실행   끝!");
		return result;
	}
	//게시글 정보수정 메서드updateBoard(bb) 끝
	
	
	
	
	public void reInsertBoard(reviewBean rb) {
		System.out.println("DAO reInsertBoard 실행");
		int num = 0;
		//1.2. 디비연결
		//3. sql 쿼리 & pstmt 객체 
		//4. sql 실행
		try {
			con = getCon();
			
			//////////////////////////////////////////////////////////////////////
			// 1. 글번호 계산
			sql="select max(num) from sy_board";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				num = rs.getInt(1) + 1;
			}
			
			System.out.println("답글 번호  : "+num);
			
			//////////////////////////////////////////////////////////////////////
			// 2. 답글 순서 재배치
			// 같은그룹 re_ref, 값중에서  re_seq 값이 기존값보다 큰게 있을경우  seq+1
			sql = "update sy_board set re_seq=re_seq+1 where re_ref=? and re_seq>?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, rb.getRe_ref());
			pstmt.setInt(2, rb.getRe_seq());
			
			int value = pstmt.executeUpdate();
			if(value>0) {
				System.out.println("DAO : 답글 재배치 완료!");
			}
			
			//////////////////////////////////////////////////////////////////////
			// 3. 답글을 등록
			sql = "insert into sy_board(num,name,pass,subject,content,readcount, "
					+ "re_ref,re_lev,re_seq,date,ip,file,rating,likeCount,regionDivied) "
					+ "values(?,?,?,?,?,?,?,?,?,now(),?,?,?,?,?) ";
			pstmt=con.prepareStatement(sql);
			
			pstmt.setInt(1, num);  //위에서 계산된 값
			pstmt.setString(2, rb.getName());
			pstmt.setString(3, rb.getPass());
			pstmt.setString(4, rb.getSubject());
			pstmt.setString(5, rb.getContent());
			pstmt.setInt(6, 0); // 조회수 0 
			pstmt.setInt(7, rb.getRe_ref()); // ref : 답글은 일반글의  ref를 저장
			pstmt.setInt(8, rb.getRe_lev()+1); // lev : 원글의  값 + 1
			pstmt.setInt(9, rb.getRe_seq()+1); // seq : 원글의 값  + 1
			pstmt.setString(10, rb.getIp());
			pstmt.setString(11, rb.getFile());
			pstmt.setString(12, rb.getRating());
			pstmt.setString(13, rb.getLikeCount());
			pstmt.setString(14, rb.getRegionDivied());
			pstmt.executeUpdate();
			
			System.out.println(" DAO :  답글 작성 완료! ");
			
			//////////////////////////////////////////////////////////////////////
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		
		
	}
	// reInsertBoard(rb)
	
	
	
	

	//게시글 삭제 메서드 deleteBoard()
	public int deleteBoard(int num, String pass) {
		System.out.println("DAO : deleteBoard실행");
		int result= -1;
		
		try {
			//1,2 디비연결
			con = getCon();
			
			//3. sql구문, pstmt작성
			sql = "select pass from sy_board where num=? ";
			pstmt = con.prepareStatement(sql);		
			pstmt.setInt(1, num);
			
			//4. sql실행
			rs = pstmt.executeQuery();
			
			//5. 정보처리 	
			if(rs.next()) {//글 있을 때
				if(pass.equals(rs.getString("pass"))) {
					//3. sql구문, pstmt작성
					sql = "delete from sy_board where num=?";
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
				SQL = "SELECT * FROM sy_board WHERE regionDivied LIKE ? AND CONCAT(name, title, content) LIKE ? ORDER BY num DESC LIMIT " + pageNumber_ * 5 + ", " + pageNumber_ * 5 + 6;
			} else if(searchType.equals("추천순")) {
				SQL = "SELECT * FROM sy_board WHERE regionDivied LIKE ? AND CONCAT(name, title, content) LIKE ? ORDER BY likeCount DESC LIMIT " + pageNumber_ * 5 + ", " + pageNumber_ * 5 + 6;
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
	
	
	
	//getBoardCount(search) -검색게시판 사용 : 검색어 해당하는 글 개수 계산
	public int getBoardCount(String search) {
		int count = 0;
		
		System.out.println("검색어 : "+search);
		
		try {
			
			
			//디비연결(1,2)
			getCon();
			
			//3. sql구문작성 pstmt
			//where subject like '%2%';
			//=> 제목중에서 [2]글자가 포함되어 있는 정보만 가져오기

			if(search.equals("all")) {// 전체
				sql = "select count(*) from sy_board";
			}else {//검색어가 있을 때
				sql = "select count(*) from sy_board where subject like ?";
			}
			pstmt = con.prepareStatement(sql);
			
			if(search.equals("all")) {
				/*all 검색어 일때는 ? 값이 필요없음*/	
			}
			else {
				pstmt.setString(1, "%"+search+"%"); //'' <- s는  setString으로 타입을 지정해주므로 붙이지 않아도 된다
			}
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
			  count = rs.getInt(1); // 1번 인덱스에 있는 값을 카운트에 저장한다.
			}
			
			System.out.println("검색된 글의 수 " +  count);
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			closeDB();
		}
		
		return count;
	}
	
	//getBoardCount(search) -검색게시판 사용 : 검색어 해당하는 글 개수 계산

	
	
	//content search 
	//페이지 요청 메소드	
	public List<reviewBean> getBoardList(int startRow, int pageSize, String search) {
		System.out.println("DAO :getBoardList(startRow, pageSize, search) 실행! ");
		List<reviewBean> boardList= new ArrayList<reviewBean>();
		
		try {
		
			con= getCon();
			
			if(search.equals("all")) {
				sql = "select * from sy_board order by re_ref desc, re_seq asc limit ?,?";
			}else {
				sql = "select * from sy_board where subject like ? order by re_ref desc, re_seq asc limit ?,?";
				
			}
			pstmt = con.prepareStatement(sql);
			
			if(search.equals("all")) {
				 pstmt.setInt(1, startRow-1); // 1번글 -> 1번인덱스 (두번째글)(x) -> 0번 인덱스(첫번째글)(o)
				 pstmt.setInt(2, pageSize);
			 }else {
				 pstmt.setString(1, "%"+search+"%");
				 pstmt.setInt(2, startRow-1); // 1번글 -> 1번인덱스 (두번째글)(x) -> 0번 인덱스(첫번째글)(o)
				 pstmt.setInt(3, pageSize);
				 
			 }
			
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
				rb.setRating(rs.getString("rating"));
				
				//보드빈 객체 1개를 arrayList한칸에 저장
				boardList.add(rb);
				//System.out.println("boardList : " + boardList);
			}	//while
			
			   System.out.println("@@@@@@@@@@@@@@@@");
			   System.out.println("DAO : SQL 구문 실행 완료! ");
			   System.out.println(" DB에 있는 모든 글을 리스트에 저장 ");
			   System.out.println(boardList); // 확인용 
			}
			catch(Exception e) {
				e.printStackTrace();
			}finally {
				closeDB();
			}
			
			return boardList;
		}
		
	
	
//	추천용 메서드

//추천수 늘리기 메서드 //특정글의 좋아요수를 1늘림
public int like(int num) {

	
	try {
		//1.2. 
		con = getCon();
		
		//sql , pstmt
		sql = "UPDATE sy_board SET likeCount=(ifnull(likeCount,0))+1 WHERE num =?" ;  
		//"UPDATE sy_board SET likeCount=likeCount+1 WHERE num = ?"; X
		// "UPDATE sy_board SET likeCount=(ifnull(likeCount,0))+1 WHERE num =?" ;  O
		
		
		pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, num);

		//값 저장
		return pstmt.executeUpdate();

	} catch (Exception e) {
		e.printStackTrace();

	} finally {
		closeDB();	
	}
	return -1;
}//추천수 늘리기 메서드 끝

//글 지우기 메서드
public int delete(String id) {

	
	try {
		//1.2. 
		con = getCon();
		
		//sql , pstmt
		sql = "DELETE FROM sy_board WHERE id = ?";
		pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, Integer.parseInt(id));

		return pstmt.executeUpdate();

	} catch (Exception e) {

		e.printStackTrace();

	} finally {
		closeDB();
	}
	return -1;
}//글 지우기 메서드


//추천인 아이디를 찾는 메서드
public String getLikeUserID(String id) {


	try {
		//1.2. 
		con = getCon();
		
		//sql , pstmt
		sql = "SELECT id FROM likecount WHERE id = ?";
		pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, Integer.parseInt(id));

		rs = pstmt.executeQuery();

		while(rs.next()) {
			return rs.getString(1);
		}

	} catch (Exception e) {

		e.printStackTrace();

	} finally {
		closeDB(); 
	}
	return null;
}//추천하기 누르면 실행되는 메서드 끝


//	추천용 메서드


//추천수 테이블에 저장하기 메서드
	public int like(String id, int num, String ip) {
		try {
			//1.2. 
			con = getCon();
			
			//sql , pstmt
			sql = "INSERT INTO LIKEY VALUES (?, ?, ?)";
			pstmt = con.prepareStatement(sql);
			
			//?
			pstmt.setString(1, id);
			pstmt.setInt(2, num);
			pstmt.setString(3, ip);
			return pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeDB();
		}
		
		return -1; // 추천 중복 오류
	}//추천수 테이블에 저장하기 메서드


	//댓글 쓰기
		public void insertComment(commentBean cb ){	
			
			//reviewBean rb = new reviewBean();
			int commentCount=0;
							
			try{
				//1.2.DB 연결
				con=getCon();
				
				//3. sql pstmt.
				//sql="select count(*) from comment";
				sql = "select max(comment_id) from comment ";
				pstmt=con.prepareStatement(sql);
				
				//4.구문실행
				rs=pstmt.executeQuery();
				
				//데이터처리 
				if(rs.next()) {
					commentCount = rs.getInt(1) + 1;
				}
				   System.out.println("DAO insertComment : "+commentCount);	
				
					sql = "insert into comment(comment_id,name,content,parentNum ) " 
							+ "values(?,?,?,? ) ";
					pstmt=con.prepareStatement(sql);
					
					pstmt.setInt(1, commentCount);
					pstmt.setString(2,cb.getName());
					pstmt.setString(3, cb.getContent());
					pstmt.setInt(4, cb.getParentNum());
					
				//4.실행
					pstmt.executeUpdate();
				
				System.out.println("DAO : insertComment완료!");
							

			}catch(Exception e){
				e.printStackTrace();
			}finally {
				closeDB();
			}
	}

		
		 //댓글 리스트 읽어오기 public ArrayList<commentBean> comment_list(String num){
		public ArrayList<commentBean> comment_list(String num){

		  ArrayList list = new ArrayList(); commentBean cb = new commentBean();
		  
		  try{ con=getCon(); pstmt = con.prepareStatement(sql);
		  
		  sql = "select * from comment where parentNum=?"; pstmt.setString(1, num);
		  //pstmt에서 게시글 번호에 맞는 댓글 찾아온다음
		  
		  rs = pstmt.executeQuery(); // sql 실행값을 rs에 저장
		  
		  while(rs.next()){
		  
		  cb.setComment_id(rs.getInt("comment_id"));
		  cb.setContent(rs.getString("content")); cb.setName(rs.getString("name"));
		  cb.setParentNum(rs.getInt("parentNum"));
		  
		  list.add(cb); } }catch(Exception e){ e.printStackTrace(); }finally {
		  closeDB(); } return list; }
		 
		




}
//new end
	
	