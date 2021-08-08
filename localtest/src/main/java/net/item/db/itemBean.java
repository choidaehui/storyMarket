package net.item.db;

public class itemBean {
    
	private int item_num;
	private String item_id;
	private String item_title;
	private String location;
	private String buydate;
	private String trade_location;
	private String status; 
	private String sale;
	
	
	public int getItem_num() {
		return item_num;
	}
	public void setItem_num(int item_num) {
		this.item_num = item_num;
	}
	public String getItem_id() {
		return item_id;
	}
	public void setItem_id(String item_id) {
		this.item_id = item_id;
	}
	public String getItem_title() {
		return item_title;
	}
	public void setItem_title(String item_title) {
		this.item_title = item_title;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getBuydate() {
		return buydate;
	}
	public void setBuydate(String buydate) {
		this.buydate = buydate;
	}
	public String getTrade_location() {
		return trade_location;
	}
	public void setTrade_location(String trade_location) {
		this.trade_location = trade_location;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getSale() {
		return sale;
	}
	public void setSale(String sale) {
		this.sale = sale;
	}
	
	
	@Override
	public String toString() {
		return "itemBean [item_num=" + item_num + ", item_id=" + item_id + ", item_title=" + item_title + ", location="
				+ location + ", buydate=" + buydate + ", trade_location=" + trade_location + ", status=" + status
				+ ", sale=" + sale + "]";
	}
	
}
