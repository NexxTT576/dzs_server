package com.mdy.dzs.game.domain.swordfight;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.mdy.dzs.game.domain.Prop;


/**
 * 论剑角色模型
 * @author zhou
 *
 */
public class SwordRoleCard implements Serializable{


	/**序列化id*/
	private static final long serialVersionUID = 1L;


	/***/
	private int id;
	/**角色账号*/
	private String account;
	/**卡牌类型（0：自己，1：敌人）*/
	private int type;
	/**生命的剩余比例 基础10000*/
	private int lifeRate;
	/**初始的生命值*/
	private int initLife;
	/**剩余怒气值*/
	private int anger;
	/**当前卡的阶数*/
	private int cls;
	/**卡牌data的id*/
	private int resId;
	/**布阵的位置*/
	private int pos;
	/**神通id数组*/
	private List<Integer> shenIDAry;
	/**神通id等级数组*/
	private List<Integer> shenLvAry;
	/**附加的属性值 如附加的生命等*/
	private List<Prop> props;
	/**阵型上的顺序*/
	private int order;
	/**卡牌的品质*/
	private int star;
	/**level*/
	private int level;
	/**关联role_card中的id*/
	private int role_card_id;
	/**关联sword_role中的id*/
	private int sword_role_id;
	/***/
	private Date createTime;
	/***/
	private Date updateTime;

	public int getId(){
		return this.id;
	}
	public void setId(int id){
		this.id=id;
	}
	public String getAccount(){
		return this.account;
	}
	public void setAccount(String account){
		this.account=account;
	}
	public int getType(){
		return this.type;
	}
	public void setType(int type){
		this.type=type;
	}
	public int getLifeRate(){
		return this.lifeRate;
	}
	public void setLifeRate(int lifeRate){
		this.lifeRate=lifeRate;
	}
	public int getInitLife(){
		return this.initLife;
	}
	public void setInitLife(int initLife){
		this.initLife=initLife;
	}
	public int getAnger(){
		return this.anger;
	}
	public void setAnger(int anger){
		this.anger=anger;
	}
	public int getCls(){
		return this.cls;
	}
	public void setCls(int cls){
		this.cls=cls;
	}
	public int getResId(){
		return this.resId;
	}
	public void setResId(int resId){
		this.resId=resId;
	}
	public int getPos(){
		return this.pos;
	}
	public void setPos(int pos){
		this.pos=pos;
	}
	public int getOrder(){
		return this.order;
	}
	public void setOrder(int order){
		this.order=order;
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
	/**
	 * @return shenIDAry
	 */
	public List<Integer> getShenIDAry() {
		return shenIDAry;
	}
	/**
	 * @param shenIDAry 要设置的 shenIDAry
	 */
	public void setShenIDAry(List<Integer> shenIDAry) {
		this.shenIDAry = shenIDAry;
	}
	/**
	 * @return props
	 */
	public List<Prop> getProps() {
		return props;
	}
	/**
	 * @param props 要设置的 props
	 */
	public void setProps(List<Prop> props) {
		this.props = props;
	}
	public int getStar() {
		return star;
	}
	public void setStar(int star) {
		this.star = star;
	}
	public int getRole_card_id() {
		return role_card_id;
	}
	public void setRole_card_id(int role_card_id) {
		this.role_card_id = role_card_id;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public List<Integer> getShenLvAry() {
		return shenLvAry;
	}
	public void setShenLvAry(List<Integer> shenLvAry) {
		this.shenLvAry = shenLvAry;
	}
	public int getSword_role_id() {
		return sword_role_id;
	}
	public void setSword_role_id(int sword_role_id) {
		this.sword_role_id = sword_role_id;
	}
}