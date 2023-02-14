package com.itwillbs.board.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class BoardDAO {
	public Connection getConnection() throws Exception{
		Context init = new InitialContext();
		DataSource ds = (DataSource)init.lookup("java:comp/env/jdbc/DB");
		Connection con = ds.getConnection();
		return con;
	}
	
	public void insertBoard(BoardDTO dto) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = getConnection();
//			num 구하기
			int num=1;
			String sql ="select max(num) from board";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				num = rs.getInt("max(num)") + 1;
			}
			
			String sql2 ="insert into board(num,name, subject, content, readcount,date,file) values (?,?,?,?,?,?,?)";
			pstmt = con.prepareStatement(sql2);
			pstmt.setInt(1,num);
			pstmt.setString(2, dto.getName());
			pstmt.setString(3, dto.getSubject());
			pstmt.setString(4, dto.getContent());
			pstmt.setInt(5, dto.getReadcount());
			pstmt.setTimestamp(6, dto.getDate());
			
//			파일추가 
			pstmt.setString(7, dto.getFile());
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(con!=null) {try {con.close();} catch (Exception e2) {}}
			if(pstmt!=null){try {pstmt.close();} catch (Exception e2) {}}
			}
		}
	
	public ArrayList<BoardDTO> getBoardList(int startRow, int pageSize){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<BoardDTO> boardList = new ArrayList<BoardDTO>();
		
		try {
			con = getConnection();
			
//			게시판에 최근글이 위로 올라오게 정렬해야함 ! (기본은 num(primary key)을기준으로 오름차순임)
//			String sql = "select * from board order by num desc ";
			String sql = "select * from board order by num desc limit ?, ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, startRow-1);
			pstmt.setInt(2, pageSize );
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
//				하나의 글을 바구니에 저장 
				BoardDTO dto = new BoardDTO();
				dto.setNum(rs.getInt("num"));
				dto.setName(rs.getString("name"));
				dto.setSubject(rs.getString("subject"));
				dto.setDate(rs.getTimestamp("date"));
				dto.setReadcount(rs.getInt("readcount"));
				
//				바구니의 주소값을 배열 한칸에 저장 
				boardList.add(dto);
				}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(pstmt!=null) {try {pstmt.close();} catch (Exception e2) {}}
			if(con!=null) {try {con.close();} catch (Exception e2) {}}
			if(rs != null) {try {rs.close();} catch (Exception e2) {}}
		}
		return boardList;
		}
	
	public BoardDTO getBoard(int num) {
		Connection con = null;
		ResultSet rs = null;
		BoardDTO dto = null;
		PreparedStatement pstmt = null;
		try {
			con = getConnection();
			
			String sql = "select * from board where num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				dto = new BoardDTO();
				dto.setNum(rs.getInt("num"));
				dto.setName(rs.getString("name"));
				dto.setDate(rs.getTimestamp("date"));
				dto.setReadcount(rs.getInt("readcount"));
				dto.setSubject(rs.getString("subject"));
				dto.setContent(rs.getString("content"));	
				
//				file추가 
				dto.setFile(rs.getString("file"));
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
	
	public void updateBoard(BoardDTO dto) {
		Connection con = null;
		PreparedStatement pstmt = null;
		System.out.println(dto.getContent());
		System.out.println(dto.getNum());
		try {
			con=getConnection();
			
			String sql = "update board set subject=?, content=? where num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getSubject());
			pstmt.setString(2, dto.getContent());
			pstmt.setInt(3, dto.getNum());
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(pstmt!=null) {try {pstmt.close();} catch (Exception e2) {}}
			if(con!=null) {try {con.close();} catch (Exception e2) {}}		
		}		
	}
	
	public void deleteBoard(int num) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = getConnection();
			
			String sql = "delete from board where num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(pstmt!=null) {try {pstmt.close();} catch (Exception e2) {}}
			if(con!=null) {try {con.close();} catch (Exception e2) {}}
		}
	}
	
	public int getBoardCount() {
		int count = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = getConnection();
			String sql = "select count(*) from board";
			pstmt = con.prepareStatement(sql);
		
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				count = rs.getInt("count(*)");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(pstmt!=null) {try {pstmt.close();} catch (Exception e2) {}}
			if(con!=null) {try {con.close();} catch (Exception e2) {}}
			if(rs!=null) {try {con.close();} catch (Exception e2) {}}
		}
		
		return count;
	}
	
	public void fUpdateBoard(BoardDTO dto) {
		Connection con = null;
		PreparedStatement pstmt = null;
		System.out.println(dto.getContent());
		System.out.println(dto.getNum());
		try {
			con=getConnection();
			
			String sql = "update board set subject=?, content=?, file=? where num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getSubject());
			pstmt.setString(2, dto.getContent());
			pstmt.setString(3, dto.getFile());
			pstmt.setInt(4, dto.getNum());
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(pstmt!=null) {try {pstmt.close();} catch (Exception e2) {}}
			if(con!=null) {try {con.close();} catch (Exception e2) {}}		
		}		
	}
	
	}
