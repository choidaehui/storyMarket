package net.board.action;

import javax.mail.Session;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.board.db.reviewBean;
import net.board.db.reviewDAO;


import java.io.PrintWriter;

public class LikeAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		System.out.println("LikeAction-execute 호출!");
		
		
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		System.out.println("id : "+id);
		
		String ip = request.getParameter("ip");
		System.out.println("ip : "+ip);
		
		

		  int num = Integer.parseInt(request.getParameter("num"));
		  System.out.println("글번호 :" +num);
			reviewDAO rdao = new reviewDAO();
			int likeCount = rdao.like(num); //특정글의 추천수를 1증가시킨다.
			System.out.println("M : likeCount : "+likeCount);
			
			
			reviewBean rb = rdao.getBoard(num);
			System.out.println("M: LikeAction_execute() rb : "+rb);
			request.setAttribute("likeCount", likeCount);
					
			response.setContentType("text/html; charset=UTF-8");
			
			if (likeCount == 1) {
				
					PrintWriter script = response.getWriter();
					script.println("<script>");
					script.println("alert('추천이 완료되었습니다.');");
					script.println("location.href='./BoardList.bo'");
					script.println("</script>");
					script.close();
					return null;

				} else if(likeCount == 0) {

					PrintWriter script = response.getWriter();
					script.println("<script>");
					script.println("alert('데이터베이스 오류가 발생했습니다.');");
					script.println("history.back();");
					script.println("</script>");
					script.close();
					return null;

				

			} else {

				PrintWriter script = response.getWriter();
				script.println("<script>");
				script.println("alert('이미 추천을 누른 글입니다.');");
				script.println("history.back();");
				script.println("</script>");
				script.close();
				return null;

			}		
		
	}	
}
