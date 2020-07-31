package com.mdy.dzs.data.domain.battle;

import java.io.Serializable;
import java.util.List;


/**
 * 战斗技能模型
 * @author 房曈
 *
 */
public class BattleSkill implements Serializable{


	/**序列化id*/
	private static final long serialVersionUID = 1L;


	/**技能id*/
	private int id;
	/**技能原名*/
	private String old;
	/**技能名称*/
	private String name;
	/**技能
特效*/
	private String effect;
	/**技能类别（0：普通攻击；1：怒气攻击）*/
	private int type;
	/**技能效果（0：物理；1：法术）
类型*/
	private int style;
	/**卡牌位置*/
	private int position;
	/**命中怒气回复*/
	private int anger;
	/**临时属性组*/
	private int nature1;
	/**临时属性比例*/
	private int value1;
	/**伤害效果1类型（见技能配置表）*/
	private int dType1;
	/**伤害效果1参数组（见技能配置表）*/
	private List<Integer> dAttr1;
	/**伤害效果1类型（见技能配置表）*/
	private int dType2;
	/**伤害效果1参数组（见技能配置表）*/
	private List<Integer> dAttr2;
	/**伤害效果1类型（见技能配置表）*/
	private int dType3;
	/**伤害效果1参数组（见技能配置表）*/
	private List<Integer> dAttr3;
	/**buff概率*/
	private int prob;
	/**buff组*/
	private List<Integer> buff;
	/**是否为多次伤害*/
	private int isMutipleHit;	
	/**多段伤害的段数*/
	private int dCount;
	/**buff的目标防*/
	private int bSide;
	/**buff的目标*/
	private int bTarget;

	public int getId(){
		return this.id;
	}
	public void setId(int id){
		this.id=id;
	}
	public String getOld(){
		return this.old;
	}
	public void setOld(String old){
		this.old=old;
	}
	public String getName(){
		return this.name;
	}
	public void setName(String name){
		this.name=name;
	}
	public String getEffect(){
		return this.effect;
	}
	public void setEffect(String effect){
		this.effect=effect;
	}
	public int getType(){
		return this.type;
	}
	public void setType(int type){
		this.type=type;
	}
	public int getStyle(){
		return this.style;
	}
	public void setStyle(int style){
		this.style=style;
	}
	public int getPosition(){
		return this.position;
	}
	public void setPosition(int position){
		this.position=position;
	}
	public int getAnger(){
		return this.anger;
	}
	public void setAnger(int anger){
		this.anger=anger;
	}
	public int getNature1(){
		return this.nature1;
	}
	public void setNature1(int nature1){
		this.nature1=nature1;
	}
	public int getValue1(){
		return this.value1;
	}
	public void setValue1(int value1){
		this.value1=value1;
	}
	public int getDType1(){
		return this.dType1;
	}
	public void setDType1(int dType1){
		this.dType1=dType1;
	}
	public int getdType1() {
		return dType1;
	}
	public void setdType1(int dType1) {
		this.dType1 = dType1;
	}
	public List<Integer> getDAttr1() {
		return dAttr1;
	}
	public void setDAttr1(List<Integer> dAttr1) {
		this.dAttr1 = dAttr1;
	}
	public int getDType2() {
		return dType2;
	}
	public void setDType2(int dType2) {
		this.dType2 = dType2;
	}
	public List<Integer> getDAttr2() {
		return dAttr2;
	}
	public void setDAttr2(List<Integer> dAttr2) {
		this.dAttr2 = dAttr2;
	}
	public int getDType3() {
		return dType3;
	}
	public void setDType3(int dType3) {
		this.dType3 = dType3;
	}
	public List<Integer> getDAttr3() {
		return dAttr3;
	}
	public void setDAttr3(List<Integer> dAttr3) {
		this.dAttr3 = dAttr3;
	}
	public int getProb() {
		return prob;
	}
	public void setProb(int prob) {
		this.prob = prob;
	}
	public List<Integer> getBuff() {
		return buff;
	}
	public void setBuff(List<Integer> buff) {
		this.buff = buff;
	}
	public int getIsMutipleHit() {
		return isMutipleHit;
	}
	public void setIsMutipleHit(int isMutipleHit) {
		this.isMutipleHit = isMutipleHit;
	}
	public int getdCount() {
		return dCount;
	}
	public void setdCount(int dCount) {
		this.dCount = dCount;
	}
	public int getbSide() {
		return bSide;
	}
	public void setbSide(int bSide) {
		this.bSide = bSide;
	}
	public int getbTarget() {
		return bTarget;
	}
	public void setbTarget(int bTarget) {
		this.bTarget = bTarget;
	}
	
}