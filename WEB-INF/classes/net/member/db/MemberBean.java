package net.member.db;

import java.sql.Timestamp;

public class MemberBean {

	private String id;
	private String name;
	private String pass;
	private String phone;
	private String email;
	private String address;
	private String account;
	private Timestamp reg_date;
	private String imgname;
	private String thumbname;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public Timestamp getReg_date() {
		return reg_date;
	}
	public void setReg_date(Timestamp reg_date) {
		this.reg_date = reg_date;
	}
	public String getImgname() {
		return imgname;
	}
	public void setImgname(String imgname) {
		this.imgname = imgname;
	}
	public String getThumbname() {
		return thumbname;
	}
	public void setThumbname(String thumbname) {
		this.thumbname = thumbname;
	}
	@Override
	public String toString() {
		return "MemberBean [id=" + id + ", name=" + name + ", pass=" + pass + ", phone=" + phone + ", email=" + email
				+ ", address=" + address + ", account=" + account + ", reg_date=" + reg_date + ", imgname=" + imgname
				+ ", thumbname=" + thumbname + "]";
	}
	
	
	
	
	
}
