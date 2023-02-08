<%@page import="member.MemberDTO"%>
<%@page import="java.sql.Timestamp"%>
<%@page import="member.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>member/insertPro.jsp</title>
</head>
<body>
<h1>member/insertPro.jsp</h1>
<%
//request 한글처리
request.setCharacterEncoding("utf-8");
// request 태그이름에 해당하는 값을 가져오기 =>변수에 저장
// id, pass, name의 변수는 jsp에서 들고감 자바로 !! 
String id = request.getParameter("id");
String pass = request.getParameter("pass");
String name = request.getParameter("name");

// 가입날짜 => 시스템날짜
// java.sql.Timestamp 자바내장객체 => 날짜
Timestamp date = new Timestamp(System.currentTimeMillis());

// id, pass, name, date, age, gender, email, phone 등 회원정보를 담을 그릇..? 필요
// dao.insertMember(이안에 작성하려고하니 ) 너무 지저분해짐. 코드가 !
// 그래서 하나의 자바파일에 담아서 전달 (MemberDTO - data transfer object)
// => Java resources -> 패키지member - 파일이름 MemberDTO
// MemberDTO 객체생성 => 기억장소 할당 
MemberDTO dto = new MemberDTO();
dto.setId(id);
dto.setPass(pass);
dto.setName(name);
dto.setDate(date);

// 1단계~4단계 db작업하는 과정을 자바파일에 메서드 정의(insertMember())해서 
// 자바파일(MemberDAO : 멤버의DB작업) 객체생성하고 메서드 호출 할것임 ! 
// => Java Resouces - src/main/java 안에 만들거임 
MemberDAO dao = new MemberDAO();
System.out.println("MemberDAO 주소 : " + dao);
// dao.insertMember(id, pass, name, date);  변수 대신 MemberDTO의 주소값을 전달할거임
dao.insertMember(dto);

// 로그인 이동
response.sendRedirect("loginForm.jsp");
%>

</body>
</html>
