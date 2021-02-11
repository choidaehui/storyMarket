<%@page import="net.member.db.MemberBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>sellerWriteForm</title>

<link href="./view/assets/css/sellerWriteForm.css" rel="stylesheet">
</head>
<body>
    
  <%
     System.out.println("v : Webcontent//sellerWriteForm.jsp 호출");
  %>

  <fieldset id="field">
    <legend><h1>판매자 페이지</h1> </legend>
    <%
    
    
    String seller_id = (String) session.getAttribute("id");
  
    System.out.println("sellerWriteForm: "+seller_id);
    %>
     
   <form action="./sellerWriteAction.se" method="post" enctype="multipart/form-data">
  <div id="div">
                    <input type="hidden" name="seller_id" value="<%=seller_id %>">
   <h4>판매자 ID</h4> <input type="text" name="id" placeholder="<%=seller_id %>" id="text" readonly="readonly"><br>
  <%--  <h4>비밀번호</h4> <input type="password" name="pass" id="text" placeholder="<%=mb.getPass()%>" readonly="readonly"><br> --%>
   <h4>애장품 이미지</h4> <input type="file" name="image"><br><br>
   <h4>한줄 카피</h4> <input type="text" name="copy_text"><br>
 <h4>애장품홍보영상</h4> <input type="file" name="file"><br><br>
   <h4>사연소개</h4> <textarea rows="5" cols="90" placeholder="애장품에 담긴 사연을 입력해주세요." name="message"></textarea><br>    
   
    <input type="submit" value="다음" id="submit">
     
  </div>     
   </form>
   
  </fieldset>

</body>
</html>