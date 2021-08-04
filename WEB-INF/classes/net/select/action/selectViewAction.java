package net.select.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.item.db.itemBean;
import net.item.db.itemDAO;

public class selectViewAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
         
		System.out.println("M : selectViewAction_execute()호출");
		
		request.setCharacterEncoding("UTF-8");
		
		itemDAO idao = new itemDAO();
		HttpSession session = request.getSession();
		String item_id =  (String) session.getAttribute("id");
		
		List<itemBean> getTradeItemList = idao.notSellerTradeItemList(item_id);
		
		request.setAttribute("getTradeItemList", getTradeItemList);
		System.out.println("selectViewAction : getTradeItemList 호출: "+getTradeItemList);
		
		ActionForward forward = new ActionForward();
		
		forward.setPath("./select/selectView.jsp");
		forward.setRedirect(false);
		
		
		return forward;
	}

}
