<%@page import="net.member.db.MemberBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>storymarket</title>
<link href="./view/assets/css/info.css" rel="stylesheet">
</head>
<body>
	<%
		//로그인 체크
		String id = (String)session.getAttribute("id");
	
		if(id == null){
			response.sendRedirect("./Main.me");
		}
		
		//MemberInfoAction페이지에서 전달된 정보를 저장
		MemberBean mb = (MemberBean)request.getAttribute("MemberBean");
		String email = mb.getEmail();
		session.setAttribute("email", email);
		
		if(mb != null){ //회원정보가 있을때만 표시
	%>
	
	<img src="./memberimg/<%=mb.getImgname()%>" alt="" width="300" height="300"/>
	
	<table border="1">
		<tr>
			<td>아이디</td><td><%=mb.getId() %></td>
		</tr>
		<tr>
			<td>이름</td><td><%=mb.getName() %></td>
		</tr>
		<tr>
			<td>비밀번호</td><td><%=mb.getPass() %></td>
		</tr>
		<tr>
			<td>전화번호</td><td><%=mb.getPhone() %></td>
		</tr>
		<tr>
			<td>이메일</td><td><%=mb.getEmail() %></td>
		</tr>
		<tr>
			<td>주소</td><td><%=mb.getAddress()%></td>
		</tr>
		<tr>
			<td>계좌번호</td><td><%=mb.getAccount()%></td>
		</tr>
		<tr>
			<td>회원가입일</td><td><%=mb.getReg_date() %></td>
		</tr>
	</table>
	<%} %>
	                        
	
	<h2><a href="./IdentityCheck.me">회원정보 수정</a></h2>
	<h2><a href="./StoryMarketmain.me">메인페이지 이동</a></h2>
	<h2><a href="./MemberDelete.me">탈퇴하기</a></h2>
	
	
</body>
</html>