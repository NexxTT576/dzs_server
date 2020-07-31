package com.mdy.dzs.data.domain.robot;

import java.io.Serializable;


/**
 * 创建角色名字库模型
 * @author 房曈
 *
 */
public class Name implements Serializable{


	/**序列化id*/
	private static final long serialVersionUID = 1L;


	/**id*/
	private int id;
	/**姓*/
	private String first;
	/**男名*/
	private String male;
	/**女名*/
	private String female;

	public int getId(){
		return this.id;
	}
	public void setId(int id){
		this.id=id;
	}
	public String getFirst(){
		return this.first;
	}
	public void setFirst(String first){
		this.first=first;
	}
	public String getMale(){
		return this.male;
	}
	public void setMale(String male){
		this.male=male;
	}
	public String getFemale(){
		return this.female;
	}
	public void setFemale(String female){
		this.female=female;
	}
}