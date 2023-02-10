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
<h1>파일첨부 글쓰기</h1>
<!-- 파일이 첨부된 form일 경우 enctype에 multipart/form-data라고 꼭 타입을 알려줘야함  -->
<form action="fileBoardWritePro.bo" method="post" enctype="multipart/form-data">
<table border="1">
<tr><td>글쓴이</td>
	<td><input type="text" name="name" value="<%=id %>" readonly></td></tr>
<tr><td>글제목</td>
	<td><input type="text" name="subject"></td></tr>
<tr><td>첨부파일</td>
	<td><input type="file" name="file"></td></tr>
<tr><td>글내용</td>
	<td><textarea name="content" rows="10" cols="20"></textarea></td></tr>

</table>
<input type="submit" value="글작성">
</form>
</body>
</html>