<%@page import="net.board.db.reviewBean"%>
<%@page import="java.util.ArrayList"%>
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
<h1>WebContent/board/boardList.jsp</h1>
<a href = "./LogMain.me">회원 메인페이지</a>
<%
 String id = (String)session.getAttribute("id");
 if(id == null){
%>
  <a href = "./MemberLogin.me">로그인</a>
<%	 
 }else{
%>
<a href="./BoardListBack.bo">로그아웃</a>
<% }%>
<hr>
<%
/*   ///request 영역에 필요한 정보를 저장. 보드리스트액션에서 보내준 정보참조하여 적기
		request.setAttribute("boardList", boardList);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("count", count);//총 글의 수
		request.setAttribute("pageCount", pageCount); // 총 페이지 수
		request.setAttribute("pageBlock", pageBlock); // 페이지 블럭의 수
		request.setAttribute("startBlock", startBlock);//블럭 시작페이지
		request.setAttribute("endBlock", endBlock);//블럭 끝페이지 */
		
		
		ArrayList boardList =  (ArrayList)request.getAttribute("boardList");//업캐스팅
		String pageNum = (String)request.getAttribute("pageNum");
		
		int count = (Integer)request.getAttribute("count");
		int pageCount = (Integer)request.getAttribute("pageCount");
		int pageBlock = (Integer)request.getAttribute("pageBlock");
		int startBlock = (Integer)request.getAttribute("startBlock");
		int endBlock = (Integer)request.getAttribute("endBlock");
 
		String regionDivied = "지역전체";
		String searchType = "리뷰전체";
		String search = "";
		

 %>
 
 <h1> 게시판 목록 [총 :<%=count %>  개]</h1>
 <!--  action="./BoardList.bo?regionDivie=<%=regionDivied %>&searchType=<%=searchType %>&search=<%=search %>&pageNum=<%=pageNum%>" method="get" -->
 <form action="./BoardList.bo" name="searchForm" method="get"> 
 <select name = "f" >
    <option ${(param.f == "subject")? "selected":"" } value="subject">제목</option>
    <option ${(param.f == "name")? "selected":"" } value="name" >작성자</option>

 </select>

 <input type= "text" value="${param.q}" name="q" placeholder="검색어를 입력해주세요">
 <input type="submit" value="검색">
 </form>
<%--  <h1> 게시판 목록 [총 : ${count }  개]</h1> --%>
 <%
 if (id!=null){
%>
 <h2><a href="./BoardWrite.bo">글쓰기</a>
&nbsp; <a href="./FileBoardWrite.bo">파일첨부글쓰기</a>
&nbsp;<a href="./MailAdmin.bo">문의하기</a> 
 </h2>
<%
 }
 %>
 
 <table>
   <tr>
     <td>번호</td>
     <td>지역</td>
     <td>제목</td>
     <td>작성자</td>
     <td>작성일</td>
     <td>조회수</td>
     <td>ip</td>
     <td>추천</td>
   </tr>
   
   <%for(int i =0;i<boardList.size();i++){ 
   		reviewBean rb = (reviewBean)boardList.get(i);
   %>
      <tr>
     <td><%=rb.getNum() %></td>
     
     <td><%=rb.getRegionDivied() %></td>
     <td>
     	 <!-- 컨텐츠 내용이 보이게 제어 pageNum은 글보고 돌아올때 필요한 객체-->
         <a href="./BoardContent.bo?num=<%=rb.getNum()%>&pageNum=<%=pageNum%>"><%=rb.getSubject() %></a>
         
     </td>
     <td><%=rb.getName() %></td>
     <td><%=rb.getDate() %></td>
     <td><%=rb.getReadcount() %></td>
     <td><%=rb.getIp() %></td>
     <td>추천</td>
   </tr>
   <%} %>
 </table>
 
 
 
 
   <%
     //페이징 처리 (하단부)
     if(count != 0){
   		
    	 // 이전
    	 // start5   page2
    	 if(startBlock > pageBlock){
    		 %>
    		 <a href="./BoardList.bo?pageNum=<%=startBlock-pageBlock%>">[이전]</a>
    		 <%
    	 }
    	 
    	 //페이지숫자 먼저처리 하고 이전 이후
    	 for(int i=startBlock; i<=endBlock; i++){
    		 %>
    		   <a href="./BoardList.bo?pageNum=<%=i%>"> [<%=i %>]</a>
    		 <%
    	 }//for
    	 
    	 
    	 //다음 
    	 //end2 < page6
    	 if(endBlock < pageCount){
    		%>
    		 <a href="./BoardList.bo?pageNum=<%=startBlock+pageBlock%>">[다음]</a>
    		<%
    	 }
    	 
    	 
     }//if
   
   %>
 
 

</body>
</html>