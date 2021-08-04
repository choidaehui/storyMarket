<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>이메일 인증</title>
<link href="./view/assets/css/join.css" rel="stylesheet">

<script type="text/javascript">

    
        var httpRequest = null;
        
        // httpRequest 객체 생성
        function getXMLHttpRequest(){
            var httpRequest = null;
        
            if(window.ActiveXObject){
                try{
                    httpRequest = new ActiveXObject("Msxml2.XMLHTTP");    
                } catch(e) {
                    try{
                        httpRequest = new ActiveXObject("Microsoft.XMLHTTP");
                    } catch (e2) { httpRequest = null; }
                }
            }
            else if(window.XMLHttpRequest){
                httpRequest = new window.XMLHttpRequest();
            }
            return httpRequest;    
        }
        
     // 사용하기 클릭 시 부모창으로 값 전달 
        function sendCheckValue(){
            // 중복체크 결과인 idCheck 값을 전달한다.
            opener.document.join.emailck.value ="emailCheck";
          
            if (opener != null) {
                opener.codeCk = null;
                self.close();
            }    
        }    
</script>
</head>

<body>
	<form method="post" id="form_code" name="form_code">
		<table>
			<tr>
				<td><input type="text" name="code" id="code"
					onkeyup="checkCode()" placeholder="인증번호를 입력하세요." maxlength="6">
					<div id="checkCode"></div></td>
				<td><input type="hidden" readonly="readonly" name="code_check"
					id="code_check" value="<%=request.getAttribute("code")%>"></td>
			</tr>
		</table>
		<input id = "button" type="hidden" value='인증하기' onclick="sendCheckValue()">
		<br>
		<input type="button" value="취소" onclick="window.close()"><br>
		
	</form>
	
	<script type="text/javascript">
		function checkCode(){
			  var v1 = document.form_code.code.value;
			  var v2 = document.form_code.code_check.value;
			  if(v1 == v2){
				  
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