/**
 * 
 */
package com.mdy.dzs.game.fight.domain;

import java.io.Serializable;

/**
 * 战斗卡片初始化
 * @author fangtong  E-mail: 10786912@qq.com
 * @version 创建时间：2014年9月24日  下午6:18:37
 */
public class FightInitCardVO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * card id
	 */
	private int id;
	/**
	 * 最大生命
	 */
	private int life;
	/**
	 * fightpos
	 */
	private int pos;
	/**
	 * 怒气值
	 */
	private int anger;
	/**
	 * 星级
	 */
	private int star;
	/**阶级*/
	private int cls;
	/**
	 * 卡片缩放比例
	 */
	private double scale;
	/** 初始生命 */
	private int initLife;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getLife() {
		return life;
	}
	public void setLife(int life) {
		this.life = life;
	}
	public int getPos() {
		return pos;
	}
	public void setPos(int pos) {
		this.pos = pos;
	}
	public int getAnger() {
		return anger;
	}
	public void setAnger(int anger) {
		this.anger = anger;
	}
	public int getStar() {
		return star;
	}
	public void setStar(int star) {
		this.star = star;
	}
	public double getScale() {
		return scale;
	}
	public void setScale(double scale) {
		this.scale = scale;
	}
	public int getCls() {
		return cls;
	}
	public void setCls(int cls) {
		this.cls = cls;
	}
	public int getInitLife() {
		return initLife;
	}
	public void setInitLife(int initLife) {
		this.initLife = initLife;
	}
	
	
}
