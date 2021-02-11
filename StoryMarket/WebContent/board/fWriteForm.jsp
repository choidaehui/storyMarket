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
  <h1>WebContent/board/fWriteForm.jsp</h1>
  <%
  	System.out.println("V : WebContent/board/writeForm.jsp 호출");
  	String id = (String)request.getAttribute("id");
  %>
  
  <h1>파일 글쓰기 페이지</h1>
  
  <fieldset>
  <form action="./FileBoardWriteAction.bo" method="post" enctype="multipart/form-data">
    글쓴이 : <input type="text" name="name" placeholder="글쓴이의 이름을 입력하시오"><br>
  비밀번호 :  <input type="password" name="pass"><br>
   지역 : <select name ="regionDivied">
        <option value="전체">지역전체</option>
    	<option value="서울" >서울</option>
    	<option value="부산" >부산</option>
       </select><br>
      제목 : <input type="text" name="subject"><br>
      내용 : <br>
        <textarea rows="10" cols="30" name="content"></textarea><br>
  <hr>
   첨부파일 : <input type="file" name="filename"> 
  <hr>      
  <input type="submit" value="글쓰기">
  <input type="reset" value="초기화"><br>    
        
  </form>
  </fieldset>
  
</body>
</html>