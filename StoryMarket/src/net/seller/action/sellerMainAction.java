package net.seller.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.member.db.MemberBean;
import net.member.db.MemberDAO;
import net.seller.db.SellerBean;
import net.seller.db.SellerDAO;
import net.user.db.userBean;
import net.user.db.userDAO;

public class sellerMainAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
                    
		System.out.println("M : sellerMainAction_execute()호출");
		
		request.setCharacterEncoding("UTF-8");		
	    HttpSession session = request.getSession();
	    String seller_id = (String) session.getAttribute("id");
		
		SellerDAO sdao = new SellerDAO();
		List<SellerBean> sellerList = sdao.sellerItemList(seller_id);
				
		request.setAttribute("sellerList", sellerList);
		
		MemberDAO mdao = new MemberDAO();
		MemberBean mb = mdao.getMember(seller_id);
		
		session.setAttribute("mb", mb);
		System.out.println("sellerMainAction: mb값 호출"+mb);
	
		ActionForward forward = new ActionForward();
		
		forward.setPath("./seller/sellerMain.jsp");
		forward.setRedirect(false);
	
		return forward;
	}

}
