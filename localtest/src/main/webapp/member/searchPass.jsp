<%@page import="net.member.db.MemberBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>storymaraket</title>
<link href="./view/assets/css/join.css" rel="stylesheet">

</head>
<body>
	<%
		MemberBean mb = (MemberBean)request.getAttribute("MemberBean");
		
	%>
	
	<fieldset>
	  <form action="../MemberPassUpdateAction.me" method="post" name="uform" id="uform" onsubmit="return fun1()" >
	      아이디 <input type="text" name="id" readonly="readonly" value="<%=mb.getId()%>"><br>
	      비밀번호 <input type="password" name="pass" id="pass" value=""><br>
	      비밀번호 확인 <input type="password" name="passck" onkeyup="checkpass()"><div id="pass_check"></div><br>
	   <input type="submit" value="수정하기">
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
	 
	 if(v1==null){
		 document.uform.pass.focus();
		 return false;
	 }
	 
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