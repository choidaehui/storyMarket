package net.user.db;

public class userBean {
   
	private int user_num;
	private String user_id;
	private String s_image;
	private String s_copy_text;
	private String s_file;
	private String s_message;
	
	
	public int getUser_num() {
		return user_num;
	}
	public void setUser_num(int user_num) {
		this.user_num = user_num;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getS_image() {
		return s_image;
	}
	public void setS_image(String s_image) {
		this.s_image = s_image;
	}
	public String getS_copy_text() {
		return s_copy_text;
	}
	public void setS_copy_text(String s_copy_text) {
		this.s_copy_text = s_copy_text;
	}
	public String getS_file() {
		return s_file;
	}
	public void setS_file(String s_file) {
		this.s_file = s_file;
	}
	public String getS_message() {
		return s_message;
	}
	public void setS_message(String s_message) {
		this.s_message = s_message;
	}
	
	
	
	@Override
	public String toString() {
		return "userBean [user_num=" + user_num + ", user_id=" + user_id + ", s_image=" + s_image + ", s_copy_text="
				+ s_copy_text + ", s_file=" + s_file + ", s_message=" + s_message + "]";
	}
	
	
	
	
	
}


