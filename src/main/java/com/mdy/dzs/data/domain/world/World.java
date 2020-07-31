package com.mdy.dzs.data.domain.world;

import java.io.Serializable;
import java.util.List;


/**
 * 世界地图模型
 * @author 房曈
 *
 */
public class World implements Serializable{


	/**序列化id*/
	private static final long serialVersionUID = 1L;


	/**id*/
	private int id;
	/**世界名称*/
	private String name;
	/**地图图标*/
	private String icon;
	/**背景地图*/
	private String background;
	/**背景音乐*/
	private String bgm;
	/**前置地图*/
	private String preworld;
	/**开放等级*/
	private int level;
	/**副本*/
	private List<Integer> arrField;

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
	public String getIcon(){
		return this.icon;
	}
	public void setIcon(String icon){
		this.icon=icon;
	}
	public String getBackground(){
		return this.background;
	}
	public void setBackground(String background){
		this.background=background;
	}
	public String getBgm(){
		return this.bgm;
	}
	public void setBgm(String bgm){
		this.bgm=bgm;
	}
	public String getPreworld(){
		return this.preworld;
	}
	public void setPreworld(String preworld){
		this.preworld=preworld;
	}
	public int getLevel(){
		return this.level;
	}
	public void setLevel(int level){
		this.level=level;
	}
	public List<Integer> getArrField(){
		return this.arrField;
	}
	public void setArrField(List<Integer> arrField){
		this.arrField=arrField;
	}
}