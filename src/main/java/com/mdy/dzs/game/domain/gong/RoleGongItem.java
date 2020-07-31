package com.mdy.dzs.game.domain.gong;

import java.io.Serializable;


/**
 * 内外功碎片模型
 * @author 房曈
 *
 */
public class RoleGongItem implements Serializable{



	/**序列化id*/
	private static final long serialVersionUID = 1L;


	/***/
	private int id;
	/***/
	private int roleId;
	/***/
	private int itemId;
	/***/
	private int itemCnt;
	/***/
	private int itemLimit;

	public int getId(){
		return this.id;
	}
	public void setId(int id){
		this.id=id;
	}
	public int getRoleId(){
		return this.roleId;
	}
	public void setRoleId(int roleId){
		this.roleId=roleId;
	}
	public int getItemId(){
		return this.itemId;
	}
	public void setItemId(int itemId){
		this.itemId=itemId;
	}
	public int getItemCnt(){
		return this.itemCnt;
	}
	public void setItemCnt(int itemCnt){
		this.itemCnt=itemCnt;
	}
	public int getItemLimit(){
		return this.itemLimit;
	}
	public void setItemLimit(int itemLimit){
		this.itemLimit=itemLimit;
	}
}