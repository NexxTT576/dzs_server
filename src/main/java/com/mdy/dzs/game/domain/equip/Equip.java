package com.mdy.dzs.game.domain.equip;

import java.io.Serializable;
import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

import com.mdy.dzs.game.domain.IdObject;
import com.mdy.dzs.game.domain.Prop;

/**
 * 装备模型
 * 
 * @author 房曈
 *
 */
public class Equip implements Serializable, IdObject {

	/** 序列化id */
	private static final long serialVersionUID = 1L;
	/**
	 * 空闲装备
	 */
	private static final int freepos = 0;
	/***/

	private int id;
	/***/
	private int roleId;
	/** 1-6上阵, 0未上阵 */
	private int pos;
	/** 子位置1-6 */
	private int subpos;
	/** 资源id */
	private int resId;
	/** 类型 */
	private int type;
	/** 星级 */
	private int star;
	/***/
	private int level;
	/** 当前经验值 */
	private int curExp;
	/** 生命,攻击,物防,法防,最终伤害,最终免伤 */
	private List<Integer> base;
	/** 消耗银币 */
	private int silver;
	/** 已洗炼替换属性 */
	private List<Prop> props;
	/** 洗炼未替换属性 */
	private List<Prop> propsWait;
	/** 羁绊 */
	private List<Integer> relation;

	////////////////////////////////////
	private int cid;

	public int getRoleId() {
		return this.roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

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

	public int getResId() {
		return this.resId;
	}

	public void setResId(int resId) {
		this.resId = resId;
	}

	public int getType() {
		return this.type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getStar() {
		return this.star;
	}

	public void setStar(int star) {
		this.star = star;
	}

	public int getLevel() {
		return this.level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getCurExp() {
		return this.curExp;
	}

	public void setCurExp(int curExp) {
		this.curExp = curExp;
	}

	@JsonProperty(value = "base")
	public List<Integer> getViewBase() {
		return this.base;
	}

	public void setViewBase(List<Integer> base) {
		this.base = base;
	}

	public int getSilver() {
		return this.silver;
	}

	public void setSilver(int silver) {
		this.silver = silver;
	}

	public List<Prop> getProps() {
		return this.props;
	}

	public void setProps(List<Prop> props) {
		this.props = props;
	}

	public List<Prop> getPropsWait() {
		return this.propsWait;
	}

	public void setPropsWait(List<Prop> propsWait) {
		this.propsWait = propsWait;
	}

	public List<Integer> getRelation() {
		return this.relation;
	}

	public void setRelation(List<Integer> relation) {
		this.relation = relation;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public int getObjId() {
		return id;
	}

	public void setObjId(int id) {
		this.id = id;
	}

	public static int getFreepos() {
		return freepos;
	}

}