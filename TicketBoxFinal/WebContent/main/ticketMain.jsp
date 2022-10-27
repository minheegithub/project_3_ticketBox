<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<script src="../js/jquery-1.7.2.min.js"></script>  
<script src="../js/main1.js"></script> 

</head>   			
<body>			
			<jsp:include page="mainTop.jsp"></jsp:include>
			<!--베너  -->
			<div class="keyvisual">
				<div class="control_btn">
					<button class="prev"><img src="../images/mainkeyvisual/prev.png" alt=""></button>
					<button class="next"><img src="../images/mainkeyvisual/next.png" alt=""></button>
				</div>
				<div class="keyvisual_inner">
					<ul>
						<li class="mainvisual1"><a href="" title=""><img src="../images/mainkeyvisual/saxophone.jpg" alt=""></a></li>
						<li class="mainvisual2"><a href="" title=""><img src="../images/mainkeyvisual/banner.jpg" alt=""></a></li>
						<li class="mainvisual3"><a href="" title=""><img src="../images/mainkeyvisual/ticket.jpg" alt=""></a></li>
					</ul>
				</div>
			</div>
			<div class="key_control">
				<ul>
					<li><a href="" title="">1</a></li>
					<li><a href="" title="">2</a></li>
					<li><a href="" title="">3</a></li>
				</ul>
			</div>
			<div id="gogo">
			 	<div><a href="${pageContext.request.contextPath}/selectMusical.jsp"><h3>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;뮤지컬 예매 </h3></a></div>
			 	<div><a href="#"><h3>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;콘서트 예매 </h3></a></div>
				<div><a href="${pageContext.request.contextPath}/login_signup/memInfo.jsp"><h3>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;마이페이지 </h3></a></div>
				<div><a href="${pageContext.request.contextPath}/board/BoardList"><h3>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;공연 후기 게시판 </h3></a></div>				
				<div><a href="${pageContext.request.contextPath}/login_signup/index.jsp"><h3>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;로그인 </h3></a></div>
				<div><a href="${pageContext.request.contextPath}/login_signup/signup.jsp"><h3>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;회원가입 </h3></a></div>
			</div>
		    <div id="ad">
		        <div>
		            <h3>New! 공연 </h3>
		            <a href="#">
		                <img src="../image/hedwig.jpg" alt="이미지대체 텍스트">
		            </a>
		        </div>
		        <div>
		            <h3>Hot! 콘서트 </h3>
		            <p>
		                <img src="../image/mari.jpg" alt="이미지대체 텍스트">
		            </p>
		        </div>
		        <div>
		            <h3>10주년 기념공연</h3>
		            <a href="#">
		                <img src="../image/mozart.jpg" alt="이미지대체 텍스트">
		            </a>
		        </div>
		    </div>
		  
			    <!--  메인이미지 박스 -->
			<div id="main_picture">
			</div> 
		
			<jsp:include page="mainBottom.jsp"></jsp:include>
	
</body>
</html>