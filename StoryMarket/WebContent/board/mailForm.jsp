<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="../view/assets/css/publicStyle.css" rel="stylesheet">
</head>
<body>
  <h1>WebContent/board/writeForm.jsp</h1>
  <%
  	System.out.println("V : WebContent/board/writeForm.jsp 호출");
    String id = request.getParameter("id");

  %>
  
  <h1>문의 메일 양식</h1>
  
  <fieldset>
  <form action="./MailSendAction.bo" method="post">
    글쓴이 : <input type="text" name="name" ><br>
 이메일주소 :  <input type="email" name="pass"><br>
      제목 : <input type="text" name="subject"><br>
      내용 : <br>
        <textarea rows="10" cols="30" name="content" placeholder="[문의 내용을 기입해주세요]"></textarea><br>
  <hr>      
  <input type="submit" name="mailSend" value="보내기"><br>
  <input type="reset" value="초기화"><br>    
        
  </form>
  </fieldset>
  
</body>
</html>