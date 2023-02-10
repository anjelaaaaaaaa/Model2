package com.itwillbs.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
			
	MultipartRequest multi = new MultipartRequest(request, uploadPath,maxSize,"utf-8",new DefaultFileRenamePolicy());
		
		
		
	
		return null;
	}
}
