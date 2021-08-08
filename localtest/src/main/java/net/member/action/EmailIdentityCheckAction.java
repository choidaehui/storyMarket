package net.member.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.member.db.MemberDAO;


@WebServlet("/EmailIdentityCheckAction")
public class EmailIdentityCheckAction extends HttpServlet implements Action {
	private static final long serialVersionUID = 1L;

	@Override
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	       
	}

		@Override
		public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
			
			System.out.println("EmailIdentityCheckAction_execute");
			
			String email = request.getParameter("receiver"); //사용자가 입력한 이메일 받아오기
			request.setAttribute("email", email);
			int mapnum = Integer.parseInt(request.getParameter("mapnum")); //mapnum받아오기
			
			System.out.println(email);
			MemberDAO dao = new MemberDAO();
	        boolean result = dao.duplicateEmailCheck(email);
	        
	        //페이지응답 정보를 설정
			 response.setContentType("text/html; charset=UTF-8");
	
			 //응답 정보를 출력하는 통로(IO스트림)
			 PrintWriter out = response.getWriter();
			 if(mapnum==0) {//이메일 인증
		        if(result) {
					out.print("<script>");
					out.print(" alert('이미존재하는 이메일입니다.');");
					out.print(" history.back(); ");
					out.print("</script>");
					out.close();
					
					//컨트롤러를 사용한 페이지 이동을 사용하지 않기 위해서
					return null;
				}
			 }else if(mapnum==1) {//id찾기
				 if(result) {
						
					}else {
						out.print("<script>");
						out.print(" alert('없는 이메일입니다.');");
						out.print(" history.back(); ");
						out.print("</script>");
						out.close();
						
						//컨트롤러를 사용한 페이지 이동을 사용하지 않기 위해서
						return null;
					}
			 }else if(mapnum==2) {//비밀번호 찾기
				 String id = request.getParameter("id");
				 if(result) {
						boolean resultID = dao.duplicateIdCheck(id, email);
						if(resultID) {
							
						}else {
							out.print("<script>");
							out.print(" alert('없는 이메일이거나 없는 아이디 입니다.');");
							out.print(" history.back(); ");
							out.print("</script>");
							out.close();
							
							return null;
						}
					}else {
						out.print("<script>");
						out.print(" alert('없는 이메일입니다.');");
						out.print(" history.back(); ");
						out.print("</script>");
						out.close();
						
						//컨트롤러를 사용한 페이지 이동을 사용하지 않기 위해서
						return null;
					}
			 }
	        
	        
			ActionForward forward = new ActionForward();
			
			Properties props = System.getProperties();
	        props.put("mail.smtp.user", "snowfox975"); // 서버 아이디만 쓰기
			props.put("mail.smtp.host", "smtp.gmail.com"); // 구글 SMTP
			props.put("mail.smtp.port", "465");
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.socketFactory.port", "465");
			props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			props.put("mail.smtp.socketFactory.fallback", "false");
	           
	        Authenticator auth = new MyAuthentication();
	         
	        //session 생성 및  MimeMessage생성
	        Session session1 = Session.getDefaultInstance(props, auth);
	        MimeMessage msg = new MimeMessage(session1);
	         
	        try{
	            //편지보낸시간
	            msg.setSentDate(new Date());
	             
	            InternetAddress from = new InternetAddress("snowfox975@gmail.com");             

	            // 이메일 발신자
	            msg.setFrom(from);           
	             
	            // 이메일 수신자
	            
	            InternetAddress to = new InternetAddress(email);
	            msg.setRecipient(Message.RecipientType.TO, to);
	             
	            // 이메일 제목
	            msg.setSubject("비밀번호 인증번호", "UTF-8");
	             
	            // 이메일 내용 

	            String code = request.getParameter("codecheck"); //인증번호 값 받기
	            request.setAttribute("code", code);
	            msg.setText(code, "UTF-8");
	             
	            // 이메일 헤더 
	            msg.setHeader("content-Type", "text/html");
	             
	            //메일보내기
	            javax.mail.Transport.send(msg);
	            System.out.println("보냄!");
	             
	        }catch (AddressException addr_e) {
	            addr_e.printStackTrace();
	        }catch (MessagingException msg_e) {
	            msg_e.printStackTrace();
	        }
	        
	        if(mapnum == 1) {
	        	forward.setPath("/SearchIDCheck.me");
				forward.setRedirect(false);
				
				return forward;
				
	        }else if(mapnum == 2) {
	        	forward.setPath("/SearchPassCheck.me");
				forward.setRedirect(false);
				
				return forward;
	        }

	        
				forward.setPath("./emailcodeCheck.jsp");
				forward.setRedirect(false);
				
				return forward;
		}
	}

	class MyAuthentication extends Authenticator {
	      
	    PasswordAuthentication pa;
	    
	 
	    public MyAuthentication(){
	         
	        String id = "snowfox975";       // 구글 ID
	        String pw = "smhwypyhjlxakfos";          // 구글 비밀번호
	 
	        // ID와 비밀번호를 입력한다.
	        pa = new PasswordAuthentication(id, pw);
	      
	    }
	 
	    // 시스템에서 사용하는 인증정보
	    public PasswordAuthentication getPasswordAuthentication() {
	        return pa;
	    }

}