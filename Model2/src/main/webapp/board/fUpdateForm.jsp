<%@page import="com.itwillbs.board.db.BoardDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>board/fUpdateForm.jsp</title>
</head>
<body>
<%
BoardDTO dto = (BoardDTO)request.getAttribute("dto");
%>

<h1>파일 글수정</h1>

<form action = "fileBoardUpdatePro.bo" method="post" enctype="multipart/form-data"> 

<!-- num 들고 가고싶은데 보이게는 하기 싫으면 hidden으로 숨겨서 들고감   -->
<input type="hidden" name="num" value="<%=dto.getNum()%>">

<table border="1">
<tr><td>글쓴이</td>
	<td><input type="text" name="name" value="<%=dto.getName() %>" readonly></td></tr>
<tr><td>글제목</td>
	<td><input type="text" name="subject" value="<%=dto.getSubject() %>"></td></tr>
<tr><td>글내용</td>
	<td><textarea name="content" rows="10" cols="20"><%=dto.getContent()%></textarea></td></tr>	
<tr><td>첨부파일</td>
	<td><input type="file" name="file"><%=dto.getFile() %>
<!-- 	수정할 파일이없고 기존파일을 그대로 들고가고싶은 경우 hidden 타입으로 들고가야함  -->
	<input type="hidden" name="oldfile" value="<%=dto.getFile() %>">
	</td></tr>
</table>

<input type="submit" value="글수정">

</form>
</body>
</html>