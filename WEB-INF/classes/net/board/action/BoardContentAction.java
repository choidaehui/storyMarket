package net.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.board.db.reviewBean;
import net.board.db.reviewDAO;

public class BoardContentAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("M: BoardContentAction_execute()호출");
		
		//전달되는 정보 저장 (주소줄 num, pageNum) => 콘텐츠로 보내줘야함!
		int num = Integer.parseInt(request.getParameter("num"));
		String pageNum = request.getParameter("pageNum");
		String rating = request.getParameter("rating");
		System.out.println("M: BoardContentAction_execute() rating : "+rating);
		//DAO객체 생성 
		reviewDAO rdao = new reviewDAO();
		
		//->특정 글 조회수를 수정(updateReadCount())
		rdao.updateReadCount(num); //매개변수로 특정게시글 번호를 지정한다. 
		System.out.println("M : 조회수 1증가 완료!");
		
		//->특정 글번호에 해당하는 정보를 가져오기(getBoard())
		reviewBean rb = rdao.getBoard(num); //매개변수로 특정 게시글번호를 지정한다. 
		System.out.println("M: BoardContentAction_execute() rb : "+rb);
		
		// 글정보를 저장(request영역에 저장) // 페이지 번호도 넘겨줘야 이동 가능하다. ㄴ
		request.setAttribute("rb", rb);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("rating", rating);
		//페이지 이동(boardContent.jsp)
		ActionForward forward = new ActionForward();
		forward.setPath("./board/boardContent.jsp");
		forward.setRedirect(false);
		
		
		return forward;
	}

	
	
}
