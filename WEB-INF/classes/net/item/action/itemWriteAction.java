package net.item.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.item.db.itemBean;
import net.item.db.itemDAO;

public class itemWriteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        
		System.out.println("M : itemWriteAction_execute()호출");
		
		request.setCharacterEncoding("UTF-8");
		
		itemDAO idao = new itemDAO();
		int item_num = idao.maxNum();
		
		itemBean ib = new itemBean();
	
		ib.setItem_num(item_num);
		ib.setItem_id(request.getParameter("item_id"));
		ib.setItem_title(request.getParameter("item_title"));
		ib.setLocation(request.getParameter("location"));
		ib.setBuydate(request.getParameter("buydate"));
		ib.setTrade_location(request.getParameter("trade_location"));
		ib.setStatus(request.getParameter("status"));
		ib.setSale(request.getParameter("sale"));
		
		System.out.println("M : 전달된 정보"+ib);
		
		idao.insertItem(ib);
		
		System.out.println("insertItem 메소드 실행!"+ib);
		HttpSession session = request.getSession();
		session.setAttribute("ib", ib);
		
	   
		    
	      ActionForward forward = new ActionForward();
	      forward.setPath("./itemListAction.it");
	      forward.setRedirect(true);
	    
		  return forward;
	}

}
