package com.mdy.dzs.game.domain.card;

import java.io.Serializable;
import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

import com.mdy.dzs.game.domain.IdObject;
import com.mdy.dzs.game.domain.Prop;

/**
 * 玩家卡牌模型
 * 
 * @author 房曈
 *
 */
public class RoleCard implements Serializable, IdObject {

	/** 序列化id */
	private static final long serialVersionUID = 1L;

	int id;
	/***/
	private int roleId;
	/** 1-6上阵, 0未上阵 */
	int pos;
	/** 子位置0 */
	private int subpos;
	/** 类型 */
	private int type;
	/** 资源id */
	private int resId;
	/** 等级 */
	int level;
	/** 星级 */
	int star;
	/** 进阶 */
	private int cls;
	/** 当前经验值 */
	private int curExp;
	/** 统帅,武力,智力(体质,力量,悟性) */
	private List<Integer> lead;
	/** 基础属性(生命,攻击,物防,法防) */
	private List<Integer> base;

	/** 稀有属性 */
	private List<Prop> props;
	/** 羁绊 */
	List<Integer> relation;
	/** 神通点数 */
	private int shenPt;
	/** 神通ID数组 */
	private List<Integer> shenIDAry;
	/** 神通等级数组 */
	private List<Integer> shenLvAry;
	/** 是否锁定 */
	private int lock;
	/** 是否在其他阵型中 */
	private List<Integer> battle;

	// extend
	/////////////////////////////////////
	private Integer objId;
	private String name;
	private Integer levelLimit;

	public int getPos() {
		return this.pos;
	}

	public void setPos(int pos) {
		this.pos = pos;
	}

	public int getSubpos() {
		return this.subpos;
	}

	public void setSubpos(int subpos) {
		this.subpos = subpos;
	}

	public int getType() {
		return this.type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getResId() {
		return this.resId;
	}

	public void setResId(int resId) {
		this.resId = resId;
	}

	public int getLevel() {
		return this.level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getStar() {
		return this.star;
	}

	public void setStar(int star) {
		this.star = star;
	}

	public int getCls() {
		return this.cls;
	}

	public void setCls(int cls) {
		this.cls = cls;
	}

	public int getCurExp() {
		return this.curExp;
	}

	public void setCurExp(int curExp) {
		this.curExp = curExp;
	}

	public List<Prop> getProps() {
		return this.props;
	}

	public void setProps(List<Prop> props) {
		this.props = props;
	}

	public List<Integer> getRelation() {
		return this.relation;
	}

	public void setRelation(List<Integer> relation) {
		this.relation = relation;
	}

	public int getShenPt() {
		return this.shenPt;
	}

	public void setShenPt(int shenPt) {
		this.shenPt = shenPt;
	}

	public List<Integer> getShenIDAry() {
		return this.shenIDAry;
	}

	public void setShenIDAry(List<Integer> shenIDAry) {
		this.shenIDAry = shenIDAry;
	}

	public List<Integer> getShenLvAry() {
		return this.shenLvAry;
	}

	public void setShenLvAry(List<Integer> shenLvAry) {
		this.shenLvAry = shenLvAry;
	}

	public boolean IsLock() {
		return lock == 1;
	}

	public void setLock(int lock) {
		this.lock = lock;
	}

	public int getLock() {
		return this.lock;
	}

	public Integer getObjId() {
		return objId;
	}

	public void setObjId(Integer objId) {
		this.objId = objId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getLevelLimit() {
		return levelLimit;
	}

	public void setLevelLimit(Integer levelLimit) {
		this.levelLimit = levelLimit;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setViewBase(List<Integer> viewBase) {
		this.base = viewBase;
	}

	@JsonProperty(value = "base")
	public List<Integer> getViewBase() {
		return this.base;
	}

	public void setViewLead(List<Integer> viewLead) {
		this.lead = viewLead;
	}

	@JsonProperty(value = "lead")
	public List<Integer> getViewLead() {
		return this.lead;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public int getRoleId() {
		return this.roleId;
	}

	public List<Integer> getBattle() {
		return battle;
	}

	public void setBattle(List<Integer> battle) {
		this.battle = battle;
	}
}