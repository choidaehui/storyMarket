<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
  <h1>WebContent/board/writeForm.jsp</h1>
  <%
  	System.out.println("V : WebContent/board/writeForm.jsp 호출");
  	
  %>
  
  <h1>글쓰기 페이지</h1>
  
  <fieldset>
  <form action="./ReviewWriteAction.bo" method="post">
    글쓴이 : <input type="text" name="name" placeholder="글쓴이의 이름을 입력하시오"><br>
  비밀번호 :  <input type="password" name="pass"><br>
      제목 : <input type="text" name="subject"><br>
      내용 : <br>
        <textarea rows="10" cols="30" name="content"></textarea><br>
  <hr>      
  <input type="submit" value="글쓰기"><br>
  <input type="reset" value="초기화"><br>    
        
  </form>
  </fieldset>
  
</body>
</html>