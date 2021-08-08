package net.member.db;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;


public class MemberDAO {
	//DB에 관련된 모든 처리를 하는 객체
	Connection con =null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String sql = "";
	
	//디비 연결
	private Connection getCon() throws Exception{
		
		//context.xml파일 불러오기
		Context init = new InitialContext();
		DataSource ds = (DataSource)init.lookup("java:comp/env/jdbc/storymarketdb");
		con=ds.getConnection();
				
				
		return con;
	}
	
	//디비 자원해제
	public void closeDB() {
		try {
			if(rs != null) {rs.close();}
			if(pstmt != null) {pstmt.close();}
			if(con != null) {con.close();}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//insertMember(mb)
	public void insertMember(MemberBean mb) {
		
		System.out.println("DAO - insertMember(mb) 실행");
		
		
		try {
			//1,2 디비연결
			con = getCon();
			//3. sql 쿼리 & pstmt객체
			sql = "insert into sy_member values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, mb.getId());
			pstmt.setString(2, mb.getName());
			pstmt.setString(3, mb.getPass());
			pstmt.setString(4, mb.getPhone());
			pstmt.setString(5, mb.getEmail());
			pstmt.setString(6, mb.getAddress());
			pstmt.setString(7, mb.getAccount());
			pstmt.setTimestamp(8, mb.getReg_date());
			pstmt.setString(9, mb.getImgname());
			pstmt.setString(10, mb.getThumbname());
			
			//4.sql구문 실행
			pstmt.executeUpdate();
			
			System.out.println("DAO - SQL 구문실행 완료! 회원가입 성공!");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("DAO - SQL 구문실행 실패! 회원가입 실패!");
		}finally {
			
			closeDB();
			
		}
		
	}
	//insertMember(mb)
	
	//loginMember(id,pass)
	public MemberBean loginMember(String id, String pass) {
		
		System.out.println("DAO - loginMember 실행");
		MemberBean mb = new MemberBean();  
		
		try {
			con=getCon();
			
			sql = "select * from sy_member where id=? and pass=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pass);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				
				mb.setId(rs.getString("id"));
				mb.setPass(rs.getString("pass"));
				mb.setPhone(rs.getString("phone"));				
				mb.setEmail(rs.getString("email"));
				mb.setAddress(rs.getString("address"));
				mb.setAccount(rs.getString("account"));			
				mb.setImgname(rs.getString("imgname"));
				mb.setThumbname(rs.getNString("thumbname"));
				
				System.out.println("id,pass 로그인 후 자바빈 저장 "+id+"로그인 했음!");
				
			}
			
		} catch (Exception e) {

			e.printStackTrace();
		}finally {
			
			closeDB();
		}
		  return mb;
	}
	
	//loginCheck(id,pass)
	public int loginCheck(String id,String pass) {
		int result = -1;
		
		try {
			//1,2 디비 연결
			con=getCon();
			
			//3 sql 작성 & pstmt 객체
			sql = "select pass from sy_member where id = ? ";
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, id);
			//4 sql실행
			rs = pstmt.executeQuery();
			
			//5 데이터 처리
			if(rs.next()) {
				//회원이다.
				if(pass.equals(rs.getString("pass"))) {
					//본인
					result=1;
				}else {
					//본인x(비밀번호 오류)
					result = 0;
				}
			}else {
				//비회원이다.
				result = -1;
			}
			System.out.println("DAO : 로그인 체크완료"+result);
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeDB();
		}
		
		return result;
	}
	//loginCheck(id,pass)
	
	//getMember(id)
	public MemberBean getMember(String id) {
		
		MemberBean mb = null;
		
		try {
			//1,2단계
			con = getCon();
			//3
			sql = "select * from sy_member where id=?";
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, id);
			
			//4
			rs = pstmt.executeQuery();
			
			//5
			if(rs.next()) {//데이터가 있을때, 저장할 객체 생성
				mb = new MemberBean();
				
				mb.setAccount(rs.getString("account"));
				mb.setAddress(rs.getString("address"));
				mb.setPhone(rs.getString("phone"));
				mb.setEmail(rs.getString("email"));
				mb.setId(rs.getString("id"));
				mb.setName(rs.getString("name"));
				mb.setPass(rs.getString("pass"));
				mb.setReg_date(rs.getTimestamp("reg_date"));
				mb.setImgname(rs.getString("imgname"));
				mb.setThumbname(rs.getString("thumbname"));
				
				
			}
			
			System.out.println("DAO : SQL 실행완료(회원정보 조회)");
			System.out.println("DAO : "+ mb);
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeDB();
		}
		
