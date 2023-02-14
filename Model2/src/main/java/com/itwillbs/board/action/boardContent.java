package com.itwillbs.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.itwillbs.board.db.BoardDAO;
import com.itwillbs.board.db.BoardDTO;

public class boardContent implements action{

	@Override
	public actionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {		
		System.out.println("boardContent execute() 실행");
		
//		request에 저장된 num파라미터값 가져오기
//  	세션값으로해서 안들고오고 파라미터로 들고오는 이유는 글 클릭할때마다 들고가는 번호가 달라지기 때문에
// 		세션값 유지할 필요가 없음. 
		int num =Integer.parseInt(request.getParameter("num"));
	
		BoardDAO dao = new BoardDAO();
		
//		getBoard할때 file도 추가해야함 
		BoardDTO dto = dao.getBoard(num);
		
		
//		dto request 담아서 이동 => board/content.jsp로 이동
		request.setAttribute("dto", dto);
				
		actionForward forward = new actionForward();
		forward.setPath("board/content.jsp");
		forward.setRedirect(false);
		return forward;
	}
}