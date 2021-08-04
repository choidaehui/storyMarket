package net.seller.db;

public class SellerBean {
	
	private int seller_num;
	private String seller_id;
	private String image;
	private String copy_text;
	private String file;
	private String message;
	
	
	public int getSeller_num() {
		return seller_num;
	}
	public void setSeller_num(int seller_num) {
		this.seller_num = seller_num;
	}
	public String getSeller_id() {
		return seller_id;
	}
	public void setSeller_id(String seller_id) {
		this.seller_id = seller_id;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getCopy_text() {
		return copy_text;
	}
	public void setCopy_text(String copy_text) {
		this.copy_text = copy_text;
	}
	public String getFile() {
		return file;
	}
	public void setFile(String file) {
		this.file = file;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
	@Override
	public String toString() {
		return "SellerBean [seller_num=" + seller_num + ", seller_id=" + seller_id + ", image=" + image + ", copy_text="
				+ copy_text + ", file=" + file + ", message=" + message + "]";
	}
	
	
	
	
	
	
}
	
	