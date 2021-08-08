package net.select.action;

import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.select.db.selectBean;
import net.select.db.selectDAO;
import net.seller.db.SellerDAO;

public class selectContentViewAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, 
			HttpServletResponse response) throws Exception {

	 System.out.println("selectContentViewAction.execute()실행");
		
     int seller_num = Integer.parseInt(request.getParameter("seller_num"));
	 int item_num = Integer.parseInt(request.getParameter("item_num"));
	 
	HttpSession session = request.getSession();
	String member_id = (String)session.getAttribute("id");
	 
	 selectBean seb = new selectBean();
	 selectDAO sedao = new selectDAO();
	 
	 sedao.insertItemSelect(seller_num, item_num, member_id);
	 
	 List<selectBean> selectList = sedao.getSelectList(member_id);
	 
	 request.setAttribute("selectList", selectList);
	 System.out.println("selectContentViewAction : selectList 호출 "+selectList);
		
	 ActionForward forward = new ActionForward();
	 
	 forward.setPath("./select/selectContentView.jsp");
     forward.setRedirect(false);		
		
     return forward;
	
	}

}
