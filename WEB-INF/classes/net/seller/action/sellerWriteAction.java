package net.seller.action;

import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import net.seller.db.SellerBean;
import net.seller.db.SellerDAO;
import net.user.db.userBean;

public class sellerWriteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        
		System.out.println("M : sellerWriteAction_execute()호출");
		
	    request.setCharacterEncoding("UTF-8");
	    
	    ServletContext context = request.getServletContext();
	    String realPath = context.getRealPath("/upload");
	    
	    int maxSize = 100 * 1024 * 1024;
	   
	    
     try {
	    MultipartRequest multi = 
	    		new MultipartRequest(
	    				request,
	    				realPath,
	    				maxSize,
	    				"utf-8",
	    				new DefaultFileRenamePolicy()
	    				);
	    
	    System.out.println("M : 파일 업로드 완료!");
	    
		
		  Enumeration files = multi.getFileNames(); 
		  String file = (String)files.nextElement(); 
		  file = multi.getFilesystemName(file);
		  String image = (String)files.nextElement();
		  image = multi.getFilesystemName(image);
		 
	    
	    SellerDAO sdao = new SellerDAO();
	    int seller_num = sdao.maxNum();
	    

		SellerBean sb = new SellerBean();

	
		sb.setSeller_num(seller_num);
		sb.setCopy_text(multi.getParameter("copy_text"));
		sb.setMessage(multi.getParameter("message"));
		sb.setSeller_id(multi.getParameter("seller_id"));
		
		//판매자와 유저 데이터 베이스 같은 내용
		userBean ub = new userBean();
		
		ub.setUser_num(seller_num);
		ub.setS_copy_text(multi.getParameter("copy_text"));
		ub.setS_message(multi.getParameter("message"));
		ub.setUser_id(multi.getParameter("seller_id"));
		
		
		System.out.println("M : 파일이름-> "+image+file);
		
		sb.setImage(image);
		sb.setFile(file);
        
		ub.setS_image(image);
		ub.setS_file(file);
		
		
		
		System.out.println("M : 전달된 정보"+sb);
		System.out.println("M : 전달된 정보"+ub);
			        
	     sdao.insertSeller(sb);
	        
	     System.out.println("insertSeller 메소드 실행!"+sb);
	     
			
	 	
			
		HttpSession session = request.getSession();	
        session.setAttribute("sb", sb);
        session.setAttribute("ub", ub);
       
     }catch(Exception e) {
    	 
    	 e.printStackTrace();
     }
 
        ActionForward forward = new ActionForward();
        forward.setPath("./sellerList.se");
        forward.setRedirect(true);
		       
		return forward;
		
		
	}
	  
}
