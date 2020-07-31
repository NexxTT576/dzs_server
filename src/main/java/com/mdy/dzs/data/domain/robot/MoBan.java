package com.mdy.dzs.data.domain.robot;

import java.io.Serializable;
import java.util.List;


/**
 * 机器人模板模型
 * @author 房曈
 *
 */
public class MoBan implements Serializable{


	/**序列化id*/
	private static final long serialVersionUID = 1L;


	/**玩家等级*/
	private int level;
	/**各品质的概率*/
	private List<Integer> arrProb;
	/**白品质cardid范围*/
	private List<Integer> white;
	/**绿品质cardid范围*/
	private List<Integer> green;
	/**蓝品质cardid范围*/
	private List<Integer> blue;
	/**紫品质cardid范围*/
	private List<Integer> purple;
	/**金品质cardid范围*/
	private List<Integer> gold;
	/**初始怒气*/
	private int anger;
	/**基础三围（统帅；武力；智力）*/
	private List<Integer> lead;
	/**基础属性组（生命；攻击；物防；法防）*/
	private List<Integer> base;
	/**基础比例组（生命比例；攻击比例；物防比例；法防比例）*/
	private List<Integer> baseRate;
	/**修正属性组（命中;闪避;暴击;抗暴;破击;格挡）*/
	private List<Integer> baseMod;
	/**稀有属性组（物理伤害加成；法术伤害加成；中毒伤害加成；火焰伤害加成；物理伤害减免；法术伤害减免；中毒伤害减免；火焰伤害减免）*/
	private List<Integer> rare;
	/**稀有比例组（物理伤害加成比例；法术伤害加成比例；中毒伤害加成比例；火焰伤害加成比例；物理伤害减免比例；法术伤害减免比例；中毒伤害减免比例；火焰伤害减免比例）*/
	private List<Integer> rareRate;
	/**治疗效果组（治疗效果加成数值；治疗效果加成比例；被治疗效果加成数值；被治疗效果加成比例）*/
	private List<Integer> heal;
	/**身手值*/
	private int rate;
	/**buff抵抗*/
	private int resist;
	/**暴击伤害数组（暴伤倍率加成；暴伤倍率减免）*/
	private List<Integer> crit;
	/**机器人掉落碎片概率组*/
	private List<Integer> drop;
	/**真实玩家掉落碎片概率组*/
	private List<Integer> dedrop;
	/**保底次数组*/
	private List<Integer> max;
	/**夺宝翻牌*/
	private int prob;
	/**竞技场翻牌*/
	private int prob1;

	public int getLevel(){
		return this.level;
	}
	public void setLevel(int level){
		this.level=level;
	}
	public List<Integer> getArrProb(){
		return this.arrProb;
	}
	public void setArrProb(List<Integer> arrProb){
		this.arrProb=arrProb;
	}
	public List<Integer> getWhite(){
		return this.white;
	}
	public void setWhite(List<Integer> white){
		this.white=white;
	}
	public List<Integer> getGreen(){
		return this.green;
	}
	public void setGreen(List<Integer> green){
		this.green=green;
	}
	public List<Integer> getBlue(){
		return this.blue;
	}
	public void setBlue(List<Integer> blue){
		this.blue=blue;
	}
	public List<Integer> getPurple(){
		return this.purple;
	}
	public void setPurple(List<Integer> purple){
		this.purple=purple;
	}
	public List<Integer> getGold(){
		return this.gold;
	}
	public void setGold(List<Integer> gold){
		this.gold=gold;
	}
	public int getAnger(){
		return this.anger;
	}
	public void setAnger(int anger){
		this.anger=anger;
	}
	public List<Integer> getLead(){
		return this.lead;
	}
	public void setLead(List<Integer> lead){
		this.lead=lead;
	}
	public List<Integer> getBase(){
		return this.base;
	}
	public void setBase(List<Integer> base){
		this.base=base;
	}
	public List<Integer> getBaseRate(){
		return this.baseRate;
	}
	public void setBaseRate(List<Integer> baseRate){
		this.baseRate=baseRate;
	}
	public List<Integer> getBaseMod(){
		return this.baseMod;
	}
	public void setBaseMod(List<Integer> baseMod){
		this.baseMod=baseMod;
	}
	public List<Integer> getRare(){
		return this.rare;
	}
	public void setRare(List<Integer> rare){
		this.rare=rare;
	}
	public List<Integer> getRareRate(){
		return this.rareRate;
	}
	public void setRareRate(List<Integer> rareRate){
		this.rareRate=rareRate;
	}
	public List<Integer> getHeal(){
		return this.heal;
	}
	public void setHeal(List<Integer> heal){
		this.heal=heal;
	}
	public int getRate(){
		return this.rate;
	}
	public void setRate(int rate){
		this.rate=rate;
	}
	public int getResist(){
		return this.resist;
	}
	public void setResist(int resist){
		this.resist=resist;
	}
	public List<Integer> getCrit(){
		return this.crit;
	}
	public void setCrit(List<Integer> crit){
		this.crit=crit;
	}
	public List<Integer> getDrop(){
		return this.drop;
	}
	public void setDrop(List<Integer> drop){
		this.drop=drop;
	}
	public List<Integer> getDedrop(){
		return this.dedrop;
	}
	public void setDedrop(List<Integer> dedrop){
		this.dedrop=dedrop;
	}
	public int getProb(){
		return this.prob;
	}
	public void setProb(int prob){
		this.prob=prob;
	}
	public int getProb1(){
		return this.prob1;
	}
	public void setProb1(int prob1){
		this.prob1=prob1;
	}
	public List<Integer> getMax() {
		return max;
	}
	public void setMax(List<Integer> max) {
		this.max = max;
	}
}