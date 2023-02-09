package com.itwillbs.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.itwillbs.member.db.MemberDAO;
import com.itwillbs.member.db.MemberDTO;

public class memberInfo implements action{

	@Override
	public actionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("memberInfo execute()실행 ");
		
//		회원정보 기준값 id => session에 "id"라는 이름으로 저장해놨음 !!!!
//		세션에서 id값을 가져와서 변수에 저장. => 다운캐스팅
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");

//		MemberDAO 객체생성 => 기억장소 할당
		MemberDAO dao = new MemberDAO();
//		MemberDTO형으로 getMember(String id) 메서드 정의, 메서드 호출 
		MemberDTO dto = dao.getMember(id);
		
//		dto 정보를 들고가야함 (request 정보를 담아서 담아가기)!
		request.setAttribute("dto", dto);
		
//		member/info.jsp로 이동 (주소변경없이 이동)
		actionForward forward = new actionForward();
		forward.setPath("member/info.jsp");
		forward.setRedirect(false);
				
//		이동정보 return 시켜주고 저위에 dto정보는 request로 넘어감 
		return forward;
	}
}