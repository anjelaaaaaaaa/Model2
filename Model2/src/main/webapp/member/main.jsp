<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>member/main.jsp</title>
</head>
<body>
<h1>member/main.jsp</h1>
<%
// session.setAttribute("id",모든 참조형값); => 업캐스팅(자동형변환)
// 자식 = (자식)업캐스팅된 부모
String id=(String)session.getAttribute("id");

// 세션값이 없으면 (세션값이 null이면,,) => loginForm.jsp로 이동
if(id==null){
	response.sendRedirect("memberLoginForm.me");	
}
%>

<%=id%> 님이 로그인 하셨습니다.

<a href="memberLogout.me">로그아웃</a><br>
<a href="memberInfo.me">회원정보조회</a><br>
<a href="memberUpdateForm.me">회원정보수정</a><br>
<a href="memberDeleteForm.me">회원정보삭제</a><br>

<%
// 로그인 한 회원
if(id!=null){
	// 관리자인 admin인 경우에만 회원목록 보이게끔 ! 
	if(id.equals("admin")){
		%>
		<a href="memberList.me">회원목록</a><br>
		<%
	}
}
%>

<a href="../board/writeForm.jsp">글쓰기</a><br>
<a href="../board/list.jsp">글목록 </a><br>

</body>
</html>


