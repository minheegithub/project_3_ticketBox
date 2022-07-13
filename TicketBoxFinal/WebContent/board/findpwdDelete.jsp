<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<script language=javascript>
		self.window.alert("게시글을 삭제합니다.");
		location.href="BoardDelete?aid="+${requestScope.aid};
</script>
</head>
<body>

</body>
</html>