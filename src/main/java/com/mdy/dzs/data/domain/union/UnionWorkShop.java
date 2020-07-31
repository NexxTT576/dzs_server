package com.mdy.dzs.data.domain.union;

import java.io.Serializable;

public class UnionWorkShop  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4077138060180082543L;
	/**功能id*/
	private int id ;
	/**生成到家type*/
	private int type ;
	/**生成道具id*/
	private int itemId;
	/**功能开启等级*/
	private int openLevel ;
	/**普通生产系数1*/
	private int norWorkOne;
	/**普通生产系数2*/
	private int norWorkTwo;
	/**加班生产系数1*/
	private int extWorkOne;
	/**加班生产系数2*/
	private int extWorkTwo;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	public int getOpenLevel() {
		return openLevel;
	}
	public void setOpenLevel(int openLevel) {
		this.openLevel = openLevel;
	}
	public int getNorWorkOne() {
		return norWorkOne;
	}
	public void setNorWorkOne(int norWorkOne) {
		this.norWorkOne = norWorkOne;
	}
	public int getNorWorkTwo() {
		return norWorkTwo;
	}
	public void setNorWorkTwo(int norWorkTwo) {
		this.norWorkTwo = norWorkTwo;
	}
	public int getExtWorkOne() {
		return extWorkOne;
	}
	public void setExtWorkOne(int extWorkOne) {
		this.extWorkOne = extWorkOne;
	}
	
	public int getExtWorkTwo() {
		return extWorkTwo;
	}
	public void setExtWorkTwo(int extWorkTwo) {
		this.extWorkTwo = extWorkTwo;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	
}
