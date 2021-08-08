package user;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/UserSearchServlet")
public class UserSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		String userName = request.getParameter("userName");
		response.getWriter().write(getJSON(userName));  //메소드로 출력할 수 있게 처리해준다.
		
	}
	//JSON 파싱하기 쉬운 하나의 형태
	//특정 회원을 검색했을 때 JSON형태로 출력 -> 파싱해서 다시 보여줌
	public String getJSON(String userName) {
		if(userName == null) userName = ""; 
		StringBuffer result =new StringBuffer(""); //공백문자열을 넣어준다.
		result.append("{\"result\":["); //결과값을 담아주자
		UserDAO userDAO = new UserDAO();
		ArrayList<User> userList = userDAO.search(userName);
		for(int i=0; i<userList.size();i++) {
			result.append("[{\"value\":\"" + userList.get(i). getUserName() + "\"},");
			result.append("{\"value\":\"" + userList.get(i). getUserName() + "\"},");
			result.append("{\"value\":\"" + userList.get(i). getUserName() + "\"},");
			result.append("{\"value\":\"" + userList.get(i). getUserName() + "\"}],");
 		}
		result.append("]}");
		return result.toString();
		
		
	}
	
}
