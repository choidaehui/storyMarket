package net.board.action;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import java.util.List;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.board.db.reviewBean;
import net.board.db.reviewDAO;



public class searchAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		System.out.println("M : 게시판 검색 시작!");
	    
		//한글처리
		request.setCharacterEncoding("UTF-8");
		
		
		String field_=request.getParameter("f");
		String query_=request.getParameter("q");
		
		String field = "subject";
		if(field_!= null) {
			field = field_;
		}
		
		String query = "";
		if(query_ != null) {
			query = query_;
		}
		
	
		
		reviewDAO rdao = new reviewDAO();
		List<reviewBean> boardList = rdao.getBoardList(field, query,1);
		request.setAttribute("boardList", boardList);
		
		
		//페이지 이동(forward)
		    ActionForward forward = new ActionForward();
		    forward.setPath("./board/boardList.jsp");
			forward.setRedirect(false);
			return forward;
			
	}	
}	
		
	
	


