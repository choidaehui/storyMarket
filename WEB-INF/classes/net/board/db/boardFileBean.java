package net.board.db;

public class boardFileBean {

	private int file_num;
	private String file_oriname;
	private String file_name;
	private boolean isImage;
	
	public boardFileBean() {}

	@Override
	public String toString() {
		return "boardFileBean [file_num=" + file_num + ", file_oriname=" + file_oriname + ", file_name=" + file_name
				+ ", isImage=" + isImage + "]";
	}

	public int getFile_num() {
		return file_num;
	}

	public void setFile_num(int file_num) {
		this.file_num = file_num;
	}

	public String getFile_oriname() {
		return file_oriname;
	}

	public void setFile_oriname(String file_oriname) {
		this.file_oriname = file_oriname;
	}

	public String getFile_name() {
		return file_name;
	}

	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}

	public boolean isImage() {
		return isImage;
	}

	public void setImage(boolean isImage) {
		this.isImage = isImage;
	}
	
	
	
	
}
