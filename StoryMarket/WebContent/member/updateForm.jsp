<%@page import="net.member.db.MemberBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="./view/assets/css/publicStyle.css" rel="stylesheet">
</head>
<body>
<%
		/* //로그인 체크
		String id = (String)session.getAttribute("id");
	
		if(id == null){
			response.sendRedirect("./Main.me");
		} */
		
		MemberBean mb = (MemberBean)request.getAttribute("MemberBean");
		
		System.out.println(mb);
	%>
	
 <fieldset>
  <form action="./MemberUpdateAction.me" method="post" enctype="multipart/form-data">
      아이디 <input type="text" name="id" readonly="readonly" value="<%=mb.getId()%>"><br>
      비밀번호 <input type="password" name="pass"><br>
      이름 <input type="text" name="name" readonly="readonly" value="<%=mb.getName()%>"><br> 
      프로필 <input type="file" name="filename" value="<%=mb.getImgname()%>"><br>
      전화번호 <input type="text" name="phone" value="<%=mb.getPhone()%>"><br>
      이메일 <input type="email" name="email" value="<%=mb.getEmail()%>"><br> <!-- 이메일 api -->
      주소 <input type="text" name="address" value="<%=mb.getAddress()%>"><br> <!-- 주소찾기 api -->
      계좌 <input type="text" name="account" value="<%=mb.getAccount()%>"><br> <!-- 은행계좌 api -->
      
   <input type="submit" value="수정하기">
   <input type="reset" value="초기화">
  </form>
 </fieldset>
 
</body>
</html>