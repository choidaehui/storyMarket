<%@page import="net.item.db.itemBean"%>
<%@page import="net.seller.db.SellerBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>sellerWriteForm2</title>
<link href="style.css" rel="stylesheet">
<link href="./view/assets/css/sellerWriteForm2.css" rel="stylesheet">
</head>
<body>

<%

  String item_id = (String)session.getAttribute("id");
  System.out.println("itemWriteForm : id값"+item_id);
 %>

  <fieldset id="field">
    
 
   <form action="./itemWriteAction.it" method="post">
  <div id="div">  
                   <input type="hidden" name="item_id" value="<%=item_id%>">
   <h4>애장품명</h4> <input type="text" name="item_title"><br>  
  <h4>사는 곳</h4> <input type="text" name="location"><br>
  <h4>구입날짜</h4> <input type="text" name="buydate"><br>
  <h4>거래장소</h4> <input type="text" name="trade_location"><br>
   <h4>애장품 상태</h4> <textarea rows="5" cols="90" placeholder="애장품의 현재 상태를 입력해주세요." name="status"></textarea><br>  
    <h4>가격</h4> <input type="text" name="sale"><br>
    
    <input type="reset" value="다시쓰기" id="reset">
    <input type="submit" value="전송" id="submit">

  </div>     
   </form>
   
  </fieldset>

 
</body>
</html> 
 