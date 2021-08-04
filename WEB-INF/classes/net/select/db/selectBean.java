package net.select.db;

public class selectBean {
    
	private int select_num;
	private String member_id;
	private String select_id;
	private String j_image;
	private String j_copy_text;
	private String j_file;
	private String j_message;
	private String j_item_title;
	private String j_location;
	private String j_buydate;
	private String j_trade_location;
	private String j_status;
	private String j_sale;
	
	
	public int getSelect_num() {
		return select_num;
	}
	public void setSelect_num(int select_num) {
		this.select_num = select_num;
	}
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public String getSelect_id() {
		return select_id;
	}
	public void setSelect_id(String select_id) {
		this.select_id = select_id;
	}
	public String getJ_image() {
		return j_image;
	}
	public void setJ_image(String j_image) {
		this.j_image = j_image;
	}
	public String getJ_copy_text() {
		return j_copy_text;
	}
	public void setJ_copy_text(String j_copy_text) {
		this.j_copy_text = j_copy_text;
	}
	public String getJ_file() {
		return j_file;
	}
	public void setJ_file(String j_file) {
		this.j_file = j_file;
	}
	public String getJ_message() {
		return j_message;
	}
	public void setJ_message(String j_message) {
		this.j_message = j_message;
	}
	public String getJ_item_title() {
		return j_item_title;
	}
	public void setJ_item_title(String j_item_title) {
		this.j_item_title = j_item_title;
	}
	public String getJ_location() {
		return j_location;
	}
	public void setJ_location(String j_location) {
		this.j_location = j_location;
	}
	public String getJ_buydate() {
		return j_buydate;
	}
	public void setJ_buydate(String j_buydate) {
		this.j_buydate = j_buydate;
	}
	public String getJ_trade_location() {
		return j_trade_location;
	}
	public void setJ_trade_location(String j_trade_location) {
		this.j_trade_location = j_trade_location;
	}
	public String getJ_status() {
		return j_status;
	}
	public void setJ_status(String j_status) {
		this.j_status = j_status;
	}
	public String getJ_sale() {
		return j_sale;
	}
	public void setJ_sale(String j_sale) {
		this.j_sale = j_sale;
	}
	
	
	@Override
	public String toString() {
		return "selectBean [select_num=" + select_num + ", member_id=" + member_id + ", select_id=" + select_id
				+ ", j_image=" + j_image + ", j_copy_text=" + j_copy_text + ", j_file=" + j_file + ", j_message="
				+ j_message + ", j_item_title=" + j_item_title + ", j_location=" + j_location + ", j_buydate="
				+ j_buydate + ", j_trade_location=" + j_trade_location + ", j_status=" + j_status + ", j_sale=" + j_sale
				+ "]";
	}
	
	
	
	
	
}
