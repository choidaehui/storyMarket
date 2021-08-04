package net.member.action;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.renderable.ParameterBlock;
import java.io.File;
import java.sql.Timestamp;
import java.util.Enumeration;

import javax.imageio.ImageIO;
import javax.media.jai.JAI;
import javax.media.jai.RenderedOp;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import net.member.db.MemberBean;
import net.member.db.MemberDAO;

public class MemberJoinAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("MemberJoinAction_execute() 호출");
		
		//한글 처리
		request.setCharacterEncoding("UTF-8");
		
		ServletContext context = request.getServletContext();
		String imagePath = context.getRealPath("memberimg");
		System.out.println("파일이 저장되는 실제 경로 :"+imagePath);
		
		int maxSize = 10 * 1024 * 1024;
		
		String filename="";
		
		 try{
	    	 //생성자 역활
		     MultipartRequest multi 
		            = new MultipartRequest(
		            		request,
		            		imagePath,
		            		maxSize,
		            		"UTF-8",
		            		new DefaultFileRenamePolicy()
		            		);
		     
		     System.out.println("파일업로드 성공!");
		     
		     Enumeration files = multi.getFileNames();
		     String file = (String)files.nextElement();
		     filename = multi.getFilesystemName(file);
		     
		     if(filename == null) {
		    	 filename = "member_img_sy.JPG";
		     }
		     //DB에 file정보 올리기 
		 		//전달된 정보 (파라미터)저장
		 		
		     	//전달되는 데이터 저장
				MemberBean mb = new MemberBean();
				
		 		mb.setImgname(filename);
		 		mb.setThumbname("sm_"+filename);
		 		mb.setAccount(multi.getParameter("account"));
				mb.setAddress(multi.getParameter("address"));
				mb.setPhone(multi.getParameter("phone"));
				mb.setEmail(multi.getParameter("email"));
				mb.setId(multi.getParameter("id"));
				mb.setName(multi.getParameter("name"));
				mb.setPass(multi.getParameter("pass"));
				
				mb.setReg_date(new Timestamp(System.currentTimeMillis()));
		 		
				System.out.println("전달된 회원정보 저장완료!");
				System.out.println(mb);
		 		
				//DB처리 (DAO 객체 생성)
				MemberDAO mdao = new MemberDAO();
				
				//회원 가입 메서드
				mdao.insertMember(mb);
				
		 		
		     
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
		 
		
		
		//페이지 이동->이동정보만 저장해서 종료(값을 리턴)
		//회원가입 : /MemberJoinAction.me
		//로그인 페이지 : /MemberLogin.me
		ActionForward forward = new ActionForward();
		forward.setPath("./MemberLogin.me");
		forward.setRedirect(true);
		
		return forward;
	}

	
}
