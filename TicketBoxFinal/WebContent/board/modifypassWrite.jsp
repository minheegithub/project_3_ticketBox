<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<%
	String aid= request.getParameter("str_aid");
	if(aid == null || aid.equals("")){
%>

<% 		
	return;
	}

%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 수정 암호 입력화면 </title>
<link href="../css/modifypassWrite.css" rel="stylesheet" type="text/css">
<script src="../js/jquery-1.7.2.min.js"></script>


</head>
<body>
	
	    <jsp:include page="../main/mainTop.jsp"></jsp:include>
	    
	     <!--메인이미지 박스-->
		 <div id="main_picture1">
		 </div>   
	    
	    
	    <div id="pass">  
			<aside>
        	</aside>
				<section>
			<div id="board">
					<form name="f" method="post" action="ModifyView">
						<input type="hidden" name="aid" value="${param.str_aid}" id="aid">
						<input type="hidden" name="dbname" value="${sessionScope.id}" id="dbname">
							<table class="board_title">
								<tr>
									<td>
										<b>password</b>
									</td>				
								</tr>			
							</table>
							<table class="passWrite" >
						 		<tr>
									<th width="70" height="15"style="border-right:1px solid #ccc;">비밀번호</th> 
									<td colspan="3">
										<input name="pwd" id="pwd" type="password"  maxlength="20" required class="input">
									</td>
								</tr>  
							</table>
							<table class="btnClass">
								<tr>
									<td style="width:100px; align="center" colspan="3">
										<input type=button value="뒤로" OnClick="history.back();" class="btn">
										<input type=button value="수정" OnClick="send_check();" class="btn">
										<input type=reset value="취소" class="btn">
									</td>
								</tr>	 
							</table>
						</form>
					</div>
					</section>		
				</div>

				<jsp:include page="../main/mainBottom.jsp"></jsp:include>
</body>
<script>
	function send_check(){
		var f = document.f;	
		
		if( f.pwd.value.trim() == '' ){
			alert("비밀번호를 입력해야 합니다");
			f.pwd.focus();
			return;
		}
		
		$.ajax({
			url : 'ModifyPwd',
			type : 'POST',
			data : {
				pwd : $("#pwd").val(),
				aid : $("#aid").val(),
				dbname :$("#dbname").val()
			},
			success : function(data) {
				if(data == 0){
					self.window.alert("비밀번호or작성자가 일치하지 않습니다.");
				}else{
					self.window.alert("비밀번호와 장성자가를 일치합니다. 수정창으로 이동합니다.");
					f.submit();
				}
			},
		});
	}
	

</script>
</html>
