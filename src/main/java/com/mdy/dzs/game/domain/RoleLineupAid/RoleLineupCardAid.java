package com.mdy.dzs.game.domain.RoleLineupAid;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.mdy.dzs.game.domain.Prop;

/**
 * 用户辅助阵型卡牌模型
 * @author zhou
 *
 */
public class RoleLineupCardAid implements Serializable{

	public RoleLineupCardAid(int roleId ,int sysId , int cardId,int resId){
		this.roleId = roleId;
		this.sysId = sysId;
		this.cardId = cardId;
	}
	public RoleLineupCardAid(){
		
	}

	/**序列化id*/
	private static final long serialVersionUID = 1L;


	/***/
	private int id;
	/**角色id*/
	private int roleId;
	/**系统id*/
	private int sysId;
	/**卡牌唯一id*/
	private int cardId;
	/**卡牌类型id*/
	private int resId;
	/**生命剩余比例*/
	private int lifeRate;
	/**怒气值*/
	private int anger;
	/**卡牌的位置*/
	private int pos;
	/**创建时间*/
	private Date createTime;
	/**更新时间*/
	private Date updateTime;
	
	/**当前卡的阶数*/
	private int cls;
	/**神通id数组*/
	private List<Integer> shenIDAry;
	/**神通id等级数组*/
	private List<Integer> shenLvAry;
	/**附加的属性值 如附加的生命等*/
	private List<Prop> props;
	/**卡牌的品质*/
	private int star;
	/**level*/
	private int level;
	/**初始的生命值*/
	private int initLife;

	public int getId(){
		return this.id;
	}
	public void setId(int id){
		this.id=id;
	}
	public int getCardId(){
		return this.cardId;
	}
	public void setCardId(int cardId){
		this.cardId=cardId;
	}
	public int getResId(){
		return this.resId;
	}
	public void setResId(int resId){
		this.resId=resId;
	}
	public int getLifeRate(){
		return this.lifeRate;
	}
	public void setLifeRate(int lifeRate){
		this.lifeRate=lifeRate;
	}
	public int getAnger(){
		return this.anger;
	}
	public void setAnger(int anger){
		this.anger=anger;
	}
	public int getPos(){
		return this.pos;
	}
	public void setPos(int pos){
		this.pos=pos;
	}
	public Date getCreateTime(){
		return this.createTime;
	}
	public void setCreateTime(Date createTime){
		this.createTime=createTime;
	}
	public Date getUpdateTime(){
		return this.updateTime;
	}
	public void setUpdateTime(Date updateTime){
		this.updateTime=updateTime;
	}
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public int getSysId() {
		return sysId;
	}
	public void setSysId(int sysId) {
		this.sysId = sysId;
	}
	public int getCls() {
		return cls;
	}
	public void setCls(int cls) {
		this.cls = cls;
	}
	public List<Integer> getShenIDAry() {
		return shenIDAry;
	}
	public void setShenIDAry(List<Integer> shenIDAry) {
		this.shenIDAry = shenIDAry;
	}
	public List<Integer> getShenLvAry() {
		return shenLvAry;
	}
	public void setShenLvAry(List<Integer> shenLvAry) {
		this.shenLvAry = shenLvAry;
	}
	public List<Prop> getProps() {
		return props;
	}
	public void setProps(List<Prop> props) {
		this.props = props;
	}
	public int getStar() {
		return star;
	}
	public void setStar(int star) {
		this.star = star;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public int getInitLife() {
		return initLife;
	}
	public void setInitLife(int initLife) {
		this.initLife = initLife;
	}
}