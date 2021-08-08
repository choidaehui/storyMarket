<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>storymarket</title>
<link href="./view/assets/css/fileWriteForm.css" rel="stylesheet">
</head>
<body>
  
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
    	<option value="경기도" >경기도</option>
    	<option value="강원도" >강원도</option>
    	<option value="전라남도" >전라남도</option>
    	<option value="전라북도" >전라북도</option>
    	<option value="경상남도" >경상남도</option>
    	<option value="경상북도" >경상북도</option>
    	<option value="충청남도" >충청남도</option>
    	<option value="충청북도" >충청북도</option>
    	<option value="제주도" >제주도</option>
    	<option value="부산" >부산</option>
    	<option value="대전" >대전</option>
    	<option value="대구" >대구</option>
    	<option value="울산" >울산</option>
    	<option value="인천" >인천</option>
 
       </select><br>
      제목 : <input type="text" name="subject"><br>
      내용 : <br>
        <textarea rows="10" cols="30" name="content"></textarea><br>
  
   첨부파일 : <input type="file" name="filename"> 
      
  <input type="submit" value="글쓰기">
  <input type="reset" value="초기화"><br>    
        
  </form>
  </fieldset>
  
</body>
</html>