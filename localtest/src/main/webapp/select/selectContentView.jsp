<%@page import="net.select.db.selectBean"%>
<%@page import="net.item.db.itemBean"%>
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
		<link rel="stylesheet" href="./view/assets/css/selectContentView.css" />
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
         
         List<selectBean> selectList = (List)request.getAttribute("selectList");
         
         selectBean sel = new selectBean();
         

%>				

			   
  		   			<span class="avatar"><img src="./memberimg/<%=mb.getImgname()%>" /></span>
						<br>
						<a href="./main.me" id="id"><%=id %></a>
						<br><br>
						<h1><strong>사연마켓</strong></h1>
						<ul class="icons">
							<li><a href="./sellerSelection.se" id="item">등록</a></li>
							<li><a href="./tradeItem.it" id="trade">거래</a></li>
							<li><a href="./BoardList.bo" id="review">후기</a></li>
							<li><a href="./selectView.sel" id="select">찜</a></li>
							<li><input type="text" id="search" placeholder="검색"></li>
						</ul>
					</header>
              
        <!-- Main -->
		<section class="main">
                       
                        <h1 style="text-align: center;">"<%=id %>"님의 찜</h1>
			<!-- Thumbnails -->
				<section class="thumbnails">
				 <div class="grid">
				    
                        
                     <% for(int i=0; i<selectList.size(); i++){ 
                           
                    	 sel = selectList.get(i);
                          
                     %>                   
                       <div>                                 
                             <a href="./selectContent.sel?select_num=<%=sel.getSelect_num()%>">
                               <h2>판매자 <%=sel.getSelect_id() %></h2>
				               <img src="./upload/<%=sel.getJ_image() %>" id="img" />		
				               <h2 id="copy"><%=sel.getJ_copy_text() %></h2></a>
				               
				             <form action="./selectContentView2.sel" method="post">
				             <input type="hidden" name="select_num" value="<%=sel.getSelect_num()%>"> 
				             <input type="hidden" name="member_id" value="<%=sel.getMember_id() %>">
				             <input type="hidden" name="select_id" value="<%=sel.getSelect_id()%>">  
				             <input type="hidden" name="j_image" value="<%=sel.getJ_image() %>">  
				             <input type="hidden" name="j_copy_text" value="<%=sel.getJ_copy_text()%>">  
				             <input type="hidden" name="j_file" value="<%=sel.getJ_file()%>">  
				             <input type="hidden" name="j_message" value="<%=sel.getJ_message()%>">  
					         <input type="hidden" name="j_buydate" value="<%=sel.getJ_buydate() %>">
					         <input type="hidden" name="j_item_title" value="<%=sel.getJ_item_title() %>">
					         <input type="hidden" name="j_location" value="<%=sel.getJ_location() %>">
					         <input type="hidden" name="j_sale" value="<%=sel.getJ_sale() %>">
					         <input type="hidden" name="j_status" value="<%=sel.getJ_status() %>">
					         <input type="hidden" name="j_trade_location" value="<%=sel.getJ_trade_location() %>">
					         <input type="submit" name="jjim" value="보기">
					         </form>
					         					
				       </div>	
					
					<% } %>	
				</div>
				 
			 </section>	
			      <div id="backhome">
			       <input type="button" value="돌아가기" onclick="history.back()">				
		          </div>  
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