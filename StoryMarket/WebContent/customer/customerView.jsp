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
      
     int item_num = Integer.parseInt(request.getParameter("item_num"));
     String item_id = request.getParameter("item_id"); 
     String item_title = request.getParameter("item_title"); 
     String location = request.getParameter("location"); 
     String trade_location = request.getParameter("trade_location"); 
     String status = request.getParameter("status"); 
     String sale = request.getParameter("sale"); 
     String buydate = request.getParameter("buydate"); 
 
 %>




    <table border="1">
      <tr>
       <td>판매번호</td>
       <td><%=item_num %></td>      
      </tr>
      <tr>
       <td>판매자</td>
       <td><%=item_id %></td>
      </tr>
      
       <tr>
       <td>애장품명</td>
       <td><%=item_title%></td>      
      </tr>
       <tr>
       <td>사는 곳</td>
       <td><%=location %></td>      
      </tr>
       <tr>
       <td>구입날짜 </td>
       <td><%=buydate %></td>      
      </tr>
       <tr>
       <td>거래장소</td>
       <td><%=trade_location %></td>      
      </tr>
     <tr>
       <td>애장품상태</td>
       <td><%=status %></td>      
      </tr>
     <tr>
       <td>가격</td>
       <td><%=sale %></td>      
     </tr>
   </table>
   
   <form action="./customerchat.cu?toID=<%=item_id %>" method="post" target="_blank" rel="noopener">
   <input type="submit" name="chat" value="<%=item_id %>님과의 대화">
	</form>
</body>
</html>