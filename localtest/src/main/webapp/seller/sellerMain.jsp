<%@page import="java.util.List"%>
<%@page import="net.seller.db.SellerBean"%>
<%@page import="org.apache.commons.collections4.Get"%>
<%@page import="net.member.db.MemberDAO"%>
<%@page import="net.member.db.MemberBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<title>StoryMarket</title>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1" />
		<link rel="stylesheet" href="./view/assets/css/sellerMain.css" />
		<link rel="preconnect" href="https://fonts.gstatic.com">
        <link href="https://fonts.googleapis.com/css2?family=Nanum+Myeongjo&display=swap" rel="stylesheet">
	</head>
	<body>

		<!-- Wrapper -->
			<div id="wrapper">

				<!-- Header -->
					<header id="header">
<%
         String seller_id = (String) session.getAttribute("id");         
          
         System.out.println("세션값 아이디sellerMain.jsp 호출"+seller_id);
         
         MemberBean mb = (MemberBean) session.getAttribute("mb");
         
         System.out.println("자바빈객체 세션값 sellerMain호출"+mb);

          List sellerList = (List) request.getAttribute("sellerList");
          SellerBean sb = new SellerBean();

%>				

			   
  		   			<span class="avatar"><img src="./memberimg/<%=mb.getImgname()%>" /></span>
						<br>
						<a href="./main.me" id="id"><%=seller_id %></a>
						<br><br>
						<h1><strong>사연마켓</strong></h1>
						<ul class="icons">
							<li><a href="./sellerSelectionForm.se" id="item">등록</a></li>
							<li><a href="./tradeItem.it" id="trade">거래</a></li>
							<li><a href="./BoardList.bo" id="review">후기</a></li>
							<li><a href="./selectView.sel" id="select">찜</a></li>
<!-- 							<li><input type="text" id="search" placeholder="검색"></li>
 -->						</ul>
					</header>
                 <h1 style="text-align: center;">"<%=seller_id %>"님의 판매목록</h1>
        <!-- Main -->
		<section class="main">
                       
               
			<!-- Thumbnails -->
				<section class="thumbnails">
				    
				 <div class="grid">
                        
                     <% for(int i=0; i<sellerList.size(); i++){ 
                            sb = (SellerBean)sellerList.get(i);
                     
                     %>
                       <div>
                            <a href="./userContentView.us?seller_num=<%=sb.getSeller_num()%>">
				               <img src="./upload/<%=sb.getImage() %>" id="img" />		
				               <h2 id="copy"><%=sb.getCopy_text() %></h2></a>					
				       </div>	
					
					<% } %>	
				</div>
			 </section>	
			         <input type="button" name="home" value="홈으로" onclick="location.href='./storymarket_main.us';">         		
		  </section>		
		<!-- Footer -->
		    <div>
					<footer id="footer">
						<p>&copy; Untitled. All rights reserved. Design <h2>StoryMarket</h2></p>
					</footer>

			</div>

		<!-- Scripts -->
			<script src="assets/js/jquery.min.js"></script>
			<script src="assets/js/jquery.poptrox.min.js"></script>
			<script src="assets/js/skel.min.js"></script>
			<script src="assets/js/main.js"></script>
       </div>
	</body>
</html>