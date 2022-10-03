<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 글쓰기</title>
<link rel="stylesheet" href="../css/boardModify.css">
<script src="../js/jquery-1.7.2.min.js"></script>
<script>
function send_check(){
	var f = document.f;	
	
	if( f.content.value.trim() == '' ){
		alert("내용을 입력해야 합니다");
		f.content.focus();
		return;
	}
	
	if( f.jumsoo.value != "1" && f.jumsoo.value != "2" && f.jumsoo.value != "3" && f.jumsoo.value != "4" && f.jumsoo.value != "5"){
		alert("1점부터 5점까지 작품의 점수를 선택해 주세요");
		return;
	}
	
	$.ajax({
		url : 'BoardModify',
		type : 'POST',
		data : {
			aid : $("#aid").val(),
			content : $("#content").val(),
			jumsoo : $('input[name="jumsoo"]:checked').val()
		},
		success : function(data) {
			if(data == true){
				self.window.alert("입력한 글을 수정하였습니다.");
				f.submit();
			}else{
				self.window.alert("저장실패");
				return;
			}
		},
 	});	
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
					<form name="f" method="get" action="BoardView"> 
							<table class="update_table">							
								 <caption>게시글 수정 </caption>
								<tr>
									<th width="120" height="25" style="border-right:1px solid #ccc;">작품명</th>
									<td colspan="3">
								 	 &nbsp;&nbsp;${view.subject} 
									</td>
								</tr>
							 	<tr>
									<th width="120" height="25" style="border-right:1px solid #ccc;">글번호</th>
									<td colspan="3" style="padding-left:7px;">
										${view.aid}
										<input name="str_aid" type="hidden" style="width:470px" value="${view.aid}" id="aid">
									</td>
								</tr>  
								<tr>
									<th width="120" height="25" style="border-right:1px solid #ccc;">작성자</th>
									<td colspan="3" style="padding-left:7px;">
										${view.name}
									</td>
								</tr>	
								<tr>
								<th width="120" height="25" style="border-right:1px solid #ccc;">평점</th>
								<td colspan="3" style="padding-left:7px;">
						       		 <input type="radio" name="jumsoo" value="1">1점 &nbsp;
						       		 <input type="radio" name="jumsoo" value="2">2점 &nbsp;
						       		 <input type="radio" name="jumsoo" value="3">3점 &nbsp;
						       		 <input type="radio" name="jumsoo" value="4">4점 &nbsp;
						       		 <input type="radio" name="jumsoo" value="5">5점 &nbsp;
								</td>
							</tr> 
							<tr>
								<th width="120" height="25" style="border-right:1px solid #ccc;">
									<img src="../image/${image_name}" alt="이미지대체 텍스트">
								</th>
								<td colspan="3">
									<textarea name="content" rows="9" cols="80" id="content">${view.content}</textarea>
								</td>
							</tr>	
							</table>
						</form>
						<table class="btnT">
							<tr>
								<td style="width:600px; align="center" colspan="3">
									<input type=button value="수정" OnClick="send_check();"  class="btn">
									<input type=button value="리스트" OnClick="location.href='BoardList'"  class="btn">
									<input type=reset value="취소"  class="btn">
								</td>
							</tr>
						</table>	 
					</div>
				</section>		
			</div>
			<jsp:include page="../main/mainBottom.jsp"></jsp:include>
</body>
</html>