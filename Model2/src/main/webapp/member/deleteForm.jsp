<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>jsp3/deleteForm.jsp</title>
</head>
<body>
<h1>jsp3/deleteForm.jsp</h1>
<%
String id = (String)session.getAttribute("id");
%>

<form action="memberDeletePro.me" method="post">
아이디 : <input type="text"name="id" value="<%=id%>" readonly><br>
비밀번호 : <input type="password" name="pass"><br>

<input type="submit" value="삭제하기">

<a href="memberMain.me">메인으로 이동</a>
</form>
</body>
</html>