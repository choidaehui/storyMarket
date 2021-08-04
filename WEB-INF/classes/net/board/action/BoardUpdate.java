package net.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.board.db.reviewBean;
import net.board.db.reviewDAO;

public class BoardUpdate implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("M : BoardUpdate_execute() 호출!");
		
		//전달된 파라미터 값 저장 (num, pageNum)
		int num = Integer.parseInt(request.getParameter("num"));
		String pageNum = request.getParameter("pageNum");
		
		//DAO 객체 생성 -> 게시글 정보 가져오기() - getBoard();
		reviewDAO rdao = new reviewDAO();
		reviewBean rb = rdao.getBoard(num);
		
		
		//정보저장 (request)
		request.setAttribute("rb", rb);
		request.setAttribute("pageNum", pageNum);
		
		// 페이지이동(view  페이지 작성  ./board/updateForm.jsp)
		ActionForward forward = new ActionForward();
		forward.setPath("./board/updateForm.jsp");
		forward.setRedirect(false);
		
		return forward;
	}

}
