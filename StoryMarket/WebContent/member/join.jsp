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
  <form action="./MemberJoinAction.me" method="post" enctype="multipart/form-data">
     <h3>아이디</h3> <input type="text" name="id">
      <h3>비밀번호</h3> <input type="password" name="pass">
      <h3>이름</h3> <input type="text" name="name"> <!-- 실명인증 api추가할것 -->
      <h3>프로필</h3> <input type="file" name="filename" id="profile">
      <h3>전화번호</h3> <input type="text" name="phone">
      <h3>이메일</h3> <input type="email" name="email"> <!-- 이메일 api -->
      <h3>주소</h3> <input type="text" name="address"> <!-- 주소찾기 api -->
      <h3>계좌</h3> <input type="text" name="account"> <!-- 은행계좌 api -->
      
   <input type="submit" value="회원가입" id="submit">
   <input type="reset" value="초기화" id="reset">
  </form>
 </fieldset>
 
</body>
</html>