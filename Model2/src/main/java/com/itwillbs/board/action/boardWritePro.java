package com.itwillbs.board.action;

import java.io.PrintWriter;
import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itwillbs.board.db.BoardDAO;
import com.itwillbs.board.db.BoardDTO;

public class boardWritePro implements action{

	@Override
	public actionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("boardWritePro execute()실행");
		
		// board/writePro.jsp
		// request 한글처리
		request.setCharacterEncoding("utf-8");
		// request name, subject, content 가져와서 변수에 저장
		int num =1;
		String name = request.getParameter("name");
		String subject = request.getParameter("subject");
		String content = request.getParameter("content");
		int readcount = 0;

		Timestamp date = new Timestamp(System.currentTimeMillis());

		// 패키지 board 파일이름 BoardDTO
		// 멤버변수 num, name, subject, content, readcount, date
		// set get 만들기
		// BoardDTO객체생성
		// set 메서드 호출해서 값 저장

		// 패키지 board 파일이름 BoardDAO
		// 리턴할 형 없음 insertBoard(BoardDTO dto)메서드 정의
		// BoardDAO 객체생성
		// insertBoard(dto) 메서드 호출

		
		BoardDTO dto = new BoardDTO();
		
		dto.setName(name);
		dto.setSubject(subject);
		dto.setContent(content);
		dto.setDate(date);
		dto.setReadcount(readcount);
		
		BoardDAO dao = new BoardDAO();
		dao.insertBoard(dto);
		

//		글목록 list.jsp
		actionForward forward = new actionForward();
		forward.setPath("boardList.bo");
		forward.setRedirect(true);	
	
		return forward;
	}
}
