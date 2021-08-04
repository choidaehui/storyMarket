package net.item.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import net.board.db.reviewBean;
import net.seller.db.SellerBean;

public class itemDAO {
	
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
    	
    	int item_num = 0;
    	
    	try {
    		
    		con = getCon();
    		
    		sql = "select max(item_num) from sy_item";
    		pstmt = con.prepareStatement(sql);
    		rs = pstmt.executeQuery();
    		
    		if(rs.next()) {
    			
    			item_num = rs.getInt(1)+1;
    		}
    		
    	}catch(Exception e) {
    		
    		e.printStackTrace();
    	}finally {
			closeDB();
		}
    	
    	System.out.println("판매자번호"+item_num);
    	
    	return item_num;
    	
    }
    
    public void insertItem(itemBean ib) {
    	
    	int item_num = maxNum();
    	
    	if(item_num == 0) {
    		
    		item_num = 1;
    		
    	}
    	
    	System.out.println("DAO : insertItem(ib)호출");
    	System.out.println("DAO : item_num 값"+item_num);
    	
    	try {
    		
    		con = getCon();
    		
    		sql = "insert into sy_item(item_num,item_id,item_title,location,buydate,trade_location,status,sale) "
    				+"values(?,?,?,?,?,?,?,?)";
    		
    		pstmt = con.prepareStatement(sql);
    		pstmt.setInt(1, item_num);
    		pstmt.setString(2, ib.getItem_id());
    		pstmt.setString(3, ib.getItem_title());
    		pstmt.setString(4, ib.getLocation());
    		pstmt.setString(5, ib.getBuydate());
    		pstmt.setString(6, ib.getTrade_location());
    		pstmt.setString(7, ib.getStatus());
    		pstmt.setString(8, ib.getSale());
    		
    		pstmt.executeUpdate();
    		
    		System.out.println("DAO : 판매자 글쓰기 완료");
    		
    	}catch(Exception e) {
    		
    		e.printStackTrace();
    	}finally {
			closeDB();
		}
    	
    }
    
    public itemBean getList(int item_num) {
    	
    	      itemBean ib = null;
    	      
    	      
    	      try {
				con = getCon();
				
				sql = "select * from sy_item where item_num=? ";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, item_num);
				
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					
					ib = new itemBean();
					
					ib.setItem_num(rs.getInt("item_num"));
					ib.setItem_id(rs.getString("item_id"));
					ib.setItem_title(rs.getString("item_title"));
					ib.setBuydate(rs.getString("buydate"));
					ib.setLocation(rs.getString("location"));
					ib.setStatus(rs.getString("status"));
					ib.setTrade_location(rs.getString("trade_location"));
					ib.setSale(rs.getString("sale"));
					
				}
				
				System.out.println("DAO : 판매자 애장품 정보 저장 완료!");
			} catch (Exception e) {

				
				e.printStackTrace();
			}finally {
				
				closeDB();
			}
            System.out.println(ib);
            
            return ib;
    	
    }
    
    public List<itemBean> getTradeItemList(){
    	
    	List<itemBean> tradeItemList = new ArrayList<itemBean>();
    	
    	 try {
				con = getCon();
				
				sql = "select * from sy_item order by item_num desc ";
				pstmt = con.prepareStatement(sql);				
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					
					itemBean ib = new itemBean();
					
					
		
					ib.setItem_num(rs.getInt("item_num"));
					ib.setItem_id(rs.getString("item_id"));
					ib.setItem_title(rs.getString("item_title"));
					ib.setBuydate(rs.getString("buydate"));
					ib.setLocation(rs.getString("location"));
					ib.setStatus(rs.getString("status"));
					ib.setTrade_location(rs.getString("trade_location"));
					ib.setSale(rs.getString("sale"));
					
					tradeItemList.add(ib);
					
				}
				
				System.out.println("DAO : 구매자 애장품목록  저장 완료!");
			} catch (Exception e) {

				
				e.printStackTrace();
			}finally {
				
				closeDB();
			}
       
         
         return tradeItemList;
    	
    }
    
  public List<itemBean> notSellerTradeItemList(String item_id){
    	
    	List<itemBean> notSellerTradeItemList = new ArrayList<itemBean>();
    	
    	 try {
				con = getCon();
				
				sql = "select * from sy_item where item_id not in (?) order by item_num desc ";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, item_id);
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					
					itemBean ib = new itemBean();
					
					ib.setItem_num(rs.getInt("item_num"));
					ib.setItem_id(rs.getString("item_id"));
					ib.setItem_title(rs.getString("item_title"));
					ib.setBuydate(rs.getString("buydate"));
					ib.setLocation(rs.getString("location"));
					ib.setStatus(rs.getString("status"));
					ib.setTrade_location(rs.getString("trade_location"));
					ib.setSale(rs.getString("sale"));
					
					notSellerTradeItemList.add(ib);
					
				}
				
				System.out.println("DAO : 판매자제외 애장품목록  저장 완료!");
			} catch (Exception e) {

				
				e.printStackTrace();
			}finally {
				
				closeDB();
			}
       
         
         return notSellerTradeItemList;
    	
    }
  
}
