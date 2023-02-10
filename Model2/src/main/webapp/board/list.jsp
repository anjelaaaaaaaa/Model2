<%@page import="java.util.ArrayList"%>
<%@page import="com.itwillbs.board.action.boardList"%>
<%@page import="com.itwillbs.board.db.BoardDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>board/list.jsp</title>
</head>
<body>
<%
ArrayList<BoardDTO> boardList= (ArrayList<BoardDTO>)request.getAttribute("boardList");

int currentPage = (Integer)request.getAttribute("currentPage");
int startPage = (Integer)request.getAttribute("startPage");
int pageBlock = (Integer)request.getAttribute("pageBlock");
int endPage = (Integer)request.getAttribute("endPage");
int pageCount = (Integer)request.getAttribute("pageCount");
%>

<h1>board/list.jsp</h1>
<h1><a href="boardWriteForm.bo">글쓰기</a></h1>
<table border="1">
<tr><td>글번호</td><td>글쓴이</td><td>글제목</td><td>글쓴날짜</td><td>조회수</td></tr>
<%
	for(int i=0; i<boardList.size();i++){
		BoardDTO dto = boardList.get(i);
		%>
		<tr><td><%=dto.getNum() %></td><td><%=dto.getName() %></td>
			<td>
<!-- 			세션값으로 안들고가고 파라미터 값으로 들고감!  -->
			<a href="content.jsp?num=<%=dto.getNum() %>">
			<%=dto.getSubject() %>
			</a></td>
			<td><%=dto.getDate() %></td>
			<td><%=dto.getReadcount() %></td></tr>
		<%
	}
%>
</table>
<%
if(currentPage > 1){
	%>
	<a href = "boardList.bo?pageNum=<%=currentPage-1 %>">[이전]</a>	
	<%
}
if(startPage > pageBlock){
	%>
	<a href = "boardList.bo?pageNum=<%=startPage-pageBlock %>">[3페이지 이전]</a>	
	<%
}
for(int i=startPage; i<=endPage;i++){
	%>
	<a href="boardList.bo?pageNum=<%=i%>"><%=i %></a>
	<%
}
if(currentPage < pageCount){
	%>
	<a href = "boardList.bo?pageNum=<%=currentPage+1 %>">[다음]</a>
	<%
}
if(endPage < pageCount){
	%>
	<a href = "boardList.bo?pageNum=<%=startPage + pageBlock %>">[3페이지 다음]</a>
	<%
}
%>

</body>
</html>