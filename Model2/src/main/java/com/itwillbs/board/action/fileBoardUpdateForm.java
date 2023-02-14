package com.itwillbs.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itwillbs.board.db.BoardDAO;
import com.itwillbs.board.db.BoardDTO;

public class fileBoardUpdateForm implements action{
	@Override
	public actionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("fileBoardUpdateForm execute() 실행");
		
		int num=Integer.parseInt(request.getParameter("num"));
		
		BoardDAO dao = new BoardDAO();
		BoardDTO dto = dao.getBoard(num);

		request.setAttribute("dto", dto);
		
		actionForward forward = new actionForward();
		forward.setPath("board/fUpdateForm.jsp");
		forward.setRedirect(false);
		return forward;	
	}
}