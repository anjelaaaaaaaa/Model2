package com.itwillbs.member.db;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class MemberDAO {
//		1,2단계 디비연결 메서드는 항상!! 쓰기 때문에 따로 뺄거임
//		예외처리를 해야하는데 반복되면 좀 그럼.. 메서드 호출한 곳으로 뒤로 미루자!!!!! 
	public  Connection getConnection() throws Exception{
//		1단계 
//		Class.forName("com.mysql.cj.jdbc.Driver");
//		2단계 
//		String dbUrl = "jdbc:mysql://localhost:3306/jspdb1";
//		String dbUser = "root";
//		String dbPass = "1234";		
//		Connection con = DriverManager.getConnection(dbUrl, dbUser, dbPass);
//		return con;
//		con을 리턴하는 이유는 insertMember()에 보면 con이 나옴. 그래서 여기서 넘겨줘서 받아야함.
		
//		서버에서 미리 1, 2단계 => 디비연결 => 이름을 불러 연결정보를 가져오기 
//		=> 속도향상됨.  디비연결 정보 수정 최소화.
//		DataBase Connection pool(DBCP) => 디비 연결정보 서버에 저장하는 장소 
//		1. META-INF context.xml (디비연결정보) 
//		2. MemberDAO 에서 context.xml을 불러서 사용하게끔 ! 
		Context init = new InitialContext();
		DataSource ds = (DataSource)init.lookup("java:comp/env/jdbc/DB");
		Connection con = ds.getConnection();
		return con;
	}
		
//		insertMember() 메서드 정의해서
//		멤버변수로 String id, String name, .... 길어지면 복잡함. 
//		MemberDTO라는 그릇?을 만들어서 거기안에 정보담고 주소값을 전달함
	public void insertMember(MemberDTO dto) {
		System.out.println("MemberDAO insertMember()");
		Connection con = null;
		PreparedStatement pstmt = null;
		System.out.println(dto.getId());
		System.out.println(dto.getName());
		try {
//			예외가 발생할 가능성이 높은 명령 (1단계 ~ 4단계)
//			1~2단계 (반복작업이기 때문에 위에 메서드 따로 정의해놓았음)
			con = getConnection();
//			3단계
			String sql = "insert into members(id,pass,name,date) values(?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1,dto.getId());
			pstmt.setString(2,dto.getPass());
			pstmt.setString(3,dto.getName());
			pstmt.setTimestamp(4,dto.getDate());
//			4단계
			pstmt.executeUpdate();			
			
		} catch (Exception e) {
//			예외가 발생하면 처리하는 곳
			e.printStackTrace();			
		}finally {
//			예외 상관없이 마무리 작업.. ! => 객체 생성한 기억장소 해제
			if(pstmt!=null) {try {pstmt.close();} catch (Exception e2) {}
			if(con!=null) {try {con.close();} catch (Exception e2) {}
			}
			}
		}// finally 괄호닫기	
	}	// 메서드 괄호닫기

	public MemberDTO userCheck(String id, String pass) {
//		바구니 주소가 저장되는 변수에 null로 초기화
		MemberDTO dto = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
//			1,2단계 디비연결 메서드 호출함 
			con = getConnection();
			
			String sql = "select * from members where id=? and pass=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pass);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
//				바구니 객체 생성 MemberDTO
				dto = new MemberDTO();
				dto.setId(rs.getString("id"));
				dto.setPass(rs.getString("pass"));
				dto.setName(rs.getString("name"));
				dto.setDate(rs.getTimestamp("date"));
			}else {		}
			
		} catch (Exception e) {
			e.printStackTrace();
		}	finally {
			if(pstmt!=null) {try {pstmt.close();} catch (Exception e2) {}}
			if(con!=null) {try {con.close();} catch (Exception e2) {}}
			if(rs != null) {try {rs.close();} catch (Exception e2) {}}
			}return dto;
		}
		
	
	public MemberDTO getMember(String id) {
		MemberDTO dto = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = getConnection();
			
			String sql = "select * from members where id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				dto = new MemberDTO();
				dto.setId(rs.getString("id"));
				dto.setPass(rs.getString("pass"));
				dto.setName(rs.getString("name"));
				dto.setDate(rs.getTimestamp("date"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {		
			if(pstmt!=null) {try {pstmt.close();} catch (Exception e2) {}}
			if(con!=null) {try {con.close();} catch (Exception e2) {}}
			if(rs != null) {try {rs.close();} catch (Exception e2) {}}
		}
		return dto;
	}
	
	public void updateMember(MemberDTO updateDTO) {
		Connection con = null;
		PreparedStatement pstmt = null;		
		try {
			con = getConnection();
			
			String sql = "update members set name=? where id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1,updateDTO.getName());
			pstmt.setString(2,updateDTO.getId());
			
			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(pstmt!=null) {try {pstmt.close();} catch (Exception e2) {}}
			if(con!=null) {try {con.close();} catch (Exception e2) {}}			
		}
	}
	
	public void deleteMember(String id) {
		Connection con = null;
		PreparedStatement pstmt = null;	
		try {
			con = getConnection();
	
			String sql = "delete from members where id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1,id);
			
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(pstmt!=null) {try {pstmt.close();} catch (Exception e2) {}}
			if(con!=null) {try {con.close();} catch (Exception e2) {}}			
		}
	}
	
	public ArrayList<MemberDTO> getMemberList() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<MemberDTO> memberList = new ArrayList<MemberDTO>();
		try {
			con = getConnection();
			
			String sql = "select * from members";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
//			5단계 조건이 true => 실행문 => 다음행 데이터 있으면 true = >
//			=> 열접근 =>한명 정보 MemberDTO에 저장 , 
			while(rs.next()) {
//				MemberDTO 객체생성 while문안에 돌려야함. 
				MemberDTO dto = new MemberDTO(); 
				System.out.println("회원정보 " + dto);
//				set 메서드 호출 
				dto.setId(rs.getString("id"));
				dto.setPass(rs.getString("pass"));
				dto.setName(rs.getString("name"));
				dto.setDate(rs.getTimestamp("date"));
//				배열 한칸에 저장 (한명의 데이터는 dto에담고, 그걸 또 배열에담고,,)
				memberList.add(dto);		
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(pstmt!=null) {try {pstmt.close();} catch (Exception e2) {}}
			if(con!=null) {try {con.close();} catch (Exception e2) {}}
			if(rs != null) {try {rs.close();} catch (Exception e2) {}}
		}
		return memberList;
	}
} // 클래스 파일 닫기