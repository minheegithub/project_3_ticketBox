<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="css/booking.css" rel="stylesheet" type="text/css">
<script src="js/jquery-1.7.2.min.js"></script>
<script src="js/main.js"></script>

<!-- jQueryUI css파일 -->
<link rel="stylesheet" href="http://code.jquery.com/ui/1.8.18/themes/base/jquery-ui.css" type="text/css"/>
<!-- jQuery 기본 js 파일 -->
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
<!-- jQuery UI 라이브러리 js파일 -->
<script src="http://code.jquery.com/ui/1.8.18/jquery-ui.min.js"></script>
<!-- 예약페이지 js파일 -->
<script src="js/booking.js"></script>

<%
	String MovieName = request.getParameter("name");
%>
<%
	String sessionId = (String) session.getAttribute("id");
	String sessionName = (String) session.getAttribute("name");
	if (sessionId == null || sessionName == null) {
		response.sendRedirect("login_signup/index.jsp");
	}
	String pname;

	if (MovieName.equals("mozart")) {
		pname = "모차르트";
		session.setAttribute("pname", pname);
	} else if (MovieName.equals("mari")) {
		pname = "마리앙뚜아네트";
		session.setAttribute("pname", pname);
	} else if (MovieName.equals("hedwig")) {
		pname = "헤드윅";
		session.setAttribute("pname", pname);
	} else if (MovieName.equals("Excalibur")) {
		pname = "엑스칼리버";
		session.setAttribute("pname", pname);
	}
%> 

</head>

<body>
   		
<%
	String sessionPw = (String)session.getAttribute("pw");
%>
	 	<div class="container">
			<div class="header">
				<div class="social">
					<ul>
						<li><a href=""><img src="./images/social_icon1.gif" alt=""></a></li>
						<li><a href=""><img src="./images/social_icon2.gif" alt=""></a></li>
						<li><a href=""><img src="./images/social_icon3.gif" alt=""></a></li>
					</ul>
					<div class="mileage_btn"><a href=""><img src="./images/btn_mileage.gif" alt=""></a></div>
				</div>
				<div class="signature" style="width:240px; height:60px;"><a href="${pageContext.request.contextPath}/main/ticketMain.jsp"><img src="./images/Ticket.png" alt=""></a></div>
				<div class="location">
					<ul>
						<li><a href="${pageContext.request.contextPath}/login_signup/index.jsp">Login</a></li>
						<li><a href="${pageContext.request.contextPath}/login_signup/signup.jsp">Join</a></li>
						<li><a href="">개인정보취급방침</a></li>
					</ul>
				</div>
			</div><!-- // header end -->
	 	</div> 
			 
			 <header>
		        <div id="menuBar">
		        
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
		              
		               		<li><a href="${pageContext.request.contextPath}/login_signup/Logout">Logout</a></li>
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
<!-- ------------------------------header 끝-------------------------------- -->
		
			   <!--메인이미지 박스-->
		 <div id="main_picture">
	
		 </div>
		<div id = "timeChoice">				
		<div id="musicalImg" style="border:2px solid #b4252d;
				border-radius:10px;" >
			<img style="margin-left:500px;margin-top:40px;margin-bottom:40px;" src="image/<%=MovieName %>.jpg"> <!-- 이부분을 이미지를 바꿔야함. --> 
			<!-- var url = 'action.jsp?param='+encodeURI(str); -->
		</div>		

		<form action= "Seat_selection/Seat_main.jsp">
		<input type="hidden" value="<%=MovieName %>" name="MovieName"/>
			<div id="select" style="border:2px solid #b4252d;
						border-radius:10px;">
				<div id="selectDate">
					<input type="date" id="input_date" name="MovieDate"> 
				</div>
		
				<div id="selectTime">
					<ul>
						<li>
							<div class="hour1">
								<a name="MovieTime" href="javascript:void(0);" onclick="showSeat(1400)">14시 00분</a>
							</div>
						</li>
						<li>
							<div class="hour1">
								<a name="MovieTime" href="javascript:void(0);" onclick="showSeat(1800)">18시 00분</a>	
							</div>
						</li>
					</ul>
					<script type="text/javascript">
					</script>
				</div>
					<div id="currentSeat" ><!-- 이자리에 좌석  -->
						 <li>
							<lu id="vip"> 	 </lu><br>
							<lu id="r">		 </lu><br>
							<lu id="s"> 	 </lu><br>
						</li>
					</div>
			</div>
			<div>
					<button style="background-color:#980000;
						border-radius: 5px;
						color: #fff;width:100px;height:30px;border:none;" type="submit" id="bookingButton" 
					onclick="window.open('Seat_selection/Seat_main.jsp','name','resizable=no width=1100 height=615');return false"> 예매하기</button>
			</div>
			</form>
		</div>
		<footer>			
		        <div>
		            <p>
		               ㈜티켓박스 컴퍼니 경기도 성남시 분당구, 5층 대표이사 : 홍길동 사업자등록번호 : 333-5326-753<br>
				고객센터(평일/주말 09:00~18:00) : 1899-xxxx (유료)<a href="#">개인정보처리방침</a>
		                <br>
		                <span>Copyright ⓒTicket Box Company Corp</span>
		            </p>
		            <div>
		                
		                <select>
		                    <option>관련사이트</option>
		                </select>
		            </div>
	 	        </div>	
	  </footer>
	
</body>
</html>