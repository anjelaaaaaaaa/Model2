package com.itwillbs.board.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class boardFrontController extends HttpServlet{
//	자동으로 doGet() doPost()호출
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("boardFrontController doGet()");
//		방식 상관없이 호출 
		doProcess(request, response); } // doGet()괄호 

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("boardFrontController doPost()");
		doProcess(request, response); } // doPost()괄호
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("boardFrontController doProcess()"); 
	
//		가상주소 뽑아오기
		System.out.println("뽑은 가상주소 : " + request.getServletPath());
		String sPath = request.getServletPath();
		
//		부모 인터페이스 틀 선언
		action action = null;
//		이동정보를 저장하는 자바파일 선언
		actionForward forward = null;
		
		if(sPath.equals("/boardWriteForm.bo")) {
//			board/writeForm.jsp로 이동 (실제 페이지 jsp로 이동)
			
//			writeForm.jsp 주소로 바뀌지않고 이동하게끔 함
			forward = new actionForward();
			forward.setPath("board/writeForm.jsp");
			forward.setRedirect(false);			
			
		} else if(sPath.equals("/boardWritePro.bo")) {
			
			action = new boardWritePro();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}			
		
		} else if (sPath.equals("/boardList.bo")) {
			
			action = new boardList();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}			
		
		} else if (sPath.equals("/fileBoardWriteForm.bo")) {
			
//			board/fWriteForm.jsp 이동
			forward = new actionForward();
			forward.setPath("board/fWriteForm.jsp");
			forward.setRedirect(false);			
			
		} else if(sPath.equals("/fileBoardWritePro.bo")) {
			System.out.println("fileBoardWritePro.bo 실행");
			
			action = new fileBoardWritePro();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else if (sPath.equals("/boardContent.bo")) {
			System.out.println("boardContent.bo 실행");
			
			action = new boardContent();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} else if (sPath.equals("/boardUpdateForm.bo")) {
			
			action = new boardUpdateForm();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} else if(sPath.equals("/boardUpdatePro.bo")) {
			System.out.println("boardUpdatePro.bo 실행");
			
			action = new boardUpdatePro();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}  else if(sPath.equals("/boardDeletePro.bo")) {
			System.out.println("boardDeletePro.bo 실행");
			
			action = new boardDeletePro();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}		
		} else if(sPath.equals("/fileBoardUpdateForm.bo")) {
			System.out.println("fileBoardUpdateForm.bo 실행");
			
			action = new fileBoardUpdateForm();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(sPath.equals("/fileBoardUpdatePro.bo")) {
			System.out.println("fileBoardUpdatePro.bo 실행");
			
			action = new fileBoardUpdatePro();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
						
//		forward(이동정보 담은 객체에 뭔가 있다면 ! == 이동정보가 있다면 )
		if(forward != null) {
//			이동방식 비교
			if(forward.isRedirect() == true) {
				response.sendRedirect(forward.getPath());
			} else {
				RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
//				request, response정보를 들고감! 
				dispatcher.forward(request, response);}
			} // if(forward != null) 괄호 
	} // doProcess()괄호
} // class{}괄호