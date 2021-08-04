package net.board.action;

import net.board.db.ThumbBean;
import net.board.db.ThumbDAO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import java.awt.Graphics2D;
import java.awt.image.renderable.ParameterBlock;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.media.jai.JAI;
import javax.media.jai.RenderedOp;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import java.util.*;
import java.io.*;

public class ThumbBoardAction implements Action {


		@Override
		public ActionForward execute(HttpServletRequest request, 
				HttpServletResponse response) throws Exception {
		
		
		//파일업로드
		//파일을 업로드할 폴더 생성 /upload
		
		//실제 파일이 저장될 서버의 경로 확인
	
		ServletContext context = request.getServletContext();
		String imagePath = context.getRealPath("img");
		System.out.println("파일이 저장되는 실제 경로 :"+imagePath);
		
		int maxSize = 10 * 1024 * 1024;
		
		String filename="";
		String meal="";
		String price="";
		 try{
	    	 //생성자 역활
		     MultipartRequest multi 
		            = new MultipartRequest(
		            		request,
		            		imagePath,
		            		maxSize,
		            		"euc-kr",
		            		new DefaultFileRenamePolicy()
		            		);
		     System.out.println("파일업로드 성공!");
		     
		     Enumeration files = multi.getFileNames();
		     String file = (String)files.nextElement();
		     filename = multi.getFilesystemName(file);
		     meal= multi.getParameter("meal");
		     price= multi.getParameter("price");
		     
		     
		     //DB에 file정보 올리기 
		 		//전달된 정보 (파라미터)저장
		 		//글쓴이, 비밀번호, 제목, 내용
		 		//=>액션태그 사용 저장
		 		
		 		
				ThumbBean tb = new ThumbBean();
		 		tb.setImgname(filename);
		 		tb.setThumbname("sm_"+filename);

		 		
		 		System.out.println(tb);
		 		
		 		System.out.println(tb);
		 		//ThumbDAO 객체생성
		 		//- insertBoard();
		 		ThumbDAO tdao = new ThumbDAO();
		 		
		 		tdao.insertFile(tb);
		     
	     }catch(Exception e){
	    	 System.out.println("파일 업로드 실패!!!!");
	    	 e.printStackTrace();
	     }

		 ParameterBlock pb = new ParameterBlock();
		 pb.add(imagePath+"/"+filename);
		 
		 RenderedOp rOp = JAI.create("fileload",pb);
		 
		 BufferedImage bi = rOp.getAsBufferedImage();
		 BufferedImage thumb = new BufferedImage(370,395,BufferedImage.TYPE_INT_RGB);
		 Graphics2D g = thumb.createGraphics();
		 g.drawImage(bi, 0, 0, 370, 395, null);
		 File file = new File(imagePath+"/sm_"+filename);
		 ImageIO.write(thumb, "jpg", file);
		 
	
		 
		 
			/*
			 * <img src="img/<%=filename%>"> <a href="#.jsp">
			 */

	
			//페이지이동
			ActionForward forward = new ActionForward();
			forward.setPath("./BoardList.bo");
			forward.setRedirect(true);
			return forward;
	
	
		}
}
