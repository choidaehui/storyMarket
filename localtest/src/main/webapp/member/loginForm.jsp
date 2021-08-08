<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>storymarket</title>
<link href="style.css" rel="stylesheet">
<link href="view/assets/css/index.css" rel="stylesheet">

</head>
<body>
 
                
   
 <div>
  <section id="sec01">
   
   
   <h1>사연마켓</h1>
   
   
      
  </section>
  
  
     <form action="./MemberLoginAction.me" method="post">
     
       <section id="sec02">
   
     <input type="text" name="id" placeholder="사용자 ID" id="text02"><br><br>
     <input type="password" name="pass" placeholder="비밀번호" id="text02"><br><br><br>
     <input type="submit" name="login" value="로그인" id="text03">
     <br>
    
  </section>  
    
     </form>
        <h2 id="or">또는</h2>
    
    <section id="sec03">
    
    <h3><a href="./SearchID.me">아이디를 잊으셨나요?</a></h3>
    <h3><a href="./SearchPass.me">비밀번호를 잊으셨나요?</a></h3>
    </section>
    
    
    <section id="sec04"> 
     <h3>계정이 없으신가요? <a href="./MemberJoin.me">가입하기</a></h3>
     
    </section>
    

   </div> 

</body>
</html>