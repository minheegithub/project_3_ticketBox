<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
<head>
<title>목록</title>
<link rel="stylesheet" href="../css/boardList.css">
</head>
<body>

	    <jsp:include page="../main/mainTop.jsp"></jsp:include>

		 <!--메인이미지 박스-->
		<div id="main_picture1">
		</div>
		    		    
		<div id="list">
			<aside>
			</aside>			
			<section>	
			<div id="table" >
				<table class="board_title">
					<tr>
						<td>
							<b>솔직하고 생생한 후기를 남겨주세요</b>
						</td>				
					</tr>			
				</table>

				<%
					int total_cnt = (int) request.getAttribute("total_cnt");
					String str_c_page = (String) request.getAttribute("str_c_page");

					int c_page = Integer.parseInt(str_c_page);
					int list_num = 10;
					int start = 0;
					int t_page = 0;
				%>

				<table  width=700 style="font-size:13px;margin-bottom:7px;">
						<tr><td>총 게시물수: <%=total_cnt%></td><td><p align=right>페이지:<%=c_page%></td></tr>
				</table>
				<table width=700 >
					<tr>
						<td><input type=button value="글쓰기" OnClick="window.location='boardWrite.jsp'" class="btn" style="font-size:12px;"></td>
						<td>
							<form name=searchf method=post action="BoardList">
				   				<p align=right>
				   				<input type=text name=dbsearch size=30  maxlength=30 class="input" placeholder="제목으로 검색 하세요" style="font-size:12px;">
				   				<input type=submit value="검색" class="input2" style="font-size:12px;">
				   				</p>
				   			</form>
				   		</td>
					</tr>
				</table>
				<table  class="board_list"  width=700 style="font-size:12px;margin-top:7px;" >
						<tr>
							<th width=60><p align=center>번호</p></th>
							<th width=380><p align=center>제목</p></th>
							<th width=90><p align=center>작성자</p></th>
							<th width=100><p align=center>등록일</p></th>
							<th width=60><p align=center>조회수</p></th>
						</tr>

					<c:forEach var="vo" items="${allist}">
						<tr>
							<td width=40><p>${vo.aid}</p></td>
							<td width=380>
								<p>
								<a href="BoardView?str_aid=${vo.aid}" title="${vo.content}">${vo.subject}</a>
								
								<jsp:useBean id="toDay" class="java.util.Date" /> 
								<fmt:formatDate value="${toDay}" pattern="yyyy-MM-dd HH" var="today" />  
								<fmt:parseDate value="${today}" pattern="yyyy-MM-dd HH" var="today1" /> 
							 	<fmt:parseNumber value="${today1.time/(1000*60*60)}" integerOnly="true" var="strDate"></fmt:parseNumber>
							 	<%-- ${strDate}- --%>
								<fmt:parseDate value="${vo.date}" pattern="yyyy-MM-dd HH" var="writeDay" /> 
								<fmt:parseNumber value="${writeDay.time/(1000*60*60)}" integerOnly="true" var="writeDate"></fmt:parseNumber> 
								<%-- ${writeDate}
								=${strDate - writeDate}   --%>
								<c:if test="${strDate - writeDate < 24}">
								   <!-- <span style="color:#F15F5F;font-size:12px;">&nbsp;new</span> -->
								   &nbsp;<span id="new">&nbsp;N&nbsp;</span>
								</c:if>   
							<td width=90><p>${vo.name}</p></td>
							<fmt:formatDate value="${vo.date}" pattern="yyyy-MM-dd" var="writeDay1"/> 
							<td width=120 ><p>${writeDay1}</p></td>
							<td width=60><p>${vo.hits}</p></td>							
						</tr>						
					</c:forEach>
				</table><!--class="board_list"  -->
				<%
					if( ( total_cnt % list_num ) == 0 )
						t_page = total_cnt / list_num;
					else
						t_page = ( total_cnt / list_num ) + 1;
					
					int block_num = 5;
					int t_block = t_page / block_num;
					
					if( t_page % block_num != 0 ) 
						t_block++;
					
					int c_block = c_page / block_num;
					
					if( c_page % block_num != 0 )
						c_block++;
				%>
				
				<table class="board_page" width=700 >
					<tr>
						<td>
							<a href="#" class="next"> << </a>
							<a href="#" class="next"> < </a>
							<%
								for( int i=(c_block-1)*block_num+1; i<=c_block*block_num && i<=t_page; i++ ) { 
							%>
									<a href="BoardList?str_c_page=<%=i%>" class="paging"> 
							<% 
									if( c_page == i ) 
										out.print( "<b>" );
							%>
										[<%=i%>]
							<% 
									if( c_page == i ) 
										out.print( "</b>" );
									
							%>
									</a>
							<%
								} 
							%>
							<a href="#" class="next">></a>
							<a href="#" class="next">>></a>
						</td>
					</tr>
				</table>
				</div>	
				</section>
		</div>

		<jsp:include page="../main/mainBottom.jsp"></jsp:include>
</body>
</html>