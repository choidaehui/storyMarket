package user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import user.User;


public class UserDAO {
  private Connection conn;
  private PreparedStatement pstmt;
  private ResultSet rs;
  
  
  public UserDAO() {
	  try {
		
		  String dbURL="jdbc:mysql://localhost:3306/AJAX";
		  String dbID = "root";
		  String dbPassword = "1234";
		  Class.forName("com.mysql.jdbc.Driver");
		  conn = DriverManager.getConnection(dbURL, dbID, dbPassword);
		  
	} catch (Exception e) {
		e.printStackTrace();
	}
	  
  }//UserDAO //
  
  //모든 회원정보를 어레이리스트로 들고 오자 // 유저 클래스를 담을 수 있는 어레이 리스트
  public ArrayList<User> search(String userName){//서치 함수만들기
	    String SQL = "select* from USER where userName LIKE ?";
	    ArrayList<User> userList = new ArrayList<User>();
	    
	    try {
			
	    	pstmt=conn.prepareStatement(SQL);
	    	pstmt.setString(1, userName);
	    	rs=pstmt.executeQuery();
	    	
	    	while(rs.next()) {//사용자의 정보를 반복해서 읽어들이는 반복문
	    		
	    		User user= new User();
	    		
	    		user.setUserName(rs.getString(1));
	    		user.setUserAge(rs.getInt(2));
	    		user.setUserGender(rs.getString(3));
	    		user.setUserEmail(rs.getString(4));
	    		
	    		userList.add(user);
	    	}
	    	
	    	
	    	
		} catch (Exception e) {
			e.printStackTrace();
		}
	    return userList;
	    
	    
  }
  
  
}
