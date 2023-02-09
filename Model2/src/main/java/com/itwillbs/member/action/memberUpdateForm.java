package com.itwillbs.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.itwillbs.member.db.MemberDAO;
import com.itwillbs.member.db.MemberDTO;

public class memberUpdateForm implements action{

	@Override
	public actionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("memberUpdateForm execute() 실행");
		
//		회원정보 기준값 id => session에 "id"라는 이름으로 저장해놨음 !!!!
//		세션에서 id값을 가져와서 변수에 저장. => 다운캐스팅
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		
		MemberDAO dao = new MemberDAO();
		MemberDTO dto = dao.getMember(id);
		
//		dto정보 들고가기 (request정보를 담아가기)
		request.setAttribute("dto", dto);
		
//		member/updateForm.jsp 주소 변경없이(false) 이동
		actionForward forward = new actionForward();
		forward.setPath("member/updateForm.jsp");
		forward.setRedirect(false);
		return forward;		
	}
}