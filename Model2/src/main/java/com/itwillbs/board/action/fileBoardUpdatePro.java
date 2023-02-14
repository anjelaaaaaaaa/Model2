package com.itwillbs.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.itwillbs.board.db.BoardDAO;
import com.itwillbs.board.db.BoardDTO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class fileBoardUpdatePro implements action{
	@Override
	public actionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("fileBoardUpdatePro execute() 실행");
//		객체생성 (생성자에 5가지 작성해야함)
//		생성자 1. request
//			 2. 웹서버에 업로드 폴더위치 => 폴더만들어야함 (webapp에 upload라는 폴더만들거임)
			String uploadPath = request.getRealPath("/upload");
			System.out.println(uploadPath);
//			 3. 파일크기
			int maxSize = 10*1024*1024; // 10메가임
//			 4. 한글처리
//			 "utf-8"쓸거임
//			 5. 파일이름 동일할때 파일이름 변경작업
//			new DefaultFileRenamePolicy()라는 프로그램이 cos.jar안에 있음 
			
			MultipartRequest multi = new MultipartRequest
			(request, uploadPath, maxSize, "utf-8", new DefaultFileRenamePolicy());
		
//		request => num, name, subject, content 파라미터 => 변수저장 
		int num = Integer.parseInt(multi.getParameter("num"));
		String name = multi.getParameter("name");
		String subject = multi.getParameter("subject");
		String content = multi.getParameter("content");
		
		String file = multi.getFilesystemName("file");
		
//		수정할 업로드 파일이 없으면 기존파일을 유지해야함
		if(file == null) {
			file = multi.getParameter("oldfile");
		}		
	
//		BoardDAO 객체생성
		BoardDAO dao = new BoardDAO();

//		BoardDTO 객체생성
		BoardDTO dto = new BoardDTO();

//		set메서드 호출해서 변수에 저장한 파라미터값 저장 
		dto.setNum(num);
		dto.setName(name);
		dto.setSubject(subject);
		dto.setContent(content);
		dto.setFile(file);

//		BoardDAO 리턴형없음 updateBoard(BoardDTO dto) 메서드 정의
//		updateBoard(dto) 메서드 호출하기 
		dao.fUpdateBoard(dto);
//		리턴값 없음(업뎃만하고끝나니까) updateMember(MemberDTO updateDTO)메서드 정의
		
		actionForward forward = new actionForward();
		forward.setPath("boardList.bo");
		forward.setRedirect(true);	
	
		return forward;
	}
}