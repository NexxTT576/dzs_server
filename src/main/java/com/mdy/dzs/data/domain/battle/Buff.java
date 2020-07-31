package com.mdy.dzs.data.domain.battle;

import java.io.Serializable;
import java.util.List;


/**
 * buff模型
 * @author 房曈
 *
 */
public class Buff implements Serializable{


	public static final int TYPE_增益 		= 1;
	public static final int TYPE_减益 		= 2;
	public static final int TYPE_回血 		= 3;
	public static final int TYPE_中毒掉血 	= 4;
	public static final int TYPE_火烧掉血 	= 5;
	public static final int TYPE_眩晕 		= 6;
	public static final int TYPE_封技 		= 7;
	public static final int TYPE_冰冻 		= 8;
	public static final int TYPE_封神 		= 9;
	public static final int TYPE_无敌 		= 10;
	public static final int TYPE_禁疗 		= 11;
	public static final int TYPE_禁怒		= 12;
	public static final int TYPE_增加神通 	= 13;
	public static final int TYPE_免疫buff 	= 14;
	
	/**序列化id*/
	private static final long serialVersionUID = 1L;


	/**buffid*/
	private int id;
	/**名称*/
	private String name;
	/**buff组*/
	private int group;
	/**共存组*/
	private int coexist;
	/**效果类型（1：增益buff:2：减益buff:3：回血:4：中毒掉血；5-火烧掉血；6-眩晕；7-封技；8-冰冻；9-封神；10-无敌；11-禁疗；12-禁怒）*/
	private int effect;
	/**目标方（1：己方；2：对方）*/
	private int side;
	/**目标类型（同技能）*/
	private int target;
	/**概率*/
	private int prob;
	/**回合数*/
	private int round;
	/**计算方式（1-固定值；2-我方剩余人数*表格数据；3-目标生命上限/10000*表格数据）*/
	private int attCalc;
	/**参数组（普通空；对于回血（回血系数1；回血系数2）；对于掉血（dot系数1；dot系数2））*/
	private List<Integer> param;
	/**础属性组（生命；攻击；物防；法防）*/
	private List<Integer> base;
	/**基础比例组（生命比例；攻击比例；物防比例；法防比例）*/
	private List<Integer> baseRate;
	/**修正属性组（命中；闪避；暴击；抗暴；破击；格挡）*/
	private List<Integer> baseMod;
	/**稀有属性组（物理伤害加成；法术伤害加成；中毒伤害加成；火焰伤害加成；物理伤害减免；法术伤害减免；中毒伤害减免；火焰伤害减免）*/
	private List<Integer> rare;
	/**稀有比例组（物理伤害加成比例；法术伤害加成比例；中毒伤害加成比例；火焰伤害加成比例；物理伤害减免比例；法术伤害减免比例；中毒伤害减免比例；火焰伤害减免比例）*/
	private List<Integer> rareRate;
	/**buff抵抗*/
	private int value6;
	/**暴击伤害数组（暴伤倍率加成；暴伤倍率减免）*/
	private List<Integer> nature7;
	/**免疫buffid组*/
	private List<Integer> defBuffId;
	/**是否及时生效*/
	private int isNow;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getGroup() {
		return group;
	}
	public void setGroup(int group) {
		this.group = group;
	}
	public int getCoexist() {
		return coexist;
	}
	public void setCoexist(int coexist) {
		this.coexist = coexist;
	}
	public int getEffect() {
		return effect;
	}
	public void setEffect(int effect) {
		this.effect = effect;
	}
	public int getSide() {
		return side;
	}
	public void setSide(int side) {
		this.side = side;
	}
	public int getTarget() {
		return target;
	}
	public void setTarget(int target) {
		this.target = target;
	}
	public int getProb() {
		return prob;
	}
	public void setProb(int prob) {
		this.prob = prob;
	}
	public int getRound() {
		return round;
	}
	public void setRound(int round) {
		this.round = round;
	}
	public int getAttCalc() {
		return attCalc;
	}
	public void setAttCalc(int attCalc) {
		this.attCalc = attCalc;
	}
	public List<Integer> getParam() {
		return param;
	}
	public void setParam(List<Integer> param) {
		this.param = param;
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
	public int getValue6() {
		return value6;
	}
	public void setValue6(int value6) {
		this.value6 = value6;
	}
	public List<Integer> getNature7() {
		return nature7;
	}
	public void setNature7(List<Integer> nature7) {
		this.nature7 = nature7;
	}
	public List<Integer> getDefBuffId() {
		return defBuffId;
	}
	public void setDefBuffId(List<Integer> defBuffId) {
		this.defBuffId = defBuffId;
	}
	public int getIsNow() {
		return isNow;
	}
	public void setIsNow(int isNow) {
		this.isNow = isNow;
	}

}