<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="http://code.jquery.com/jquery-1.11.2.min.js"></script>
<script src="http://code.jquery.com/jquery-migrate-1.2.1.min.js"></script>

<script type="text/javascript">
  $(document).ready(function(){
	  $.ajax({
		  
		  type:"get",
		  url : "ajaxAc.jsp",
		  data : {
			  name : '이름출력하기'
		  },		  
		  dataType : "text",
		  error : function(data){
			alert('error!'); 
		  },
		  success : function(data){
			  alert("데이터값 : "+data);
			  $('#dataArea').append(data);
		  }
		  
		  
	  });
	  
  });

</script>
</head>
<body>
<h1>AJAX 테스트 페이지</h1>

	<div id = "dataArea">  </div>


</body>
</html>