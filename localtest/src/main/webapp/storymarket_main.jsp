<%@page import="net.seller.db.SellerDAO"%>
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
		<link rel="preconnect" href="https://fonts.gstatic.com">
        <link href="https://fonts.googleapis.com/css2?family=Nanum+Myeongjo&display=swap" rel="stylesheet">
	</head>
	<body>

		<!-- Wrapper -->
			<div id="wrapper">

				<!-- Header -->
					<header id="header">
<%
         String id = (String) session.getAttribute("id");         
                     
         MemberBean mb = (MemberBean) session.getAttribute("mb");
         
         SellerDAO sdao = new SellerDAO();
         List mainList =  sdao.getMainList();
                          
          SellerBean sb = new SellerBean();

%>				

			   
  		   			<span class="avatar"><img src="./memberimg/<%=mb.getImgname()%>" /></span>
						<br>
						<a href="./Main.me" id="id"><%=id %></a>
						<br><br>
						 <input type="button" name="chatbox" value="채팅함"  style="margin-right: 290px;"
       					  onclick="location.href='./customerchatBox.cu';">
						<br><br>
						<h1><strong>사연마켓</strong></h1>
						<ul class="icons">
							<li><a href="./sellerSelection.se" id="item">등록</a></li>
							<li><a href="./tradeItem.it" id="trade">거래</a></li>
							<li><a href="./BoardList.bo" id="review">후기</a></li>
							<li><a href="./selectView.sel" id="select">찜</a></li>
						
						</ul>
					</header>
					
					     <form action="./searchList.se" method="post">
					        <input type="text" id="searchss" name="search" placeholder="판매자 아이디 ">
							<input type="submit" value="검색" id="side">
					     
					     </form>
              
        <!-- Main -->
		<section class="main">
                       
                      
			<!-- Thumbnails -->
				<section class="thumbnails">
				 <div class="grid">
				    
                        
                     <% for(int i=0; i<mainList.size(); i++){ 
                            sb = (SellerBean)mainList.get(i);
                          
                     %>                   
                       <div>                                 
                             <a href="./userContentView.us?seller_num=<%=sb.getSeller_num()%>">
                               <h2>판매자 <%=sb.getSeller_id() %></h2>
				               <img src="./upload/<%=sb.getImage() %>" id="img" />		
				               <h2 id="copy"><%=sb.getCopy_text() %></h2></a>					
				       </div>	
					
					<% } %>	
				</div>
			 </section>			
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