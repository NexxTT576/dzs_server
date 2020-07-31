package com.mdy.dzs.game.fight.domain.fightskill;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.mdy.dzs.game.fight.domain.fighttalent.FightTalentVO;

/**
 * buff效果模型
 * @author fangtong  E-mail: 10786912@qq.com
 * @version 创建时间：2014年9月24日  下午3:56:34
 */
public class FightBuffVO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * type,buff效果
	 */
	private int t = 4;
	
	/**
	 * count,回合计数
	 */
	private int n;
	/**
	 * side,使用方(1战斗发起方,2应战方)
	 */
	private int s;
	/**
	 * pos,发起位置(1-6) 
	 */
	private int p;
	/**
	 * buff_id 
	 */
	private int b;
	/**
	 * 
	 */
	private int eff;
	private int l;
	/**
	 * buff加成 keys
	 */
	private List<Integer> k;
	/**
	 * buff加成 vals
	 */
	private List<Integer> v;
	
	private List<FightTalentVO> tal;
	
	public FightBuffVO() {
		k = new ArrayList<Integer>();
		v = new ArrayList<Integer>();
		tal =new ArrayList<FightTalentVO>();
	}
	
	public int getT() {
		return t;
	}
	public void setT(int t) {
		this.t = t;
	}
	public int getN() {
		return n;
	}
	public void setN(int n) {
		this.n = n;
	}
	public int getS() {
		return s;
	}
	public void setS(int s) {
		this.s = s;
	}
	public int getP() {
		return p;
	}
	public void setP(int p) {
		this.p = p;
	}
	public int getB() {
		return b;
	}
	public void setB(int b) {
		this.b = b;
	}
	public int getEff() {
		return eff;
	}
	public void setEff(int eff) {
		this.eff = eff;
	}
	public List<Integer> getK() {
		return k;
	}
	public void setK(List<Integer> k) {
		this.k = k;
	}
	public List<Integer> getV() {
		return v;
	}
	public void setV(List<Integer> v) {
		this.v = v;
	}

	public List<FightTalentVO> getTal() {
		return tal;
	}

	/**
	 * @return l
	 */
	public int getL() {
		return l;
	}

	/**
	 * @param l 要设置的 l
	 */
	public void setL(int l) {
		this.l = l;
	}
	
	
}