		return mb;
	}
	//getMember(id)
	
	//updateMember(updateInfoMemberBean, filename, imagePath)
	public int updateMember(MemberBean UIMB, String action_filename, String path) {
		int result = -1;
		System.out.println("DAO : "+UIMB.getId());
		String ac_filename = action_filename;
		String imagePath = path;
		
		try {
		//1.2. 드라이버 로드, 디비연결
			con = getCon();
			
			//기존 파일 지우기
			sql = "select imgname, thumbname from sy_member where id = ?";
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, UIMB.getId());
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				if(ac_filename != null) {
					
					//if(pass.equals(rs.getString("pass")))
					String oriimg = rs.getString("imgname");
					String thimg = rs.getString("thumbname");
					System.out.println("DAO : "+ oriimg);
					
					String nullimg = "member_img_sy.JPG";
					
					String filepath = imagePath+"\\"+oriimg;
					String thfilepath = imagePath+"\\"+thimg;
					
					System.out.println(filepath);
					System.out.println(thfilepath);
					
					File imgfile = new File(filepath);
					File thimgfile = new File(thfilepath);
					if(!oriimg.equals(nullimg)) {
						if(imgfile.exists()) {
							imgfile.delete();
							thimgfile.delete();
						}
					}
					
					System.out.println("DAO : imgfile 초기화");
				}
				
				
			
			}
			
		//3. sql 쿼리(select)&pstmt객체
			sql = "select * from sy_member where id = ?";
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, UIMB.getId());
			
		//4. sql 실행
			rs = pstmt.executeQuery();
			
		//5. 데이터 처리
			if(rs.next()) {
				
					//3. sql 생성
					sql = "update sy_member set name=?, pass=?, phone=?, email=?, address=?, account=?, imgname=?, thumbname=? where id = ?";
					pstmt = con.prepareStatement(sql);
					
					pstmt.setString(1, UIMB.getName());
					pstmt.setString(2, UIMB.getPass());
					pstmt.setString(3, UIMB.getPhone());
					pstmt.setString(4, UIMB.getEmail());
					pstmt.setString(5, UIMB.getAddress());
					pstmt.setString(6, UIMB.getAccount());
					pstmt.setString(7, UIMB.getImgname());
					pstmt.setString(8, UIMB.getThumbname());
					
					pstmt.setString(9, UIMB.getId());
					
					//4. sql 실행
					result = pstmt.executeUpdate();
					
				}else {
					//비밀번호 오류
					result = 0;
				}
			
			
			System.out.println("DAO : 회원정보 수정 완료! =>"+result);
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeDB();
		}
		
		return result;
	}
	//updateMember(updateInfoMemberBean, filename, imagePath)
	
	
	//deleteMember(id,pass)
	public int deleteMember(String id, String path) {
		int result = -1;
		String imagePath = path;
		
		try {
			
			//1.2. 디비연결
			con = getCon();
			//3.sql쿼리(select) & pstmt 갹체
			sql = "select imgname, thumbname from sy_member where id = ?";
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, id);
			
			//4.sql실행
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
					
					//if(pass.equals(rs.getString("pass")))
					String oriimg = rs.getString("imgname");
					String thimg = rs.getString("thumbname");
					System.out.println("DAO : "+ oriimg);
					
					String nullimg = "member_img_sy.JPG";
					
					String filepath = imagePath+"\\"+oriimg;
					String thfilepath = imagePath+"\\"+thimg;
					
					System.out.println(filepath);
					System.out.println(thfilepath);
					
					File imgfile = new File(filepath);
					File thimgfile = new File(thfilepath);
					if(!oriimg.equals(nullimg)) {
						if(imgfile.exists()) {
							imgfile.delete();
							thimgfile.delete();
						}
					}
			
				
				//3.sql쿼리(delete)
					sql = "delete from sy_member where id = ?";
					pstmt = con.prepareStatement(sql);
					
					pstmt.setString(1, id);
				//4.sql실행
					result = pstmt.executeUpdate();
					
				
			}else {
				
				result = -1;
			}
			
			System.out.println("DAO : 회원삭제 완료 -> "+result);
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeDB();
		}
		return result;
	}
	//deleteMember(id,pass)

	//duplicateIdCheck(id)
	public boolean duplicateIdCheck(String id){
        
        boolean x= false;
        
        try {
            // 쿼리                
            getCon();
            sql = "SELECT id FROM sy_member WHERE id=?";
            pstmt = con.prepareStatement(sql);
            
            pstmt.setString(1, id);
            rs = pstmt.executeQuery();
            
            if(rs.next()) x= true; //해당 아이디 존재

            
        } catch (Exception e) {
        	e.printStackTrace();
        } finally {
            closeDB();
        }
        
        return x;
    }
	//duplicateIdCheck(id)
	
	//duplicateIdCheck(id, email)
		public boolean duplicateIdCheck(String id, String email){
	        
	        boolean x= false;
	        
	        try {
	            // 쿼리                
	            getCon();
	            sql = "SELECT id FROM sy_member WHERE id=? and email=?";
	            pstmt = con.prepareStatement(sql);
	            
	            pstmt.setString(1, id);
	            pstmt.setString(2, email);
	            rs = pstmt.executeQuery();
	            
	            if(rs.next()) x= true; //해당 아이디 존재

	            
	        } catch (Exception e) {
	        	e.printStackTrace();
	        } finally {
	            closeDB();
	        }
	        
	        return x;
	    }
	//duplicateIdCheck(id, email)
	
	//duplicateEmailCheck(email)
	public boolean duplicateEmailCheck(String email){
        
        boolean x= false;
        
        try {
            // 쿼리                
            getCon();
            sql = "SELECT email FROM sy_member WHERE email=?";
            pstmt = con.prepareStatement(sql);
            
            pstmt.setString(1, email);
            rs = pstmt.executeQuery();
            
            if(rs.next()) x= true; //해당 이메일이 이미 존재

            
        } catch (Exception e) {
        	e.printStackTrace();
        } finally {
            closeDB();
        }
        
        return x;
    }
	//duplicateEmailCheck(email)
	
	//getIDPass(email)
	public MemberBean getIDPass(String email) {
		
		MemberBean mb = null;
		
		try {
			//1,2단계
			con = getCon();
			//3
			sql = "select * from sy_member where email=?";
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, email);
			
			//4
			rs = pstmt.executeQuery();
			
			//5
			if(rs.next()) {//데이터가 있을때, 저장할 객체 생성
				mb = new MemberBean();
				
				mb.setAccount(rs.getString("account"));
				mb.setAddress(rs.getString("address"));
				mb.setPhone(rs.getString("phone"));
				mb.setEmail(rs.getString("email"));
				mb.setId(rs.getString("id"));
				mb.setName(rs.getString("name"));
				mb.setPass(rs.getString("pass"));
				mb.setReg_date(rs.getTimestamp("reg_date"));
				mb.setImgname(rs.getString("imgname"));
				mb.setThumbname(rs.getString("thumbname"));
				
				
			}
			
			System.out.println("DAO : SQL 실행완료(회원정보 조회)");
			System.out.println("DAO : "+ mb);
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeDB();
		}
		
		return mb;
	}
	//getIDPass(email)
	
	// updatePassMember(UIMB)
		public int updatePassMember(MemberBean UIMB) {
		
			int result = -1;
			
			try {
				// 1.2. 드라이버 로드, 디비연결
				con = getCon();
				// 3. sql 쿼리(select) & pstmt 객체
				sql = "select id from sy_member where id = ?";
				pstmt = con.prepareStatement(sql);
				
				pstmt.setString(1, UIMB.getId());
				
				// 4. sql 실행
				rs = pstmt.executeQuery();
				// 5. 데이터 처리
				if(rs.next()) {
					
	                    //3. sql 생성
						sql = "update sy_member set pass=? where id=?";
						pstmt = con.prepareStatement(sql);
						
						pstmt.setString(1, UIMB.getPass());
						pstmt.setString(2, UIMB.getId());
						
						
						//4. sql 실행
						result = pstmt.executeUpdate();
						// -> 쿼리문으로 실행되는 구문수를 리턴
						//result = 1;
					
				}else {
					// 비회원
					 result = -1;
				}
				
				System.out.println("DAO : 회원정보 수정 완료! => "+result);

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				closeDB();
			}
			
			return result;
			
		}	
		// updatePassMember(UIMB)
		
}
