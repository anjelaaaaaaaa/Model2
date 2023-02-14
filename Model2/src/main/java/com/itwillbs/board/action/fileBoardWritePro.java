package com.itwillbs.board.action;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itwillbs.board.db.BoardDAO;
import com.itwillbs.board.db.BoardDTO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class fileBoardWritePro implements action{
	@Override
	public actionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("fileBoardWritePro execute()실행");
		
//		파일 업로드 작업 ! 
//		파일 업로드 프로그램 설치 !
//		http://www.servlets.com/cos/
//		WEB-INF안에 lib안에 cos.jar 넣기 (cos.jar안에 MultipartRequest가 있음)
		
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
			(request, uploadPath,maxSize,"utf-8",new DefaultFileRenamePolicy());
//			경로에 들어가보면 파일이 첨부되어 있음 ! 
//
// 			multi업로드 정보 => 디비저장
// 			multi name, subject, content 가져와서 변수에저장
// 			request name, subject, content 가져와서 변수에 저장
			String name = multi.getParameter("name");
			String subject = multi.getParameter("subject");
			String content = multi.getParameter("content");
			int readcount = 0;

			Timestamp date = new Timestamp(System.currentTimeMillis());

//			추가 => 업로드된 파일이름 정보 ("file"은 fWriteForm에 있는 첨부파일의 "file"의미)
			String file = multi.getFilesystemName("file");
			
//			패키지 board 파일이름 BoardDTO
//			멤버변수 num, name, subject, content, readcount, date
//			set get 만들기
//			BoardDTO객체생성
//			set 메서드 호출해서 값 저장

//			패키지 board 파일이름 BoardDAO
//			리턴할 형 없음 insertBoard(BoardDTO dto)메서드 정의
//			BoardDAO 객체생성
//			insertBoard(dto) 메서드 호출
			BoardDTO dto = new BoardDTO();
			
			dto.setName(name);
			dto.setSubject(subject);
			dto.setContent(content);
			dto.setDate(date);
			dto.setReadcount(readcount);
			
//			file추가 (BoardDTO객체안에 file private한 멤버변수를 추가하고와야함 !!=> set, get 메서드도 ! )
			dto.setFile(file);
			
			BoardDAO dao = new BoardDAO();
			dao.insertBoard(dto);

//			글목록 list.jsp
			actionForward forward = new actionForward();
			forward.setPath("boardList.bo");
			forward.setRedirect(true);	
		
			return forward;
	}
}