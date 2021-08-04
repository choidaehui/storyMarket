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
	
	// 회원가입창의 이메일 입력란의 값을 가져온다.
    function pValue(){
        document.getElementById("receiver").value = opener.document.join.email.value;
    }
	
</script>
</head>
<body onload="pValue()">


<%!
	public int getRandom(){
	int random = 0;
	random = (int)Math.floor(Math.random()*(999999-100000+1))+100000;
	return random;
}
%>
	<br>
    <b>이메일 인증</b>
    <hr>
    <br>
  <fieldset>
   	 <form action="./EmailIdentityCheckAction.me" method="post">
   	     이메일 : <input type="text" name="receiver" id="receiver" readonly="readonly">
   	     	  <input type="submit" value="인증번호발송"><br>
   	     	  <input type="button" value="취소" onclick="window.close()"><br>
   	     	  <input type="hidden" readonly="readonly" name="codecheck" value="<%=getRandom() %>">
   	     	  <input type="hidden" readonly="readonly" name="mapnum" value="0">
   	 </form>
   </fieldset>
   
</body>
</html>