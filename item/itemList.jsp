<%@page import="net.item.db.itemBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>storymarket</title>
<link href="./view/assets/css/itemList.css" rel="stylesheet">
</head>
<body>

  <%
       
      itemBean ib = (itemBean)request.getAttribute("ib");
      int item_num = ib.getItem_num();
    
    %>
    
  
     
     <table border="1">
      <tr>
       <td>번호</td>
       <td><%=item_num %></td>      
      </tr>
      
       <tr>
       <td>애장품명</td>
       <td><%=ib.getItem_title() %></td>      
      </tr>
       <tr>
       <td>사는 곳</td>
       <td><%=ib.getLocation() %></td>      
      </tr>
       <tr>
       <td>구입날짜 </td>
       <td><%=ib.getBuydate() %></td>      
      </tr>
       <tr>
       <td>거래장소</td>
       <td><%=ib.getTrade_location() %></td>      
      </tr>
     <tr>
       <td>애장품상태</td>
       <td><%=ib.getStatus() %></td>      
      </tr>
     <tr>
       <td>가격</td>
       <td><%=ib.getSale() %></td>      
     </tr>
      
     </table>
       <section>
         <input type="button" name="main" value="등록" 
         onclick="location.href='./sellerMainAction.se';">
       </section>
               
</body>
</html>