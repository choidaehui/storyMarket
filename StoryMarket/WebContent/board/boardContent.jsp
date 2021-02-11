<%@page import="net.board.db.reviewBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

<meta name="viewport" content="width=device-width, initial-scale=1">
<meta charset="UTF-8">
<link href="./board.css" rel="stylesheet">
<title>Insert title here</title>
<link href="../view/assets/css/publicStyle.css" rel="stylesheet">
</head>
<body>
<h1>WebContent/board/boardContent.jsp</h1>
<%

	//BoardContentAction에서 보낸 글정보 저장(request)
	//request.setAttribute("bb", bb);
	//request.setAttribute("pageNum", pageNum);
	//Attribute으로 보낸 정보는 getAttribute로 받아서 저장한다. 
	
	reviewBean rb = (reviewBean)request.getAttribute("rb");
	String pageNum = (String)request.getAttribute("pageNum");
	//원래보던 글 정보로 돌아가기 위해서 필요한 페이지넘버.
	System.out.println("reviewBean rb : "+rb);
	
	//정보 보내기
	request.setAttribute("rb", rb);
	
%>


<h2>글 본문보기</h2>
<table border="1">
   <tr>
     <td>번호</td>
     <td><%=rb.getNum() %></td>
     <td>조회수</td>
     <td><%=rb.getReadcount() %></td>
   </tr>
      <tr>
     <td>글쓴이</td>
     <td><%=rb.getName() %></td>
     <td>작성일</td>
     <td>${rb.date }</td> <!-- Attribute값이 있으므로 el표현식을 써도 됨. -->
   </tr>
   <tr>
     <td>글제목</td>
     <td colspan="3"><%=rb.getSubject() %></td>
   </tr>
   
   <tr>
     <td>지역</td>
     <td colspan="3"><%=rb.getRegionDivied() %></td>
   </tr>
   
   
   <tr>
     <td>글내용</td>
     <td colspan="3"><%=rb.getContent() %></td>
   </tr>
   
  
  <tr>
     <td>첨부파일</td>
     <td colspan="3">
      <%
   if(rb.getFile() != null){
	%>
	  <%--  <a href="./upload/<%=rb.getFile() %>" onclick="javascript: fileCk.submit() "><%=rb.getFile() %> --%>
	      <a href="./FileDown.bo?file_name=<%=rb.getFile() %>" onclick="javascript: fileCk.submit() "><%=rb.getFile() %>
	   </a>
	 <%  
   }
   %>
     </td>
   </tr>
   
   
   <tr>
   	 <td colspan="4"><!-- 수정이 필요한 정보는 &로 계속 붙여준다.  -->
   	   <input type="button" value="수정하기"
   	     onclick="location.href='./BoardUpdate.bo?pageNum=<%=pageNum %>&num=<%=rb.getNum() %>';" 
   	   > 
   	
   	   <input type="button" value="삭제하기"
   	   	onclick="location.href='./BoardDelete.bo?pageNum=<%=pageNum %>&num=<%=rb.getNum() %>';"
   	   > 
   	
   	   <input type="button" value="답글쓰기"
   	   	onclick="location.href='./BoardReWrite.bo?pageNum=<%=pageNum %>&num=<%=rb.getNum() %>&re_ref=<%=rb.getRe_ref() %>&re_lev=<%=rb.getRe_lev() %>&re_seq=<%=rb.getRe_seq() %>';"
   	   > 
   	
   	   <input type="button" value="목록으로" 
   	   	onclick="location.href='./BoardList.bo?pageNum=<%=pageNum %>';"
   	   > 
   	   
   	   <input type="button" value="추천하기"
   	     onclick="location.href='./Rating.bo?pageNum=<%=pageNum %>&num=<%=rb.getNum() %>';" 
   	   > 
   	   
   	   
   	 </td>
   </tr>
</table>



</body>
</html>