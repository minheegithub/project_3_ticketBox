<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
    <meta charset="utf-8">
    <title>Login</title>
    <link href="../css/index.css" rel="stylesheet" type="text/css"> 
    <script src="../js/jquery-1.7.2.min.js"></script>
</head>

<body>
     <jsp:include page="../main/mainTop.jsp"></jsp:include>
     <!--메인이미지 박스-->
		<div id="main_picture1">
		</div>
	<%
		if (session.getAttribute("id") != null) {
			String name = (String) session.getAttribute("name");

			response.sendRedirect("memInfo.jsp");
	%>
    <%
        } else {
    %>
	<div id="login">
	    <form action="login" method="POST" name="f">
				<div id="logo" style="width: 140px; margin-bottom: 20px;">
					<img alt="로고이미지" src="../images/Ticket.png">
				</div>
				<h2>티켓박스에 오신걸 환영합니다</h2>
				<h1>로그인</h1>
				<label for="id">아이디 : </label> 
				<input type="text" name="id" id="id" placeholder="아이디를 입력하세요" class="box" required> 
				<label for="pw">비밀번호 : </label>
				<input type="password" name="pw" id="pw" placeholder="비밀번호를 입력하세요" class="box" required> 
		</form>
	<input style="margin-bottom: 10px;" type="submit" value="로그인" class="btn" onclick="loginCheck()"><br>
	<div id="msg"></div><br>
	</div>
    <%
        }
    %>
   <jsp:include page="../main/mainBottom.jsp"></jsp:include>
</body>
<script>
function loginCheck(){
	var f = document.f;

	$.ajax({
		url : 'LoginCheck',
		type : 'POST',
		data : {
			id : $("#id").val(),
			pw : $("#pw").val()
		},
		success : function(data) {
			if(data == 0){
				document.getElementById("msg").innerHTML ="아이디와 비밀번호를 확인해주세요";
				//self.window.alert("아이디와 비밀번호를 확인해주세요.");
			}else{
				self.window.alert("로그인 성공");
				f.submit();
			}
		}
	});

}
</script>
</html>