<%@page import="net.member.db.MemberBean"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-widthm, intial-scale=1">
<link rel="stylesheet" href="css/bootstrap.css">
<link rel="stylesheet" href="css/custom.css">
<title>사연마켓 채팅창</title>
<script src="./jq/jquery-3.5.1.js"></script>
<script src="js/bootstrap.js"></script>
<script type="text/javascript">	

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

var lastID = 0;
function submitFunction(){
	var chatName = $('#chatName').val();
	var chatContent = $('#chatContent').val();
	$.ajax({
		type: "POST",
		url: "./chatSubmitServlet",
		data: {
			chatName: encodeURIComponent(chatName),
			chatContent: encodeURIComponent(chatContent)
		},
		success: function(result) {
			console.log(result)
			if(result==1) {
				autoClosingAlert('#successMessage', 2000);
			}else if(result==0){
				autoClosingAlert('#dangerMessage', 2000);
			}else {
				autoClosingAlert('#successMessage', 2000);
			}
		}
	});
	$('#chatContent').val('');
}
function autoClosingAlert(selector, delay) {
	var alert = $(selector).alert();
	alert.show();
	window.setTimeout(function() {alert.hide()}, delay);
}

	function submitFunction(){
		var fromID = $('#chatName').val();
		var toID = '<%=toID%>';

		var chatContent = $('#chatContent').val();
		$.ajax({
			type: "POST",
			url: "./chatsSubmitServlet",
			data: {
				fromID: encodeURIComponent(fromID),
				toID: encodeURIComponent(toID),
				chatContent: encodeURIComponent(chatContent),
			},
			success: function(result) {
				console.log(result)
				if(result==1) {
					autoClosingAlert('#successMessage', 2000);
				}else if(result==0){
					autoClosingAlert('#dangerMessage', 2000);
				}else {
					autoClosingAlert('#successMessage', 2000);
				}
			}
		});
		$('#chatContent').val('');
	}
	function autoClosingAlert(selector, delay) {
		var alert = $(selector).alert();
		alert.show();
		window.setTimeout(function() {alert.hide()}, delay);
	}
	
	
	function chatListFunction(type){
		var fromID = $('#chatName').val();
		var toID = '<%=toID%>';

		$.ajax({
			type: "POST",
			url: "./chatsListServlet",
			data: {	
				fromID: encodeURIComponent(fromID),
				toID: encodeURIComponent(toID),
				listType: type
				},
			success: function(data) {
				if(data == "") return;
				var parsed = JSON.parse(data);
				var result = parsed.result;
				console.log(data)
				for(var i = 0; i < result.length; i++) {
					addChat(result[i][0].value, result[i][2].value, result[i][3].value);
				}
				lastID = Number(parsed.last);
				console.log(lastID);
			}
		});
	}
	function addChat(chatName, chatContent, chatTime) {
		$('#chatList').append('<div class="row">' +
				'<div class = "col-lg-12">' +
				'<div class = "media">' +
				'<a class="pull-left" href="#">' +
				'<img class="media-object img-circle" src="images/go.png" alt="">' +
				'</a>' +
				'<div class="media-body">' +
				'<h4 class="media-heading">' +
				chatName +
				'<span class="small pull-right">' +
				chatTime +
				'</span>' +
				'</h4>' +
				'<p>' +
				chatContent +
				'</p>' +
				'</div>'+
				'</div>'+
				'</div>'+
				'</div>'+
				'<hr>');
		$('#chatList').scrollTop($('#chatList')[0].scrollHeight);
	} 
	
	function getInfiniteChat() {
		setInterval(function() {
			chatListFunction(lastID);
		}, 1000);
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

<div class="alert alert-success" id="successMessage" style="display: none;">
				<strong>메시지 전송에 성공하였습니다.</strong>
			</div>
			<div class="alert alert-danger" id="dangerMessage"	style="display: none;">
				<strong>이름과 내용을 모두 입력해주세요.</strong>
			</div>
			<div class="alert alert-warning" id="warningMessage" style="display: none;">
				<strong>데이터베이스 오류가 발생했습니다.</strong>
			</div>

	<div class="container">
		<div class="container bootstrap snippet">
			<div class="row">
				<div class="col-xs-6">
					<div class="portlet portlet-default">
						<div class="portlet-heading">
							<div class="portlet-title">
								<h4>
									<i class="fa fa-circle text-green"></i>실시간채팅방
								</h4>
							</div>
							<div class="clearfix"></div>
						</div>
						<div id="chat" class="panel-collapse collapse in">
							<div id="chatList" class="portlet-body char-widget"
								style="overflow-y: auto; width: auto; height: 400px;">
							</div>
						</div>
					<div class="portlet-footer">
							<div class="row">
								<div class="form-group col-xs-4">
									<input style="height: 40px;" type="text" id="chatName" class="form-control" value="<%=id%>" readonly="readonly">
								</div>
							</div>
							<div class="row" style="height: 90px">
								<div class="form-group col-xs-10">
									<textarea style="height: 80px;" id="chatContent"
										class="form-control" placeholder="메시지를입력하시오" maxlength="100"></textarea>
								</div>
								<div class="form-group col-xs-2">
									<button type="button" class="btn btn-default pull-right" onclick="submitFunction();">전송</button>
									<div class="clearfix"></div>
								</div>
							</div>
						</div>
					</div>
				</div>
			 </div>
		</div>
		</div>	
		
	
			<script type="text/javascript">
			$(document).ready(function() {
				chatListFunction('ten');
				getInfiniteChat();
			});

		</script>
		<br>
		<div style="position: relative; left: 650px;">
		  <input type="button" value="홈으로" onclick="location.href='./storymarket_main.jsp';">
		
		</div>
</body>
</html>