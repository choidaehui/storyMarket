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
  	System.out.println("V : WebContent/board/reWriteForm.jsp 호출");
  
  //BoardReWrite.bo?pageNum=3&num=8&re_ref=8&re_lev=0&re_seq=0
  //--->전달한 정보 저장()해서 폼태그에 넣어주기 // 다음페이지로 연결해주기 위함
  String pageNum = request.getParameter("pageNum");
  int num = Integer.parseInt(request.getParameter("num"));
  int re_ref = Integer.parseInt(request.getParameter("re_ref"));
  int re_lev = Integer.parseInt(request.getParameter("re_lev"));
  int re_seq = Integer.parseInt(request.getParameter("re_seq"));
  
  
  %>
  
  <h1> 답글쓰기 페이지</h1>
  
  <fieldset><!--pageNum=<%=pageNum %>" el 표현식으로 적어보기 -->
  <form action="./BoardReWriteAction.bo?pageNum=<%=pageNum %>" method="post">
  <input type="hidden" name="num" value="<%=num%>">
  <input type="hidden" name="re_ref" value="<%=re_ref%>">
  <input type="hidden" name="re_lev" value="<%=re_lev%>">
  <input type="hidden" name="re_seq" value="<%=re_seq%>">
  
    글쓴이 : <input type="text" name="name" placeholder="글쓴이의 이름을 입력하시오"><br>
  비밀번호 :  <input type="password" name="pass"><br>
      지역 : <select name ="regionDivied">
        <option value="전체">지역전체</option>
    	<option value="서울" >서울</option>
    	<option value="부산" >부산</option>
       </select> 
      제목 : <input type="text" name="subject" value="[답글]"><br>
      내용 : <br>
        <textarea rows="10" cols="30" name="content"></textarea><br>
  
 <div id="result">     
  <input type="submit" value="답글쓰기">
  <input type="reset" value="초기화"><br>    
 </div>      
  </form>
  </fieldset>
  
</body>
</html>