package com.itwillbs.member.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MemberFrontController extends HttpServlet{
	
//	메서드 호출 => 자동으로 => 처리담당자 (서블릿) 만들기 => 자동으로 doGet() doPost() 이런애들을 호출함.. 
	
//	메서드오버라이딩  alt shift s -> v 
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("MemberFrontController doGet()");
//		방식 상관없이 호출 
		doProcess(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("MemberFrontController doPost()");
		doProcess(request, response);
	}
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		주소매핑 
//		가상주소 http://localhost:8080/Model2/InsertForm.me
//		 /InsertForm.me 가상주소 뽑아오기
		System.out.println("do process()실행");
		System.out.println("뽑은 가상주소 : " + request.getServletPath());
		String sPath = request.getServletPath();
		
//		이동정보를 저장하는 자바파일 선언
		actionForward forward = null;	
		
//		부모 인터페이스 틀 선언
		action action = null;
		
		if(sPath.equals("/memberInsertForm.me")) {
//			실제페이지로(jsp)이동 
//			response.sendRedirect("member/insertForm.jsp"); 주소가 바뀌면서 이동하는 방법
			
//			주소가 변경되지 않고 이동하는 방법 (insertForm.me 주소를 그대로 가지고 이동하는 방법 ! )
//			RequestDispatcher dispatcher = request.getRequestDispatcher("member/insertForm.jsp");
//			dispatcher.forward(request, response);
			
//			이동정보를 저장하는 자바파일 객체생성
			forward = new actionForward();
			forward.setPath("member/insertForm.jsp");
			forward.setRedirect(false);
		
		} else if (sPath.equals("/memberInsertPro.me")) {
			System.out.println("가상주소에 InsertPro.me 들어오면 => 실제자바파일 연결");
//			실제페이지 (처리)자바 insertPro.java <=> (디비)자바
//			패키지 com.itwillbs.member.action
//			파일이름 insertPro.java
//			execute(HttpServletRequest request, HttpServletResponse response) 메서드 정의
			
//			InsertPro 객체생성
//			insertPro insertPro = new insertPro();
////			메서드 호출 execute(request, response)
//			
//			try {
//				insertPro.execute(request, response);	
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
			
//			부모 = 자식 객체생성
			action = new memberInsertPro();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
//			로그인으로 주소 변경되면서 이동
//			response.sendRedirect("LoginForm.me");
			
//			이동정보를 저장하는 자바파일 객체생성
//			forward = new actionForward();
//			forward.setPath("loginForm.me");
//			forward.setRedirect(true);	
			
		} else if (sPath.equals("/memberLoginForm.me")) {
//			member/loginForm.jsp 이동 
//			주소가 변경되지 않고 이동하는 방법 (insertForm.me 주소를 그대로 가지고 이동하는 방법 ! )
//			RequestDispatcher dispatcher = request.getRequestDispatcher("member/loginForm.jsp");
//			dispatcher.forward(request, response);
			
//			이동정보를 저장하는 자바파일 객체생성
			forward = new actionForward();
			forward.setPath("member/loginForm.jsp");
			forward.setRedirect(false);
		
//			뽑아온 주소값을 적을때는 앞에 /를 붙여줌 
		} else if (sPath.equals("/memberLoginPro.me")) {
			System.out.println("가상주소에 loginpro.me 들어오면 자바파일연결");
			
//			실제페이지 (처리)자바 insertPro.java <=> (디비)자바
//			패키지 com.itwillbs.member.action
//			파일이름 loginPro.java
//			execute2(HttpServletRequest request, HttpServletResponse response) 메서드 정의
			
//			loginPro 객체생성
			loginPro loginPro = new loginPro();
			
//			메서드 호출 execute2(request, response)
			try {
				loginPro.execute2(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
//			주소를 변경하면서 이동
//			response.sendRedirect("main.me");
//			이동정보를 저장하는 자바파일 객체생성
			forward = new actionForward();
			forward.setPath("main.me");
			forward.setRedirect(true);
	
		} else if(sPath.equals("/memberMain.me")) {
//			주소 변경되지 않고 이동 
//			RequestDispatcher dispatcher = request.getRequestDispatcher("member/main.jsp");
//			dispatcher.forward(request, response);
			
//			이동정보를 저장하는 자바파일 객체생성
			forward = new actionForward();
			forward.setPath("member/main.jsp");
			forward.setRedirect(false);
		}
		
//		true이면 response.sendRedirect로 이동
//		false이면 dispatcher.forward로 이동하게끔.. 해보자.....
//		이동 => 이동정보 : (주소값필요 , 이동방식) => 이동정보를 저장하는 자바파일,,,필요,,,^^,,,,ㅎㅎ 
//		String path = "주소정보";
//		boolean isRedirect = true;
			
//		이동정보 자바파일이 있는지 확인
		if(forward != null) {
//			이동방식 비교
			if(forward.isRedirect()==true) {
				response.sendRedirect(forward.getPath());
			}else {
				RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
				dispatcher.forward(request, response);
			}
		}
		
			
	} //  doProcess	
} // 클래스 