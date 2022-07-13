<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>배송/결제</title>
<%-- <link rel="stylesheet" a href="<c:out value='${pageContext.request.contextPath}'/>css/seat_frame.css"> --%>
<link href="${pageContext.request.contextPath}/ Pay/css/seat_frame.css" rel="stylesheet" type="text/css">
</head>

<body>
<% String sessionID = (String)session.getAttribute("id"); %>
<div class ="info" style="font-family:'S-CoreDream-3Light'">배송 결제 &nbsp;&nbsp;&nbsp;

</div>
<p></p>
<div class ="content-box" style="font-family:'S-CoreDream-3Light'">
	<table style="margin-left: auto; margin-right: auto;">
	<tr collaps=3>
	<td><img src="../image/11.png"></td>
	<td><h5>이메일 주소</h5>
	<input type="text" value="${pv1[1]}" name="email"><br> 	
	<h5>티켓 수령방법</h5> <!-- 체크한 방법을 마지막 결과창에 모여줄 수 있게 넘겨준다. -->
	<input type="checkbox" name="book" value="come">방문 수령<br>
	<input type="checkbox" name="book" value="post">우편 수령<br>
	<h5>주소</h5> <!-- 회워가입할 때 주소 기입창이 없어서 넣어준다. -->
	<input type="type" value="${pv1[3]}" name="adr"><br><br>
	
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

<div class="seat_info" >
	<!--이미지 삽입  -->
	<img src="images/cube.png" class="resizing"> <!--이미지를 클릭하면 메인 홈으로 이동할 수 있도록 링크를 달 예정. -->
	
	<!-- 콘서트/뮤지컬 이름, 시간  -->
	<h5>공연 이름/시간</h5>
	<div class ="p_seat">
		<div style="white-space: pre-line;float:left;">	
		콘서트/뮤지컬 이름 불러올 장소<br> <!-- 정보 불러오기 -->
		<input type="hidden" value="콘서트/뮤지컬"> 
		시간 불러올 장소 
		<input type="hidden" value="콘서트/뮤지컬 시간">
		
	</div>
	</div> 

	<!-- 콘서트/뮤지컬의 금액, 할인된 총 금액 -->
	<h5 >결제 금액</h5>
	<div class = "s_seat" id="s_seat">
		총 금액<br>
		할인된 금액
	</div> 
	<br></br>
	 <form action="../PayInsert" method="post"> 
	<button id= "select_complete" type="submit" onclick()="pay.jsp">결제 하기</button>	
	 </form> 
</div>
</body>

</html>