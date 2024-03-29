package com.poll;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import com.utility.DBClose;
import com.utility.DBOpen;

public class PollitemDAO {
	public Vector<PollitemDTO> getView(int num){
		Vector<PollitemDTO> list = new Vector<PollitemDTO>();
		Connection con = DBOpen.open();
		PreparedStatement pstmt = null;
		ResultSet rs = null; 
		StringBuffer sql = new StringBuffer();
		sql.append(" select item, count from pollitem where num = ? ");
		
		try {
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, num);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				PollitemDTO dto = new PollitemDTO();
				dto.setItem(rs.getString("item"));
				dto.setCount(rs.getInt("count"));
				
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

	public int sumCount(int num) {
		int count = 0;		
		Connection con = DBOpen.open();
		PreparedStatement pstmt = null;
		ResultSet rs = null; 
		StringBuffer sql = new StringBuffer();
		sql.append(" select sum(count) from pollitem where num = ? ");
		
		
		try {
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, num);
			
			rs = pstmt.executeQuery();
			
			rs.next();
			
			count = rs.getInt(1);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBClose.close(rs, pstmt, con);
		}
	
		return count;
	}
	
	
	/**
	 * 투표하기
	 * @param itemnum 항목들
	 * @return 투표성공/실패 boolean
	 */
	public boolean updateCount(String[] itemnum) {
		boolean flag = false;
		Connection con = DBOpen.open();
		PreparedStatement pstmt = null;
		StringBuffer sql = new StringBuffer();
		sql.append(" update pollitem  ");
		sql.append(" set count = count + 1   ");
		sql.append(" where itemnum = ?   ");
		
		try {
			pstmt = con.prepareStatement(sql.toString());
			
			for(int i=0; i<itemnum.length; i++) {
				pstmt.setInt(1, Integer.parseInt(itemnum[i]));
				pstmt.executeUpdate();
			}
			
			flag = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBClose.close(pstmt, con);
		}

		return flag;
	}
	public Vector<PollitemDTO> itemList(int num){
		Vector<PollitemDTO> list = new Vector<PollitemDTO>();
		Connection con = DBOpen.open();
		PreparedStatement pstmt = null;
		ResultSet rs = null; 
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from pollitem where num = ? ");
		
		try {
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, num);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				PollitemDTO dto = new PollitemDTO();
				dto.setItemnum(rs.getInt("itemnum"));
				dto.setItem(rs.getString("item"));
				dto.setCount(rs.getInt("count"));
				dto.setNum(rs.getInt("num"));
				
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
	public boolean create(PollitemDTO dto) {
		boolean flag = true;
		Connection con = DBOpen.open();
		PreparedStatement pstmt = null;
		
		StringBuffer sql = new StringBuffer();
		sql.append(" insert into pollitem(item, num) ");
		sql.append(" values(?, ?) ");
		
		try {
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, dto.getItem());
			pstmt.setInt(2, dto.getNum());
			
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

}
