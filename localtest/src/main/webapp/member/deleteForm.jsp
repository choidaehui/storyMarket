<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>storymarket</title>
</head>
<body>
<%
	String id = (String)session.getAttribute("id");

	//id의 정보가 없을때, (로그인을 안한경우 다시 로그인 페이지로 이동)
	if(id == null){
		response.sendRedirect("./MemberLogin.me");
	}
	
	String email = (String)session.getAttribute("email");
%>

<%!
	public int getRandom(){
	int random = 0;
	random = (int)Math.floor(Math.random()*(999999-100000+1))+100000;
	return random;
}
%>
  <fieldset>
   	 <form action="./IdentityCheckAction.me" method="post">
   	     이메일 : <input type="text" name="receiver" value="<%=email %>" readonly="readonly"> <br>
   	     	  <input type="submit" value="인증번호발송"> <br>
   	     	  <input type="hidden" readonly="readonly" name="codecheck" value="<%=getRandom() %>">
   	     	  <input type="hidden" readonly="readonly" name="mapnum" value = 0>
   	 </form>
   </fieldset>
   
</body>
</html>