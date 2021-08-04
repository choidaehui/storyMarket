<%@page import="net.select.db.selectDAO"%>
<%@page import="net.select.db.selectBean"%>
<%@page import="net.seller.db.SellerBean"%>
<%@page import="net.user.db.userBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>storymarket</title>
<link href="./view/assets/css/userContentView.css" rel="stylesheet">
<link rel="preconnect" href="https://fonts.gstatic.com">
<link href="https://fonts.googleapis.com/css2?family=Nanum+Myeongjo&display=swap" rel="stylesheet">
</head>
<body>

    <%
      selectBean sel = (selectBean) session.getAttribute("sel");
    %>
    
     <table>  
     
       <tr>
     
       <td colspan="2"><video width="1000" height="550" controls="controls" autoplay="autoplay" preload="metadata"> 
        <source src="./upload/<%=sel.getJ_file()%>" type="video/mp4" /> 
        <source src="./upload/<%=sel.getJ_file()%>" type="video/ogg" /> 
               
           </video> </td> 
                
      </tr>
       <tr>
      
       <td colspan="2" id="td"><h2><strong id="message"><%=sel.getJ_message() %></strong></h2></td>      
      </tr>
       <tr>
      
     </table>
      <input type="button" name="button" value="돌아가기" onclick="history.back()">
    

</body>
</html>