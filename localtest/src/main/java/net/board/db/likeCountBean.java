package net.board.db;

public class likeCountBean {
	
	private String userId;
	private int board_num;
	private String userIp;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public int getBoard_num() {
		return board_num;
	}
	public void setBoard_num(int board_num) {
		this.board_num = board_num;
	}
	public String getUserIp() {
		return userIp;
	}
	public void setUserIp(String userIp) {
		this.userIp = userIp;
	}
	@Override
	public String toString() {
		return "likeCountBean [userId=" + userId + ", board_num=" + board_num + ", userIp=" + userIp + "]";
	}

	
	
}
