<%@page import="com.itwillbs.member.db.MemberDTO"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>member/updateForm.jsp</title>
</head>
<body>
<h1>member/updateForm.jsp</h1>

<%
MemberDTO dto = (MemberDTO)request.getAttribute("dto");
%>

<form action="memberUpdatePro.me" method="post">
아이디 : <input type="text"name="id" value="<%=dto.getId()%>" readonly><br>
비밀번호 : <input type="password" name="pass"><br>
이름 : <input type="text" name="name" value="<%=dto.getName()%>"><br>
<input type="submit" value="수정하기">	

<a href="memberMain.me">메인으로이동</a>

</form>
</body>
</html>