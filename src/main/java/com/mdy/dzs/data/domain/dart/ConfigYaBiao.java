package com.mdy.dzs.data.domain.dart;

import java.io.Serializable;
import java.util.List;


/**
 * 押镖数值设定表模型
 * @author 白雪林
 *
 */
public class ConfigYaBiao implements Serializable{


	/**序列化id*/
	private static final long serialVersionUID = 1L;


	/***/
	private String id;
	/***/
	private String name;
	/***/
	private List<Integer> value;
	/***/
	private String content;

	public String getId(){
		return this.id;
	}
	public void setId(String id){
		this.id=id;
	}
	public String getName(){
		return this.name;
	}
	public void setName(String name){
		this.name=name;
	}
	public List<Integer> getValue(){
		return this.value;
	}
	public void setValue(List<Integer> list){
		this.value=list;
	}
	public String getContent(){
		return this.content;
	}
	public void setContent(String content){
		this.content=content;
	}
}