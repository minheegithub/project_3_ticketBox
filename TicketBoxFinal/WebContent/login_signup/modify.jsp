<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
    <meta charset="utf-8">
    <title>Modify</title>
    <link href="../css/modify.css" rel="stylesheet" type="text/css"> 
</head>
<body>


	<jsp:include page="../main/mainTop.jsp"></jsp:include>
	<!--메인이미지 박스-->
	<div id="main_picture1"></div>

	<div id="modify_wrap">
		<div id="logo" style="padding-left: 140px;">
			<img alt="로고이미지" src="../images/Ticket.png">
		</div>
		<h3>회원정보 수정</h3>

		<form action="modify" method="POST">
			<fieldset>
				<legend class="title" style="font-size: 13px;">*수정 입력정보</legend>
				<table class="modifyTable">
					<caption>회원수정 선택 입력표</caption>

					<tr>
						<th class="first"><label for="id">아이디</label></th>
						<td class="first">${sessionScope.id } <input type="hidden"
							name="id" id="id" class="textForm" value="${sessionScope.id }">
						</td>
					</tr>
					<tr>
						<th><label for="pw">비밀번호</label></th>
						<td>****** <input type="hidden" name="pw" id="pw"
							class="textForm" value="${sessionScope.pw}">
						</td>
					</tr>
					<tr>
						<th><label for="name">이름</label></th>
						<td><input type="text" name="name" id="name"></td>
					</tr>
					<tr>
						<th><label for="phone1">전화번호</label></th>
						<td><select name="phone1" id="phone1">
								<option value="010">010</option>
								<option value="011">011</option>
								<option value="012">012</option>
								<option value="013">013</option>
								<option value="014">014</option>
								<option value="015">015</option>
								<option value="016">016</option>
								<option value="017">017</option>
								<option value="018">018</option>
								<option value="019">019</option>
						</select> - <input type="text" name="phone2" size="5"> - <input
							type="text" name="phone3" size="5"></td>
					</tr>
					<tr>
						<th><label for="address">주소</label></th>
						<td><input type="text" name="address" id="address"></td>
					</tr>
					<tr>
						<th><label for="email">이메일</label></th>
						<td><input type="text" name="eamil" id="email"></td>
					</tr>
					<tr>
						<th class="last"><label for="gender">성별</label></th>
						<td class="last"><input type="radio" name="gender"
							id="gender" value="male"> 남성 <input type="radio"
							name="gender" value="female"> 여성</td>
					</tr>
				</table>

			</fieldset>
			<div class="btn_area">

				<button type="submit" class="btn_modify">회원정보 수정</button>
				<button type="reset">취소</button>
			</div>
		</form>
	</div>

	<jsp:include page="../main/mainBottom.jsp"></jsp:include>
</body>
</html>