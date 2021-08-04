package net.select.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import net.item.db.itemBean;
import net.item.db.itemDAO;
import net.seller.db.SellerBean;
import net.seller.db.SellerDAO;

public class selectDAO {
	
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
			if(con != null) {
				
				con.close();
			}
		}catch(SQLException e) {
			
			
			e.printStackTrace();
		}
		
		
	}
    
	//maxNum()
	
	public int maxNum() {
		
		int select_num = 0;
		
		try {
			
			con = getCon();
			
			sql = "select max(select_num) from sy_select";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				select_num = rs.getInt(1)+1;
				
			}
			
		}catch(Exception e) {
			
			
			e.printStackTrace();
		}finally {
			closeDB();
		}
		
		System.out.println("찜번호"+select_num);
		
		return select_num;
		
	}
	
	
	//selectBean에 찜한 애장품 홍보,정보 저장
	
	public void insertItemSelect(int seller_num, int item_num, String member_id){
		
		   int select_num = maxNum();
		   
		   itemDAO idao = new itemDAO();
		   itemBean ib = new itemBean();
		   
		   ib = idao.getList(item_num);
		   
		   SellerDAO sdao = new SellerDAO();
		   
		   SellerBean sb = new SellerBean();
		   sb = sdao.getList(seller_num);
		   
		   
		
		   System.out.println("DAO : insertSelect(slb)호출!!");
		   System.out.println("DAO : select_num 값"+select_num);
		   
		      try {
		    	  			
		    	  con = getCon();
		    	  
		    	  sql = "insert into sy_select(select_num,member_id,select_id,j_image,j_copy_text,j_file,j_message,j_item_title,j_location,j_buydate,j_trade_location,j_status,j_sale) "
		    			  +"values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
		    	  
		    	  pstmt = con.prepareStatement(sql);
		    	  
		    	  pstmt.setInt(1, select_num);
		    	  pstmt.setString(2, member_id);
		    	  pstmt.setString(3, sb.getSeller_id());
		    	  pstmt.setString(4, sb.getImage());
		    	  pstmt.setString(5, sb.getCopy_text());
		    	  pstmt.setString(6, sb.getFile());
		    	  pstmt.setString(7, sb.getMessage());
		    	  pstmt.setString(8, ib.getItem_title());
		    	  pstmt.setString(9, ib.getLocation());
		    	  pstmt.setString(10, ib.getBuydate());
		    	  pstmt.setString(11, ib.getTrade_location());
		    	  pstmt.setString(12, ib.getStatus());
		    	  pstmt.setString(13, ib.getSale());
	  
		    	  pstmt.executeUpdate();
		    	  
		    	  
			} catch (Exception e) {

				e.printStackTrace();
			}finally {
				closeDB();
			}
	}
	
	public List<selectBean> getSelectList(String member_id){
		
		List<selectBean> selectList = new ArrayList<selectBean>();
		
		try {
			
			con = getCon();
			
			sql = "select * from sy_select where member_id=? order by select_num desc";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, member_id);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				selectBean sel = new selectBean();
				
				sel.setJ_buydate(rs.getString("j_buydate"));
				sel.setJ_copy_text(rs.getString("j_copy_text"));
				sel.setJ_file(rs.getString("j_file"));
				sel.setJ_image(rs.getString("j_image"));
				sel.setJ_item_title(rs.getString("j_item_title"));
				sel.setJ_location(rs.getString("j_location"));
				sel.setJ_message(rs.getString("j_message"));
				sel.setJ_sale(rs.getString("j_sale"));
				sel.setJ_status(rs.getString("j_status"));
				sel.setJ_trade_location(rs.getString("j_trade_location"));
				sel.setSelect_id(rs.getString("select_id"));
				sel.setSelect_num(rs.getInt("select_num"));
				sel.setMember_id(rs.getString("member_id"));
				
				selectList.add(sel);
				
			}
			
		} catch (Exception e) {

			
			e.printStackTrace();
		}finally {
			
			closeDB();
		}
		
		System.out.println("찜한 애장품 리스트 저장: "+selectList);
		return selectList;
		
	}
	
	public selectBean getList(int select_num) {
		
		selectBean sel = null;
		
		try {
			
			con = getCon();
			
			sql = "select * from sy_select where select_num=? ";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, select_num);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
                sel = new selectBean();
				
				sel.setJ_buydate(rs.getString("j_buydate"));
				sel.setJ_copy_text(rs.getString("j_copy_text"));
				sel.setJ_file(rs.getString("j_file"));
				sel.setJ_image(rs.getString("j_image"));
				sel.setJ_item_title(rs.getString("j_item_title"));
				sel.setJ_location(rs.getString("j_location"));
				sel.setJ_message(rs.getString("j_message"));
				sel.setJ_sale(rs.getString("j_sale"));
				sel.setJ_status(rs.getString("j_status"));
				sel.setJ_trade_location(rs.getString("j_trade_location"));
				sel.setSelect_id(rs.getString("select_id"));
				sel.setSelect_num(rs.getInt("select_num"));
				sel.setMember_id(rs.getString("member_id"));
				
				
				
				
				
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}finally {
			closeDB();
		}
		
		System.out.println("찜한 번호의 애장품을 자바빈에 저장완료!"+sel);
		return sel;
	}
		
	    
	
}
