<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script type="text/javascript" src="./resource/js/jquery-3.4.1.min.js"></script>
<script type="text/javascript">
$(function(){
		//아이디 중복검사
	var check = 0;
	var idCheckBtn = function(){
		var request = $.ajax({
			url: "ajaxIdCheck.jsp",
			method: "POST",			
			data: { len : $('#memberId').val() },		
			dataType: "json"			
		});
		request.done(function(data) {
			console.log(data);
			if(data != undefined && data != ''){
				if(data.result == "Y"){
					alert("사용 가능한 아이디 입니다");
					check++;
				}else{
					alert("사용 불가능한 아이디 입니다");
				}	
			}
		});
		request.fail(function( jqXHR, textStatus ) {
			alert( "Request failed: " + textStatus );
		});
	};
	var userInfoCheck = function(){
		if($('#memberId').val() == ''){
			alert('아이디를 입력하세요');
			return;
		}else if($('#memberPw').val() == ''){
			alert('비밀번호를 입력하세요');
			return;
		}else if($('#memberPw2').val() == ''){
			alert('비밀번호 확인을 입력하세요');
			return;
		}else if($('#memberPw').val() != $('#memberPw2').val()){
			alert('비밀번호와 비밀번호 확인이 다릅니다');	
		}else if($('#memberName').val() == ''){
			alert('이름을를 입력하세요');
			return;
		}else if($('#memberEmail').val() == ''){
			alert('이메일을 입력하세요');
			return;
		}else if($('[name="memberGender"]:checked').length == 0){
			alert('성별을 입력하세요');
			return;
		}else if(check==0){
			idCheckBtn();
		}else{
			alert('회원가입 완료');
		}		
	};
	$('#idCheckBtn').click(function(){
		if(check == 0){
			idCheckBtn();
		}
	})
	$('#userInfoCheck').click(function(){
		userInfoCheck();
	});
});
</script>
<form action="./">
	아이디 :<input type="text" id="memberId">
	<button type="button" id="idCheckBtn">아이디 중복 확인</button><br/>
	비밀번호 : <input type="text" id="memberPw"><br/>
	비밀번호 확인 : <input type="text" id="memberPw2"><br/>
	이름 : <input type="text" id="memberName"><br/>
	이메일 : <input type="text" id="memberEmail"><br/>
	성별 : 남 <input type="radio" class="memberGender" name="memberGender" value="남">
		  여  <input type="radio" class="memberGender" name="memberGender" value="여"><br/>
	<button type="button" id="userInfoCheck">회원가입</button>
</form>