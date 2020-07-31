package com.mdy.dzs.data.domain.biwu;

import java.io.Serializable;
import java.util.List;


/**
 * 比武配置模型
 * @author zhou
 *
 */
public class ConfigBiwu implements Serializable{


	/**序列化id*/
	private static final long serialVersionUID = 1L;


	/***/
	private int id;
	/***/
	private String name;
	/***/
	private List<Integer> value;
	/***/
	private String content;

	public int getId(){
		return this.id;
	}
	public void setId(int id){
		this.id=id;
	}
	public String getName(){
		return this.name;
	}
	public void setName(String name){
		this.name=name;
	}
	public String getContent(){
		return this.content;
	}
	public void setContent(String content){
		this.content=content;
	}
	public List<Integer> getValue() {
		return value;
	}
	public void setValue(List<Integer> value) {
		this.value = value;
	}
}