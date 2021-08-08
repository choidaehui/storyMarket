<%@page import="net.board.db.reviewBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>storymarket</title>
<link href="./view/assets/css/updateForm.css" rel="stylesheet">

</head>
<body>
 
  <%
  	System.out.println("V : WebContent/board/updateForm.jsp 호출");
  	
  	//액션페이지에서 받은 request정보 저장
    //request.setAttribute("bb", bb);
	//request.setAttribute("pageNum", pageNum);

	reviewBean rb = (reviewBean)request.getAttribute("rb");
	String pageNum = (String)request.getAttribute("pageNum");
  
  %>
 <section> 
 <div id="hh">
  <h1 id="h1">글 수정하기 페이지</h1>
 </div>
 </section> 
  <fieldset id="field"><!-- 주소뒤에 ?를 써서 파라미터 정보를 들고간다.  Num정보는 DB 안에 있으므로 히든으로 숨겨서 덩어리로 이동시킨다. -->
    
  <form action="./BoardUpdateAction.bo?pageNum=<%=pageNum %>" method="post">
  <input type="hidden" name="num" value="<%=rb.getNum()%>">
    글쓴이 : <input type="text" name="name" value="<%=rb.getName()%>"><br>
  비밀번호 :  <input type="password" name="pass" placeholder="비밀번호를 입력하시오"><br>
          지역 : <select name ="regionDivied">
        <option value="전체">지역전체</option>
    	<option value="서울" >서울</option>
    	<option value="경기도" >경기도</option>
    	<option value="강원도" >강원도</option>
    	<option value="전라남도" >전라남도</option>
    	<option value="전라북도" >전라북도</option>
    	<option value="경상남도" >경상남도</option>
    	<option value="경상북도" >경상북도</option>
    	<option value="충청남도" >충청남도</option>
    	<option value="충청북도" >충청북도</option>
    	<option value="제주도" >제주도</option>
    	<option value="부산" >부산</option>
    	<option value="대전" >대전</option>
    	<option value="대구" >대구</option>
    	<option value="울산" >울산</option>
    	<option value="인천" >인천</option>
       </select><br>
      제목 : <input type="text" name="subject" value="<%=rb.getSubject() %>"><br>
      내용 : <br>
        <textarea rows="10" cols="30" name="content"><%=rb.getContent() %></textarea><br>
 <div id="update">    
  <input type="submit" value="수정하기">
  <input type="reset" value="초기화">   
 </div>       
  </form>
  </fieldset>
  
</body>
</html>