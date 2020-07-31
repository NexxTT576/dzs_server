package com.mdy.dzs.game.fight.domain;

public class FightEndCardVO {
	/** ID */
	private int id;
	/** 剩余血比例 分母是10000 */
	private int lifeRate;
	/** 剩余怒气值 */
	private int anger;
	/** 位置 */
	private int pos;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getLifeRate() {
		return lifeRate;
	}
	public void setLifeRate(int lifeRate) {
		this.lifeRate = lifeRate;
	}
	public int getAnger() {
		return anger;
	}
	public void setAnger(int anger) {
		this.anger = anger;
	}
	public int getPos() {
		return pos;
	}
	public void setPos(int pos) {
		this.pos = pos;
	}
}
