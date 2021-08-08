package net.item.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.item.db.itemBean;
import net.item.db.itemDAO;

public class tradeItemAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        
		System.out.println("M : tradeItemAction_execute()호출");
		
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession();
		String item_id = (String)session.getAttribute("id");
		itemDAO idao = new itemDAO();
		
		List<itemBean> notSellerTradeItemList = idao.notSellerTradeItemList(item_id);
		
		request.setAttribute("notSellerTradeItemList", notSellerTradeItemList);
		System.out.println("tradeItemAction : notSellerTradeItemList값 호출: "+notSellerTradeItemList);
		
		ActionForward forward = new ActionForward();
		
		forward.setPath("./item/tradeItem.jsp");
		forward.setRedirect(false);
		
		return forward;
	}

}
