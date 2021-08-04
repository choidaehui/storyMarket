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
		//로그인 체크
		String id = (String)session.getAttribute("id");
	
		if(id == null){
			response.sendRedirect("./MemberLogin.me");
		}
		
		MemberBean mb = (MemberBean)request.getAttribute("MemberBean");
		
	%>
	
 <fieldset>
  <form action="./MemberUpdateAction.me" method="post" enctype="multipart/form-data" name="uform" id="uform" onsubmit="return fun1()" >
      아이디 <input type="text" name="id" readonly="readonly" value="<%=mb.getId()%>"><br>
      비밀번호 <input type="password" name="pass" id="pass" value="<%=mb.getPass()%>"><br>
      비밀번호 확인 <input type="password" name="passck" onkeyup="checkpass()"><div id="pass_check"></div><br>
      이름 <input type="text" name="name" readonly="readonly" value="<%=mb.getName()%>"><br> 
      프로필 <input type="file" name="filename" id="filename"><br>
   		 <input type="hidden" name="orifilename" id="orifilename" value="<%=mb.getImgname()%>">
      전화번호 <input type="text" name="phone" value="<%=mb.getPhone()%>"><br>
      이메일 <input type="email" name="email" value="<%=mb.getEmail()%>"><br> <!-- 이메일 api -->
      주소 <input type="text" name="address" value="<%=mb.getAddress()%>"><br> <!-- 주소찾기 api -->
      계좌 <input type="text" name="account" value="<%=mb.getAccount()%>"><br> <!-- 은행계좌 api -->
      
   <input type="submit" value="수정하기">
   <input type="reset" value="초기화">
  </form>
 </fieldset>
 
 <script type="text/javascript">
 function checkpass(){
	  var v1 = document.uform.pass.value;
	  var v2 = document.uform.passck.value;
	  if(v1 == v2){
		  
		   document.getElementById('pass_check').style.color = "blue";
		   document.getElementById('pass_check').innerHTML = "비밀번호가 일치합니다."; 
		   
	  }else{
		  
		  document.getElementById('pass_check').style.color = "red";
		  document.getElementById('pass_check').innerHTML = "비밀번호가 일치하지 않습니다.";
		  
	  }
	 }
 
 function fun1(){
	 var v1 = document.uform.pass.value;
	 var v2 = document.uform.passck.value;
	 
	 
	 if(v1 != v2){
		 document.uform.passck.focus();
		 document.getElementById('pass_check').style.color = "red";
		 document.getElementById('pass_check').innerHTML = "비밀번호가 일치하지 않습니다.";
		 return false;
	 } 
	 
 }
 
 </script>
 
</body>
</html>