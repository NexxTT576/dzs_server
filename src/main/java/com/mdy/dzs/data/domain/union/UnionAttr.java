package com.mdy.dzs.data.domain.union;

import java.io.Serializable;
import java.util.List;

public class UnionAttr implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4248310029527827064L;

	private int id;
	/**1大殿，2作坊，3商店，4地洞，5青龙堂，6白虎堂*/
	private int type;
	
	private int level;
	/**升级需要的钱数*/
	private int usemoney;
	/**前置建筑id，等级*/
	private List<Integer> requirements;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getUsemoney() {
		return usemoney;
	}

	public void setUsemoney(int usemoney) {
		this.usemoney = usemoney;
	}

	public List<Integer> getRequirements() {
		return requirements;
	}

	public void setRequirements(List<Integer> requirements) {
		this.requirements = requirements;
	}
}
