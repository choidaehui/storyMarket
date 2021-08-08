<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>storymarket</title>
<link href="./view/assets/css/fileWriteForm.css" rel="stylesheet">

</head>

<body id="field">


<h2>글쓰기</h2>

   
   <form action="./FileBoardWriteAction.bo" method="post" enctype="multipart/form-data">

     <table  id="tablelist" >
      <tr>
      <td colspan="4">
                제목 <input type="text" name="subject">
      </td>
      <td>
        <select id="rating" name="rating">
          <option value="0">평가하기</option>
          <option value="★">★</option>
          <option value="★★">★★</option>
          <option value="★★★">★★★</option>
          <option value="★★★★">★★★★</option>
          <option value="★★★★★">★★★★★</option>
        </select>
      </td>
      
      </tr>
      
      <tr>
      <td colspan="2">이름 <input type="text" name="name"></td>
      <td colspan="2">비밀번호 <input type="password" name="pass"></td>
      <td>
        <select name="regionDivied">
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
        </select>
      </td>
      </tr>
      <tr>
        <td colspan="5">
          <textarea rows="10" cols="30" name="content">내용</textarea>
        </td>
      </tr>
      <tr>
        <td colspan="5">첨부파일
          <input type="file" name="file">
        </td>
      </tr>

     </table> 
     <br>
    <div id="divwrite">
        <input type="submit" value="글쓰기">
        <input type="reset" value="초기화">   
    </div>
     
   </form>
   
     
    <form id="like_form">
	
	<input type="hidden" name="command" value="like_it">
	<input type="hidden" name="board_num" value="${board.num}">
	<input type="hidden" value="좋아요!" onclick="return like()" > 
	<div id="like_result">${board.like_it}</div> 
	
	</form>
	
	

    
     

</body>
</html>