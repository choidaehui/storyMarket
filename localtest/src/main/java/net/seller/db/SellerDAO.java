package net.seller.db;

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

public class SellerDAO {
	
   Connection con = null;
   PreparedStatement pstmt = null;
   ResultSet rs = null;
   String sql = "";
  
   
   private Connection getCon() throws Exception{
	   
	   Context init = new InitialContext();
	   DataSource ds = (DataSource)init.lookup("java:comp/env/jdbc/storymarketdb");
	   
		   
		con = ds.getConnection();
		
	
	   
	   System.out.println("DAO : 디비연결완료"+con);
	   
	   return con;
	   
   }
   
   public void closeDB() {
	
	   
	  try { 
	   if(rs != null) {
		   rs.close();
		   
	   }
	   if(pstmt != null) {
		   
		   pstmt.close();
	   }
	   if (con != null) {
		   
		   con.close();
	   }
	  }catch (SQLException e) {
		  
		  e.printStackTrace();
	  }
	   
	  
   }

   //maxNum()
   
   public int maxNum() {
	   
	   int seller_num = 0;
	   
	   try {
		   
			  con = getCon();	
			  
			   //seller_num 값 수정
			   sql = "select max(seller_num) from sy_seller";
			   pstmt = con.prepareStatement(sql);
			   rs = pstmt.executeQuery();
			   
			   if(rs.next()) {
				   
				   seller_num = rs.getInt(1)+1;
			   }
		  
	   }catch (Exception e) {
            
	        e.printStackTrace();
	   }finally {
		   
		   closeDB();
	   }
	   
	   return  seller_num;
   }
   
   
   
   //sellerBean이용 판매자홍보정보 데이터베이스 저장
   
   public void insertSeller(SellerBean sb) {
	   
	    int seller_num =  maxNum();    
	   
	   
	   System.out.println("DAO : insertSeller(sb)호출!!");
	   System.out.println("DAO: seller_num 값"+seller_num);
	   
	  
	  
	   
	   try {
		   
			  con = getCon(); 
		   
		      sql = "insert into sy_seller(seller_num,seller_id,image,copy_text,file,message) "
				   + "values(?,?,?,?,?,?)";
		  
		   pstmt = con.prepareStatement(sql);
		   pstmt.setInt(1, seller_num); 
		   pstmt.setString(2, sb.getSeller_id());
		   pstmt.setString(3, sb.getImage());
		   pstmt.setString(4, sb.getCopy_text());
		   pstmt.setString(5, sb.getFile());
		   pstmt.setString(6, sb.getMessage());		
		   
		   pstmt.executeUpdate();
		   

		   System.out.println("DAO : 판매자 글쓰기 완료!");
		   
		   //유저데이터베이스와 판매자 데이터 베이스가 같다.
		   
		       con = getCon();
		      
		       sql = "insert into sy_user(user_num,user_id,s_image,s_copy_text,s_file,s_message) "
		    		+ "values(?,?,?,?,?,?)";
		      
		       pstmt = con.prepareStatement(sql);
		       
		       pstmt.setInt(1, seller_num); 
			   pstmt.setString(2, sb.getSeller_id());
			   pstmt.setString(3, sb.getImage());
			   pstmt.setString(4, sb.getCopy_text());
			   pstmt.setString(5, sb.getFile());
			   pstmt.setString(6, sb.getMessage());		
			   
			   pstmt.executeUpdate();
			   
			   System.out.println("DAO : 유저 사용정보 등록!");
		   
		  
		   
		   }catch (Exception e) {

		     e.printStackTrace();
		   }finally {
			
			   closeDB();
		}

	   }
	 
	  //listseller(sb) = getBoard(num)
     
      public SellerBean getList(int seller_num) {
    	  
			   SellerBean sb = null; 
    	    
    	  try {
    		  
    		  con = getCon();
    		  
    		  sql = "select * from sy_seller where seller_num=? ";
    		  pstmt = con.prepareStatement(sql);
    		  pstmt.setInt(1, seller_num);
    		  
    		  rs = pstmt.executeQuery();
    		  
    		  
    		  if(rs.next()) {
    			  
    			  sb = new SellerBean();
    			  
    			  sb.setSeller_num(rs.getInt("seller_num"));
    			  sb.setSeller_id(rs.getString("seller_id"));
    			  sb.setImage(rs.getString("image"));
    			  sb.setCopy_text(rs.getString("copy_text"));
    			  sb.setFile(rs.getString("file"));
    			  sb.setMessage(rs.getString("message"));
    			
    		  }
    		  
    		  System.out.println("DAO : 판매자 애장품 홍보 저장완료!");
    		 
    		  
    	  }catch(Exception e) {
    		  
    		  e.printStackTrace();
    	  }finally {
    		  
    		  closeDB();
    	  }
    	  
    	  System.out.println(sb);
    	  
    	     return sb;  
 
      }
      
