package net.user.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.seller.db.SellerBean;
import net.seller.db.SellerDAO;
import net.user.db.userBean;
import net.user.db.userDAO;

public class userContentViewAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
           
		System.out.println("M : userContentAction_execute()호출");
		
		request.setCharacterEncoding("UTF-8");
		
		userDAO udao = new userDAO();
		SellerDAO sdao = new SellerDAO();
		

		int seller_num = Integer.parseInt(request.getParameter("seller_num"));
		SellerBean sb =  sdao.getList(seller_num);
		System.out.println("userContentView 페이지"+sb);
		
		request.setAttribute("sb", sb);
		
		
		int user_num = seller_num;
		
		
		
	    userBean ub = udao.getList(user_num); 
		  System.out.println(ub);
		  System.out.println("M : user_num확인 "+user_num);  
		  request.setAttribute("ub", ub);
		 
		
		//페이지 이동(userContentView.jsp)
		ActionForward forward = new ActionForward();
		forward.setPath("./user/userContentView.jsp");
		forward.setRedirect(false);
		
		
		return forward;
	}

}
