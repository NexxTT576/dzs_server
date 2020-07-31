/**
 * 
 */
package com.mdy.dzs.game.fight.domain.fighttalent;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnore;

import com.mdy.dzs.game.fight.domain.fightskill.FightSkillBuffVO;
import com.mdy.dzs.game.fight.domain.fightskill.FightSkillVO;

/**
 * 神通技能vo
 * 
 * @author fangtong E-mail: 10786912@qq.com
 * @version 创建时间：2014年9月29日 上午10:52:40
 */
public class FightTalentVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** side,使用方(1战斗发起方,2应战方) */
	private int s;
	/** pos,发起位置(1-6) */
	private int p;
	/** 神通id */
	private int sid;

	private List<FightTalentPropVO> prop;
	private List<FightSkillBuffVO> buff;
	private List<FightSkillVO> skill;

	public FightTalentVO() {
		prop = new ArrayList<FightTalentPropVO>();
		buff = new ArrayList<FightSkillBuffVO>();
		skill = new ArrayList<FightSkillVO>();
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

	public int getSid() {
		return sid;
	}

	public void setSid(int sid) {
		this.sid = sid;
	}

	public List<FightTalentPropVO> getProp() {
		return prop;
	}

	public void setProp(List<FightTalentPropVO> prop) {
		this.prop = prop;
	}

	public List<FightSkillBuffVO> getBuff() {
		return buff;
	}

	public void setBuff(List<FightSkillBuffVO> buff) {
		this.buff = buff;
	}

	public List<FightSkillVO> getSkill() {
		return skill;
	}

	public void setSkill(List<FightSkillVO> skill) {
		this.skill = skill;
	}

	@JsonIgnore
	public boolean isNoEffect() {
		return buff.size() == 0 && prop.size() == 0 && skill.size() == 0;
	}

}
