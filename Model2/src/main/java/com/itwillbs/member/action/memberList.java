package com.itwillbs.member.action;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.itwillbs.member.db.MemberDAO;
import com.itwillbs.member.db.MemberDTO;

public class memberList implements action{

	@Override
	public actionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
//		MemberDAO 객체생성
//		리턴할형 ArrayList<MemberDTO> getMemberList() 메서드 정의
		MemberDAO dao = new MemberDAO();
		ArrayList<MemberDTO> memberList = dao.getMemberList();
		
//		memberList데이터를 request에 담아서 이동 ! 
		request.setAttribute("memberList", memberList);
		
//		member/list.jsp로 이동할거임.
		actionForward forward = new actionForward();
		forward.setPath("member/list.jsp");
		forward.setRedirect(false);
		
		return forward;
	}
}
