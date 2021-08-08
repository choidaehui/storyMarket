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
		<link rel="stylesheet" href="./view/assets/css/storymarket_main.css" />
	</head>
	<body>

		<!-- Wrapper -->
			<div id="wrapper">

				<!-- Header -->
					<header id="header">
<%
              
         MemberBean mb = new MemberBean(); 
         session.getAttribute("mb");
         
          System.out.println("메인페이지: "+mb);
          
          List mainList = (List)request.getAttribute("mainList");

%>				

			   
						<span class="avatar"><img src="./memberimg/<%=mb.getImgname()%>"/></span>
						<br>
						<a href="./main.me" id="id"><%=mb.getId() %></a>
						<br><br>
						<h1><strong>사연마켓</strong></h1>
						<ul class="icons">
							<li><a href="./sellerSelection.se" id="item">등록</a></li>
							<li><a href="./tradeItem.it" id="trade">거래</a></li>
							<li><a href="./BoardList.bo" id="review">후기</a></li>
							<li><a href="./selectView.sel" id="select">찜</a></li>
<!-- 							<li><input type="text" id="search" placeholder="검색"></li>
 -->						</ul>
					</header>
                   
				<!-- Main -->
					<section id="main">

						<!-- Thumbnails -->
							<section class="thumbnails">
							<% 
								   for(int i=0; i<mainList.size(); i++){
								
								   SellerBean sb = (SellerBean) mainList.get(i);
								%>
								
								<div>
							      
									<a href="images/fulls/01.jpg">
									    <h3><%=sb.getSeller_id() %></h3> 
										<img src="./memberimg/<%=sb.getImage() %>" alt="" />
										<h3><%=sb.getCopy_text() %></h3>
									</a>
								 </div>	
								 <br><br>
								<% } %>
							</section>
						</section>

				<!-- Footer -->
					<footer id="footer">
						<p>&copy; Untitled. All rights reserved. Design <h2>StoryMarket</h2></p>
					</footer>

			</div>

		<!-- Scripts -->
			<script src="assets/js/jquery.min.js"></script>
			<script src="assets/js/jquery.poptrox.min.js"></script>
			<script src="assets/js/skel.min.js"></script>
			<script src="assets/js/main.js"></script>

	</body>
</html>