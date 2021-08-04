<%@page import="net.seller.db.SellerDAO"%>
<%@page import="net.seller.db.SellerBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>sellerList</title>
<link href="./view/assets/css/sellerList.css" rel="stylesheet">
</head>
<body>
    
    <%
       
      SellerBean sb = (SellerBean) request.getAttribute("sb");
 
      String seller_id = sb.getSeller_id();    
      session.setAttribute("seller_id", seller_id);
    
    %>
    
       
     
     <table border="1">
      <tr>
       <td>번호</td>
       <td><%=sb.getSeller_num() %></td>      
      </tr>
      
       <tr>
       <td>이미지</td>
       <td><a href="./upload/<%=sb.getImage() %>"><%=sb.getImage() %></a></td>      
      </tr>
       <tr>
       <td>한 줄 카피</td>
       <td><%=sb.getCopy_text() %></td>      
      </tr>
       <tr>
       <td>동영상 </td>
       <td><a href="./upload/<%=sb.getFile() %>"><%=sb.getFile()%></a></td>      
      </tr>
       <tr>
       <td>사연</td>
       <td><%=sb.getMessage() %></td>      
      </tr>
       <tr>
      
     </table>
       <section>
         <input type="button" name="main" value="등록" 
         onclick="fun1()">
       </section>
       
       
       <script type="text/javascript">
       function fun1() {
		
    	   alert("애장품정보를 꼭 클릭해주세요!")
    	   location.href="./sellerSelection.se";
	}
       
       
       
       </script>
               
</body>
</html>