<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>member/Logout.jsp</title>
</head>
<body>
<h1>member/Logout.jsp</h1>
<%
// 세션전체 기억장소 삭제
session.invalidate();
%>
// 로그아웃, main.jsp로 이동
<script type="text/javascript">

	alert("로그아웃");
	location.href="main.jsp";
</script>
</body>
</html>

