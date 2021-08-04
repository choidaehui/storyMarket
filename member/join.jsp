<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="./view/assets/css/join.css" rel="stylesheet">
</head>
<body>
 <fieldset>
  <form action="./MemberJoinAction.me" method="post" enctype="multipart/form-data" onsubmit="return validate();" name="join">
      <h3>아이디</h3> <input type="text" id="id" name="id" placeholder="4-12자의 영문 대소문자와 숫자" onkeydown="inputIdChk()">
      				 <input type="button" value="중복확인" onclick="openIdChk()" id="copy">    
                     <input type="hidden" name="idDuplication" value="idUncheck" >
      <h3>비밀번호</h3> <input type="password" id="pass" name="pass" placeholder="4-12자의 영문 대소문자와 숫자" required="required">
      <h3>비밀번호 확인</h3> <input type="password" id="passck" name="passck" onkeyup="checkpass()"><div id="pass_check"></div><br> 
      <h3>이름</h3> <input type="text" name="name" id="name">
      <h3>프로필</h3> <input type="file" name="filename" id="profile">
      <h3>전화번호</h3> <input type="text" name="phone">
      <h3>이메일</h3> <input type="email" name="email" id="email" onkeydown="inputEmailChk()"> 
      				<input type="button" value="인증하기" onclick="openEmailChk()" id="identity">    
                    <input type="hidden" name="emailck" value="emailunck" >
      <h3>주소</h3> <input type="text" name="address"> <!-- 주소찾기 api -->
      <h3>계좌</h3> <input type="text" name="account"> <!-- 은행계좌 api -->
      
   <input type="submit" value="회원가입" id="submit">
   <input type="reset" value="초기화" id="reset">
  </form>
 </fieldset>
 
 <script type="text/javascript">
	//아이디 중복체크 화면open
	 function openIdChk(){
	 
	     window.name = "parentForm";
	     window.open("member/idCheckForm.jsp",
	             "chkForm", "width=500, height=300, resizable = no, scrollbars = no");    
	 }
	
	
	 // 아이디 입력창에 값 입력시 hidden에 idUncheck를 세팅한다.
	 // 이렇게 하는 이유는 중복체크 후 다시 아이디 창이 새로운 아이디를 입력했을 때
	 // 다시 중복체크를 하도록 한다.
	 function inputIdChk(){
	     document.join.idDuplication.value ="idUncheck";
	 }
	
	//이메일 인증 화면open
	 function openEmailChk(){
			window.name="emailparentForm";
			window.open("member/emailIdentityCheck.jsp",
		             "codeCk", "width=500, height=300, resizable = no, scrollbars = no");
	}
	
	 function inputEmailChk(){
	     document.join.emailck.value ="emailunck";
	 }
	 
   function validate() {
       var re = /^[a-zA-Z0-9]{4,12}$/ // 아이디와 패스워드가 적합한지 검사할 정규식
       var re2 = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
       // 이메일이 적합한지 검사할 정규식

       var id = document.getElementById("id");
       var pass = document.getElementById("pass");
       var email = document.getElementById("email");
       

       // ------------ 이메일 까지 -----------

       if(!check(re,id,"아이디는 4~12자의 영문 대소문자와 숫자로만 입력")) {
           return false;
       }

       if(!check(re,pass,"패스워드는 4~12자의 영문 대소문자와 숫자로만 입력")) {
           return false;
       }
		
       if(join.idDuplication.value != "idCheck"){
           alert("아이디 중복체크를 해주세요.");
           return false;
       }

       if(join.emailck.value != "emailCheck"){
           alert("이메일 인증을 해주세요");
           return false;
       }
       
       if(join.pass.value != join.passck.value) {
           alert("비밀번호가 다릅니다.");
           join.passck.value = "";
           join.passck.focus();
           return false;
       }

       if(email.value=="") {
           alert("이메일을 입력해 주세요");
           email.focus();
           return false;
       }

       if(!check(re2, email, "적합하지 않은 이메일 형식입니다.")) {
           return false;
       }

       if(join.name.value=="") {
           alert("이름을 입력해 주세요");
           join.name.focus();
           return false;
       }
       
       if(join.phone.value=="") {
           alert("전화번호를 입력해 주세요");
           join.phone.focus();
           return false;
       }
       
       if(join.address.value=="") {
           alert("주소를 입력해 주세요");
           join.address.focus();
           return false;
       }
       
       if(join.account.value=="") {
           alert("주소를 입력해 주세요");
           join.address.focus();
           return false;
       }
       
       
       alert("회원가입이 완료되었습니다.");
   }

   function check(re, what, message) {
       if(re.test(what.value)) {
           return true;
       }
       alert(message);
       what.value = "";
       what.focus();
       //return false;
   }
   
   function checkpass(){
		  var v1 = document.join.pass.value;
		  var v2 = document.join.passck.value;
		  if(v1 == v2){
			  
			   document.getElementById('pass_check').style.color = "blue";
			   document.getElementById('pass_check').innerHTML = "비밀번호가 일치합니다."; 
			   
		  }else{
			  
			  document.getElementById('pass_check').style.color = "red";
			  document.getElementById('pass_check').innerHTML = "비밀번호가 일치하지 않습니다.";
			  
		  }
		 }
</script>

</body>
</html>