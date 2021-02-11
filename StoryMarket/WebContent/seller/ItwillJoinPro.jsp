<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<%
 


 String id = request.getParameter("id");
 int pwd = Integer.parseInt(request.getParameter("pwd"));
 String name = request.getParameter("name");
 int num1 = Integer.parseInt(request.getParameter("num1"));
 int num2 = Integer.parseInt(request.getParameter("num2"));
 String gender = request.getParameter("gender");
 String hobby1 = request.getParameter("hobby1");
 String hobby2 = request.getParameter("hobby2");
 String hobby3 = request.getParameter("hobby3");
 String classRoom = request.getParameter("classRoom");
 String textA = request.getParameter("textA");
 
 
 
 
 out.println("아이디: "+id+"<br>");
 out.println("패스워드: "+pwd+"<br>");
 out.println("이름: "+name+"<br>");
 out.println("주민번호: "+num1+"-"+num2+"<br>");
 out.println("성별: "+gender+"<br>");
 out.println("취미: "+hobby1+","+hobby2+","+hobby3); 
 

 
 
 
 /* out.print("취미: ");  
 for(int i=0; i<hobby.length; i++){
	
	 out.print(hobby[i]);
	 
	 if(i+1 == hobby.length){
		 
		 out.println();
	 }else{
		 
		 out.print(",");
	 }
	 
 }
	
 */
/*  if(hobby == "여행" && hobby == "게임"){
	 
	 out.println("여행,게임");
 }else if(hobby == "여행" && hobby == "요리"){
	 
	 out.println("여행,요리");
 }else if(hobby == "게임" && hobby == "요리"){
	 
	 out.println("게임,요리");
 }else if(hobby == "여행" && hobby == "게임" && hobby == "요리"){
	 
	 out.println("여행,게임,요리");
 } */
 
 out.println("강의실: "+classRoom+"<br>");
 out.println("하고싶은말: "+textA+"<br>");
%>




</body>
</html>