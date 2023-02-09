package com.itwillbs.member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class memberLogout implements action{

	@Override
	public actionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("memberLogout execute()실행");
		
//		session값을 초기화하고 
		HttpSession session = request.getSession();
		session.invalidate();

//		memberMain.me로 이동 (주소변경하면서)
		actionForward forward = new actionForward();
		forward.setPath("memberMain.me");
		forward.setRedirect(true);
	
		return forward;
	}
}