<%@page import="com.itwillbs.board.db.BoardDTO"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>board/content.jsp</title>
</head>
<body>
<%
// http://localhost:8080/webProject/board/content.jsp?num=1
// request에 저장된 num파라미터값 가져오기
//  세션값으로해서 안들고오고 파라미터로 들고오는 이유는 글 클릭할때마다 들고가는 번호가 달라지기 때문에
// 세션값 유지할 필요가 없음. 
// int num =Integer.parseInt(request.getParameter("num"));

// // BoardDAO 객체생성
// BoardDAO dao = new BoardDAO();
// // 리턴할형 BoardDTO getBoard(int num)메서드 정의
// // BoardDTO dto = dao.getBoard(num) 메서드 호출 

// BoardDTO dto = dao.getBoard(num);

// 로그인 => 세션값 있음 / 로그인 한 사람이랑 글쓴사람이랑 헷갈리면 안되ㅣㅁ
// 세션에서 들고온 사람은 로그인한 사람이고 , dto에 담겨있는건 글쓴사람임.. 
String id = (String)session.getAttribute("id");

BoardDTO dto = (BoardDTO)request.getAttribute("dto");

%>
<h1>글 내용</h1>
<table border="1"> 
<tr><td>글 번호</td><td><%=dto.getNum() %></td></tr>
<tr><td>작성자</td><td><%=dto.getName() %></td></tr>
<tr><td>작성날짜</td><td><%=dto.getDate() %></td></tr>
<tr><td>조회수</td><td><%=dto.getReadcount() %></td></tr>
<tr><td>글 제목</td><td><%=dto.getSubject() %></td></tr>
<tr><td>글 내용</td><td><%=dto.getContent() %></td></tr>

<!-- 파일클릭했을때 하이퍼링크 걸고싶다면,, 실제 서버엣어 파일을 올렸을때 webapp에 upload라는 폴더안에 파일이 있을거임  -->
<!-- 		a href="upload/파일이름" 이렇게 써주면 됨. -->
<tr><td>첨부파일</td><td><a href="upload/<%=dto.getFile()%>" download><%=dto.getFile() %></a><br>
<img src ="upload/<%=dto.getFile()%>" width="100" height="100">
</td></tr>

<tr><td colspan="2">

<!-- <!-- 로그인 한 사람만 글수정, 글 삭제가 보이게끔 해야함  -->
<%
if(id != null){
	// 글쓴이랑 세션값이 일치하면 => 내가 쓴 글임! => 글 수정, 글 삭제 보이게함 
	if(id.equals(dto.getName())){
		%>
	<input type="button" value="글수정" onclick="location.href='boardUpdateForm.bo?num=<%=dto.getNum()%>'">
	<input type="button" value="파일수정" onclick="location.href='fileBoardUpdateForm.bo?num=<%=dto.getNum()%>'">
	<input type="button" value="글삭제" onclick="location.href='boardDeletePro.bo?num=<%=dto.getNum()%>'">
		<%
	}
}
%>

<input type="button" value="글목록" onclick="location.href='boardList.bo'"></td></tr>
</table>
</body>
</html>