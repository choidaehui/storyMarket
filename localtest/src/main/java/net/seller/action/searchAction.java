package net.seller.action;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import java.util.ArrayList;
import java.util.List;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.board.db.reviewBean;
import net.board.db.reviewDAO;
import net.seller.db.SellerBean;
import net.seller.db.SellerDAO;



public class searchAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		System.out.println("M : 게시판 검색 시작!");
	    
		//한글처리
		request.setCharacterEncoding("UTF-8");
		
		//검색어 정보 저장
		 String search = request.getParameter("search");
		System.out.println("검색어 확인: "+search);
		 
		 if(search == null || search == ""){
			  //검색어가 없을 경우 글 전체를 확인
			  search = "all";
		  }
		
		 SellerDAO sdao = new SellerDAO();
         sdao.getSearchCount(search);
         int count = sdao.getSearchCount(search);
                          
         SellerBean sb = new SellerBean();
         List searchList = (List) sdao.getSearchList(search);
         
         HttpSession session = request.getSession();
         session.setAttribute("searchList", searchList);
         request.setAttribute("count", count);
				
	 
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
	
		//페이지 이동(forward)
		    ActionForward forward = new ActionForward();
		    forward.setPath("./seller/itemSearchList.jsp");
			forward.setRedirect(false);
			return forward;
			
	}	
}	
		


