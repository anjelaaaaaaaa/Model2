
<%@page import="com.itwillbs.member.db.MemberDTO"%>
<%@page import="com.itwillbs.member.db.MemberDAO"%>
<%@page import="java.util.ArrayList"%>

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
<title>member/list.jsp</title>
</head>
<body>
<h1>member/list.jsp</h1>

<%
// MemberDAO 객체생성
// 리턴할형 ArrayList<MemberDTO> getMemberList() 메서드 정의
MemberDAO dao = new MemberDAO();
ArrayList<MemberDTO> memberList = dao.getMemberList();
%>

<table border="1">
<tr><td>아이디</td><td>비밀번호</td><td>이름</td><td>가입날짜</td></tr>
<%
	for(int i = 0;i<memberList.size();i++){
		// 배열 한칸의 내용 가져오기 
		MemberDTO dto = memberList.get(i);		
		%>
		
		<tr><td><%=dto.getId() %></td><td><%=dto.getPass() %></td>
		<td><%=dto.getName() %></td><td><%=dto.getDate() %></td></tr>
		<%
	}
%>
</table>

<a href="main.jsp">메인으로 이동</a>

</body>
</html>