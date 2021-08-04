package chats;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class chatsDAO {

	private Connection conn;
	
	public chatsDAO() {
		try {
			String dbURL = "jdbc:mysql://localhost:3306/storymarketdb?serverTimezone=UTC&useSSL=false&autoReconnect=true";
			String dbID = "root";
			String dbPassword = "1234";
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(dbURL, dbID, dbPassword);
		} catch (Exception e) {
			e.printStackTrace();
		}
 
	}
	
	public ArrayList<ChatsDTO> getChatListByID(String fromID, String toID, String chatID){
		ArrayList<ChatsDTO> chatList = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String SQL = "select * from chats where ((fromID =? and toID =?) or (fromID =? and toID =?)) and chatID >? order by chatTime";
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, fromID);
			pstmt.setString(2, toID);
			pstmt.setString(3, toID);
			pstmt.setString(4, fromID);
			pstmt.setInt(5, Integer.parseInt(chatID));
			rs=pstmt.executeQuery();
			chatList=new ArrayList<ChatsDTO>();
			
			while(rs.next()) {
				ChatsDTO chat = new ChatsDTO();
				chat.setChatID(rs.getInt("chatID"));
				chat.setFromID(rs.getString("fromID").replaceAll(" ","&nbsp;").replaceAll(">", "&lt").replaceAll("<", "&gt").replaceAll("\n", "<br>"));
				chat.setToID(rs.getString("toID").replaceAll(" ","&nbsp;").replaceAll(">", "&lt").replaceAll("<", "&gt").replaceAll("\n", "<br>"));
				chat.setChatContent(rs.getString("chatContent").replaceAll(" ","&nbsp;").replaceAll(">", "&lt").replaceAll("<", "&gt").replaceAll("\n", "<br>"));
				int chatTime = Integer.parseInt(rs.getString("chatTime").substring(11,13));
				String timeType = "오전";
				if(Integer.parseInt(rs.getString("chatTime").substring(11, 13)) >= 12) {
					timeType = "오후";
					chatTime -= 12;
				}
				chat.setChatTime(rs.getString("chatTime").substring(0, 11)+" "+ timeType + " " + chatTime + ":" + rs.getString("chatTime").substring(14,16)+" ");
				chatList.add(chat);}
			
		} catch (Exception e) {
			e.printStackTrace();
			}finally {
			try {
				if(rs!=null) rs.close();
				if(pstmt!=null) pstmt.close();
				if(conn!=null) conn.close();
			} catch (Exception e) {
			e.printStackTrace();}
		}
		return chatList;
	}
	
	public ArrayList<ChatsDTO> getChatListByRecent(String fromID, String toID, int number){
		ArrayList<ChatsDTO> chatList = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		//System.out.println("ㅇㅇㅇ"+number);
		String SQL = "select * from chats where ((fromID =? and toID =?) or (fromID =? and toID =?)) and chatID > (select max(chatID) -? from chats) order by chatTime";
		try {
		
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, fromID);
			pstmt.setString(2, toID);
			pstmt.setString(3, toID);
			pstmt.setString(4, fromID);
			pstmt.setInt(5, number);
			rs=pstmt.executeQuery();
			chatList=new ArrayList<ChatsDTO>();
			
			while(rs.next()) {
				ChatsDTO chat = new ChatsDTO();
				chat.setChatID(rs.getInt("chatID"));
				chat.setFromID(rs.getString("fromID").replaceAll(" ","&nbsp;").replaceAll(">", "&lt").replaceAll("<", "&gt").replaceAll("\n", "<br>"));
				chat.setToID(rs.getString("toID").replaceAll(" ","&nbsp;").replaceAll(">", "&lt").replaceAll("<", "&gt").replaceAll("\n", "<br>"));
				chat.setChatContent(rs.getString("chatContent").replaceAll(" ","&nbsp;").replaceAll(">", "&lt").replaceAll("<", "&gt").replaceAll("\n", "<br>"));
				int chatTime = Integer.parseInt(rs.getString("chatTime").substring(11,13));
				String timeType = "오전";
				if(Integer.parseInt(rs.getString("chatTime").substring(11, 13)) >= 12) {
					timeType = "오후";
					chatTime -= 12;
				}
				chat.setChatTime(rs.getString("chatTime").substring(0, 11)+" "+ timeType + " " + chatTime + ":" + rs.getString("chatTime").substring(14,16)+" ");
				chatList.add(chat);}
			
		} catch (Exception e) {
			e.printStackTrace();
			}finally {
			try {
				if(rs!=null) rs.close();
				if(pstmt!=null) pstmt.close();
				if(conn!=null) conn.close();
				
			} catch (Exception e) {
			e.printStackTrace();}
		}
		return chatList;
	}
	
	
	public int submit (String fromID, String toID, String chatContent){
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String SQL = "insert into chats values (null, ?, ?, ?, now())"; 
		try {
		
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, fromID);
			pstmt.setString(2, toID);
			pstmt.setString(3, chatContent);
			return pstmt.executeUpdate();
			
			
		} catch (Exception e) {
			e.printStackTrace();
			}finally {
			try {
				if(rs!=null) rs.close();
				if(pstmt!=null) pstmt.close();
				if(conn!=null) conn.close();
			
			} catch (Exception e) {
			e.printStackTrace();}
		}
		return -1;
	}
	
	public ArrayList<ChatsDTO> getBox(String userID){
		ArrayList<ChatsDTO> chatList = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String SQL = "select * from chats where chatID in(select max(chatID) from chats where toID=? or fromID=? group by fromID,toID)";
		try {
		
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, userID);
			pstmt.setString(2, userID);
			rs=pstmt.executeQuery();
			chatList=new ArrayList<ChatsDTO>();
			
			while(rs.next()) {
				ChatsDTO chat = new ChatsDTO();
				chat.setChatID(rs.getInt("chatID"));
				chat.setFromID(rs.getString("fromID").replaceAll(" ","&nbsp;").replaceAll(">", "&lt").replaceAll("<", "&gt").replaceAll("\n", "<br>"));
				chat.setToID(rs.getString("toID").replaceAll(" ","&nbsp;").replaceAll(">", "&lt").replaceAll("<", "&gt").replaceAll("\n", "<br>"));
				chat.setChatContent(rs.getString("chatContent").replaceAll(" ","&nbsp;").replaceAll(">", "&lt").replaceAll("<", "&gt").replaceAll("\n", "<br>"));
				int chatTime = Integer.parseInt(rs.getString("chatTime").substring(11,13));
				String timeType = "오전";
				if(Integer.parseInt(rs.getString("chatTime").substring(11, 13)) >= 12) {
					timeType = "오후";
					chatTime -= 12;
				}
				chat.setChatTime(rs.getString("chatTime").substring(0, 11)+" "+ timeType + " " + chatTime + ":" + rs.getString("chatTime").substring(14,16)+" ");
				chatList.add(chat);}
			
				for(int i=0; i<chatList.size(); i++) {
					ChatsDTO x = chatList.get(i);
					for(int j=0; j< chatList.size(); j++) {
						ChatsDTO y = chatList.get(j);
						if(x.getFromID().equals(y.getToID()) && x.getToID().equals(y.getFromID())){
							if(x.getChatID()<y.getChatID()) {
								chatList.remove(x);
								i--;
								break;
							}else {
								chatList.remove(y);
								j--;
								
							}
						}
					}
				}
		} catch (Exception e) {
			e.printStackTrace();
			}finally {
			try {
				if(rs!=null) rs.close();
				if(pstmt!=null) pstmt.close();
				if(conn!=null) conn.close();
			} catch (Exception e) {
			e.printStackTrace();}
		}
		return chatList;
	}
	
	
	
	
}
