package com.itwillbs.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 인터페이스 : 클래스 만들기위한 틀(공통적인 부모 틀) 
public interface action {

//	추상메서드를 정의(공통적인 부모의 메서드 툴)
//	리턴할형은 이동정보를 담아서 리턴할거임 
	public actionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception;		
}