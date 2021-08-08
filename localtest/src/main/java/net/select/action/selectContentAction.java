package net.select.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.select.db.selectBean;
import net.select.db.selectDAO;

public class selectContentAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		
		System.out.println("selectContentAction.execute()실행");

		 int select_num = Integer.parseInt(request.getParameter("select_num"));
	     selectDAO sedao = new selectDAO();
	      
	     selectBean sel = new selectBean();
	     sel = sedao.getList(select_num); 
	     
	     HttpSession session = request.getSession();
	     session.setAttribute("sel", sel);
	     
	     
		ActionForward forward = new ActionForward();
		
		forward.setPath("./select/selectContent.jsp");
		forward.setRedirect(false);
		
		return forward;
	}

}
