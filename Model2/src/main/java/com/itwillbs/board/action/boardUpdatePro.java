package com.itwillbs.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.itwillbs.board.db.BoardDAO;
import com.itwillbs.board.db.BoardDTO;

public class boardUpdatePro implements action{
	@Override
	public actionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("boardUpdatePro execute() 실행");
		
//		request 한글처리 
		request.setCharacterEncoding("utf-8");
		
//		request => num, name, subject, content 파라미터 => 변수저장 
		int num = Integer.parseInt(request.getParameter("num"));
		String name = request.getParameter("name");
		String subject = request.getParameter("subject");
		String content = request.getParameter("content");

//		BoardDAO 객체생성
		BoardDAO dao = new BoardDAO();

//		BoardDTO 객체생성
		BoardDTO dto = new BoardDTO();

//		set메서드 호출해서 변수에 저장한 파라미터값 저장 
		dto.setNum(num);
		dto.setName(name);
		dto.setSubject(subject);
		dto.setContent(content);

//		BoardDAO 리턴형없음 updateBoard(BoardDTO dto) 메서드 정의
//		updateBoard(dto) 메서드 호출하기 
		dao.updateBoard(dto);
//		리턴값 없음(업뎃만하고끝나니까) updateMember(MemberDTO updateDTO)메서드 정의
		
		actionForward forward = new actionForward();
		forward.setPath("boardList.bo");
		forward.setRedirect(true);	
	
		return forward;
	}
}