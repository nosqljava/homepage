package com.poll;

import java.util.Arrays;

public class PollitemDTO {

	private int 		itemnum       ;
	private String		item		;
	private String[] 	items; //아이템들
	private int 		count		;
	private int 		num			;
	public PollitemDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public PollitemDTO(int itemnum, String item, String[] items, int count, int num) {
		super();
		this.itemnum = itemnum;
		this.item = item;
		this.items = items;
		this.count = count;
		this.num = num;
	}
	
	@Override
	public String toString() {
		return "PollitemDTO [itemnum=" + itemnum + ", item=" + item + ", items=" + Arrays.toString(items) + ", count="
				+ count + ", num=" + num + "]";
	}

	public int getItemnum() {
		return itemnum;
	}
	public void setItemnum(int itemnum) {
		this.itemnum = itemnum;
	}
	public String getItem() {
		return item;
	}
	public void setItem(String item) {
		this.item = item;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}

	public String[] getItems() {
		return items;
	}

	public void setItems(String[] items) {
		this.items = items;
	}
	
	
}
