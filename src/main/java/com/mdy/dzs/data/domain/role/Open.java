package com.mdy.dzs.data.domain.role;

import java.io.Serializable;
import java.util.List;


/**
 * 系统开放模型
 * @author 房曈
 *
 */
public class Open implements Serializable{

	public static int OPEN_SYSTEM_LINEUP = 14;

	/**序列化id*/
	private static final long serialVersionUID = 1L;


	/**id*/
	private int id;
	/**系统id*/
	private int system;
	/**开放等级*/
	private List<Integer> level;
	/**提示内容*/
	private String prompt;
	/**提示内容2*/
	private String prompt1;

	public int getId(){
		return this.id;
	}
	public void setId(int id){
		this.id=id;
	}
	public int getSystem(){
		return this.system;
	}
	public void setSystem(int system){
		this.system=system;
	}
	public List<Integer> getLevel(){
		return this.level;
	}
	public void setLevel(List<Integer> level){
		this.level=level;
	}
	public String getPrompt(){
		return this.prompt;
	}
	public void setPrompt(String prompt){
		this.prompt=prompt;
	}
	public String getPrompt1(){
		return this.prompt1;
	}
	public void setPrompt1(String prompt1){
		this.prompt1=prompt1;
	}
}