<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>제2강의장 최대희</title>
</head>
<body>

   <h1>WebContent/JSP2/ItwillJoinForm.jsp</h1>
   <!-- 
      회원 가입 페이지 생성
      아이디 - text
      비밀번호 - password
      비밀번호 확인 - password
      이름 - text
      주민번호 - text-text
      성별 - radio (남/여)
      취미 - checkbox (3가지)
      강의장 - select (1~7강)
      하고싶은말  - textarea   
    -->
 <fieldset>
  <legend>회원가입페이지</legend>
   <form action="ItwillJoinPro.jsp" name="fr" method="get" onsubmit="return fun1()">
   1.아이디: <input type="text" name="id"><br><br>
   2.비밀번호: <input type="password" name="pwd"><br><br>
   3.비밀번호확인: <input type="password" name="pwd2"><br><br>
   4.이름: <input type="text" name="name"><br><br>
   5.주민번호: <input type="text" name="num1">-<input type="text" name="num2"><br><br>
   6.성별: <input type="radio" name="gender" id="male" value="남">
       <label for= "male">남</label>
        <input type="radio" name="gender" id="female" value="여">
        <label for= "female">여</label><br><br>
   7.취미: <input type="checkbox" name="hobby1" value="여행">여행     
        <input type="checkbox" name="hobby2" value="게임">게임     
        <input type="checkbox" name="hobby3" value="요리">요리<br><br>
             
   8.강의장: <select name="classRoom">
          
          <option value="">강의장선택</option>
          <option>1강의장</option>
          <option>2강의장</option>
          <option>3강의장</option>
          <option>4강의장</option>
          <option>5강의장</option>
          <option>6강의장</option>
          <option>7강의장</option>
       </select><br><br>
    <p>9.하고싶은 말</p><textarea cols="60" rows="6" name="textA"></textarea><br><br>
    <input type="submit" value="전송">
    <input type="button" value="유효성체크" onclick="fun1()">
 
 
 
 
  </form>
 </fieldset>
    <%
       // 해당정보를 모두 저장해서 ItwillJoinPro.jsp 페이지에서 화면 출력
       
       // 1. 아이디가 공백인지 체크
       // 2. 아이디가 5~10 사이인지 체크
       // 3. 비밀번호가 공백인 체크
       // 4. 비밀번호, 비밀번호 확인이 같은지 체크
       // 5. 이름 공백 체크
       // 6. 주민번호 앞 6자리, 뒤 7자리 체크
       // 7. 성별, 취미, 좋아하는 과목 선택여부 체크
       // 8. 하고싶은말 공백여부 체크
       
       // 9. 주민번호 입력 완료시  자동으로 성별을 체크 
       
       // => form 태그 -> 유효성 체크(JS) -> Submit() -> 
       
       // ~11/6(금) jgt8731@itwillbs.co.kr 제출
       //  [2강의장 ㅇㅇㅇ]
       
       
    
    
    %>
   <script type="text/javascript">
  
   function fun1(){
	   
	   
	   if(document.fr.id.value == ""){
		   
		   alert("아이디를 입력하시오.");
		   return;
	   }
	   	   
	   var IDlength = document.fr.id.value.length; 
		   
	   if(!(IDlength>=5 && IDlength<=10)){
		   
		   alert("아이디 사용이 부적합 합니다.");
		   return;
	   }
	   if(document.fr.pwd.value == ""){
		   
		   alert("비밀번호를 입력하시오.");
		   return;
	   }
	   
	   if(!(document.fr.pwd.value == document.fr.pwd2.value)){
		   
		   alert("비밀번호와 비밀번호확인 값이 다릅니다");
		   return;
	   }
	   if(document.fr.name.value == ""){
		   
		   alert("이름을 입력하시오");
		   return;
		   
	   }
	   if(!(document.fr.num1.value.length == 6 && 
			   document.fr.num2.value.length == 7)){
		   
		   alert("주민번호가 부적합 합니다.");
		   document.fr.gender.focus();
		   return;
	   }
	   
	   
	   if(document.fr.gender[0].checked == false && document.fr.gender[1].checked == false){
		   
		   alert("성별을 선택하세요!");
		   return false;
	   }
	   
	   //var hob = "";
	   if(document.fr.hobby1.checked == false && document.fr.hobby2.checked == false 
			   && document.fr.hobby3.checked == false){
		   
		   alert("취미를 선택하세요!");
		   return false;
	   }
	     /*else{ 
		   
		   if(document.fr.hobby[0].checked == true){
		   
		   hob += document.fr.hobby[0].value;
		   
	   } 
		   if(document.fr.hobby[1].checked == true){
		   
		   
		   hob += document.fr.hobby[1].value;
		  
	   }
		   if(document.fr.hobby[2].checked == true){
		   
		   hob += document.fr.hobby[2].value;
		   
	   }
		   
	   }*/
	  
		
	   if(document.fr.classRoom.options[0].selected == true){
		   
		   alert("강의장을 선택하세요!");
		   return false;
	   }
	
	
	  
	  
	   if(document.fr.textA.value.length == 0){
		   
		   alert("하고싶은말을 입력하시오!")
		   return false;
		   
	   }else{
		   
		   alert("유효성검토 통과!!")
		   
	   }
	   
	   
       	  
	   }
	   
	   
	  
	   
	   
	   
	   
   
   
   
   
   
   </script>
   
   
   
   

</body>
</html>