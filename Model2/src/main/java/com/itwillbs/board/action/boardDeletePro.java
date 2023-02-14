package com.itwillbs.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itwillbs.board.db.BoardDAO;

public class boardDeletePro implements action{
	@Override
	public actionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("boardDeletePro.bo execute 실행");
		
//		request => num 파라미터값 받아와서 num변수에 저장하기
		int num = Integer.parseInt(request.getParameter("num"));
		
		BoardDAO dao = new BoardDAO();
		dao.deleteBoard(num);
		
		actionForward forward = new actionForward();
		forward.setPath("boardList.bo");
		forward.setRedirect(true);
		
		return forward;
	}
}