package net.member.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class MemberFrontController extends HttpServlet{

	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		System.out.println("c: doProcess()호출-MemberFrontController");
		
		
		System.out.println("\n\n 1. 가상주소계산하기");
		
		String requestURI = request.getRequestURI();
		System.out.println("requestURI: "+requestURI);
		
		String contextPath = request.getContextPath();
		System.out.println("contextPath: "+contextPath);
		
		String command = requestURI.substring(contextPath.length());
		System.out.println("command : "+command);
		System.out.println(" 1. 가상주소계산하기\n\n");
///////////////////////////////////////////////////////////////////////////////////////////////////////////////
		System.out.println("\n\n 2. 가상주소 페이지 처리");
		
		Action action = null;
		ActionForward forward = null;
		
//회원가입
		if(command.equals("/MemberJoin.me")) {
			System.out.println("/MemberJoin.me 회원가입 입력창 처리");
			System.out.println(" view페이지로 이동(forward): ./member/join.jsp");
			
			forward = new ActionForward();
			forward.setPath("./member/join.jsp");
			forward.setRedirect(false);
		}
							   
		else if(command.equals("/MemberJoinAction.me")) {
			System.out.println("/MemberJoinAction.me 페이지 호출 (회원가입)");
			System.out.println("model 페이지 이동 (자바 페이지 이동)");
			
			//MemberJoinAction객체 생성
			action = new MemberJoinAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
		else if(command.equals("/member/MemberIdCheckAction.me")) {
			System.out.println("/MemberIdCheckAction.me 페이지 호출 (중복검사)");
			
			//MemberIdCheckAction 객체 생성
			action = new MemberIdCheckAction();
			
			try {
				action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
	
		}
		
//로그인			
		else if(command.equals("/MemberLogin.me")) {
			System.out.println("c : /MemberLogin.me 주소호출");
			System.out.println("c : DB이동 없이 view페이지 이동(/member/loginForm.jsp)");
			
			forward = new ActionForward();
			forward.setPath("./member/loginForm.jsp");
			forward.setRedirect(false);
			
		}
		
		else if(command.equals("member/MemberLogin.me")) {
			System.out.println("c : /MemberLogin.me 주소호출");
			System.out.println("c : DB이동 없이 view페이지 이동(/member/loginForm.jsp)");
			
			forward = new ActionForward();
			forward.setPath("./member/loginForm.jsp");
			forward.setRedirect(false);
			
		}
		else if(command.equals("/MemberLoginAction.me")) {
			System.out.println("c : /MemberLoginAction.me 주소호출");
			System.out.println("c : DB이동후 로그인 체크");
			///MemberLoginAction() 객체 생성
			
			action = new MemberLoginAction();
			
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/Main.me")) {
			System.out.println("C : /Main.me 주소 호출");
			System.out.println("C : ./member/main.jsp 페이지로 이동(view)");
			
			forward = new ActionForward();
			forward.setPath("./member/main.jsp");
			forward.setRedirect(false);
		}
		
		else if(command.equals("/StoryMarketmain.me")) {
			System.out.println("C : /StoryMarketmain.me 주소 호출");
			System.out.println("C : ./storymarket_main.jsp 페이지로 이동(view)");
			
			forward = new ActionForward();
			forward.setPath("./storymarket_main.jsp");
			forward.setRedirect(false);
		}
		
//로그아웃
		else if(command.equals("/MemberLogout.me")) {
			System.out.println("C : /MemberLogout.me 주소 호출");
			System.out.println("C : MemberLogoutAction 객체 생성");
			
			//MemberLogoutAction() 객체생성
			action = new MemberLogoutAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
		}

//개인정보 확인
		else if(command.equals("/MyInfo.me")) {
			System.out.println("C : /MyInfo.me 주소 호출");
			System.out.println("C : ./member/myinfo.jsp 페이지로 이동(view)");
			
			forward = new ActionForward();
			forward.setPath("./member/myinfo.jsp");
			forward.setRedirect(false);
		}
		
		else if(command.equals("/MemberInfoAction.me")) {
			System.out.println("C : /MemberInfoAction.me 주소 호출");
			System.out.println("C : DB 정보 필요 -> 객체생성-> 화면출력");
			//MemberInfoAction()객체생성
			
			action = new MemberInfoAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
//이메일 인증
		else if(command.equals("/IdentityCheck.me")) {
			System.out.println("C : /IdentityCheck.me 주소 호출");
			System.out.println("C : ./member/identityCheck.jsp 페이지로 이동(view)");
			
			forward = new ActionForward();
			forward.setPath("./member/identityCheck.jsp");
			forward.setRedirect(false);
			
		}
		
		else if (command.equals("/IdentityCheckAction.me")) {
			System.out.println("C : /IdentityCheckAction.me 주소 호출");
			System.out.println("C : IdentityCheckAction 객체 생성");
			
			action = new IdentityCheckAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
		else if (command.equals("/member/EmailIdentityCheckAction.me")) {
			System.out.println("C : /EmailIdentityCheckAction.me 주소 호출");
			System.out.println("C : EmailIdentityCheckAction 객체 생성");
			
			action = new EmailIdentityCheckAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
//개인정보 수정		
		else if(command.equals("/MemberUpdate.me")) {
			System.out.println("C : /MemberUpdate.me주소 호출");
			System.out.println("C : MemberUpdate.me 객체 생성");
			
			action = new MemberUpdate();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		else if(command.equals("/MemberUpdateAction.me")) {
			System.out.println("C : /MemberUpdateAction.me주소 호출");
			System.out.println("C : DB 사용 -> 페이지 이동(화면출력x)");
			
			// MemberUpdateAction() 객체 생성
	    	action = new MemberUpdateAction();
	    	
	    	try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
	    	
		}
		
//회원 탈퇴
		else if(command.equals("/MemberDelete.me")) {
			System.out.println("C : /MemberDelete.me 호출");
	    	System.out.println("C : DB사용X ->view 페이지 이동(jsp)");
	    	
	    	forward = new ActionForward();
	    	forward.setPath("./member/deleteForm.jsp");
	    	forward.setRedirect(false);
		}
		
		else if(command.equals("/MemberDeleteAction.me")) {
			System.out.println("C : /MemberDeleteAction.me 호출");
	    	System.out.println("C : DB 사용 -> 출력x,페이지 이동");
	    	
	    	// MemberDeleteAction() 객체 
	    	action =  new MemberDeleteAction();
	    	
	    	try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
//계정 찾기
		else if(command.equals("/SearchID.me")) {
			System.out.println("C : /SearchID.me 주소 호출");
			System.out.println("C : ./member/searchIdForm.jsp 페이지로 이동(view)");
			
			forward = new ActionForward();
			forward.setPath("./member/searchIdForm.jsp");
			forward.setRedirect(false);
		}
		
		else if(command.equals("/SearchPass.me")) {
			System.out.println("C : /SearchPass.me 주소 호출");
			System.out.println("C : ./member/searchPassForm.jsp 페이지로 이동(view)");
			
			forward = new ActionForward();
			forward.setPath("./member/searchPassForm.jsp");
			forward.setRedirect(false);
		}
		
		else if(command.equals("/SearchIDCheck.me")) {
			System.out.println("C : /SearchIDCheck.me 주소 호출");
			System.out.println("C : ./member/searchIdCheck.jsp 페이지로 이동(view)");
			
			forward = new ActionForward();
			forward.setPath("./member/searchIdCheck.jsp");
			forward.setRedirect(false);
		}
		
		else if(command.equals("/SearchPassCheck.me")) {
			System.out.println("C : /SearchPassCheck.me 주소 호출");
			System.out.println("C : ./member/searchPassCheck.jsp 페이지로 이동(view)");
			
			forward = new ActionForward();
			forward.setPath("./member/searchPassCheck.jsp");
			forward.setRedirect(false);
		}
		
		else if(command.equals("/member/ID.me")) {
			System.out.println("C : /ID.me주소 호출");
			System.out.println("C : MemberID 객체 생성");
			
			action = new MemberID();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		else if(command.equals("/member/Pass.me")) {
			System.out.println("C : /Pass.me주소 호출");
			System.out.println("C : MemberPass 객체 생성");
			
			action = new MemberPass();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		else if(command.equals("/MemberPassUpdateAction.me")) {
			System.out.println("C : /MemberPassUpdateAction.me주소 호출");
			System.out.println("C : DB 사용 -> 페이지 이동(화면출력x)");
			
			// MemberUpdateAction() 객체 생성
	    	action = new MemberPassUpdateAction();
	    	
	    	try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
	    	
		}
		
		System.out.println("\n\n 2. 가상주소 페이지 처리");
///////////////////////////////////////////////////////////////////////////////////////////////////////////
		System.out.println("\n\n 3. 페이지 이동(모든 페이지 총괄)");
		//이동할 주소정보가 있는 경우
				if(forward != null) {
					//페이지 이동
					if(forward.isRedirect()) {//true
						System.out.println("true - sendRedirect 방식");
						response.sendRedirect(forward.getPath());
					}else {//false
						System.out.println("false - forward 방식");
						RequestDispatcher dis = request.getRequestDispatcher(forward.getPath());
						
						dis.forward(request, response);
					}
				}
		System.out.println(" 3. 페이지 이동(모든 페이지 총괄)\n\n");
	
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	@Override
	protected void doGet(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
	
		System.out.println("c: doGet()호출-MemberFrontController");
		doProcess(request, response);
		
	
	}

	@Override
	protected void doPost(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
	
		System.out.println("c: doPost()호출-MemberFrontController");
		doProcess(request, response);
	
	}
	
	

}
