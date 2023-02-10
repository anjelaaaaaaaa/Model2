<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>board/writeForm.jsp</title>
</head>
<body>
<%
// 로그인 한 사람만 글을 쓸 수 있게함 
String id = (String)session.getAttribute("id");
if(id ==null ){
	response.sendRedirect("memberLoginForm.me");
}

%>
<h1>글쓰기</h1>
<form action="boardWritePro.bo" method="post">
<table border="1">
<tr><td>글쓴이</td>
	<td><input type="text" name="name" value="<%=id %>" readonly></td></tr>
<tr><td>글제목</td>
	<td><input type="text" name="subject"></td></tr>
<tr><td>글내용</td>
	<td><textarea name="content" rows="10" cols="20"></textarea></td></tr>

</table>
<input type="submit" value="글작성">
</form>
</body>
</html>