package net.member.action;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.renderable.ParameterBlock;
import java.io.File;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.Enumeration;

import javax.imageio.ImageIO;
import javax.media.jai.JAI;
import javax.media.jai.RenderedOp;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import net.member.db.MemberBean;
import net.member.db.MemberDAO;

public class MemberUpdateAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println("M : MemberUpdateAction_execute() 호출");
		
		
		//한글 처리
				request.setCharacterEncoding("UTF-8");
				
				ServletContext context = request.getServletContext();
				String imagePath = context.getRealPath("memberimg");
				System.out.println("파일이 저장되는 실제 경로 :"+imagePath);
				
				int maxSize = 10 * 1024 * 1024;
				int check = -1;
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
				     
				     
				     //DB에 file정보 올리기 
				 		//전달된 정보 (파라미터)저장
				 		
				     	//전달되는 데이터 저장
						MemberBean updateInfoMemberBean = new MemberBean();
						
						if(filename != null) {
							updateInfoMemberBean.setImgname(filename);
							updateInfoMemberBean.setThumbname("sm_"+filename);
						}else {
							updateInfoMemberBean.setImgname(multi.getParameter("orifilename"));
							updateInfoMemberBean.setThumbname("sm_"+multi.getParameter("orifilename"));
						}
						
						updateInfoMemberBean.setAccount(multi.getParameter("account"));
						updateInfoMemberBean.setAddress(multi.getParameter("address"));
						updateInfoMemberBean.setPhone(multi.getParameter("phone"));
						updateInfoMemberBean.setEmail(multi.getParameter("email"));
						updateInfoMemberBean.setId(multi.getParameter("id"));
						updateInfoMemberBean.setName(multi.getParameter("name"));
						updateInfoMemberBean.setPass(multi.getParameter("pass"));
				 		
						System.out.println("M : 수정 정보 저장 완료!");
						System.out.println(updateInfoMemberBean);
				 		
						
						 //해당 자바빈객체를 가지고 DB이동 -> DAO 객체 생성 -> updateMember(updateInfoMemberBean, filename, imagePath)
						 MemberDAO mdao = new MemberDAO();
						 check = mdao.updateMember(updateInfoMemberBean, filename, imagePath);
				 		
				     
			     }catch(Exception e){
			    	 System.out.println("파일 업로드 실패!!!!");
			    	 e.printStackTrace();
			     }
				 
			 if(filename != null) {
				 
				 ParameterBlock pb = new ParameterBlock();
				 pb.add(imagePath+"/"+filename);
				 RenderedOp rOp = JAI.create("fileload",pb);
				 
				 BufferedImage bi = rOp.getAsBufferedImage();
				 BufferedImage thumb = new BufferedImage(370,395,BufferedImage.TYPE_INT_RGB);
				 Graphics2D g = thumb.createGraphics();
				 g.drawImage(bi, 0, 0, 370, 395, null);
				 File file = new File(imagePath+"/sm_"+filename);
				 ImageIO.write(thumb, "jpg", file);
			 }
		
				 System.out.println("M : DB 처리결과 =>"+check);
		
				 //처리결과를 리턴받아서 페이지 이동(자바스크립트 사용 이동)
				 //수정성공 - 1
				 //비밀번호 오류 - 0
				 //아이디가 없음 - -1
		
				 //페이지응답 정보를 설정
				 response.setContentType("text/html; charset=UTF-8");
		
				 //응답 정보를 출력하는 통로(IO스트림)
				 PrintWriter out = response.getWriter();
		
				 if(check == 0) {
				out.print("<script>");
				out.print(" alert('오류!');");
				out.print(" history.back(); ");
				out.print("</script>");
				out.close();
				
				//컨트롤러를 사용한 페이지 이동을 사용하지 않기 위해서
				return null;
			}else if(check == -1) {
				out.print("<script>");
				out.print(" alert('아이디 없음!');");
				out.print(" history.back(); ");
				out.print("</script>");
				out.close();
				
				return null;
			}else {
			
				//check == 1
				out.print("<script>");
				out.print(" alert('수정성공!');");
				out.print(" location.href='./Main.me'; ");
				out.print("</script>");
				out.close();
			
				return null;
			}

		
	}
}
