<%@page import="com.itwillbs.board.db.BoardDTO"%>
<%@page import="com.itwillbs.board.db.BoardDAO"%>
<%@page import="java.sql.Timestamp"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
// board/writePro.jsp
// request 한글처리
request.setCharacterEncoding("utf-8");
// request name, subject, content 가져와서 변수에 저장
int num=1;
String name = request.getParameter("name");
String subject = request.getParameter("subject");
String content = request.getParameter("content");
int readcount = 0;

Timestamp date = new Timestamp(System.currentTimeMillis());

// 패키지 board 파일이름 BoardDTO
// 멤버변수 num, name, subject, contetn, readcount, date
// set get 만들기
// BoardDTO객체생성
// set 메서드 호출해서 값 저장

// 패키지 board 파일이름 BoardDAO
// 리턴할 형 없음 insertBoard(BoardDTO dto)메서드 정의
// BoardDAO 객체생성
// insertBoard(dto) 메서드 호출

BoardDAO dao = new BoardDAO();
BoardDTO dto = new BoardDTO();

dto.setName(name);
dto.setSubject(subject);
dto.setContent(content);
dto.setDate(date);

if(dto!=null){
	dao.insertBoard(dto);
	response.sendRedirect("list.jsp");
}else{
	%>
	<script type="text/javascript">
	alert("내용이 없습니다.");
	history.back();
	</script>
	<%
}
%>



