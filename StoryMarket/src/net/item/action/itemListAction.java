package net.item.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.item.db.itemBean;
import net.item.db.itemDAO;

public class itemListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        System.out.println("M : itemListAction_execute()호출");
        
        request.setCharacterEncoding("UTF-8");
		
		itemDAO idao = new itemDAO();
		
		int item_num = idao.maxNum()-1;
		
		System.out.println(item_num+"확인");
		
		itemBean ib = idao.getList(item_num);
		
		System.out.println(ib);
		
		request.setAttribute("ib", ib);
		
		
		ActionForward forward = new ActionForward();
		forward.setPath("./item/itemList.jsp");
		forward.setRedirect(false);
		
		return forward;
	}

}
