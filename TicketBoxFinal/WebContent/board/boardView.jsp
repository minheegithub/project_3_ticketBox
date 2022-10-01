<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="../css/boardView.css">
<script src="../js/jquery-1.7.2.min.js"></script> 
<!--awesome icons--> 
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<script src="https://kit.fontawesome.com/69851f8880.js" crossorigin="anonymous"></script>
<!--로그아웃 되어 있다면 로그인하러 가자  -->
<%
	String sessionId = (String)session.getAttribute("id");
	String sessionPw = (String)session.getAttribute("pw");
	if(sessionId == null ||sessionPw == null){
		response.sendRedirect("../login_signup/index.jsp");
	}

%>


<script language='javascript'>
 function passWrite()
 {
  location.href = "passWrite.jsp?str_aid="+${view.aid}+"";
 }
 
 function modifypassWrite(){
  location.href = "modifypassWrite.jsp?str_aid="+${view.aid}+"";
	 
	 
 }
</script>
<script>
	$(function() {
		/*.rating마다 할 일*/
		var rating = $('.review .rating');
		/*each:각각이 할 일*/
		rating.each(function() {
			/*$(this):<div class="rating" data-rate="3">*/
			/*attr로 속성에 접근해서 메서드 가져오기*/
			var targetScore = $(this).attr('data-rate');
			console.log(targetScore);
			$(this).find(' i:nth-child(-n+ ' + targetScore + ')').css({
				color : 'rgb(252, 190, 52)'
			});
			/*
			    -n+'3' 이 숫자는 targetScore로 받아오고, 
			    숫자 앞까지는 전부 문자열 처리해줌
			 */
		})
	})
</script>
</head>
<body>
	
	 <jsp:include page="../main/mainTop.jsp"></jsp:include>
	    <!--메인이미지 박스-->
	 <div id="main_picture1">
	 </div>

	<div id="view">
		<aside>	
		</aside>
		
		<section>
			<div id="table">
				<table class="board_title">
								<tr>
									<td>
										<b>솔직하고 생생한 후기</b>
									</td>				
								</tr>			
				</table>
				<table class="view_table" width=500>
					<tr>
						<td style="border-right:1px solid #ccc;"><b>글번호</b></td>
						<td>
							${view.aid}
						</td>  
					</tr>
					<tr><td style="border-right:1px solid #ccc;"><b>조회수</b></td><td>${view.hits}</td></tr>
					<tr><td style="border-right:1px solid #ccc;"><b>이름 </b></td><td>${view.name}</td></tr>
					<tr>
						<td style="border-right:1px solid #ccc;">
							<b>제목 </b>
						</td>
						<td>
							${view.subject}
						</td>
					</tr>
					<tr>
						<td style="border-right:1px solid #ccc;"><b>평점 </b></td>
						<td>
					        <div class="review">
						        <div class="rating" data-rate="${view.jumsoo}">
						            <i class="fas fa-star"></i>
						            <i class="fas fa-star"></i>
						            <i class="fas fa-star"></i>
						            <i class="fas fa-star"></i>
						            <i class="fas fa-star"></i> 
						        </div>
						     </div>  
       					 </td>
					</tr>
					<tr>
						<td style="height:200px; vertical-align:top; border-right:1px solid #ccc;">
							<img src="../image/${image_name}" alt="이미지대체 텍스트">
						</td>
						<td width=350 style="vertical-align:top;">
						${view.content}</td>
					</tr>
				</table>
			 
				<table width=500 class="btnT" >
					<tr><td>
						<input type=button value="목록" OnClick="window.location='BoardList'" class="btn">
						<input type=button value="수정" OnClick="javascript:modifypassWrite();" class="btn">
						<input type=button value="삭제" OnClick="javascript:passWrite();" class="btn">
					</td></tr>
				</table>
			</div>
		</section>
	</div>
	<jsp:include page="../main/mainBottom.jsp"></jsp:include>
</body>
</html>