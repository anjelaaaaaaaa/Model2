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
<title>jsp3/info.jsp</title>
</head>
<body>
<h1>jsp3/info.jsp</h1>

<%
// 자바단에서 request로 담아온걸 jsp단에 request.getAttribute로 전달함 
// "dto"형을 받아온거고 이렇게 받아온건 object형이기 때문에 MemberDTO로 형변환 해줘야함 
MemberDTO dto = (MemberDTO)request.getAttribute("dto");
%>

아이디 : <%= dto.getId() %><br>
비밀번호 : <%= dto.getPass() %><br>
이름 : <%= dto.getName() %><br>
가입날짜 : <%=dto.getDate() %><br>

<a href="memberMain.me">메인으로 이동</a>
</body>
</html>