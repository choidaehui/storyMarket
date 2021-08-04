<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아이디 찾기</title>
<link href="./view/assets/css/searchPass.css" rel="stylesheet">


</head>
<body>

<%!
	public int getRandom(){
	int random = 0;
	random = (int)Math.floor(Math.random()*(999999-100000+1))+100000;
	return random;
}
%>
	<br>
    <b id="secret"> 아이디 찾기</b>
    <hr>
    <br>
  <fieldset>
   	 <form action="./member/EmailIdentityCheckAction.me" method="post">
   	     이메일 : <input type="text" name="receiver" id="receiver">
   	     	  <input type="submit" value="인증번호발송" id="identity"><br>
   	     	  <input type="hidden" readonly="readonly" name="codecheck" value="<%=getRandom() %>">
   	     	  <input type="hidden" readonly="readonly" name="mapnum" value="1">
   	 </form>
   </fieldset>
   
   <h2><a href="./MemberLogin.me" id="reset">취소</a></h2>
   
</body>
</html>