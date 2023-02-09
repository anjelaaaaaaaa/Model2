package com.itwillbs.member.action;

import java.sql.Timestamp;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.itwillbs.member.db.MemberDAO;
import com.itwillbs.member.db.MemberDTO;

// 인터페이스 틀 상속받기
public class memberInsertPro implements action{

	@Override
	public actionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println("memberInsertPro execute()");
		
//		request 한글처리
		request.setCharacterEncoding("utf-8");
//		request 태그이름에 해당하는 값을 가져오기 =>변수에 저장
//		id, pass, name의 변수는 jsp에서 들고감 자바로 !! 
		String id = request.getParameter("id");
		String pass = request.getParameter("pass");
		String name = request.getParameter("name");

//		가입날짜 => 시스템날짜
//		java.sql.Timestamp 자바내장객체 => 날짜
		Timestamp date = new Timestamp(System.currentTimeMillis());
		
		MemberDTO dto = new MemberDTO();
//		기억장소에 id, pass, name, date값을 저장함 
		dto.setId(id);
		dto.setPass(pass);
		dto.setName(name);
		dto.setDate(date);
		
		MemberDAO dao = new MemberDAO();
		System.out.println("MemberDAO 주소 : " + dao);
//		dao.insertMember(id, pass, name, date);  변수 대신 MemberDTO의 주소값을 전달할거임
		dao.insertMember(dto);
		
//		memberLoginForm.me로 이동정보를 담아서 갈거임 
//		actionForward(이동정보를 담는 객체)를 생성함 ! 
//		여기에 setPath, setRedirect에 이동정보를 담아서 그 객체를 들고 호출한 곳으로 돌아감. return forward 
		actionForward forward = new actionForward();
		forward.setPath("memberLoginForm.me");
		forward.setRedirect(true);
		
		return forward;
	}	
}