package net.user.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class UserFrontController extends HttpServlet{

	
	protected void doProcess(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("c: doProcess()호출-UserFrontController");
	
		System.out.println("\n\n 가상주소계산하기");
		
		System.out.println("\n\n 가상주소계산하기");
		String requestURI = request.getRequestURI();
		System.out.println("requestURI: "+requestURI);
		
		String contextPath = request.getContextPath();
		System.out.println("contextPath: "+contextPath);
		
		String command = requestURI.substring(contextPath.length());
		System.out.println("command : "+command);
		
		Action action = null;
		ActionForward forward = null;
		
		if(command.equals("/userContentView.us")) {
			System.out.println("c: /userContentView.us 호출");
			System.out.println("c: DB사용-> 페이지 이동");
			
			action = new userContentViewAction();
			
		  try {	
			   forward = action.execute(request, response);
			
		  }catch (Exception e) {

		       e.printStackTrace();
		  }
		}
		else if(command.equals("/storymarket_main.us")) {
			  System.out.println("c: /storymarket_main.us 호출");
			  System.out.println("c: DB사용(x)-> 메인 페이지 이동");
			  
			  forward = new ActionForward();
			  forward.setPath("./user/storymarket_main.jsp");
              forward.setRedirect(false);
              
		}
		
		
		System.out.println("가상 주소처리(매핑) \n\n ");
		/*************************************************************/
			if(forward != null) {
				
				if(forward.isRedirect()) {
					System.out.println("c : 페이지 이동(sendRedirect)");
					response.sendRedirect(forward.getPath());
					
				}else {
					System.out.println("c : 페이지 이동(forward)");
					
					RequestDispatcher dis =
							request.getRequestDispatcher(forward.getPath());
					
					dis.forward(request, response);
				}
				
				
				
				
				
			}
			
			
		   System.out.println("3.페이지 이동 \n\n");
			
			
		}
	
	@Override
	protected void doGet(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
	
		System.out.println("c: doGet()호출-UserFrontController");
		doProcess(request, response);
		
	
	}

	@Override
	protected void doPost(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
	
		System.out.println("c: doPost()호출-UserFrontController");
		doProcess(request, response);
	
	}
	
	
}
