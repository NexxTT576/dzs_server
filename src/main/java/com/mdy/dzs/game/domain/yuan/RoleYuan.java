package com.mdy.dzs.game.domain.yuan;

import java.io.Serializable;
import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

import com.mdy.dzs.game.domain.IdObject;
import com.mdy.dzs.game.domain.Prop;

/**
 * 精元模型
 * 
 * @author 房曈
 *
 */
public class RoleYuan implements Serializable, IdObject {

	/** 序列化id */
	private static final long serialVersionUID = 1L;

	/***/
	private int id;
	/***/
	private int roleId;
	/** 1-6上阵, 0未上阵 */
	private int pos;
	/** 7-14装备, 0未装备 */
	private int subpos;
	/***/
	private int resId;
	/** 品质，星级 */
	private int quality;
	/** 类型 */
	private int type;
	/** 精元种类 */
	private int variety;
	/***/
	private int level;
	/** 当前经验[值 不含level累积，初始经验值-item.price] */
	private int curExp;
	/***/
	private List<Prop> props;

	///////////////////////////////////////////
	private int cid;

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

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

	public int getQuality() {
		return this.quality;
	}

	public void setQuality(int quality) {
		this.quality = quality;
	}

	public int getType() {
		return this.type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getVariety() {
		return this.variety;
	}

	public void setVariety(int variety) {
		this.variety = variety;
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

	public List<Prop> getProps() {
		return this.props;
	}

	public void setProps(List<Prop> props) {
		this.props = props;
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
}