<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비밀번호찾기</title>
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
    <h2 id="secret">비밀번호찾기</h2>
    <hr>
    <br>
  <fieldset>
   	 <form action="./member/EmailIdentityCheckAction.me" method="post">
   	  <h2 id="cdh">아이디 :</h2> <input type="text" name="id" id="id">
   	   <h2 id="emaile">이메일 :</h2><input type="text" name="receiver" id="receiver">
   	     	  <input type="submit" value="인증번호발송" id="identity"><br>
   	     	  <input type="hidden" readonly="readonly" name="codecheck" value="<%=getRandom() %>">
   	     	  <input type="hidden" readonly="readonly" name="mapnum" value="2">
   	 </form>
   </fieldset>
   
   <h2><a href="./MemberLogin.me" id="reset">취소</a></h2>
</body>
</html>