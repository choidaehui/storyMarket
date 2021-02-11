package net.board.db;

public class ThumbBean {

	private String imgname;
	private String thumbname;

	public ThumbBean() {}

	@Override
	public String toString() {
		return "ThumbBean [imgname=" + imgname + ", thumbname=" + thumbname + "]";
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

  
	
	
}
