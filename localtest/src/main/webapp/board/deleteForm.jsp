<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>storymarket</title>
<link href="./view/assets/css/updateForm.css" rel="stylesheet">
</head>
<body>
	
  <%
  	System.out.println("V : WebContent/board/deleteForm.jsp 호출");
  
  	//전달된 정보 저장 (num, pageNum) //BoardDelete.bo?pageNum=2&num=11 
  			//---> form정보 안에 넣어서 액션처리 페이지로 넘겨주기 위함
  	int num = Integer.parseInt(request.getParameter("num"));
  	String pageNum = request.getParameter("pageNum");
  %>
  
  <h1>글삭제 페이지</h1>
  
  <fieldset>
  <form action="./BoardDeleteAction.bo?pageNum=<%=pageNum %>" method="post">
  <input type="hidden" name="num" value="<%=num %>">
  
  비밀번호 :  <input type="password" name="pass"><br>
      
 <div id="delete"> 
  <input type="submit" value="삭제하기"><br>
 </div>
        
  </form>
  </fieldset>

</body>
</html>