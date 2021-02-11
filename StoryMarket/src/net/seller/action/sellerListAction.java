package net.seller.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.seller.db.SellerBean;
import net.seller.db.SellerDAO;

public class sellerListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
         System.out.println("M : sellerListAction_execute()호출");
         
         request.setCharacterEncoding("UTF-8");
 		    
         
         SellerDAO sdao = new SellerDAO();
         
         int seller_num = sdao.maxNum()-1;
         
         System.out.println(seller_num+"확인");
         
         SellerBean sb = sdao.getList(seller_num);
         
         System.out.println(sb);
         
         request.setAttribute("sb", sb);
         
  
         //페이지 이동(sellerList.jsp)
         ActionForward forward = new ActionForward();
         forward.setPath("./seller/sellerList.jsp");
         forward.setRedirect(false);
         
		return forward;
	}

}
