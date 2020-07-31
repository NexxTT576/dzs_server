/**
 * 
 */
package com.mdy.dzs.game.fight.domain.fightskill;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 技能伤害过程
 * @author fangtong  E-mail: 10786912@qq.com
 * @version 创建时间：2014年9月24日  下午6:57:38
 */
public class FightSkillProcessVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**repeat，重复出手次序（1/2/3） */
	private int r;
	/**side,目标方(1战斗发起方,2应战方)*/
	private int s;
	/**pos,目标位置(1-6) */
	private int p;
	/**move side, 目标移动到的阵营 (1战斗发起方,2应战方)*/
	private int ms;
	/**move pos,  目标移动到的位置 (1-6)*/
	private int mp;
	/**援护神通id(没有时为0)*/
	private int mtal;
	/**damage,伤害值*/
	private int d = 1;
	/**heal,治疗值*/
	private int h;
	/**怒气值刷新值 */
	private int a;
	/**Life */
	private int l;
	/**状态(1闪避)*/
	private List<Integer> st;
	/**重复次数*/
	private int cnt = 1;
	
	private Integer sa;
	/**免疫类型 超能力（0-无；1-物理免疫；2-法术免疫)*/
	private Integer im;
	
	public FightSkillProcessVO() {
		st = new ArrayList<Integer>();
	}
	
	public int getR() {
		return r;
	}
	public void setR(int r) {
		this.r = r;
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
	public int getMs() {
		return ms;
	}
	public void setMs(int ms) {
		this.ms = ms;
	}
	public int getMp() {
		return mp;
	}
	public void setMp(int mp) {
		this.mp = mp;
	}
	public int getMtal() {
		return mtal;
	}
	public void setMtal(int mtal) {
		this.mtal = mtal;
	}
	public int getD() {
		return d;
	}
	public void setD(int d) {
		this.d = d;
	}
	public int getH() {
		return h;
	}
	public void setH(int h) {
		this.h = h;
	}
	public int getA() {
		return a;
	}
	public void setA(int a) {
		this.a = a;
	}
	public int getL() {
		return l;
	}
	public void setL(int l) {
		this.l = l;
	}
	public List<Integer> getSt() {
		return st;
	}
	public void setSt(List<Integer> st) {
		this.st = st;
	}
	public int getCnt() {
		return cnt;
	}
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
	public Integer getSa() {
		return sa;
	}
	public void setSa(Integer sa) {
		this.sa = sa;
	}

	public Integer getIm() {
		return im;
	}

	public void setIm(Integer im) {
		this.im = im;
	}
}
