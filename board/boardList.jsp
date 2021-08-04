<%@page import="net.boardLike.db.likeBean"%>
<%@page import="net.board.db.reviewBean"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta charset="UTF-8">
<link href="./view/assets/css/boardList.css" rel="stylesheet">

<title>storymarket</title>

</head>
<body>
<div>
<section>
<ul id="ullist">
 
<li><a href = "./storymarket_main.jsp">회원 메인페이지</a></li>
<%
 String id = (String)session.getAttribute("id");
 
 if(id == null){
%>
  <li><a href = "./MemberLogin.me">로그인</a></li>
<%	 
 }else{
%>
<li><a href="./BoardListBack.bo">로그아웃</a></li>
<% }%>
</ul>

</section>
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
		System.out.println("V : boardList.jsp : "+boardList);
		
		int count = (Integer)request.getAttribute("count");
		int pageCount = (Integer)request.getAttribute("pageCount");
		int pageBlock = (Integer)request.getAttribute("pageBlock");
		int startBlock = (Integer)request.getAttribute("startBlock");
		int endBlock = (Integer)request.getAttribute("endBlock");
 
		String regionDivied = "지역전체";
		String searchType = "리뷰전체";
		String search = "";

		
		//아이디 나오는지 확인
		System.out.println("V : boardList.jsp ID정보: "+id);

		//아이디정보 보내기
		//request.setAttribute("id", id);

		
		
		
 %>
 
<section>  
<div>
<h1> 게시판 목록 [총 :<%=count %>  개]</h1>
<ul class="icons">

 
 <%

 if (id!=null){
%>
 <li><a href="./BoardWrite.bo" id="item"><h5>글쓰기</h5></a></li>
 <li><a href="./FileWrite.bo" id="item"><h5>파일첨부글쓰기</h5></a></li>
 <li><a href="./MailAdmin.bo" id="item"><h5>문의하기</h5></a> </li>


<%
 }
 %>
</ul>
</div>
</section>
 <div>
  <fieldset>
 <!--  action="./BoardList.bo?regionDivie=<%=regionDivied %>&searchType=<%=searchType %>&search=<%=search %>&pageNum=<%=pageNum%>" method="get" -->
 <form action="./SearchAction.bo" id="searchForm" method="get"> 
 <input type="hidden" name="searchflag" value="true">

 <input type= "text" id="search" name="search" placeholder="검색어를 입력해주세요"><br>
<!--  <input type="submit" value="검색"> -->
 </form>
 </fieldset>
 </div>

 
 <div class="table-wrapper">
 <table class="tableWidth">
   <tr>
     <td>번호</td>
     <td>지역</td>
     <td>제목</td>
     <td>작성자</td>
     <td>작성일</td>
     <td>조회수</td>
     <!-- <td>ip</td> -->
     <td>평점</td>
   </tr>
   
   <%for(int i =0;i<boardList.size();i++){ 
   		reviewBean rb = (reviewBean)boardList.get(i);
   %>
      <tr>
     <td><%=rb.getNum() %></td>
     
     <td><%=rb.getRegionDivied() %></td>
     <td class="imgHover">
     	 <!-- 컨텐츠 내용이 보이게 제어 pageNum은 글보고 돌아올때 필요한 객체-->
         <a  href="./BoardContent.bo?num=<%=rb.getNum()%>&pageNum=<%=pageNum%>&file=<%=rb.getFile()%>&ip=<%=rb.getIp()%>&rating=<%=rb.getRating() %>">
         <%=rb.getSubject() %>
         <%
         String img = rb.getFile();
         if(rb.getFile() != null){%>
         <img id="img" src="./upload/<%=img %>" height="30" width="30">
         <%}%>
         </a>
     
     </td>
     <td><%=rb.getName() %></td>
     <td><%=rb.getDate() %></td>
     <td><%=rb.getReadcount() %></td>
     <%-- <td><%=rb.getIp() %></td> --%>
     <td><%=rb.getRating() %>
     <%
     if(rb.getLikeCount() != null){
    %>♡+
    <%=rb.getLikeCount() %>
    <%
     } 
     %>
     </td>
   </tr>
   <%} %>
 </table>
 </div>
 <br>
 

   <%
     //페이징 처리 (하단부)
     if(count != 0){
   		
    	 // 이전
    	 // start5   page2
    	 if(startBlock > pageBlock){
    		 %>
    		 <a href="./BoardList.bo?pageNum=<%=startBlock-pageBlock%>"><h2>[이전]</h2></a>
    		 <%
    	 }
    	 
    	 //페이지숫자 먼저처리 하고 이전 이후
    	 for(int i=startBlock; i<=endBlock; i++){
    		 %>
    		   <a href="./BoardList.bo?pageNum=<%=i%>"> <h2>[<%=i %>]</h2></a>
    		 <%
    	 }//for
    	 
    	 
    	 //다음 
    	 //end2 < page6
    	 if(endBlock < pageCount){
    		%>
    		 <a href="./BoardList.bo?pageNum=<%=startBlock+pageBlock%>"><h2>[다음]</h2></a>
    		<%
    	 }
    	 
    	 
     }//if
   
   %>
 
 </div>

</body>
</html>