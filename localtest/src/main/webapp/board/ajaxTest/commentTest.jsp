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
	  function like(){
		  $.ajax({
		  url: "BoardServlet",
		  type: "POST",
		  cache: false,
		  dataType: "json",
		  data: $('#like_form').serialize(), //아이디가 like_form인 곳의 모든 정보를 가져와 파라미터 전송 형태(표준 쿼리형태)로 만들어줌
		  success:
		  function(data){ //ajax통신 성공시 넘어오는 데이터 통째 이름 =data
		  alert("'좋아요'가 반영되었습니다!") ; // data중 put한 것의 이름 like
		  $("#like_result").html(data.like); //id값이 like_result인 html을 찾아서 data.like값으로 바꿔준다.
		  },
		  error:
		  function (request, status, error){
		  alert("ajax실패")
		  }
		  });
		  }

  });

</script>

</head>
<body>

  <form id="like_form">
      <textarea id="" name="" value="" >[내용을 입력하세요]</textarea>
    <hr>
      <textarea id="" name="" value="">[d]</textarea>
    
    <br>
    <table>
    <input type="hidden" name="command" value="like_it">
	<input type="hidden" name="board_num" value="${board.num}">
	<tr><input type="button" value="좋아요!" onclick="return like()" > </tr>
	<tr><div id="like_result">${board.like_it}</div> </tr>
    
  	</table>
  </form>



</body>
</html>