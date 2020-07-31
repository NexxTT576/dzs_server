package com.mdy.dzs.data.domain.battle;

import java.io.Serializable;
import java.util.List;


/**
 * 战斗模型
 * @author 房曈
 *
 */
public class Battle implements Serializable{


	/**序列化id*/
	private static final long serialVersionUID = 1L;


	/***/
	private int id;
	/**关卡名称*/
	private String name;
	/**所属副本*/
	private int field;
	/**每日挑战次数*/
	private int number;
	/**连战最高次数*/
	private int lianzhan;
	/**消耗体力*/
	private int power;
	/**是否可复活*/
	private int reborn;
	/**掉落id组*/
	private List<Integer> arrDropid;
	/**关卡难度星级*/
	private int star;
	/**简单模式怪物阵型组*/
	private int npc1;
	/**产出货币类型组（类型1；类型2）*/
	private List<Integer> coin1;
	/**产出货币类型组（类型1；类型2）*/
	private List<Integer> num1;
	/**普通模式怪物阵型组*/
	private int npc2;
	/**产出货币类型组（类型1；类型2）*/
	private List<Integer> coin2;
	/**产出货币类型组（类型1；类型2）*/
	private List<Integer> num2;
	/**困难模式怪物阵型组*/
	private int npc3;
	/**产出货币类型组（类型1；类型2）*/
	private List<Integer> coin3;
	/**产出货币类型组（类型1；类型2）*/
	private List<Integer> num3;
	/**掉落随机组id*/
	private int drop;
	
	/**是否有剧情战(1无,2有)*/
	private int sbattle;
	/**助阵cardid数组*/
	private List<Integer> arrCard;
	/**上阵位置数组*/
	private List<Integer> arrPos;
	/**主角属性id加成数组*/
	private List<Integer> arrNature;
	/**我方属性id加成数值数组*/
	private List<Integer> arrNum;
	/**敌方阵型id*/
	private int npc4;

	
	
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
	public int getField() {
		return field;
	}
	public void setField(int field) {
		this.field = field;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public int getLianzhan() {
		return lianzhan;
	}
	public void setLianzhan(int lianzhan) {
		this.lianzhan = lianzhan;
	}
	public int getPower() {
		return power;
	}
	public void setPower(int power) {
		this.power = power;
	}
	public int getReborn() {
		return reborn;
	}
	public void setReborn(int reborn) {
		this.reborn = reborn;
	}
	public List<Integer> getArrDropid() {
		return arrDropid;
	}
	public void setArrDropid(List<Integer> arrDropid) {
		this.arrDropid = arrDropid;
	}
	public int getStar() {
		return star;
	}
	public void setStar(int star) {
		this.star = star;
	}
	public int getNpc1() {
		return npc1;
	}
	public void setNpc1(int npc1) {
		this.npc1 = npc1;
	}
	public List<Integer> getCoin1() {
		return coin1;
	}
	public void setCoin1(List<Integer> coin1) {
		this.coin1 = coin1;
	}
	public List<Integer> getNum1() {
		return num1;
	}
	public void setNum1(List<Integer> num1) {
		this.num1 = num1;
	}
	public int getNpc2() {
		return npc2;
	}
	public void setNpc2(int npc2) {
		this.npc2 = npc2;
	}
	public List<Integer> getCoin2() {
		return coin2;
	}
	public void setCoin2(List<Integer> coin2) {
		this.coin2 = coin2;
	}
	public List<Integer> getNum2() {
		return num2;
	}
	public void setNum2(List<Integer> num2) {
		this.num2 = num2;
	}
	public int getNpc3() {
		return npc3;
	}
	public void setNpc3(int npc3) {
		this.npc3 = npc3;
	}
	public List<Integer> getCoin3() {
		return coin3;
	}
	public void setCoin3(List<Integer> coin3) {
		this.coin3 = coin3;
	}
	public List<Integer> getNum3() {
		return num3;
	}
	public void setNum3(List<Integer> num3) {
		this.num3 = num3;
	}
	public int getDrop() {
		return drop;
	}
	public void setDrop(int drop) {
		this.drop = drop;
	}
	public int getSbattle() {
		return sbattle;
	}
	public void setSbattle(int sbattle) {
		this.sbattle = sbattle;
	}
	public List<Integer> getArrCard() {
		return arrCard;
	}
	public void setArrCard(List<Integer> arrCard) {
		this.arrCard = arrCard;
	}
	public List<Integer> getArrPos() {
		return arrPos;
	}
	public void setArrPos(List<Integer> arrPos) {
		this.arrPos = arrPos;
	}
	public List<Integer> getArrNature() {
		return arrNature;
	}
	public void setArrNature(List<Integer> arrNature) {
		this.arrNature = arrNature;
	}
	public List<Integer> getArrNum() {
		return arrNum;
	}
	public void setArrNum(List<Integer> arrNum) {
		this.arrNum = arrNum;
	}
	public int getNpc4() {
		return npc4;
	}
	public void setNpc4(int npc4) {
		this.npc4 = npc4;
	}

	public List<Integer> getCoin(int type){
		switch (type) {
		case 1:
			return coin1;
		case 2:
			return coin2;
		case 3:
			return coin3;
		}
		return null;
	}
	
	public List<Integer> getNum(int type){
		switch (type) {
		case 1:
			return num1;
		case 2:
			return num2;
		case 3:
			return num3;
		}
		return null;
	}
}