package net.user.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import net.seller.db.SellerBean;

public class userDAO {
	
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
		   
		   int user_num = 0;
		   
		   try {
			   
				  con = getCon();	
				  
				   //seller_num 값 수정
				   sql = "select max(user_num) from sy_user";
				   pstmt = con.prepareStatement(sql);
				   rs = pstmt.executeQuery();
				   
				   if(rs.next()) {
					   
					   user_num = rs.getInt(1)+1;
				   }
			  
		   }catch (Exception e) {
	            
		        e.printStackTrace();
		   }finally {
			   
			   closeDB();
		   }
		   
		   return  user_num;
	   }
	   
	   
	 
	   //sellerBean이용 userBean getList실행
	 
	   public userBean getList(int user_num){
		  	    	  
		      userBean ub = null;
		      SellerBean sb = null;
	    
		  
		      
	  try {
		  
		  con = getCon();
		  
		  sql = "select * from sy_seller where seller_num=? ";
		  pstmt = con.prepareStatement(sql);
		  pstmt.setInt(1, user_num);
		  
		  rs = pstmt.executeQuery();
		  
		  
		  if(rs.next()) {
			  
			  sb = new SellerBean();
			  
			  sb.setSeller_num(rs.getInt("seller_num"));
			  sb.setSeller_id(rs.getString("seller_id"));
			  sb.setImage(rs.getString("image"));
			  sb.setCopy_text(rs.getString("copy_text"));
			  sb.setFile(rs.getString("file"));
			  sb.setMessage(rs.getString("message"));
			  
			  ub = new userBean();
			  
			  ub.setS_copy_text(rs.getString("copy_text"));
			  ub.setS_file(rs.getString("file"));
			  ub.setS_image(rs.getString("image"));
			  ub.setS_message(rs.getString("message"));
			  ub.setUser_id(rs.getString("seller_id"));
			  ub.setUser_num(rs.getInt("seller_num"));
			
		  }
		  
		  System.out.println("DAO : 일반유저 애장품 홍보 저장완료!");
		 
		  
	  }catch(Exception e) {
		  
		  e.printStackTrace();
	  }finally {
		  
		  closeDB();
	  }
	  
	  System.out.println(ub);
	  
	     return ub;  

  }
	   
	 
}
