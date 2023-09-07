package com.poll;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.Vector;

import com.utility.DBClose;
import com.utility.DBOpen;

public class PollDAO {
	
	public PollDTO read(int num) {
		PollDTO dto = null;
		Connection con = DBOpen.open();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from poll ");
		sql.append(" where num = ? ");
	
		try {
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, num);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				dto = new PollDTO();
				dto.setQuestion(rs.getString("question"));
				dto.setType(rs.getInt("type"));
				dto.setActive(rs.getInt("active"));
				dto.setEdate(rs.getString("edate"));			
			}	
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBClose.close(rs, pstmt, con);
		}	
		
		return dto;
	}
	public int getMaxNum() {
		int num = 0;
		Connection con = DBOpen.open();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();
		sql.append(" select max(num) from poll ");
		
		try {
			pstmt = con.prepareStatement(sql.toString());
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				num = rs.getInt(1);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBClose.close(rs, pstmt, con);
		}		
		
		return num;
	}
	
	public boolean create(PollDTO dto) {
		boolean flag = false;
		Connection con = DBOpen.open();
		PreparedStatement pstmt = null;
		StringBuffer sql = new StringBuffer();
		sql.append(" insert into poll(question, sdate, edate, wdate, type) ");
		sql.append(" values(?, ?, ?, now(), ?) ");
		
		try {
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, dto.getQuestion());
			pstmt.setString(2, dto.getSdate());
			pstmt.setString(3, dto.getEdate());
			pstmt.setInt(4, dto.getType());
			
			int cnt = pstmt.executeUpdate();
			
			if(cnt>0) flag = true;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBClose.close(pstmt, con);
		}
		
		return flag;
	}
	
	public int total(Map map) {
		int total = 0;
		Connection con = DBOpen.open();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String col = (String)map.get("col");
		String word = (String)map.get("word"); //검색어
		StringBuffer sql = new StringBuffer();
		sql.append(" select count(*) from poll ");
		if(word.trim().length() > 0)
		   sql.append(" where "+ col +" like concat('%',?,'%') ");
		
		try {
			pstmt = con.prepareStatement(sql.toString());
			if(word.trim().length() > 0)
				pstmt.setString(1, word);
			
			rs = pstmt.executeQuery();
			
			rs.next();
			
			total = rs.getInt(1);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBClose.close(rs, pstmt, con);
		}
		
		return total;
	}
	
	public Vector<PollDTO> getList(Map map){
		Vector<PollDTO> list = new Vector<PollDTO>();
		Connection con = DBOpen.open();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String col = (String)map.get("col");
		String word = (String)map.get("word"); //검색어
		int sno = (int)map.get("sno");
		int eno = (int)map.get("eno");
		
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from poll ");
		if(word.trim().length() > 0)
		   sql.append(" where "+ col +" like concat('%',?,'%') ");
		sql.append(" order by num desc ");
		sql.append(" limit ?, ? ");
		
		
		try {
			int i = 0;
			pstmt = con.prepareStatement(sql.toString());
			if(word.trim().length() >0)
				pstmt.setString(++i, word);
			pstmt.setInt(++i, sno);
			pstmt.setInt(++i, eno);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				PollDTO dto = new PollDTO();
				dto.setNum(rs.getInt("num"));
				dto.setQuestion(rs.getString("question"));
				dto.setSdate(rs.getString("sdate"));
				dto.setEdate(rs.getString("edate"));
				
				list.add(dto);
			}			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBClose.close(rs, pstmt, con);
		}
		
		return list;
	}
}
