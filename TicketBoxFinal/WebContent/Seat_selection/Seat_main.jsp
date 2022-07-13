<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ page import="TicketVO.TicketVO"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>좌석 선택</title>
<link rel="stylesheet" href="css/seat_frame.css"/>
<script src="js/jquery-3.3.1.js"></script>

<!-- jQueryUI css파일 -->
<link rel="stylesheet" href="http://code.jquery.com/ui/1.8.18/themes/base/jquery-ui.css" type="text/css"/>
<!-- jQuery 기본 js 파일 -->
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
<!-- jQuery UI 라이브러리 js파일 -->
<script src="http://code.jquery.com/ui/1.8.18/jquery-ui.min.js"></script>

<%
String MovieName= request.getParameter("name");
%>
<%
	String sessionId = (String)session.getAttribute("id"); ////////id와 pid로 검색한다.
	String sessionName = (String)session.getAttribute("name");
	/* if(sessionId == null ||sessionName == null){
		response.sendRedirect("login_signup/index.jsp");
	} */
	int vip_left = (Integer)session.getAttribute("vip_left");
	int r_left = (Integer)session.getAttribute("r_left");
	int s_left = (Integer)session.getAttribute("s_left");	
%> 
</head>
<%

	String pname = (String)session.getAttribute("pname");
	String p_id = (String)session.getAttribute("p_id");
	
	//날짜
	String p_date = p_id.substring(2,4)+"월 "+p_id.substring(4,6)+"일";
	String p_time = p_id.substring(6,8)+"시 "+ p_id.substring(8,10)+"분"; //시간
	String p_info = p_date + " "+ p_time;
	
%>

<body>
<div class ="info" style="font-family:'S-CoreDream-3Light'"><%=pname %>&nbsp;&nbsp;<%=p_info %> <!--공연정보, 선택 창 상단영역  --> 
<!-- <select name="perform_list">
	<option value="8월 20일 14:00시">8월 28일 14:00<br> 
	<option value="8월 20일 18:00시">8월 28일 18:00<br>
	회차 정보를 이전 페이지에서 가져와야 한다. 이전 페이지와 연동된 공연정보 DB 
</select> -->
</div>
<p></p>
<div class ="content-box" style="font-family:'S-CoreDream-3Light'">		
	<div id="stage">무대</div> 
	<p></p>		
	<%@ include file="Seat_frame.jsp" %>															
	
</div>
<!-- 멀티 스레드, 동시 접속 및 실시간 좌석 변동을 할 수 있는지. 일단 잔여 좌석 정보는 DB에 저장한 다음 불러오기를 해야 한다. -->
<!-- 페이지가 바뀜 없이 서버와 통신하는 방법 - ajax -->

<p></p>

<div class="seat_info" >
	<!--이미지 삽입  -->
	<img src="images/cube.png" class="resizing"> <!--이미지를 클릭하면 메인 홈으로 이동할 수 있도록 링크를 달 예정. -->
	
	<!--남아있는 자리  -->
	<h5>좌석등급/잔여석</h5> 
	<div class ="p_seat">
		<div style="white-space: pre-line;float:left;">		
		<ul id="seatDescription" > <!--등급, 잔여석 기술  -->
		<li id ="vip" style="background:url('images/question.png') no-repeat scroll 0 0 transparent;font-size:13px;">VIP석    150,000원   <%=vip_left %>석</li><br>
		<li id ="r" style="background:url('images/plus.png') no-repeat scroll 0 0 transparent;font-size:13px;">R석      130,000원  <%=r_left %>석 </li><br>		
		<li id ="s" style="background:url('images/check.png') no-repeat scroll 0 0 transparent;font-size:13px;">S석     100,000원  <%=s_left %>석 </li> 
		</ul>	
	</div>
	</div> 

	<!--선택된 자리  --> 
	<h5>선택한 좌석</h5>
	<div class = "s_seat" id="s_seat">
	</div> 
	<br></br>	

	
	<form action ="../seatSelect" method="post">		
		<button id= "select_complete" type="submit"  value="" name="seats">선택 완료</button> 		
	</form>

</div>
<script src="js/seat_settings.js"></script> <!--선택한 좌석 정보 쓰기 위해서!!  -->
	
</body>
</html>