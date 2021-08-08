<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="./view/assets/css/updateForm.css" rel="stylesheet">
<title>storymarket</title>
<script src="https://code.jquery.com/jquery-3.5.1.js" integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc=" crossorigin="anonymous">

  $.ajax({
    url: "./WriteReplyAction/bo",
    context: document.body
  }).done(function() {
    $( this ).addClass( "done" );
  });
  
  </script>
</head>
<body>

  <form id="writeReplyForm">
    <input type="hidden" id="articleId" name="articleId" value="${ selectedArticle.articleI}" />
    <input type="hidden" id="depth" name="depth" value="0" />
    <input type="hidden" id="parentReplyId" name="parentReplyId" value="0"/>
    <input type="hidden" id="groupId" name="groupId" value="-1"/>
    <input type="hidden" id="orderNo" name="orderNo" value="-1"/>
    <textarea id="description" name="description"></textarea>
    <input type="button" id="writeReplyBtn"  value="댓글등록"/>
  </form>
  
  

</body>
</html>