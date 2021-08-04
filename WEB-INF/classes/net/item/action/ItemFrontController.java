package net.item.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ItemFrontController extends HttpServlet{

	

	protected void doProcess(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
	
		System.out.println("c: doProcess()호출-ItemFrontController");
		
		
		System.out.println("\n\n 가상주소계산하기");
		String requestURI = request.getRequestURI();
		System.out.println("requestURI: "+requestURI);
		
		String contextPath = request.getContextPath();
		System.out.println("contextPath: "+contextPath);
		
		String command = requestURI.substring(contextPath.length());
		System.out.println("command : "+command);
		
		Action action = null;
		ActionForward forward = null;
		
		if(command.equals("/itemWriteForm.it")) {
			System.out.println("c: /itemWriteForm.it호출");
			System.out.println("c: view 페이지로 이동");
			
		    forward = new ActionForward();
		    forward.setPath("./item/itemWriteForm.jsp");
			forward.setRedirect(false);
			
		}
		
		else if(command.equals("/itemWriteAction.it")) {
			System.out.println("c: /itemWriteAction.it 호출");
			System.out.println("c: DB사용(o)->페이지 이동");
			
			action = new itemWriteAction();
			
			try {
				
				forward = action.execute(request, response);
			}catch(Exception e) {
				
				e.printStackTrace();
			}
			
		}
		else if(command.equals("/itemListAction.it")) {
			System.out.println("c: /itemListAction.it 호출");
			System.out.println("c: DB사용(o)-> 페이지 이동");
		
			action = new itemListAction();
			
			try {
				
				forward = action.execute(request, response);
			}catch(Exception e) {
				
				e.printStackTrace();
			}
			
		}
		
		
		else if(command.equals("/storymarket_main.it")) {
		     System.out.println("c: /stroymarket_main.it 호출");
		     System.out.println("c: DB사용(x)->뷰페이지 이동");
		  
		     forward = new ActionForward();
		     
		     forward.setPath("./item/storymarket_main.jsp");
		     forward.setRedirect(false);
		  }
		else if(command.equals("/tradeItem.it")) {
			System.out.println("C: /tradeItem.it 호출");
			System.out.println("c: DB사용(o)-> 페이지이동");
			
			action = new tradeItemAction();
			
			try {
				
				forward = action.execute(request, response);
			
			} catch (Exception e) {

				
				e.printStackTrace();
			}
			
			
		}
		 
	    System.out.println("가상주소처리(매핑) \n\n");
/***************************************************************/
	    if(forward != null) {
	    	
	    	if(forward.isRedirect()) {
	    		System.out.println("c: 페이지 이동(sendRedirect)");
	    		response.sendRedirect(forward.getPath());
	    		
	    	}else {
	    		System.out.println("c: 페이지 이동(forward)");
	    		
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
	
		System.out.println("c: doGet()호출-ItemFrontController");
		doProcess(request, response);
		
	
	}

	@Override
	protected void doPost(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
	
		System.out.println("c: doPost()호출-ItemFrontController");
		doProcess(request, response);
	
	}
	
	

}
