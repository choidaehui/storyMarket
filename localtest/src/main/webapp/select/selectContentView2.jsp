<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>storymarket</title>
<link href="./view/assets/css/customerView.css" rel="stylesheet">
</head>
<body>

 <%
     request.setCharacterEncoding("UTF-8");
      
     int select_num = Integer.parseInt(request.getParameter("select_num"));
     String member_id = request.getParameter("member_id");
     String select_id = request.getParameter("select_id"); 
     String j_item_title = request.getParameter("j_item_title"); 
     String j_location = request.getParameter("j_location"); 
     String j_trade_location = request.getParameter("j_trade_location"); 
     String j_status = request.getParameter("j_status"); 
     String j_sale = request.getParameter("j_sale"); 
     String j_buydate = request.getParameter("j_buydate"); 
 
 %>

    


    <table border="1">    
      <tr>
       <td>판매자</td>
       <td><%=select_id %></td>
      </tr>
      
       <tr>
       <td>애장품명</td>
       <td><%=j_item_title%></td>      
      </tr>
       <tr>
       <td>사는 곳</td>
       <td><%=j_location %></td>      
      </tr>
       <tr>
       <td>구입날짜 </td>
       <td><%=j_buydate%></td>      
      </tr>
       <tr>
       <td>거래장소</td>
       <td><%=j_trade_location %></td>      
      </tr>
     <tr>
       <td>애장품상태</td>
       <td><%=j_status %></td>      
      </tr>
     <tr>
       <td>가격</td>
       <td><%=j_sale %></td>      
     </tr>
   </table>
   
   <form action="./customerchat.cu?toID=<%=select_id %>" method="post" target="_blank" rel="noopener">
   <input type="submit" name="chat" value="<%=select_id %>님과의 대화">
	</form>
</body>
</html>