      public List<SellerBean> getMainList(){
    	  
    	  List<SellerBean> mainList = new ArrayList<SellerBean>();
    	  
    	  try {
    		  
			con = getCon();
			sql = "select * from sy_seller order by seller_num desc ";
			pstmt = con.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				SellerBean sb = new SellerBean();
				
				sb.setSeller_num(rs.getInt("seller_num"));
				sb.setSeller_id(rs.getString("seller_id"));
				sb.setImage(rs.getString("image"));
				sb.setCopy_text(rs.getString("copy_text"));
				sb.setFile(rs.getString("file"));
				sb.setMessage(rs.getString("message"));
				
				mainList.add(sb);
	
			}
			 System.out.println("DAO : 회원목록저장완료!");
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {			
			closeDB();
		}
    	  
    	  return mainList;
    	  
      }
      
      
      public List<SellerBean> sellerItemList(String seller_id) {
    	  
    	    List<SellerBean> sellerList = new ArrayList<SellerBean>();
    	    
    	    try {
				
    	    	con = getCon();
    	    	
    	    	sql = "select * from sy_seller where seller_id=? order by seller_num desc ";
    	    	pstmt = con.prepareStatement(sql);   	    	
    	    	pstmt.setString(1, seller_id);
     	    	rs = pstmt.executeQuery();
     	    	
    	    	while(rs.next()) {
    	    		
    	    		SellerBean sb = new SellerBean();
    				
    				sb.setSeller_num(rs.getInt("seller_num"));
    				sb.setSeller_id(rs.getString("seller_id"));
    				sb.setImage(rs.getString("image"));
    				sb.setCopy_text(rs.getString("copy_text"));
    				sb.setFile(rs.getString("file"));
    				sb.setMessage(rs.getString("message"));
    				
    				sellerList.add(sb);
  		
    	    	}
    	    	
    	    	   System.out.println("DAO : 판매자 한 명의 애장품 목록 저장");
			} catch (Exception e) {
				
				e.printStackTrace();
			}finally {
				
				closeDB();
			}
    	  
    	     return sellerList;
    	  
      }
   
   
   
   public List<SellerBean> notSellerItemList(String seller_id) {
	  
      List<SellerBean> notSellerList = new ArrayList<SellerBean>();
    
       try {
		
    	  con = getCon();
    	
    	  sql = "select * from sy_seller where seller_id not in (?) order by seller_num desc ";
    	  pstmt = con.prepareStatement(sql);     	 
    	  pstmt.setString(1, seller_id);
	      rs = pstmt.executeQuery();
	    	
    	while(rs.next()) {
    		
    		SellerBean sb = new SellerBean();
			
			sb.setSeller_num(rs.getInt("seller_num"));
			sb.setSeller_id(rs.getString("seller_id"));
			sb.setImage(rs.getString("image"));
			sb.setCopy_text(rs.getString("copy_text"));
			sb.setFile(rs.getString("file"));
			sb.setMessage(rs.getString("message"));
			
			notSellerList.add(sb);
	
    	}
    	
    	   System.out.println("DAO : 판매자 한 명의 애장품 목록 저장");
	} catch (Exception e) {
		
		e.printStackTrace();
	}finally {
		
		closeDB();
	}
  
     return notSellerList;
  
}
   
   public int getSearchCount(String search) {
		int count = 0;
		
		System.out.println("검색어 : "+search);
		
		try {
			
			
			//디비연결(1,2)
			con = getCon();
			
			//3. sql구문작성 pstmt
			//where subject like '%2%';
			//=> 제목중에서 [2]글자가 포함되어 있는 정보만 가져오기

			if(search.equals("all")) {// 전체
				sql = "select count(*) from sy_seller";
			}else {//검색어가 있을 때
				sql = "select count(*) from sy_seller where seller_id like ?";
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
   
 public List<SellerBean> getSearchList(String search) {
		System.out.println("DAO :getSearchList() 실행! ");
		List<SellerBean> searchList= new ArrayList<SellerBean>();
		
		try {
		
			con= getCon();
			
			if(search.equals("all")) {
				sql = "select * from sy_seller order by seller_num desc";
			}else {
				sql = "select * from sy_seller where seller_id like ? order by seller_num desc";
				
			}
			pstmt = con.prepareStatement(sql);
			
			if(search.equals("all")) {
				 
				 
			 }else {
				 pstmt.setString(1, "%"+search+"%");
				
				 
			 }
			
			rs=pstmt.executeQuery();
			
			//5. 데이터처리
			while(rs.next()) {
				//DB1줄 -> BoardBean객체 1개에 담기
				SellerBean sb = new SellerBean();
				
				sb.setSeller_num(rs.getInt("seller_num"));
				sb.setSeller_id(rs.getString("seller_id"));
				sb.setImage(rs.getString("image"));
				sb.setCopy_text(rs.getString("copy_text"));
				sb.setFile(rs.getString("file"));
				sb.setMessage(rs.getString("message"));
				
				//보드빈 객체 1개를 arrayList한칸에 저장
			              searchList.add(sb);
				//System.out.println("boardList : " + boardList);
			}	//while
			
			   System.out.println("@@@@@@@@@@@@@@@@");
			   System.out.println("DAO : SQL 구문 실행 완료! ");
			   System.out.println(" DB에 있는 모든 글을 리스트에 저장 ");
			   System.out.println("DAO : searchList "+searchList);
			}
			catch(Exception e) {
				e.printStackTrace();
			}finally {
				closeDB();
			}
			
			return searchList;
		}

}

   
   
   
   
   
   
   
   
   
   

