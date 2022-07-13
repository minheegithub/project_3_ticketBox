<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="js/jquery-3.3.1.js"></script>
<%
	String uid = "suhyunshim";
	String pid = "MA08281400";
	String pname = "마리앙뚜아네트";
%>
</head>
<body>
여기는 날짜와 시간(회차)를 선택하는 화면이다.<br>
<br>
선택 완료 후 좌석 선택 화면으로 넘어간다.<br>
아이디와 공연이름, 공연번호를 member_seat 테이블에 저장하고 ,다음 페이지에 값들을 넘긴다.

<!-- 
<form action="/examples/media/action_target.php" method="get">
    아이디 : <input type="text" name="user_id"><br>
    비밀번호 : <input type="password" name="user_pw"><br>
    <input type="hidden" id="gameToken" name="game_token" value="xm234jq">
    <input type="submit">
</form> -->
<script>
/* $('#user_id').val("suhyunShim");
$('#p_id').val("MA08281400");
$('#p_name').val("마리앙뚜아네트"); */
var uid = "suhyunShim";
var pid ="MA08281400";
var pname ="마리앙뚜아네트";

/* $('input[name=user_id]').attr('value',uid);
$('input[name=p_id]').attr('value',pid);
$('input[name=p_name]').attr('value',pname);

console.log($('#p_name').val()); */
</script>



<form action="performSelect" method= "post">
<input type="hidden" id="user_id" name="user_id" value="<%=uid%>">
<input type="hidden" id="p_id" name="p_id" value="<%=pid%>"/>
<input type="hidden" id="p_name" name="p_name" value="<%=pname%>"/>

<!--servlet에 제출도 하고, resize된 상태의 다음 창도 열릴 것인가.  -->
<button id="p_info" type="submit" onclick="window.open('Seat_main.jsp','name','resizable=no width=1200 height=615');return false">예매하기</button>
</form>

<!-- script 위치도 상관있는가 -->
<script>
console.log($('#p_name').val()); 
</script>

</body>
</html>