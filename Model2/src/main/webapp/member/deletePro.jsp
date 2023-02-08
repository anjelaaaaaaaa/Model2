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
<title>jsp3/deletePro.jsp</title>
</head>
<body>
<h1>jsp3/deletePro.jsp</h1>
<%
String id = request.getParameter("id");
String pass = request.getParameter("pass");

MemberDAO dao = new MemberDAO();
MemberDTO dto = dao.userCheck(id, pass);


if(dto != null){
	dao.deleteMember(id);
	response.sendRedirect("loginForm.jsp");
} else{	%>
	<script type="text/javascript">
	alert("아이디, 비밀번호 불일치");
	history.back();
	</script>
	<%
}
%>

</body>
</html>