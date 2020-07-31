package com.mdy.dzs.game.domain.boss;

import java.io.Serializable;
import java.util.List;

public class BattleHistoryVO implements Serializable{
	/**
	hisBattle: 上次boss战结果(若战斗进行中,状态为空)
	{
		name:'',	boss名称
		level:*,	等级
		top3Name:['name1',..],
		killName:'name'
	}
	*/
	private static final long serialVersionUID = 1L;
	
	//boss名
	private String name;
	//boss等级
	private int level;
	//前三名名字
	private List<String> top3Name;
	//击杀者
	private String killName;	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public List<String> getTop3Name() {
		return top3Name;
	}
	public void setTop3Name(List<String> top3Name) {
		this.top3Name = top3Name;
	}
	public String getKillName() {
		return killName;
	}
	public void setKillName(String killName) {
		this.killName = killName;
	}
	
	
}
