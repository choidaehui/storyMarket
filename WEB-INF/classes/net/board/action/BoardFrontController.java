package net.board.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.board.db.reviewBean;
import net.board.db.reviewDAO;
import net.member.action.MemberLogoutAction;



public class BoardFrontController extends HttpServlet {

	protected void doProcess(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println("C: doProcess() 호출 - BoardFrontController");
		
		
		/************************ 1. 가상 주소 계산 ************************/
		System.out.println("\n\n 1. 가상주소 계산하기");
		
		String requestURI = request.getRequestURI();
		System.out.println("requestURI : " + requestURI);
		
		String contextPath = request.getContextPath(); //프로젝트 패스가 뭐인지 알아보자
		System.out.println("contextPath : " + contextPath);
		
		String command = requestURI.substring(contextPath.length());
		System.out.println("command : " + command);
		
		System.out.println("1. 가상주소 계산하기 끝! \n\n");
		/************************ 1. 가상 주소 계산 ************************/

		
		
		/************************ 2. 가상 주소 처리 (매핑) ******************/
		System.out.println("\n\n 2. 가상주소 처리 (매핑)하기");
		
		//모델 객체 처리를 위한 객체
		Action action = null;
		
		//이동정보를 처리하는 객체
		ActionForward forward = null;
		
		if(command.equals("/BoardWrite.bo")){
			System.out.println("C : /BorardWrite.bo  호출");
			System.out.println("C : view페이지 이동 ");
			
			forward = new ActionForward();
			forward.setPath("./board/writeForm.jsp");
			forward.setRedirect(false);
		}	
			
		else if(command.equals("/BoardWriteAction.bo")) {
			System.out.println("C : /BoardWriteAction.bo 호출");
			System.out.println("C :DB 사용 글쓰기 -> 페이지 이동 ");
			
			//BoardWriteAction 객체 생성
			action = new BoardWriteAction();
			
			try {
				forward = action.execute(request, response);
				
			} catch (Exception e) {
				e.printStackTrace();	
			}
			
		}
		
		else if(command.equals("/BoardList.bo")) {
			System.out.println("C : /BoardList.bo 호출");
			System.out.println("C : DB를 거쳐서 데이터를 가지고 jsp페이지 이동 ");
			
			//BoardListAction 객체 생성
			action = new BoardListAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
		//DB동작이 필요할시 자바에서 만들어야함.
		else if(command.equals("/BoardContent.bo")) {
			System.out.println("C : /BoardContent.bo 호출");
			System.out.println("C : DB정보를 가져와서 화면 출력 "); //객체 필요함<-
			
			//BoardContentAction 객체 생성
			action = new BoardContentAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		
		else if(command.equals("/BoardUpdate.bo")) {
			System.out.println("C : /BoardUpdate.bo 호출");
			System.out.println("C : DB정보를 가져와서 화면 출력 "); //객체 필요함<-
			
			//BoardUpdate  객체 생성
			action = new BoardUpdate();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		
		else if(command.equals("/BoardUpdateAction.bo")) {
			System.out.println("C : /BoardUpdateAction.bo 호출");
			System.out.println("C : DB데이터 사용 -> 페이지 이동(보드리스트)");
			
			//BoardUpdateAction 객체 생성
			action = new BoardUpdateAction();
			
			try {
				forward = action.execute(request, response);
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		else if(command.equals("/BoardDelete.bo")) {
			System.out.println("C : /BoardDelete.bo 호출");
			System.out.println("C : DB사용없이 페이지 이동");
			
			forward = new ActionForward();
			forward.setPath("./board/deleteForm.jsp");
			forward.setRedirect(false);
			
		}
		
		else if(command.equals("/BoardDeleteAction.bo")) {
			System.out.println("C : /BoardDeleteAction.bo 호출");
			System.out.println("C : DB데이터 처리 -> 페이지 이동(리스트)");
			
			//BoardDeleteAction 객체 생성
			action = new BoardDeleteAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
		}
		
		else if(command.equals("/BoardReWrite.bo")) {
			System.out.println("C : /BoardReWrite.bo 호출");
			System.out.println("C : DB데이터 처리 없이 페이지 이동");
			
			forward = new ActionForward();
			forward.setPath("./board/reWriteForm.jsp");
			forward.setRedirect(false);
			
			
		}
		
		
		else if(command.equals("/BoardReWriteAction.bo")) {
			System.out.println("C : /BoardReWriteAction.bo 호출");
			System.out.println("C : DB데이터 처리 -> 페이지 이동(list)");
			
			//BoardReWriteAction 객체 생성
			action = new BoardReWriteAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
		}
		
		else if(command.equals("/BoardListBack.bo")) { //BoardListBack.bo
			System.out.println("C : /BoardListBack.bo");
			System.out.println("C : BoardListBackAction 객체 생성"); //객체 만들어서 초기화 시킨다음 이동
			
			//BoardListBackAction()객체 생성
			action = new BoardListBackAction();
			
			try {
				forward= action.execute(request, response);
			}catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		else if(command.equals("/BoardDelete.bo")) {
			System.out.println("C : /BoardDelete.bo 호출");
			System.out.println("C : DB사용없이 페이지 이동");
			
			forward = new ActionForward();
			forward.setPath("./board/deleteForm.jsp");
			forward.setRedirect(false);
		}
		
		else if(command.equals("/MailAdmin.bo")) {//고객 문의메일양식 
			System.out.println("C: /MailMe.bo 호출");
			System.out.println("C: DB이용 없이 바로 뷰파일 이동");
			
			forward = new ActionForward();
			forward.setPath("./board/mailForm.jsp");
			forward.setRedirect(false);
			
		}
		
		else if(command.equals("/MailSendAction.bo")) {//메일보내기 액션
			System.out.println("C:MailSendAction.bo 호출");
			System.out.println("C:MailSendAction 객체 생성");
			
			action = new MailSendAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		

	
		else if(command.equals("/FileBoardWriteAction.bo")) {
			System.out.println("C : FileBoardWriteAction.bo 실행!");
			System.out.println("C : 파일업로드 -> DB(파일이름 저장) -> 리스트출력");
			
			action = new FileBoardWriteAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}
		
		else if(command.equals("/FileDown.bo")) {
			System.out.println("C : FileDown.bo 실행!");
			System.out.println("C : 파일다운로드 -> DB(파일이름 꺼내오기) -> 객체 생성");
			
			action = new FileDownAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		
		
		else if(command.equals("/ThumbBoard.bo")) {
			System.out.println("C : ThumbBoard.bo 실행!");
			System.out.println("C : 파일업로드 -> DB(파일이름 저장) -> 리스트출력");
			
			action = new ThumbBoardAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}
		
		
		
		else if(command.equals("/SearchAction.bo")) {
			System.out.println("C : SearchAction.bo 실행!");
			System.out.println("C : C : DB데이터 사용 -> 페이지 이동(보드리스트)");
			
			action = new searchAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		
		
		else if(command.equals("/FileWrite.bo")) {
			System.out.println("C: /FileWrite.bo 이동");
			System.out.println("C: view페이지로 이동");
			
			forward = new ActionForward();
			forward.setPath("./board/fileWriteForm.jsp");
			forward.setRedirect(false);
		}
		
		else if(command.equals("/BoardLike.bo")) {
			System.out.println("C: /BoardLikeAction.bo 이동");
			System.out.println("C : DB정보를 가져와서 화면 출력 "); //객체 
			
			//LikeAction 객체 생성
			action = new LikeAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
		}	
		
		
		
		
		System.out.println("2. 가상주소 처리 (매핑)하기 끝 \n\n");	
		/************************ 2. 가상 주소 처리 (매핑) ******************/
		
		
		
		
		
		
		/************************ 3. 페이지 이동 *************************/
		System.out.println("\n\n 3. 페이지 이동");
		
		if(forward != null) {
			if(forward.isRedirect()) {//true
				System.out.println("C: 페이지 이동(sendRedirect)");
				response.sendRedirect(forward.getPath());
			}else {//false
				System.out.println("C : 페이지 이동(forward)");
				
				RequestDispatcher dis = 
						request.getRequestDispatcher(forward.getPath());
				
				dis.forward(request, response);
			}
		
		
		}
		
		System.out.println("\n\n 3. 페이지 이동 끝");
		/************************ 3. 페이지 이동 *************************/
		/////////////////////////3. 가상 주소 이동 끝/////////////////////////
		
	}
	
	@Override
	protected void doGet(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println("C: doGet() 호출!"); doProcess(request, response);
		
	}

	@Override
	protected void doPost(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println("C: doPost() 호출!"); doProcess(request, response);

	}

	
	
	
}
