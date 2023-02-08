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
<title>member/updateForm.jsp</title>
</head>
<body>
<h1>member/updateForm.jsp</h1>
<%
// 회원정보 기준값 id => session에 "id"라는 이름으로 저장해놨음 !!!!
// 세션에서 id값을 가져와서 변수에 저장. => 다운캐스팅
String id = (String)session.getAttribute("id");
// MemberDAO 객체생성 => 기억장소 할당
// MemberDTO dto = getMember(id) 메서드 호출
MemberDAO dao = new MemberDAO();
MemberDTO dto = dao.getMember(id);
%>
<form action="updatePro.jsp" method="post">
아이디 : <input type="text"name="id" value="<%=id%>" readonly><br>
비밀번호 : <input type="password" name="pass"><br>
이름 : <input type="text" name="name" value="<%=dto.getName()%>"><br>
<input type="submit" value="수정하기">	

<a href="main.jsp">메인으로이동</a>
</form>
</body>
</html>