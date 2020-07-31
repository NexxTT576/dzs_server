package com.mdy.dzs.data.domain.item;

import java.io.Serializable;
import java.util.List;


/**
 * 道具模型
 * @author 房曈
 *
 */
public class Item implements Serializable{


	/**序列化id*/
	private static final long serialVersionUID = 1L;


	/**道具id(id前50留给货币）*/
	private int id;
	/**名称*/
	private String name;
	/**道具类型*/
	private int type;
	/**所属背包*/
	private int bag;
	/**排序*/
	private int order;
	/**标签*/
	private String label;
	/**套装id*/
	private int Suit;
	/**道具品质*/
	private int quality;
	/**道具icon*/
	private String icon;
	/**大图icon*/
	private String bicon;
	/**道具描述*/
	private String describe;
	/**最低使用等级*/
	private int level;
	/**叠加上限*/
	private int overlay;
	/**位置*/
	private int pos;
	/**是否可售*/
	private int sale;
	/**出售货币类型*/
	private int moneytype;
	/**单价*/
	private int price;
	/**效果类型*/
	private int effecttype;
	/**开启检测背包类型组*/
	private String test;
	/**消耗物品id;数量*/
	private List<Integer> expend;
	/**消耗物品id;数量*/
	private String auto;
	/**使用效果参数1*/
	private List<Integer> para1;
	/**使用效果参数2*/
	private List<Integer> para2;
	/**使用效果参数3*/
	private List<Integer> para3;
	/**是否可炼化*/
	private int refining;
	/**炼化得洗炼石数*/
	private int number;
	/**强化花费系数*/
	private int ratio;
	/**溶解基础经验值*/
	private int exp;
	/**属性id组*/
	private List<Integer> arrNature;
	/**属性强化加成数组*/
	private List<Integer> arrAddition;
	/**属性值数组*/
	private List<Integer> arrValue;
	/**随机属性组id组*/
	private List<Integer> randomnature;
	/**随机属性组概率组*/
	private List<Integer> probability;
	/**是否可洗炼*/
	private int polish;
	/**洗练属性*/
	private List<Integer> arrXilian;
	/**初始洗炼属性上限（生命、攻击、物防、法防、最终伤害、最终免伤）*/
	private List<Integer> arrBeginning;
	/**碎片产出关卡*/
	private List<Integer> output;
	/**可重生*/
	private int reborn;
   /** 装备档次*/
	private  int equip_level;
	public int getId(){
		return this.id;
	}
	public void setId(int id){
		this.id=id;
	}
	public String getName(){
		return this.name;
	}
	public void setName(String name){
		this.name=name;
	}
	public int getType(){
		return this.type;
	}
	public void setType(int type){
		this.type=type;
	}
	public int getBag(){
		return this.bag;
	}
	public void setBag(int bag){
		this.bag=bag;
	}
	public int getOrder(){
		return this.order;
	}
	public void setOrder(int order){
		this.order=order;
	}
	public String getLabel(){
		return this.label;
	}
	public void setLabel(String label){
		this.label=label;
	}
	public int getSuit(){
		return this.Suit;
	}
	public void setSuit(int Suit){
		this.Suit=Suit;
	}
	public int getQuality(){
		return this.quality;
	}
	public void setQuality(int quality){
		this.quality=quality;
	}
	public String getIcon(){
		return this.icon;
	}
	public void setIcon(String icon){
		this.icon=icon;
	}
	public String getBicon(){
		return this.bicon;
	}
	public void setBicon(String bicon){
		this.bicon=bicon;
	}
	public String getDescribe(){
		return this.describe;
	}
	public void setDescribe(String describe){
		this.describe=describe;
	}
	public int getLevel(){
		return this.level;
	}
	public void setLevel(int level){
		this.level=level;
	}
	public int getOverlay(){
		return this.overlay;
	}
	public void setOverlay(int overlay){
		this.overlay=overlay;
	}
	public int getPos(){
		return this.pos;
	}
	public void setPos(int pos){
		this.pos=pos;
	}
	public int getSale(){
		return this.sale;
	}
	public void setSale(int sale){
		this.sale=sale;
	}
	public int getMoneytype(){
		return this.moneytype;
	}
	public void setMoneytype(int moneytype){
		this.moneytype=moneytype;
	}
	public int getPrice(){
		return this.price;
	}
	public void setPrice(int price){
		this.price=price;
	}
	public int getEffecttype(){
		return this.effecttype;
	}
	public void setEffecttype(int effecttype){
		this.effecttype=effecttype;
	}
	public String getTest(){
		return this.test;
	}
	public void setTest(String test){
		this.test=test;
	}
	public List<Integer> getExpend(){
		return this.expend;
	}
	public void setExpend(List<Integer> expend){
		this.expend=expend;
	}
	public String getAuto(){
		return this.auto;
	}
	public void setAuto(String auto){
		this.auto=auto;
	}
	public List<Integer> getPara1(){
		return this.para1;
	}
	public void setPara1(List<Integer> para1){
		this.para1=para1;
	}
	public List<Integer> getPara2(){
		return this.para2;
	}
	public void setPara2(List<Integer> para2){
		this.para2=para2;
	}
	public List<Integer> getPara3(){
		return this.para3;
	}
	public void setPara3(List<Integer> para3){
		this.para3=para3;
	}
	public int getRefining(){
		return this.refining;
	}
	public void setRefining(int refining){
		this.refining=refining;
	}
	public int getNumber(){
		return this.number;
	}
	public void setNumber(int number){
		this.number=number;
	}
	public int getRatio(){
		return this.ratio;
	}
	public void setRatio(int ratio){
		this.ratio=ratio;
	}
	public int getExp(){
		return this.exp;
	}
	public void setExp(int exp){
		this.exp=exp;
	}
	public List<Integer> getArrNature(){
		return this.arrNature;
	}
	public void setArrNature(List<Integer> arrNature){
		this.arrNature=arrNature;
	}
	public List<Integer> getArrAddition(){
		return this.arrAddition;
	}
	public void setArrAddition(List<Integer> arrAddition){
		this.arrAddition=arrAddition;
	}
	public List<Integer> getArrValue(){
		return this.arrValue;
	}
	public void setArrValue(List<Integer> arrValue){
		this.arrValue=arrValue;
	}
	public List<Integer> getRandomnature(){
		return this.randomnature;
	}
	public void setRandomnature(List<Integer> randomnature){
		this.randomnature=randomnature;
	}
	public List<Integer> getProbability(){
		return this.probability;
	}
	public void setProbability(List<Integer> probability){
		this.probability=probability;
	}
	public int getPolish(){
		return this.polish;
	}
	public void setPolish(int polish){
		this.polish=polish;
	}
	public List<Integer> getArrXilian(){
		return this.arrXilian;
	}
	public void setArrXilian(List<Integer> arrXilian){
		this.arrXilian=arrXilian;
	}
	public List<Integer> getArrBeginning(){
		return this.arrBeginning;
	}
	public void setArrBeginning(List<Integer> arrBeginning){
		this.arrBeginning=arrBeginning;
	}
	public List<Integer> getOutput(){
		return this.output;
	}
	public void setOutput(List<Integer> output){
		this.output=output;
	}
	public int getReborn() {
		return reborn;
	}
	public void setReborn(int reborn) {
		this.reborn = reborn;
	}
	public int getEquip_level() {
		return equip_level;
	}
	public void setEquip_level(int equip_level) {
		this.equip_level = equip_level;
	}
	
}