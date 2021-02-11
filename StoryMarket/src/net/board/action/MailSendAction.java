package net.board.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.mail.Authenticator;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MailSendAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("V:MailSendAction-execute 호출~");

		//한글처리
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");

		//파라미터 정보저장
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String subject= request.getParameter("subject");
		String content = request.getParameter("content");
		

		try {
			String mail_from = name + "<"+ email + ">";
			System.out.println(mail_from);
			String mail_to = "milkrenn@gmail.com";
			String title = "[문의메일] " + subject;
			String contents = "[보낸사람] "+name + "<br>" + content;  

			mail_from = new String(mail_from.getBytes( "UTF-8"),"8859_1");
			mail_to =  new String(mail_to.getBytes("8859_1"), "UTF-8");

			Properties props = new Properties();
			props.put("mail.transport.protocol", "smtp");
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.port", "465");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.socketFactory.port", "465");
            props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            props.put("mail.smtp.socketFactory.fallback", "false");
            props.put("mail.smtp.auth", "true");


            Authenticator auth = new SMTP();
            
            Session sess = Session.getDefaultInstance(props, auth);

            
            MimeMessage msg = new MimeMessage(sess);
            msg.setFrom(new InternetAddress(mail_from));
            msg.setRecipient(Message.RecipientType.TO, new InternetAddress(mail_to));
            msg.setSubject(title, "UTF-8");
            msg.setContent(contents, "text/html; charset=UTF-8");
            msg.setHeader("Content-type", "text/html; charset=UTF-8");

            Transport.send(msg);

            PrintWriter out = response.getWriter();
    		out.println("<script>");
    		out.println("alert('메일을 보냈습니다.');");
    		out.println("location.href='./BoardList.bo'");
    		out.println("</script>");
            

		}catch(Exception e) {
			e.printStackTrace();
		}finally {

		}//

		return null;
	}
}