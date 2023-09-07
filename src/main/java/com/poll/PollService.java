package com.poll;

import java.util.Map;
import java.util.Vector;

public class PollService {
	private PollDAO dao;
	private PollitemDAO idao;

	public PollService() {
		dao = new PollDAO();
		idao = new PollitemDAO();
	}
	
	public boolean create(PollDTO dto, PollitemDTO idto) {
		boolean flag = false;
		
		try {
			dao.create(dto); //설문등록
			idto.setNum(dao.getMaxNum()); //등록설문 pk-> 항목의 fk
			int size = idto.getItems().length;
			for(int i=0; i<size; i++) {
				idto.setItem(idto.getItems()[i]);
				idao.create(idto); //항목하나하나를 등록한다.
			}
			
			flag = true;
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	public int total(Map map) {
		int total = 0;
		total = dao.total(map);
		
		return total;
	}
	
	public Vector<PollDTO> getList(Map map){
		Vector<PollDTO> list = null;
		list = dao.getList(map);
		
		return list;
	}
	
	public int getMaxnum() {
		int num =0;
		num = dao.getMaxNum();
		return num;
	}
	
	public PollDTO read(int num) {
		PollDTO dto = null;
		
		dto = dao.read(num);
		
		return dto;
	}
	
	public Vector<PollitemDTO> itemList(int num){
		Vector<PollitemDTO> list = null;
		
		list = idao.itemList(num);
		
		return list;
	}
	/**
	 * 투표하기
	 * @param itemnum 항목들
	 * @return
	 */
	public boolean updateCount(String[] itemnum) {
		boolean flag = false;
		flag = idao.updateCount(itemnum);
		
		return flag;
	}
	
	public Vector<PollitemDTO> getView(int num){
		Vector<PollitemDTO> list = null;
		
		list = idao.getView(num);
		
		return list;
	}
	
	public int sumCount(int num) {
		int count = 0;
		count = idao.sumCount(num);
		
		return count;
		
	}
}
