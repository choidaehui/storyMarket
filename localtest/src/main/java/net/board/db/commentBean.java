package net.board.db;

public class commentBean {

	    private int comment_id;
	    private String name;
	    private String content;
	    private int parentNum;
		public int getComment_id() {
			return comment_id;
		}
		public void setComment_id(int comment_id) {
			this.comment_id = comment_id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getContent() {
			return content;
		}
		public void setContent(String content) {
			this.content = content;
		}
		public int getParentNum() {
			return parentNum;
		}
		public void setParentNum(int parentNum) {
			this.parentNum = parentNum;
		}
		@Override
		public String toString() {
			return "commentBean [comment_id=" + comment_id + ", name=" + name + ", content=" + content + ", parentNum="
					+ parentNum + "]";
		}
	   
	    

	
}
