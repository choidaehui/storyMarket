package net.member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.member.db.MemberDAO;

public class MemberIdCheckAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println("MemberIdCheckAction_execute");
		
		String id = request.getParameter("id");
		System.out.println(id);
        
		MemberDAO dao = new MemberDAO();
        
        boolean result = dao.duplicateIdCheck(id);
        
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
 
        if(result) {
        	out.println("0");
        	} // 아이디 중복
        else {        
        	out.println("1");
        }
        
        out.close();
        
        return null;

	}

}
