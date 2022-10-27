<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="../css/main2.css">
<style>
@import url('https://fonts.googleapis.com/css2?family=Nanum+Gothic&display=swap');
</style>
</head>
<%
	String sessionId = (String)session.getAttribute("id");
	String sessionPw = (String)session.getAttribute("pw");
%>
   			
	 	<div class="container" >
			<div class="header"  >
				<div class="social" >
					<ul>
						<li><a href=""><img src="../images/social_icon1.gif" alt=""></a></li>
						<li><a href=""><img src="../images/social_icon2.gif" alt=""></a></li>
						<li><a href=""><img src="../images/social_icon3.gif" alt=""></a></li>
					</ul>
					<div class="mileage_btn"><a href=""><img src="../images/btn_mileage.gif" alt=""></a></div>
				</div>
				<div class="signature" style="width:240px; height:60px;"><a href="${pageContext.request.contextPath}/main/ticketMain.jsp"><img src="../images/Ticket.png" alt=""></a></div>
				<div class="location">
					<ul>
						 <%if(sessionId != null){ %>
		               		<li><a href="${pageContext.request.contextPath}/login_signup/Logout">Logout</a></li>
		                <%}else{ %>
		                	<li><a href="${pageContext.request.contextPath}/login_signup/index.jsp">Login</a></li>
		                <%} %>
						<li><a href="${pageContext.request.contextPath}/login_signup/signup.jsp">Join</a></li>
						<li><a href="">개인정보취급방침</a></li>
					</ul>
				</div>
			</div><!-- // header end -->
	 	</div> 
			 
			 <header>
		        <div id="menuBar"  >
		        
		            <!--메뉴-->
		            <ul>
		                <li><a href="${pageContext.request.contextPath}/main/ticketMain.jsp">티켓박스 홈</a></li>
		                <li><a href="${pageContext.request.contextPath}/selectMusical.jsp">뮤지컬 예매</a></li>
		                <li><a href="#">콘서트 예매</a></li>
		                <li><a href="${pageContext.request.contextPath}/login_signup/memInfo.jsp">마이페이지</a></li>
		                <%-- li><a href=<%=request.getContextPath() + "/board/BoardList"%>>공연 후기 게시판</a> --%>
		                <li><a href="${pageContext.request.contextPath}/board/BoardList">공연 후기 게시판</a>		         
		                <li><a href="#">|</a></li> 
		               <%if(sessionId != null){ %>
		               		<li><a href="${pageContext.request.contextPath}/login_signup/Logout">*Logout</a></li>
		                <%}else{ %>
		                	<li><a href="${pageContext.request.contextPath}/login_signup/index.jsp">*Login</a></li>
		                <%} %>
		               		<li><a href="${pageContext.request.contextPath}/login_signup/signup.jsp">*Join</a></li>
		            </ul>
		            <select>
		                <option>Language</option>
		                <option>ENGLISH</option>
		                <option>한국어</option>
		            </select>  
		        </div>
		    </header>  
</html>