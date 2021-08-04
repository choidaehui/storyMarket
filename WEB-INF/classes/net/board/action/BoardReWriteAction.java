package net.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.board.db.reviewBean;

public class BoardReWriteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("V:BoardReWriteAction -execute 실행 ");
		
		//한글처리
		request.setCharacterEncoding("UFT-8");
		
		//전달받은 정보 저장
		//pageNum=3  
		//폼 태그 안의 num / re_ref / re_lev /re_seq / name / pass / subject / content
		//=>양이 많으므로 BoardBean 객체에 저장하여 불러오자.
		String pageNum = request.getParameter("pageNum");
		
		reviewBean rb = new reviewBean();
		request.setAttribute("rb", rb);
		request.setAttribute("pageNum", pageNum);
		
		//DAO객체 생성 => 답글쓰기 매서드 - reInsertBoard(bb) 
		
		
		//페이지이동
		
		return null;
	}

}
