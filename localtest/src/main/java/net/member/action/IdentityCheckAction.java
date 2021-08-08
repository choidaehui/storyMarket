package net.member.action;

import java.io.IOException;
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


@WebServlet("/IdentityCheckAction")
public class IdentityCheckAction extends HttpServlet implements Action {
	private static final long serialVersionUID = 1L;

	@Override
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	       
	}

		@Override
		public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
			//로그인 체크(세션)
			HttpSession session = request.getSession();
			
			String id = (String)session.getAttribute("id");
			int mapnum = Integer.parseInt(request.getParameter("mapnum"));
			ActionForward forward = new ActionForward();
			
			if(id==null) {
				forward.setPath("./MemberLogin.me");
				forward.setRedirect(true);
				return forward;
			}
			Properties props = System.getProperties();
	        props.put("mail.smtp.user", "snowfox975"); // 서버 아이디만 쓰기
			props.put("mail.smtp.host", "smtp.gmail.com"); // 구글 SMTP
			props.put("mail.smtp.port", "465");
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.socketFactory.port", "465");
			props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			props.put("mail.smtp.socketFactory.fallback", "false");
	           
	        Authenticator auth = new MyAuthentication01();
	         
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
	            String email = request.getParameter("receiver"); //사용자가 입력한 이메일 받아오기
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
	        
	        if(mapnum==1) {
				forward.setPath("./member/codeCheck.jsp");
				forward.setRedirect(false);
				
				return forward;
				
			}else if(mapnum==0){
				forward.setPath("./member/deletecodeCheck.jsp");
				forward.setRedirect(false);
				
				return forward;
			}
	        
	        return null;
		}
	}

	class MyAuthentication01 extends Authenticator {
	      
	    PasswordAuthentication pa;
	    
	 
	    public MyAuthentication01(){
	         
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