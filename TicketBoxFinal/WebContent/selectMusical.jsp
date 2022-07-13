<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- <link href="css/main1.css" rel="stylesheet" type="text/css"> -->
<link href="css/main2.css" rel="stylesheet" type="text/css">
<script src="js/jquery-1.7.2.min.js"></script>
<script src="js/main.js"></script>
<style>
#main_picture1 {
		            /*가로너비*/
		            width: 100%;
		            /*세로높이*/
		            height: 300px;
		            /*높이만큼 보여짐*/
		            overflow: hidden;
		            /*배경이미지*/
		            background-image: url(./images/phone2.jpg);
		            /*배경이미지 반복여부*/
		            background-repeat: no-repeat;
		            /*배경이미지 스크롤에 따른 이동*/
		            background-attachment: scroll;
		            /*배경이미지 사이즈*/
		            background-size: 100% 464px;
		            /*안쪽 그림자 효과 css3generator.com*/
		            -webkit-box-shadow: inset 3px 2px 25px 10px rgba(0, 0, 0, 0.8);
		            box-shadow: inset 3px 2px 25px 10px rgba(0, 0, 0, 0.8);
		        }
		    
</style>
<%
	String sessionId = (String)session.getAttribute("id");
	String sessionName = (String)session.getAttribute("name");
	if(sessionId == null ||sessionName == null){
		response.sendRedirect("login_signup/index.jsp");
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
		       
		  
		    	   <!--메인이미지 박스-->
		 <div id="main_picture1">
	
		 </div>
			
	
		    <div id="ad">
		        <div class=main_poster id=hedwig>
		            <a href="booking.jsp?name=hedwig">
		                <img src="image/hedwig.jpg" alt="이미지대체 텍스트">
		                <h3>헤드윅</h3>
		            </a>      	
		        </div>
		        
		        <div class=main_poster id=mari>
		            <a href = "booking.jsp?name=mari"> <!--p_id와의 연결성을 위해서 수정 ***********************  -->
		               <img src="image/mari.jpg" alt="이미지대체 텍스트">
		                <h3>마리 앙뚜아네트</h3>
		            </a>
		        </div>
		        
     		    <div class= main_poster id=mozart>
					<a href = booking.jsp?name=mozart>
	                	<img src="image/mozart.jpg"  alt="이미지대체 텍스트">
						<h3>모차르트</h3>
					</a>
		        </div>

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