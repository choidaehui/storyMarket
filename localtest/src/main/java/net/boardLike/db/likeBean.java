package net.boardLike.db;

public class likeBean {

	private String id;
	private int num;
	private String ip;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	@Override
	public String toString() {
		return "likeBean [id=" + id + ", num=" + num + ", ip=" + ip + "]";
	}
	

	
	
	

}
