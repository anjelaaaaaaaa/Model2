<%@page import="member.MemberDTO"%>
<%@page import="member.MemberDAO"%>
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
// 회원정보 기준값 id => session에 "id"라는 이름으로 저장해놨음 !!!!
// 세션에서 id값을 가져와서 변수에 저장. => 다운캐스팅
String id = (String)session.getAttribute("id");

// MemberDAO 객체생성 => 기억장소 할당
MemberDAO dao = new MemberDAO();
// MemberDTO형으로 getMember(String id) 메서드 정의, 메서드 호출 
MemberDTO dto = dao.getMember(id);
%>
아이디 : <%= dto.getId() %><br>
비밀번호 : <%= dto.getPass() %><br>
이름 : <%= dto.getName() %><br>
가입날짜 : <%=dto.getDate() %><br>

<a href="main.jsp">메인으로 이동</a>
</body>
</html>