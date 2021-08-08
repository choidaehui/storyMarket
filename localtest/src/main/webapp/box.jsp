
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-widthm, intial-scale=1">
<link rel="stylesheet" href="css/bootstrap.css">
<link rel="stylesheet" href="css/custom.css">
<title>사연마켓 채팅함</title>
<script src="./jq/jquery-3.5.1.js"></script>
<script src="js/bootstrap.js"></script>

<%
// * 스클립틀릿의 코드는 (java/jsp)한번에 실행 가장먼저
String id = (String)session.getAttribute("id");

//id의 정보가 없을때, (로그인을 안한경우 다시 로그인 페이지로 이동)
if(id == null){
	response.sendRedirect("./MemberLogin.me");
}

String toID=null;
if(request.getParameter("toID")!=null){
	toID=(String) request.getParameter("toID");
}

%>

<script type="text/javascript">	

function chatBoxFunction(){
		var userID ='<%=id%>';
		$.ajax({
			type: "POST",
			url: "./chatsBoxservlet",
			data: {	
				userID: encodeURIComponent(userID),
				},
			success: function(data) {
				if(data == "") return;
				$('boxTable').html('');
				var parsed = JSON.parse(data);
				var result = parsed.result;
				for(var i = 0; i < result.length; i++) {
				if(result[i][0].value == userID){
					result[i][0].value =result[i][1].value;
				}else{result[i][1].value =result[i][0].value;}
				addBox(result[i][0].value, result[i][1].value,result[i][2].value,result[i][3].value);
				}}
		});
	}
	function addBox(lastID, toID, chatContent, chatTime){
		$('#boxTable').append('<tr onclick="location.href=\'chat2.jsp?toID=' +encodeURIComponent(toID) + '\'">'+
				'<td style="width:150px;"><h5>' +lastID+ '</h5></td>' +
				'<td>' +
				'<h5>' +chatContent+ '</h5>' +
				'<div class="pull-right">' +chatTime+ '</div>'+
				'</td>' +
				'</tr>');
		
		
	}
	
	function getInfiniteBox() {
		setInterval(function() {
			chatBoxFunction();
		}, 5000)
	}
	
	</script>
</head>



<style>
		body {
		background-color: #000;
		background-image: url("./images/bg.jpg");
		background-size: cover;
		background-position: top center;
	}
</style>

<body>

	<div class="container">
		<table class="table" style="background-color:skyblue; margin-top:30px">
			<thead>
				<tr>
					<th><h4><%=id %>님의 채팅함</h4></th>
				</tr>
			</thead>
			<div style="overflow-y: auto; width: 100%; max-height: 450px;">
			<table class="table table-bordered table-hover" style=" background-color:white; center; border:1px solid #ddddd; margin:0 auto;">
				<tbody id="boxTable">
				</tbody>
			</table>
				
			</div>
	
		
		
		</table>
		</div>
		
		
		
		<script type="text/javascript">
			$(document).ready(function(){ 
				chatBoxFunction();
				//getInfiniteBox();
			
			});
		</script>
		<br>
		
		<div style="position: relative; left: 900px;">
		  <input type="button" value="홈으로" onclick="location.href='./storymarket_main.jsp';">
		
		</div>
	
</body>
</html>