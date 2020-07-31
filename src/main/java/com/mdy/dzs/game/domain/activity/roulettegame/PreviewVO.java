package com.mdy.dzs.game.domain.activity.roulettegame;

import java.io.Serializable;
import java.util.List;

public class PreviewVO implements Serializable{
	/*{
		itemType：	[]
		itemId:		[]
		itemCnt:	[]
	}*/	
	private static final long serialVersionUID = 1L;

	private List<Integer> itemType;
	private List<Integer> itemId;
	private List<Integer> itemCnt;
	
	public PreviewVO(List<Integer> itemType,List<Integer> itemId,List<Integer> itemCnt){
		this.itemType = itemType;
		this.itemId   = itemId;
		this.itemCnt  = itemCnt;
	}
	
	public List<Integer> getItemType() {
		return itemType;
	}
	public void setItemType(List<Integer> itemType) {
		this.itemType = itemType;
	}
	public List<Integer> getItemCnt() {
		return itemCnt;
	}
	public void setItemCnt(List<Integer> itemCnt) {
		this.itemCnt = itemCnt;
	}
	public List<Integer> getItemId() {
		return itemId;
	}
	public void setItemId(List<Integer> itemId) {
		this.itemId = itemId;
	}
	
}
