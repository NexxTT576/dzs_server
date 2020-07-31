package com.mdy.dzs.game.domain.battle;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * @author fangtong  E-mail: 10786912@qq.com
 * @version 创建时间：2014年9月24日  上午11:54:51
 */
public class BattleFieldInfoVO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * box:[*,*,*]领取宝箱状态,1未领取不可领/2未领取可领/3已领取
	 */
	private List<Integer> box;

	/**
	 * 总星数
	 */
	private int stars;
	
	/**
	 * 连战冷却时间秒数
	 */
	private int secWait;

	/**
	 * 今日连战冷却元宝清除次数
	 */
	private int cdClrCnt;

	public List<Integer> getBox() {
		return box;
	}

	public void setBox(List<Integer> box) {
		this.box = box;
	}

	public int getStars() {
		return stars;
	}

	public void setStars(int stars) {
		this.stars = stars;
	}

	public int getSecWait() {
		return secWait;
	}

	public void setSecWait(int secWait) {
		this.secWait = secWait;
	}

	public int getCdClrCnt() {
		return cdClrCnt;
	}

	public void setCdClrCnt(int cdClrCnt) {
		this.cdClrCnt = cdClrCnt;
	}
	
	
}
