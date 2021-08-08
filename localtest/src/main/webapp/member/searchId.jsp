<%@page import="net.member.db.MemberBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>storymarket</title>
<link href="./view/assets/css/join.css" rel="stylesheet">

</head>
<body>
	<%
		MemberBean mb = (MemberBean)request.getAttribute("MemberBean");
		
	%>
	
	당신의 아이디는 <br>  
	<h3><%=mb.getId()%></h3>
	입니다.
	
	<h3><a href="../SearchPass.me">비밀번호를 잊으셨나요?</a></h3>
	<h3><a href="../MemberLogin.me">로그인 하러가기</a></h3>
	
</body>
</html>