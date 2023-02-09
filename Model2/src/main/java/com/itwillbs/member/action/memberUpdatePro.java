package com.itwillbs.member.action;

import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.itwillbs.member.db.MemberDAO;
import com.itwillbs.member.db.MemberDTO;

public class memberUpdatePro implements action{
	
	@Override
	public actionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("memberUpdatePro execute() 실행");
		
		request.setCharacterEncoding("utf-8");
		
		String id = request.getParameter("id");
		String pass = request.getParameter("pass");
		String name = request.getParameter("name");

		MemberDAO dao = new MemberDAO();
		MemberDTO dto = dao.userCheck(id, pass); //  유저체크해서 담아둠.

//		수정할 내용을 바구니 객체 생성 => 바구니에 저장 
		MemberDTO updateDTO = new MemberDTO(); 
		updateDTO.setId(id);
		updateDTO.setPass(pass);
		updateDTO.setName(name);
		
		actionForward forward = null;

		if(dto!=null){
//			리턴값 없음(업뎃만하고끝나니까) updateMember(MemberDTO updateDTO)메서드 정의
			dao.updateMember(updateDTO);
//			response.sendRedirect("main.jsp");	
			
			forward = new actionForward();
			forward.setPath("memberMain.me");
			forward.setRedirect(true);
			
		}else {
			forward = null;
			
//			자바에서 자바스크립트/ html 동작하게 하기 
//			서버에서 클라이언트에게 응답할때 어떤 타입으로 응답할것인가? => 자바단에서 html로 동작시키겠다!라고 표현해줘야함
			response.setContentType("text/html; charset=UTF-8");
//			getWriter라는 출력권한을 받음 PrintWriter라는 java io를 사용해서
			PrintWriter out= response.getWriter();
			out.println("<script type = 'text/javascript'>"); //""안에는 ""못 쓰니까 ''로 묶어줌 
			out.println("alert('아이디 비밀번호 틀림')");
			out.println("history.back();");
			out.println("</script>");
			out.close(); // 스크립트 쓰는것을 끝내겠다 ! 
		}
		return forward;
	}
}