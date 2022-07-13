<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 글쓰기</title>
<link href="../css/boardWrite.css" rel="stylesheet" type="text/css">

<!--로그아웃 되어 있다면 로그인하러 가자  -->
<%
	String sessionId = (String)session.getAttribute("id");
	String sessionName = (String)session.getAttribute("name");
	if(sessionId == null ||sessionName == null){
		response.sendRedirect("../login_signup/index.jsp");
	}

%>

<script language="javascript">
function send_check(){
	var f = document.f;	
	
	if( f.subject.value.trim() == '' ){
		alert("제목을 입력해야 합니다");
		f.subject.focus();
		return;
	}
	
	if( f.name.value.trim() == '' ){
		alert("작성자를 입력해야 합니다");
		f.name.focus();
		return;
	}
	
	if( f.content.value.trim() == '' ){
		alert("내용을 입력해야 합니다");
		f.content.focus();
		return;
	}
	
	if( f.jumsoo.value != "1" && f.jumsoo.value != "2" && f.jumsoo.value != "3" && f.jumsoo.value != "4" && f.jumsoo.value != "5"){
		alert("1점부터 5점까지 작품의 점수를 선택해 주세요");
		
		return;
	}

	f.submit();
}

function fn_onload(){
	document.f.subject.focus();
	
}
</script>
 
</head>
<body onload="fn_onload()">

	    <jsp:include page="../main/mainTop.jsp"></jsp:include>
	    
	    <!--메인이미지 박스-->
	 	<div id="main_picture1">
	    </div>  
		       
		<div id="write">  
 				<aside>
		        </aside>
			<section>
			<div id="board">
			<form name="f" method="post" action="boardInsert">
			
					<table class="wirte_table" >					
						 <caption>생생하고 솔직한 후기 작성 </caption>
						<input name="pwd" type="hidden" value="${sessionScope.pw}">
						<tr>
							
							<th width="120" height="25" style="border-right:1px solid #ccc;">작품명</th>
							<td colspan="3">
								<input name="subject" style="width:470px" maxlength="40" required class="input">
						
							</td>
						</tr>
						<tr>
							<th width="120" height="25" style="border-right:1px solid #ccc;">작성자</th>
							<td colspan="3" style="padding-left:7px;">
								${sessionScope.id}
								<input name="name" style="width:470px;" class="input" value="${sessionScope.id}" type="hidden">
							</td>
						</tr>	
						<!--별점-->
						 <tr>
							<th width="120" height="25" style="border-right:1px solid #ccc;">평점</th>
							<td colspan="3" style="padding-left:7px;">
								<!-- <select name="jumsoo" id="makeStar">
					            <option value="1">1</option>
					            <option value="2">2</option>
					            <option value="3">3</option>
					            <option value="4">4</option>
					            <option value="5">5</option>
					       		 </select>점 -->
					       		 <input type="radio" name = "jumsoo" value="1">1점 &nbsp;
					       		 <input type="radio" name = "jumsoo" value="2">2점 &nbsp;
					       		 <input type="radio" name = "jumsoo" value="3">3점 &nbsp;
					       		 <input type="radio" name = "jumsoo" value="4">4점 &nbsp;
					       		 <input type="radio" name = "jumsoo" value="5">5점 &nbsp;
							</td>
						</tr> 
						<tr>
							<th width="120" height="25" style="border-right:1px solid #ccc;">내용</th>
							<td colspan="3">
								<textarea name="content" rows="9" cols="70"></textarea>
							</td>
						</tr>	
					</table>			
				</form>
				<table class="btnT">
					<tr>
						<td style="width:600px; colspan="3">
							<input type=button value="등록" OnClick="send_check();" class="btn">
							<input type=button value="리스트" OnClick="location.href='BoardList'" class="btn">
							<input type=reset value="취소" class="btn">
						</td>
					</tr>
				</table>
				</div>
		</section>		
	</div>
	<jsp:include page="../main/mainBottom.jsp"></jsp:include>
</body>
</html>