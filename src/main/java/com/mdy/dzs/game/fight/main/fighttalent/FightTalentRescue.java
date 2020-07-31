/**
 * 
 */
package com.mdy.dzs.game.fight.main.fighttalent;

import com.mdy.dzs.data.domain.shentong.Talent;
import com.mdy.dzs.game.fight.main.FightMain;
import com.mdy.dzs.game.fight.main.Fighter;

/**
 * @author fangtong  E-mail: 10786912@qq.com
 * @version 创建时间：2014年9月29日  下午5:37:11
 */
public class FightTalentRescue {

	private int id;
	private Talent data;
	private Fighter src;
	
	/**参数1(2援护：援护触发条件*/
	private int cond;
	/**参数2（2援护：援护目标生命比例）*/
	private int lifeRate;
	/**参数3（2援护：援护触发概率）	*/
	private int prob;
	/**参数4（2援护：伤害减免比例）*/
	private int reduceRate;

	public FightTalentRescue(Fighter src,Talent data) {
		this.src = src;
		this.id = data.getId();
		this.data = data;
		cond 		= data.getPara1();
		lifeRate 	= data.getPara2();
		prob 		= data.getPara3();
		reduceRate 	= data.getPara4();
	}
	
	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public Talent getData() {
		return data;
	}


	public void setData(Talent data) {
		this.data = data;
	}


	public Fighter getSrc() {
		return src;
	}


	public void setSrc(Fighter src) {
		this.src = src;
	}


	public int getCond() {
		return cond;
	}


	public void setCond(int cond) {
		this.cond = cond;
	}


	public int getLifeRate() {
		return lifeRate;
	}


	public void setLifeRate(int lifeRate) {
		this.lifeRate = lifeRate;
	}


	public int getProb() {
		return prob;
	}


	public void setProb(int prob) {
		this.prob = prob;
	}


	public int getReduceRate() {
		return reduceRate;
	}


	public void setReduceRate(int reduceRate) {
		this.reduceRate = reduceRate;
	}


	public FightTalentRescueResult effect(FightMain main,Fighter old) {
		FightTalentRescueResult result = null;
		do{
			if(Math.random()>(prob*0.0001)) 	break;
			double oldLife = old.getLife()*1.0;
			double oldLifeRate = old.getInitLife()*1.0;
			if((oldLife/oldLifeRate)>(lifeRate*0.0001)){
				break;
			}
			result = new FightTalentRescueResult(old,src,this);
		}while(false);
		return result;
	}

	
	public int getNode() {
		return FightTalentNode.队友被攻击时;
	}

}
