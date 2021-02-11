<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.google.gson.Gson" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap"%>
<%
	String len = request.getParameter("len");
	Gson gson = new Gson();
	Map map = new HashMap();

	if("id001".equals(len) 
    || "id002".equals(len) 
    || "id003".equals(len) 
    || "".equals(len) 
    || len == null){
		map.put("result", "N");		
	}else{
		map.put("result", "Y");
	}
	String json = gson.toJson(map);
	out.print(json);
%>