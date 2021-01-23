package net.board.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BoardFrontController extends HttpServlet{

	

	protected void doProcess(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
	
		System.out.println("c: doProcess()호출-BoardFrontController");
		
		
		System.out.println("\n\n 가상주소계산하기");
		String requestURI = request.getRequestURI();
		System.out.println("requestURI: "+requestURI);
		
		String contextPath = request.getContextPath();
		System.out.println("contextPath: "+contextPath);
		
		String command = requestURI.substring(contextPath.length());
		System.out.println("command : "+command);
		
		Action action = null;
		ActionForward forward = null;
		
		
	
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	@Override
	protected void doGet(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
	
		System.out.println("c: doGet()호출-BoardFrontController");
		doProcess(request, response);
		
	
	}

	@Override
	protected void doPost(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
	
		System.out.println("c: doPost()호출-BoardFrontController");
		doProcess(request, response);
	
	}
	
	

}
