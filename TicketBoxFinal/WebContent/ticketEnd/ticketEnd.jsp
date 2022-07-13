<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>결제 완료/확인</title>
<!-- <link href="../css/main.css" rel="stylesheet" type="text/css"> -->
<script src="../js/jquery-1.7.2.min.js"></script>
<script src="../js/main.js"></script>
<style>
#main_picture1 {
		            /*가로너비*/
		            width: 100%;
		            /*세로높이*/
		            height: 300px;
		            /*높이만큼 보여짐*/
		            overflow: hidden;
		            /*배경이미지*/
		            background-image: url(../images/phone2.jpg);
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

</head>
<body>
<%
 	String name = (String)session.getAttribute("name");
	String pname = (String)session.getAttribute("pname");
	String s1_id = (String)session.getAttribute("s1_id");
	String s2_id = (String)session.getAttribute("s2_id");
	String s3_id = (String)session.getAttribute("s3_id");
	
	int total_pay = (Integer)session.getAttribute("total_pay");
	String format_pay = Integer.toString(total_pay); 

%>
		  <jsp:include page="../main/mainTop.jsp"></jsp:include>
		    
			 
			 <!--메인이미지 박스-->
		<div id="main_picture1">
		</div>	
				
			<br>
		<!-- ----------------------------------------------- -->
<%
	
%>
		<div style="margin-bottom:40px;">
		
		<img src="../image/11.png" width="400px" height="300px" style="display: block; margin: 0px auto;"><br>
				
		
		<br>
		<table border=2 style="margin-left: auto; margin-right: auto;">
		<tr>
			<td style="text-align: center;">
			<h3>결제 확인</h3>
		
			<%=name %> 고객님께서 주문하신 내역입니다.<br>
			상품명  <%=pname %>입니다.<br>
			좌석 번호 	<%=s1_id %>	<%=s2_id %>	<%=s3_id %>입니다.<br>
			상품의 가격은 <%=format_pay %>입니다.
			
			<br></br>
			
			확인해주시기 바랍니다. 잘못된 부분이이 있다면 고객센터: 1899-xxxx 연락 바랍니다.
			<br></br>
			
			<input type="button" value="넘어가기"  onclick="location.href='../main/ticketMain.jsp'">
			</td></tr>
		</table>
		</div>

   
	<jsp:include page="../main/mainBottom.jsp"></jsp:include>
	  
</body>
</html>