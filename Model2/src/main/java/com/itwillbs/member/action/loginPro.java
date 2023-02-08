package com.itwillbs.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.itwillbs.member.db.MemberDAO;
import com.itwillbs.member.db.MemberDTO;

public class loginPro {
	
	public void execute2(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("loginPro execute2()");
		
//		폼에서 입력한 내용이 서버에 전달 => request 내장객체에 저장
//		request 태그이름에 해당하는 값을 가져오기 => 변수에 저장
		String id = request.getParameter("id");
		String pass = request.getParameter("pass");
		
		MemberDAO dao = new MemberDAO();
		MemberDTO dto = dao.userCheck(id, pass);
		
		if( dto != null) {
//			id, pass 일치하면 MemberDTO 바구니에 데이터 담아서 가져오기 
//			세션값 생성 "id", id(페이지 상관없이 값을 유지함), main.jsp로 이동 (자바에서는 session객체없음. jsp단에는 있음)
			HttpSession session = request.getSession();
			session.setAttribute("id", id);
			
//			response.sendRedirect("main.jsp");
			
		} else {
//				<script type="text/javascript">
//				alert("아이디 비밀번호 틀림");
//				history.back();
//				</script>
						
			}			
		}
	}
