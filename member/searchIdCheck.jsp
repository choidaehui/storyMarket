<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아이디 찾기</title>
<link href="./view/assets/css/join.css" rel="stylesheet">

</head>
<body>
	<form action="./ID.me" method="post" id="form_code" name="form_code">
		<table>
			<tr>
				<td><input type="text" name="code" id="code"
					onkeyup="checkCode()" placeholder="인증번호를 입력하세요." maxlength="6">
					<div id="checkCode"></div></td>
				<td><input type="hidden" readonly="readonly" name="code_check"
					id="code_check" value="<%=request.getAttribute("code")%>"></td>
				<td><input type="hidden" readonly="readonly" name="email"
					id="email" value="<%=request.getAttribute("email")%>"></td>	
			</tr>
		</table>
		<input id = "button" type="hidden" value='인증하기'>
	</form>
	
	<script type="text/javascript">
		function checkCode(){
			  var v1 = document.form_code.code.value.length;
			  if(v1 == 6){
				  
				   document.getElementById('checkCode').style.color = "blue";
				   document.getElementById('checkCode').innerHTML = "인증되었습니다."; 
				   makeReal();
			  }else{
				  
				  document.getElementById('checkCode').style.color = "red";
				  document.getElementById('checkCode').innerHTML = "잘못된인증번호";
				  makeHidden();
			  }
			 }
		function makeReal(){
			var button = document.getElementById("button");
			button.type="submit";
		}
		   function makeHidden(){
			var button = document.getElementById("button");
			button.type="hidden";
		}
	</script>
</body>
</html>