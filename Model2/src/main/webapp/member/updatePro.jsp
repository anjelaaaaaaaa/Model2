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
<title>jsp3/updatePro.jsp</title>
</head>
<body>
<h1>jsp3/updatePro.jsp</h1>
<%
// 폼에서 입력한 내용 request 저장
// request 한글처리 
request.setCharacterEncoding("utf-8");
String id = request.getParameter("id");
String pass = request.getParameter("pass");
String name = request.getParameter("name");

MemberDAO dao = new MemberDAO();
MemberDTO dto = dao.userCheck(id, pass); //  유저체크해서 담아둠.

//수정할 내용을 바구니 객체 생성 => 바구니에 저장 
MemberDTO updateDTO = new MemberDTO(); 
updateDTO.setId(id);
updateDTO.setPass(pass);
updateDTO.setName(name);


if(dto!=null){
	// 리턴값 없음(업뎃만하고끝나니까) updateMember(MemberDTO updateDTO)메서드 정의
	dao.updateMember(updateDTO);
	response.sendRedirect("main.jsp");	
}else {
	%>
	<script type="text/javascript">
	alert("아이디 비밀번호 틀림");
	history.back();
	</script>
	<%
}
%>

</body>
</html>