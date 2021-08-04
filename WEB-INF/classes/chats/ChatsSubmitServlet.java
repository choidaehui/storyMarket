package chats;

import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public class ChatsSubmitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		String fromID = request.getParameter("fromID");
		String toID = request.getParameter("toID");
		String chatContent = request.getParameter("chatContent");
		if(fromID==null || fromID.equals("") || toID==null || toID.equals("")
				||chatContent==null || chatContent.equals("")) {
			response.getWriter().write("0");
		}else {
			fromID = URLDecoder.decode(fromID, "utf-8");
			toID = URLDecoder.decode(toID, "utf-8");
			chatContent = URLDecoder.decode(chatContent, "utf-8");
			response.getWriter().write(new chatsDAO().submit(fromID, toID, chatContent) +"");
			
		
		}
		}

}
