<%@page import="net.item.action.tradeItemAction"%>
<%@page import="net.item.db.itemDAO"%>
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
		<link rel="stylesheet" href="./view/assets/css/tradeItem.css" />
		<link rel="preconnect" href="https://fonts.gstatic.com">
        <link href="https://fonts.googleapis.com/css2?family=Nanum+Myeongjo&display=swap" rel="stylesheet">
	</head>
	<body>

		<!-- Wrapper -->
			<div id="wrapper">

				<!-- Header -->
					<header id="header">
<%
         String item_id = (String) session.getAttribute("id"); 
         String seller_id = (String) session.getAttribute("id");
         
         MemberBean mb = (MemberBean) session.getAttribute("mb");
         
         SellerDAO sdao = new SellerDAO();
         List<SellerBean> notSellerList =  sdao.notSellerItemList(seller_id);
                          
          SellerBean sb = new SellerBean();
          itemBean ib = new itemBean();
          
         List<itemBean> notSellerTradeItemList = (List) request.getAttribute("notSellerTradeItemList");
          
         System.out.println("tradeItem.jsp에서 애장품정보 리스트: "+notSellerTradeItemList);
         System.out.println("tradeItem.jsp에서 애장품홍보 리스트: "+notSellerList);
      %>

			   
  		   			<span class="avatar"><img src="./memberimg/<%=mb.getImgname()%>" /></span>
						<br>
						<a href="./main.me" id="id"><%=item_id %></a>
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
                       
                      
			<!-- Thumbnails -->
				<section class="thumbnails">
				 <div class="grid">
				    
                        
                     <% for(int i=0; i<notSellerList.size(); i++){ 
                            sb = (SellerBean)notSellerList.get(i);
                            ib = (itemBean)notSellerTradeItemList.get(i);
                     %>                   
                       <div>  
                                    
                             <a href="./userContentView.us?seller_num=<%=sb.getSeller_num()%>">
                               <h2>판매자 <%=sb.getSeller_id() %></h2>
				               <img src="./upload/<%=sb.getImage() %>" id="img" />		
				               <h2 id="copy"><%=sb.getCopy_text() %></h2></a>					
				      	     
					         <form action="./customerView.cu" method="post">
	   
					         <input type="hidden" name="item_num" value="<%=ib.getItem_num() %>">
					         <input type="hidden" name="buydate" value="<%=ib.getBuydate() %>">
					         <input type="hidden" name="item_id" value="<%=ib.getItem_id() %>">
					         <input type="hidden" name="item_title" value="<%=ib.getItem_title() %>">
					         <input type="hidden" name="location" value="<%=ib.getLocation() %>">
					         <input type="hidden" name="sale" value="<%=ib.getSale() %>">
					         <input type="hidden" name="status" value="<%=ib.getStatus() %>">
					         <input type="hidden" name="trade_location" value="<%=ib.getTrade_location() %>">
					         <input type="submit" name="trade" value="구매">
					         </form>
					     
				      	     
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