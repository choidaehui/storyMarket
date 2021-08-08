<%@page import="java.io.Console"%>
<%@page import="net.board.db.reviewBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

<meta name="viewport" content="width=device-width, initial-scale=1">
<meta charset="UTF-8">
<link href="./board.css" rel="stylesheet">
<link href="./view/assets/css/boardContent.css"  rel="stylesheet">

<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>

<script>

</script>

<title>storymarket</title>
</head>
<body id="field">

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
	String id = (String)session.getAttribute("id");
	System.out.println("id : " +id);
	String rating = rb.getRating();
	System.out.println("rating : "+rating);
%>


<h2>글 본문보기</h2>

       
     <table>
      <tr>
      <td colspan="3">
                제목 :<%=rb.getSubject() %>
      </td>
      <td colspan="1">
                평가점수 :<%=rating %>	
      </td>
      </tr>
      
      <tr>
      <td colspan="3">
                이름 :  <%=rb.getName() %></td>
      <td colspan="1">
       	거래지역 : <%=rb.getRegionDivied() %>
      </td>
      </tr>
      <tr>
        <td colspan="4">
          <textarea rows="10" cols="30">
            <%=rb.getContent() %>
          </textarea>
         <%if(rb.getFile() != null){ %>
            <img src="./upload/<%=rb.getFile() %>" height="300" width="300">
         <%}else{ %>
          <h5>이미지 없음</h5>
         <%} %>             
        </td>
      </tr>
      <tr>
        <td colspan="4">첨부파일
        <%if(rb.getFile() != null){ %>
        	  
          <a href="./upload/<%=rb.getFile() %>"><%=rb.getFile() %></a>
        	
       <%}else{ %>
          <h5>이미지 없음</h5>
       <%} %>         
        </td>
      </tr>
      <tr>
   	   <td colspan="4" id="tdlist"><!-- 수정이 필요한 정보는 &로 계속 붙여준다.  -->
   	   <input type="button" value="수정하기"
   	     onclick="location.href='./BoardUpdate.bo?pageNum=<%=pageNum %>&num=<%=rb.getNum() %>';" 
   	   > 
   	
   	   <input type="button" value="삭제하기"
   	   	onclick="location.href='./BoardDelete.bo?pageNum=<%=pageNum %>&num=<%=rb.getNum() %>';"
   	   > 
   	
   	  <%--  <input type="button" value="답글쓰기"
   	   	onclick="location.href='./BoardReWrite.bo?pageNum=<%=pageNum %>&num=<%=rb.getNum() %>&re_ref=<%=rb.getRe_ref() %>&re_lev=<%=rb.getRe_lev() %>&re_seq=<%=rb.getRe_seq() %>';"
   	   >  --%>
   	   
   	   <input type="button" value="목록으로" 
   	   	onclick="location.href='./BoardList.bo?pageNum=<%=pageNum %>';"
   	   > 
   	   
   	  
   	   <%
   	   if(rb.getLikeCount() != null){
   		%>   
   		<input type="button" title="좋아요!"  value="♡+<%=rb.getLikeCount() %>"
   			onclick="location.href='./BoardLike.bo?pageNum=<%=pageNum %>&ip=<%=rb.getIp() %>&num=<%=rb.getNum() %>';"
   		>    
   	   <%   
   	   }else{
   	   %>
   	   <input type="button" value="추천하기"
   	   	onclick="location.href='./BoardLike.bo?pageNum=<%=pageNum %>&ip=<%=rb.getIp() %>&num=<%=rb.getNum() %>';"
   	   	> 
   	   <%} %>
   	   
   	   
       </td>
       </tr>
     </table> 
   </form>
   
   
   
   <!-- 덧글입력하기 -->
   <!--  <form id="comment" action="./CommentAction" method="post">
    <table border="1">
    <tr>
      <td colspan="4">코멘트!</td>
    </tr>
    <tr>
     <td>이름</td>
     <td>
     <input type="text" name="name"> 
     </td>
     </tr>
     <tr>
     <td>내용</td>
     <td>
       <input type="text" name="content">
     </td>
     </tr>
     <tr>
     <td colspan="2" id="collist">
      <input type="submit" value="등록">
      <input type="button" value="삭제">
   	  <td>
   	  </tr>	 -->
   <!--  </table>
    </form> 
    -->
   



</body>
</html>