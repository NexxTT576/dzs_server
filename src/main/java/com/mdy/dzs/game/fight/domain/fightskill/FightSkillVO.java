/**
 * 
 */
package com.mdy.dzs.game.fight.domain.fightskill;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.mdy.dzs.game.fight.domain.fighttalent.FightTalentVO;

/**
 * @author fangtong  E-mail: 10786912@qq.com
 * @version 创建时间：2014年9月24日  下午8:18:53
 */
public class FightSkillVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**type,战斗出手类型*/
	private int t = 3;
	/**count,回合计数*/
	private int n;
	/**side,使用方(1下方,2上方) */
	private int s;
	/**pos,发起位置(1-6)      */
	private int p;
	/**use,普通攻击1,天赋2,怒气3 */
	private int use;
	/**指定目标的攻击,多次伤害,单体吸血攻击,减怒,加怒,加血*/
	private int eff;
	/**skill,使用技能*/
	private int skill;
	/**生命*/
	private int l;
	/**怒气*/
	private int a;
	
	private List<Integer> st;
	
	private List<FightSkillProcessVO> tr;
	
	private List<FightSkillBuffVO> buff;
	
	private List<FightSkillAngerVO> ta;
	
	private List<FightTalentVO> tal;
	
	private List<FightSkillVO> fb;

	public int getL() {
		return l;
	}
	public void setL(int l) {
		this.l = l;
	}
	public int getA() {
		return a;
	}
	public void setA(int a) {
		this.a = a;
	}
	public List<Integer> getSt() {
		return st;
	}
	public void setSt(List<Integer> st) {
		this.st = st;
	}
	public FightSkillVO() {
		tr = new ArrayList<FightSkillProcessVO>();
		buff = new ArrayList<FightSkillBuffVO>();
		tal = new ArrayList<FightTalentVO>();
		st = new ArrayList<Integer>();
		ta = new ArrayList<FightSkillAngerVO>();
		fb = new ArrayList<FightSkillVO>();
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

	public int getUse() {
		return use;
	}

	public void setUse(int use) {
		this.use = use;
	}

	public int getEff() {
		return eff;
	}

	public void setEff(int eff) {
		this.eff = eff;
	}

	public int getSkill() {
		return skill;
	}

	public void setSkill(int skill) {
		this.skill = skill;
	}

	public List<FightSkillProcessVO> getTr() {
		return tr;
	}

	public void setTr(List<FightSkillProcessVO> tr) {
		this.tr = tr;
	}

	public List<FightSkillBuffVO> getBuff() {
		return buff;
	}

	public void setBuff(List<FightSkillBuffVO> buff) {
		this.buff = buff;
	}

	public List<FightTalentVO> getTal() {
		return tal;
	}

	public void setTal(List<FightTalentVO> tal) {
		this.tal = tal;
	}
	public List<FightSkillAngerVO> getTa() {
		return ta;
	}
	public void addAngerVO(FightSkillAngerVO vo) {
		ta.add(vo);
	}
	public void addProcess(FightSkillProcessVO skillMissProcessVO) {
		tr.add(skillMissProcessVO);
	}
	public void addBuff(FightSkillBuffVO skillBuffVO) {
		buff.add(skillBuffVO);
	}
	public List<FightSkillVO> getFb() {
		return fb;
	}
	public void addFb(FightSkillVO fbVO) {
		if(fbVO == null) return;
		this.fb.add(fbVO);
	}
	
}
