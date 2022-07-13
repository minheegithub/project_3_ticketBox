<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>배송/결제</title>
<link rel="stylesheet" href="css/selected_frame.css"/>
<%
String pname = (String)session.getAttribute("pname");
String name = (String)session.getAttribute("name");
String email = (String)session.getAttribute("email");
String phone = (String)session.getAttribute("phone");
String address = (String)session.getAttribute("address");

String s1_id = (String)session.getAttribute("s1_id");
String s2_id = (String)session.getAttribute("s2_id");
String s3_id = (String)session.getAttribute("s3_id");


int total_pay = (Integer)session.getAttribute("total_pay");
String format_pay = Integer.toString(total_pay); //천 단위 맞춰주기.

String p_id = (String)session.getAttribute("p_id");

//날짜
String p_date = p_id.substring(2,4)+"월 "+p_id.substring(4,6)+"일";
String p_time = p_id.substring(6,8)+"시 "+ p_id.substring(8,10)+"분"; //시간

%>

</head>
<body>

<div class ="info" style="font-family:'S-CoreDream-3Light'">배송/결제 &nbsp;&nbsp;&nbsp;
</div>
<p></p>	
<!--이전페이지에서 선택한 좌석 정보를 controller에서 받아서 다음 페이지인 지금 화면의 side에 출력해 줄 것이다.  -->
<div class ="content-box" style="font-family:'S-CoreDream-3Light'">		
	<p></p>
	<!--include를 사용해서 결제 수단 선택 및 결제 관련 페이지의 jsp가 들어가도록 한다.  *****************-->
	<%-- <%@ include file="seatSelected_dc.jsp" %>	 --%>		
	
		<table style="margin-left: auto; margin-right: auto;">
	<tr collaps=3>
	<td> <img src="../image/11.png"><br>신작 홍보</td>	
	<td><h5>이메일 주소</h5>
	 <%=email %><br> 
	<%-- <input type="text" value="${pv1[1]}" name="email"><br> --%> 	
	<h5>티켓 수령방법</h5> <!-- 체크한 방법을 마지막 결과창에 모여줄 수 있게 넘겨준다. -->
	<input type="checkbox" name="book" value="come">방문 수령<br>
	<input type="checkbox" name="book" value="post">우편 수령<br>
	<h5>주소</h5> <!-- 회워가입할 때 주소 기입창이 없어서 넣어준다. -->
	<%-- <input type="type" value="${pv1[3]}" name="address"><br><br> --%>
	<%=address %> <br>
	<input type="radio" name="payway" value="credit"> 신용카드  
	<input type="radio" name="payway" value="bankbook"> 무통장 입금<br>
		<h5>쿠폰함</h5>
		<select name="coupon">
			<option value="sale">첫 구매 할인</option>
		</select></td></tr>
	</table>
	<p></p>												
</div>
<p></p>


<div class ="selected_info"> <!--이전 페이지와 같다.  -->
	<img src="images/cube.png" class="resizing"> 
	
	<!--사용자가 선택한 공연 이름이 h5 태그안에 써지도록 한다. 지금은 임시로 공간 잡아놓은 것.  -->
	<h5><%=pname %></h5>
	<div class ="seat"> 
		<%=p_date +" "+p_time%>
		<div style="white-space: pre-line;float:left;">	
		<ul id="selected_description" >
			<%=s1_id %>
			<%=s2_id %>
			<%=s3_id %>
		</ul>
	</div>	
	</div>

	<!--등급별, 배송료는 현재 무료로 표기된 상태. 결제 금액이 표시된다.티켓금액+ 수수료+ 할인 금액 계산한 것 보여주기. 역시나 controller를 거친 것.  -->
	<h5>결제금액</h5>
	<div class="s_cost" id="s_cost">
	<!--  controller에서 받은 결제 금액 정보들 나타나는 곳.  -->	
	<ul id="cost_decription">
	<h5>기본가 : <%=format_pay %>원</h5>
	<h5>할인금액: </h5>
	</ul>
	</div>
	<!-- 
	<br></br> -->
	<ul class="flex-contatiner" style="list-style:none;  ">
		<li>
		<form action="../performSelect" method="post">
			<!-- <button id= "select_return" value="BACK" onclick="history.go(-1)">이전 단계</button> -->
			<button id= "select_return" type="submit" name="go_back">이전 단계</button>
		</form>
		</li>
		<li >
		 <form action="../PayInsert" method="post"><!--임시로 잡아둔 것. 이니시스 결제 화면으로 넘어가야 한다.  -->
		 <input type="hidden" name="pname" id="pname" value=<%=name %>>
		 <input type="hidden" name="email" id="email" value=<%=email %>>
		 <input type="hidden" name="phone" id="phone" value=<%=phone %>>	
		 <input type="hidden" name="address" id="address" value=<%=address %>>
		 <input type="hidden" name="pay" id="pay" value=<%=format_pay %>>
		<button type="submit" id= "select_complete" onclick ="./ticketEnd/ticketEnd.jsp" >선택 완료<!--controller에게 값을 넘겨주고, controller가 할인수단 선택 화면 view(jsp)로 좌석 정보를 전달받는다.메소드 넣기 -->	
		</button>
		</form>
		</li>
	
	</ul>
<!-- 	<form action ="../seatSelect" method="post">		
		<button id= "select_complete" type="submit"  value="" name="seats">선택 완료</button> 		
	</form> -->
	
</div>

</body>
</html>