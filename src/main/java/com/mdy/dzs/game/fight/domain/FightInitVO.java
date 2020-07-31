/**
 * 
 */
package com.mdy.dzs.game.fight.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 战斗初始
 * @author fangtong  E-mail: 10786912@qq.com
 * @version 创建时间：2014年9月24日  下午6:15:07
 */
public class FightInitVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * type
	 */
	private int t;
	/**
	 * 战斗方
	 */
	private List<FightInitCardVO> f1;
	/**
	 * 应战方
	 */
	private List<FightInitCardVO> f2;
	
	public FightInitVO() {
		t = 1;
		f1 = new ArrayList<FightInitCardVO>();
		f2 = new ArrayList<FightInitCardVO>();
	}

	public int getT() {
		return t;
	}

	public void setT(int t) {
		this.t = t;
	}

	public List<FightInitCardVO> getF1() {
		return f1;
	}

	public void setF1(List<FightInitCardVO> f1) {
		this.f1 = f1;
	}

	public List<FightInitCardVO> getF2() {
		return f2;
	}

	public void setF2(List<FightInitCardVO> f2) {
		this.f2 = f2;
	}
	
	
}
