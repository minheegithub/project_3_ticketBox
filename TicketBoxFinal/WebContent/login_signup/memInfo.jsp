<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>


<head>
    <meta charset="utf-8">
    <title>회원정보</title>
  	<link href="../css/meminfo.css" rel="stylesheet" type="text/css"> 
 
	<script>
		function goBoard(){
			
			location.href="../board/BoardList"
		}
		
		function goMain(){
			location.href="../main/ticketMain.jsp"
		}
	
	</script>

</head>

<body>
     <!--로그아웃 되어 있다면 로그인하러 가자  -->
<%
	String sessionId = (String)session.getAttribute("id");
	String sessionPw = (String)session.getAttribute("pw");
	if(sessionId == null ||sessionPw == null){
		response.sendRedirect("../login_signup/index.jsp");
	}

%>
      	<jsp:include page="../main/mainTop.jsp"></jsp:include>
      	<!--메인이미지 박스-->
		<div id="main_picture1">
		</div>
    
	    <div id="modify_wrap">
	        <fieldset>
		        <legend class="title">내정보 보기</legend>
		        <table class="modifyTable">
			        <caption>회원정보 선택 입력표</caption> 
			        <tr>
			            <th class="first"><label for="id">아이디</label></th>
			            <td class="first">
			            ${sessionScope.id }
			        	</td>
			        </tr>
			        <tr>
			            <%		            
			            String pwhint = "";
			           
			            if(sessionPw != null){
			        		
				            for(int i = 0; i < sessionPw.length(); i++ ){
				            	pwhint += "*";            	
				            }          	            
			        	}
			            %>
			            <th><label for="pw">비밀번호</label></th>	            
			            <td> 
			            <%=pwhint %>
			            </td>
			        </tr>
			        <tr>
			            <th><label for="name">이름</label></th>
			            <td>
			           	${sessionScope.name}
			            </td>
			        </tr>
			        <tr>
			            <th><label for="phone1">전화번호</label></th>
			            <td>
			            	${sessionScope.phone}
			        	 </td>
			        </tr>
			        <tr>
			            <th><label for="gender">성별</label></th>
			            <c:choose>
			            	<c:when test="${sessionScope.gender == 'male'}">
			            		<td>남성</td>
			            	</c:when>
			            	<c:when test="${sessionScope.gender == 'female'}">
			            		<td>여성</td>
			            	</c:when>		            
			            </c:choose>
			        </tr>
		    		 <tr>
			            <th><label for="phone1">주소</label></th>
			            <td>
			            	${sessionScope.address}
			        	 </td>
			        </tr>
		    		<tr>
			            <th class="last"><label for="phone1" >이메일</label></th>
			            <td class="last">
			            	${sessionScope.email}
			        	 </td>
			        </tr>
		    	</table>
	        </fieldset>
	        <div class="btn_area">
	        	<button class="btn_go" onClick="goMain();">메인</button>
	        	<button class="btn_go" onClick="goBoard();">후기 게시판</button>
	        </div>
	    </div>
		<jsp:include page="../main/mainBottom.jsp"></jsp:include>
</body>
</html>