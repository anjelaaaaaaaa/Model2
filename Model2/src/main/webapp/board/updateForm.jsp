<%@page import="board.BoardDTO"%>
<%@page import="board.BoardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>board/updateForm.jsp</title>
</head>
<body>
<%

int num=Integer.parseInt(request.getParameter("num"));
BoardDAO dao = new BoardDAO();
BoardDTO dto = dao.getBoard(num);

%>


<h1>글수정</h1>
<form action = "updatePro.jsp" method="post">

<!-- num 들고 가고싶은데 보이게는 하기 싫으면 hidden으로 숨겨서 들고감   -->
<input type="hidden" name="num" value="<%=dto.getNum()%>">
<table border="1">
<tr><td>글쓴이</td>
	<td><input type="text" name="name" value="<%=dto.getName() %>" readonly></td></tr>
<tr><td>글제목</td>
	<td><input type="text" name="subject" value="<%=dto.getSubject() %>"></td></tr>
<tr><td>글내용</td>
	<td><textarea name="content" rows="10" cols="20"><%=dto.getContent()%></textarea></td></tr>
	
</table>
<input type="submit" value="글수정">




</form>

</body>
</html>