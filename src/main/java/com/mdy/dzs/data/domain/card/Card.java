package com.mdy.dzs.data.domain.card;

import java.io.Serializable;
import java.util.List;



/**
 * 卡片模型
 * @author 房曈
 *
 */
public class Card implements Serializable{


	/**序列化id*/
	private static final long serialVersionUID = 1L;


	/**武将id*/
	private int id;
	/**武将组id*/
	private int group;
	/**武将名称*/
	private String name;
	/**类别*/
	private int type;
	/**武将职业*/
	private int job;
	/**武将性别*/
	private int sex;
	/**是否豪杰*/
	private int hero;
	/**星级数组*/
	private List<Integer> star;
	/**初始怒气*/
	private List<Integer> anger;
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
	private List<Integer> resist;
	/**暴击伤害数组（暴伤倍率加成；暴伤倍率减免）*/
	private List<Integer> crit;
	/**普攻技能*/
	private List<Integer> skill;
	/**怒攻技能*/
	private List<Integer> angerSkill;
	/**团体技能*/
	private List<Integer> groupSkill;
	/**反击技能*/
	private List<Integer> bskill;
	/**进阶获得神通点*/
	private List<Integer> arrPoint;
	/**神通组（神通1id；神通2id；神通3id）*/
	private List<Integer> talent;
	/**缘分组（缘分1：缘分2；缘分3；缘分4；缘分5；缘分6）*/
	private List<Integer> fate1;
	/**强化每级提升基础属性组（生命；攻击；物防；法防）*/
	private List<Integer> levelAdd;
	/**是否可进阶*/
	private int advance;
	/**进阶生命提升组*/
	private List<Integer> advLife;
	/**进阶攻击提升组*/
	private List<Integer> advAttack;
	/**进阶物防提升组*/
	private List<Integer> advDefense;
	/**进阶法防提升组*/
	private List<Integer> advDefenseM;
	/**进阶银币花费组*/
	private List<Integer> silver;
	/**进阶花费物品id1组*/
	private List<Integer> item1;
	/**进阶花费物品id2组*/
	private List<Integer> item2;
	/**进阶花费物品id3组*/
	private List<Integer> item3;
	/**进阶花费cardid组*/
	private List<Integer> card;
	/**进阶物品数量1组*/
	private List<Integer> number1;
	/**进阶物品数量2组*/
	private List<Integer> number2;
	/**进阶物品数量3组*/
	private List<Integer> number3;
	/**进阶card数量组*/
	private List<Integer> number;
	/**是否可溶解*/
	private int lysis;
	/**可溶基础经验*/
	private int exp;
	/**是否可炼化*/
	private int refining;
	/**炼化货币组（将魂；银币；魂玉）*/
	private List<Integer> soul;
	/**是否可重生*/
	private int reborn;
	/**贩卖价格(0表示不可贩卖）*/
	private int price;
	/**超能力（0-无；1-物理免疫；2-法术免疫)*/
	private int superNatural;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getGroup() {
		return group;
	}
	public void setGroup(int group) {
		this.group = group;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getJob() {
		return job;
	}
	public void setJob(int job) {
		this.job = job;
	}
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public int getHero() {
		return hero;
	}
	public void setHero(int hero) {
		this.hero = hero;
	}
	public List<Integer> getStar() {
		return star;
	}
	public void setStar(List<Integer> star) {
		this.star = star;
	}
	public List<Integer> getAnger() {
		return anger;
	}
	public void setAnger(List<Integer> anger) {
		this.anger = anger;
	}
	public List<Integer> getLead() {
		return lead;
	}
	public void setLead(List<Integer> lead) {
		this.lead = lead;
	}
	public List<Integer> getBase() {
		return base;
	}
	public void setBase(List<Integer> base) {
		this.base = base;
	}
	public List<Integer> getBaseRate() {
		return baseRate;
	}
	public void setBaseRate(List<Integer> baseRate) {
		this.baseRate = baseRate;
	}
	public List<Integer> getBaseMod() {
		return baseMod;
	}
	public void setBaseMod(List<Integer> baseMod) {
		this.baseMod = baseMod;
	}
	public List<Integer> getRare() {
		return rare;
	}
	public void setRare(List<Integer> rare) {
		this.rare = rare;
	}
	public List<Integer> getRareRate() {
		return rareRate;
	}
	public void setRareRate(List<Integer> rareRate) {
		this.rareRate = rareRate;
	}
	public List<Integer> getHeal() {
		return heal;
	}
	public void setHeal(List<Integer> heal) {
		this.heal = heal;
	}
	public int getRate() {
		return rate;
	}
	public void setRate(int rate) {
		this.rate = rate;
	}
	public List<Integer> getResist() {
		return resist;
	}
	public void setResist(List<Integer> resist) {
		this.resist = resist;
	}
	public List<Integer> getCrit() {
		return crit;
	}
	public void setCrit(List<Integer> crit) {
		this.crit = crit;
	}
	public List<Integer> getSkill() {
		return skill;
	}
	public void setSkill(List<Integer> skill) {
		this.skill = skill;
	}
	public List<Integer> getAngerSkill() {
		return angerSkill;
	}
	public void setAngerSkill(List<Integer> angerSkill) {
		this.angerSkill = angerSkill;
	}
	public List<Integer> getGroupSkill() {
		return groupSkill;
	}
	public void setGroupSkill(List<Integer> groupSkill) {
		this.groupSkill = groupSkill;
	}
	public List<Integer> getBskill() {
		return bskill;
	}
	public void setBskill(List<Integer> bskill) {
		this.bskill = bskill;
	}
	public List<Integer> getArrPoint() {
		return arrPoint;
	}
	public void setArrPoint(List<Integer> arrPoint) {
		this.arrPoint = arrPoint;
	}
	public List<Integer> getTalent() {
		return talent;
	}
	public void setTalent(List<Integer> talent) {
		this.talent = talent;
	}
	public List<Integer> getFate1() {
		return fate1;
	}
	public void setFate1(List<Integer> fate1) {
		this.fate1 = fate1;
	}
	public List<Integer> getLevelAdd() {
		return levelAdd;
	}
	public void setLevelAdd(List<Integer> levelAdd) {
		this.levelAdd = levelAdd;
	}
	public int getAdvance() {
		return advance;
	}
	public void setAdvance(int advance) {
		this.advance = advance;
	}
	public List<Integer> getAdvLife() {
		return advLife;
	}
	public void setAdvLife(List<Integer> advLife) {
		this.advLife = advLife;
	}
	public List<Integer> getAdvAttack() {
		return advAttack;
	}
	public void setAdvAttack(List<Integer> advAttack) {
		this.advAttack = advAttack;
	}
	public List<Integer> getAdvDefense() {
		return advDefense;
	}
	public void setAdvDefense(List<Integer> advDefense) {
		this.advDefense = advDefense;
	}
	public List<Integer> getAdvDefenseM() {
		return advDefenseM;
	}
	public void setAdvDefenseM(List<Integer> advDefenseM) {
		this.advDefenseM = advDefenseM;
	}
	public List<Integer> getSilver() {
		return silver;
	}
	public void setSilver(List<Integer> silver) {
		this.silver = silver;
	}
	public List<Integer> getItem1() {
		return item1;
	}
	public void setItem1(List<Integer> item1) {
		this.item1 = item1;
	}
	public List<Integer> getItem2() {
		return item2;
	}
	public void setItem2(List<Integer> item2) {
		this.item2 = item2;
	}
	public List<Integer> getItem3() {
		return item3;
	}
	public void setItem3(List<Integer> item3) {
		this.item3 = item3;
	}
	public List<Integer> getCard() {
		return card;
	}
	public void setCard(List<Integer> card) {
		this.card = card;
	}
	public List<Integer> getNumber1() {
		return number1;
	}
	public void setNumber1(List<Integer> number1) {
		this.number1 = number1;
	}
	public List<Integer> getNumber2() {
		return number2;
	}
	public void setNumber2(List<Integer> number2) {
		this.number2 = number2;
	}
	public List<Integer> getNumber3() {
		return number3;
	}
	public void setNumber3(List<Integer> number3) {
		this.number3 = number3;
	}
	public List<Integer> getNumber() {
		return number;
	}
	public void setNumber(List<Integer> number) {
		this.number = number;
	}
	public int getLysis() {
		return lysis;
	}
	public void setLysis(int lysis) {
		this.lysis = lysis;
	}
	public int getExp() {
		return exp;
	}
	public void setExp(int exp) {
		this.exp = exp;
	}
	public int getRefining() {
		return refining;
	}
	public void setRefining(int refining) {
		this.refining = refining;
	}
	public List<Integer> getSoul() {
		return soul;
	}
	public void setSoul(List<Integer> soul) {
		this.soul = soul;
	}
	public int getReborn() {
		return reborn;
	}
	public void setReborn(int reborn) {
		this.reborn = reborn;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getSuperNatural() {
		return superNatural;
	}
	public void setSuperNatural(int superNatural) {
		this.superNatural = superNatural;
	}

	
}