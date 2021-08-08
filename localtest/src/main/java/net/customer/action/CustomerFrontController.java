package net.customer.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CustomerFrontController extends HttpServlet{

	protected void doProcess(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
	
		System.out.println("c: doProcess()호출-CustomerFrontController");
		
		
		System.out.println("\n\n 가상주소계산하기");
		String requestURI = request.getRequestURI();
		System.out.println("requestURI: "+requestURI);
		
		String contextPath = request.getContextPath();
		System.out.println("contextPath: "+contextPath);
		
		String command = requestURI.substring(contextPath.length());
		System.out.println("command : "+command);
		
		Action action = null;
		ActionForward forward = null;
		
		if(command.equals("/customerView.cu")) {
			System.out.println("c: /customerView.cu 호출");
			System.out.println("c: DB사용x-> 페이지 이동");
			
			forward = new ActionForward();
			forward.setPath("./customer/customerView.jsp");
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
